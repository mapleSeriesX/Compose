package com.example.feature.userDetail

import com.example.core.model.User

// UiState：完整描述详情页状态
data class UserDetailUiState(
    val isLoading: Boolean = false,
    val user: User? = null,
    val errorMessage: String? = null
)

// Intent：用户操作
sealed interface UserDetailIntent {
    data object LoadUser : UserDetailIntent
    data object Retry : UserDetailIntent
}

// Effect：一次性事件
sealed interface UserDetailEffect {
    data class ShowToast(val message: String) : UserDetailEffect
}
