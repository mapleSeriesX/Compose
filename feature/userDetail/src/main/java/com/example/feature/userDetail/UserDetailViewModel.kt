package com.example.feature.userDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.user.UserRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = UserDetailViewModel.Factory::class)
class UserDetailViewModel @AssistedInject constructor(
    @Assisted private val userId: String,
    private val repository: UserRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(UserDetailUiState())
    val uiState: StateFlow<UserDetailUiState> = _uiState.asStateFlow()

    private val _effect = Channel<UserDetailEffect>(Channel.BUFFERED)
    val effect: Flow<UserDetailEffect> = _effect.receiveAsFlow()

    init {
        viewModelScope.launch {
            repository.setDarkMode(true)

            var firstEmission = true
            repository.observeUser(userId).collect { user ->
                _uiState.update { it.copy(user = user) }
                if (firstEmission) {
                    firstEmission = false
                    onIntent(UserDetailIntent.LoadUser)
                }
            }
        }
    }

    fun onIntent(intent: UserDetailIntent) {
        when (intent) {
            is UserDetailIntent.LoadUser -> loadUser()
            is UserDetailIntent.Retry -> loadUser()
        }
    }

    private fun loadUser() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            runCatching { repository.refreshUser(userId) }
                .onSuccess {
                    _uiState.update { it.copy(isLoading = false) }
                }
                .onFailure { e ->
                    val hasCache = _uiState.value.user != null
                    _uiState.update {
                        it.copy(isLoading = false, errorMessage = if (hasCache) null else e.message)
                    }
                    if (!hasCache) {
                        _effect.send(UserDetailEffect.ShowToast("加载失败：${e.message}"))
                    }
                }
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(userId: String): UserDetailViewModel
    }
}
