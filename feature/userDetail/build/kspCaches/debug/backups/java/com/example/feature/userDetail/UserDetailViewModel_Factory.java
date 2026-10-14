package com.example.feature.userDetail;

import com.example.data.user.UserRepository;
import dagger.internal.DaggerGenerated;
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
public final class UserDetailViewModel_Factory {
  private final Provider<UserRepository> repositoryProvider;

  private UserDetailViewModel_Factory(Provider<UserRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  public UserDetailViewModel get(String userId) {
    return newInstance(userId, repositoryProvider.get());
  }

  public static UserDetailViewModel_Factory create(Provider<UserRepository> repositoryProvider) {
    return new UserDetailViewModel_Factory(repositoryProvider);
  }

  public static UserDetailViewModel newInstance(String userId, UserRepository repository) {
    return new UserDetailViewModel(userId, repository);
  }
}
