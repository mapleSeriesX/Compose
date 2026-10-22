package com.example.data.user;

import com.example.core.database.AppDatabase;
import com.example.core.database.UserDao;
import com.example.core.network.ApiService;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata
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
public final class UserRepositoryImpl_Factory implements Factory<UserRepositoryImpl> {
  private final Provider<ApiService> apiServiceProvider;

  private final Provider<UserDao> userDaoProvider;

  private final Provider<AppDatabase> databaseProvider;

  private UserRepositoryImpl_Factory(Provider<ApiService> apiServiceProvider,
      Provider<UserDao> userDaoProvider, Provider<AppDatabase> databaseProvider) {
    this.apiServiceProvider = apiServiceProvider;
    this.userDaoProvider = userDaoProvider;
    this.databaseProvider = databaseProvider;
  }

  @Override
  public UserRepositoryImpl get() {
    return newInstance(apiServiceProvider.get(), userDaoProvider.get(), databaseProvider.get());
  }

  public static UserRepositoryImpl_Factory create(Provider<ApiService> apiServiceProvider,
      Provider<UserDao> userDaoProvider, Provider<AppDatabase> databaseProvider) {
    return new UserRepositoryImpl_Factory(apiServiceProvider, userDaoProvider, databaseProvider);
  }

  public static UserRepositoryImpl newInstance(ApiService apiService, UserDao userDao,
      AppDatabase database) {
    return new UserRepositoryImpl(apiService, userDao, database);
  }
}
