package com.example.data.user

import androidx.datastore.core.DataStore
import com.example.core.database.UserDao
import com.example.core.model.User
import com.example.core.network.ApiService
import com.example.data.user.proto.UserPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserRepository @Inject constructor(
    private val apiService: ApiService,
    private val userDao: UserDao,
    private val dataStore: DataStore<UserPreferences>
) {

    val isDarkMode: Flow<Boolean> = dataStore.data.map { it.isDarkMode }

    suspend fun setDarkMode(enabled: Boolean) {
        dataStore.updateData { prefs ->
            prefs.toBuilder().setIsDarkMode(enabled).build()
        }
    }

    fun observeUsers(): Flow<List<User>> =
        userDao.observeUsers().map { entities ->
            entities.map { it.toDomain() }
        }

    suspend fun refreshUsers() {
        val remote = apiService.getUsers()
        userDao.insertAll(remote.map { it.toEntity() })
    }

    fun observeUser(id: String): Flow<User?> =
        userDao.observeUser(id).map { entity -> entity?.toDomain() }

    suspend fun refreshUser(id: String) {
        val remote = apiService.getUser(id)
        userDao.insetUser(remote.toEntity())
    }
}
