package com.example.data.user;

import android.content.Context;
import androidx.datastore.core.DataStore;
import com.example.data.user.proto.UserPreferences;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class DataStoreModule_ProvideUserPreferencesDataStoreFactory implements Factory<DataStore<UserPreferences>> {
  private final Provider<Context> contextProvider;

  private DataStoreModule_ProvideUserPreferencesDataStoreFactory(
      Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public DataStore<UserPreferences> get() {
    return provideUserPreferencesDataStore(contextProvider.get());
  }

  public static DataStoreModule_ProvideUserPreferencesDataStoreFactory create(
      Provider<Context> contextProvider) {
    return new DataStoreModule_ProvideUserPreferencesDataStoreFactory(contextProvider);
  }

  public static DataStore<UserPreferences> provideUserPreferencesDataStore(Context context) {
    return Preconditions.checkNotNullFromProvides(DataStoreModule.INSTANCE.provideUserPreferencesDataStore(context));
  }
}
