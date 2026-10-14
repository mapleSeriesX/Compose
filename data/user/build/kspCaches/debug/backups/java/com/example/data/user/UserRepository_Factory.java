package com.example.data.user;

import androidx.datastore.core.DataStore;
import com.example.core.database.UserDao;
import com.example.core.network.ApiService;
import com.example.data.user.proto.UserPreferences;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast",
    "deprecation",
    "nullness:initialization.field.uninitialized"
})
public final class UserRepository_Factory implements Factory<UserRepository> {
  private final Provider<ApiService> apiServiceProvider;

  private final Provider<UserDao> userDaoProvider;

  private final Provider<DataStore<UserPreferences>> dataStoreProvider;

  private UserRepository_Factory(Provider<ApiService> apiServiceProvider,
      Provider<UserDao> userDaoProvider, Provider<DataStore<UserPreferences>> dataStoreProvider) {
    this.apiServiceProvider = apiServiceProvider;
    this.userDaoProvider = userDaoProvider;
    this.dataStoreProvider = dataStoreProvider;
  }

  @Override
  public UserRepository get() {
    return newInstance(apiServiceProvider.get(), userDaoProvider.get(), dataStoreProvider.get());
  }

  public static UserRepository_Factory create(Provider<ApiService> apiServiceProvider,
      Provider<UserDao> userDaoProvider, Provider<DataStore<UserPreferences>> dataStoreProvider) {
    return new UserRepository_Factory(apiServiceProvider, userDaoProvider, dataStoreProvider);
  }

  public static UserRepository newInstance(ApiService apiService, UserDao userDao,
      DataStore<UserPreferences> dataStore) {
    return new UserRepository(apiService, userDao, dataStore);
  }
}
