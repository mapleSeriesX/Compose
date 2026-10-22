package com.example.feature.userDetail;

import dagger.internal.DaggerGenerated;
import dagger.internal.InstanceFactory;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class UserDetailViewModel_Factory_Impl implements UserDetailViewModel.Factory {
  private final UserDetailViewModel_Factory delegateFactory;

  UserDetailViewModel_Factory_Impl(UserDetailViewModel_Factory delegateFactory) {
    this.delegateFactory = delegateFactory;
  }

  @Override
  public UserDetailViewModel create(String userId) {
    return delegateFactory.get(userId);
  }

  public static Provider<UserDetailViewModel.Factory> create(
      UserDetailViewModel_Factory delegateFactory) {
    return InstanceFactory.create(new UserDetailViewModel_Factory_Impl(delegateFactory));
  }

  public static dagger.internal.Provider<UserDetailViewModel.Factory> createFactoryProvider(
      UserDetailViewModel_Factory delegateFactory) {
    return InstanceFactory.create(new UserDetailViewModel_Factory_Impl(delegateFactory));
  }
}
