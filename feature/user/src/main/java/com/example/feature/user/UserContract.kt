package com.example.feature.user

import com.example.core.model.User

// UiState：完整描述界面
data class UserUiState(
    val isLoading: Boolean = false,
    val users: List<User> = emptyList(),
    val errorMessage: String? = null,
    val user: User? = null
)

// Intent：用户操作
sealed interface UserIntent {
    data object LoadUsers : UserIntent
    data object Refresh : UserIntent
    data class OnUserClick(val userId: String) : UserIntent
}

// Effect：一次性事件（导航、Toast 等）
sealed interface UserEffect {
    data class ShowToast(val message: String) : UserEffect
    data class NavigateToDetail(val userId: String) : UserEffect
}
