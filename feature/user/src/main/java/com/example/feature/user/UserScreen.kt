package com.example.feature.user

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun UserScreen(
    viewModel: UserViewModel = hiltViewModel(),
    onNavigateToDetail: (String) -> Unit = {}
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is UserEffect.ShowToast -> {
                    snackbarHostState.showSnackbar(effect.message)
                }

                is UserEffect.NavigateToDetail -> {
                    onNavigateToDetail(effect.userId)
                }
            }
        }
    }
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
         UserContent(
            state = uiState,
            onIntent = viewModel::onIntent,
            modifier = Modifier.padding(padding)
        )
    }
}
//@Composable
//fun  sest23(){
//    Box( contentAlignment = Alignment.Center) {
//        CircularProgressIndicator()
//    }
//}
@Composable
fun UserContent(
    state: UserUiState,
    onIntent: (UserIntent) -> Unit,
    modifier: Modifier
) {
    //sest23()
    when {
        state.isLoading -> {
            Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        state.errorMessage != null -> {
            Log.e("error", state.errorMessage)
            Column(
                modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(state.errorMessage)
                Button(onClick = { onIntent(UserIntent.Refresh) }) {
                    Text("重试")
                }
            }
        }

        else -> {
            LazyColumn {
                items(state.users, key = { it.id }) { user ->
                    ListItem(
                        headlineContent = { Text(user.name, color = Color.Red) },
                        modifier = modifier.clickable {
                            onIntent(UserIntent.OnUserClick(user.id))
                        }
                    )
                }
            }
        }
    }
}
