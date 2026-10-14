package com.example.feature.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.user.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(UserUiState())
    val uiState: StateFlow<UserUiState> = _uiState.asStateFlow()

    private val _effect = Channel<UserEffect>(Channel.BUFFERED)
    val effect: Flow<UserEffect> = _effect.receiveAsFlow()

    init {
        viewModelScope.launch {
            var firstEmission = true
            repository.observeUsers().collect { users ->
                _uiState.update { it.copy(users = users) }
                if (firstEmission) {
                    firstEmission = false
                    onIntent(UserIntent.Refresh)
                }
            }
        }
    }

    fun onIntent(intent: UserIntent) {
        when (intent) {
            is UserIntent.LoadUsers -> loadUsers()
            is UserIntent.Refresh -> loadUsers()
            is UserIntent.OnUserClick -> {
                viewModelScope.launch {
                    _effect.send(UserEffect.NavigateToDetail(intent.userId))
                }
            }
        }
    }

    private fun loadUsers() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            runCatching { repository.refreshUsers() }
                .onSuccess {
                    _uiState.update { it.copy(isLoading = false) }
                }
                .onFailure { e ->
                    val hasCache = _uiState.value.users.isNotEmpty()
                    _uiState.update {
                        it.copy(isLoading = false, errorMessage = if (hasCache) null else e.message)
                    }
                    if (!hasCache)
                        _effect.send(UserEffect.ShowToast("加载失败：${e.message}"))
                }
        }
    }
}
