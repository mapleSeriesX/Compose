package com.example.myapplication

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.example.data.user.UserRepository
import com.example.feature.user.UserScreen
import com.example.feature.userDetail.UserDetailScreen
import com.example.feature.userDetail.UserDetailViewModel

@Composable
fun AppNavigation(repository: UserRepository) {
    val backStack = remember { mutableStateListOf<NavKey>(UserList) }

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryDecorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator(),
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {
            entry<UserList> {
                LaunchedEffect(Unit) {
                    repository.setDarkMode(false)
                }
                UserScreen(
                    onNavigateToDetail = { userId ->
                        backStack.add(UserDetail(userId))
                    }
                )
            }

            entry<UserDetail> { key ->
                LaunchedEffect(Unit) {
                    repository.setDarkMode(true)
                }
                val viewModel = hiltViewModel<UserDetailViewModel, UserDetailViewModel.Factory>(
                    creationCallback = { factory -> factory.create(key.userId) }
                )
                UserDetailScreen(viewModel = viewModel)
            }
        }
    )
}
