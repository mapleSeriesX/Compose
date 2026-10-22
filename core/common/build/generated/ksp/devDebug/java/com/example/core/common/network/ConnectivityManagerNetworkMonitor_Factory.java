package com.example.core.common.network;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Provider;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import kotlinx.coroutines.CoroutineDispatcher;

@ScopeMetadata
@QualifierMetadata({
    "dagger.hilt.android.qualifiers.ApplicationContext",
    "com.example.core.common.network.Dispatcher"
})
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
public final class ConnectivityManagerNetworkMonitor_Factory implements Factory<ConnectivityManagerNetworkMonitor> {
  private final Provider<Context> contextProvider;

  private final Provider<CoroutineDispatcher> ioDispatcherProvider;

  private ConnectivityManagerNetworkMonitor_Factory(Provider<Context> contextProvider,
      Provider<CoroutineDispatcher> ioDispatcherProvider) {
    this.contextProvider = contextProvider;
    this.ioDispatcherProvider = ioDispatcherProvider;
  }

  @Override
  public ConnectivityManagerNetworkMonitor get() {
    return newInstance(contextProvider.get(), ioDispatcherProvider.get());
  }

  public static ConnectivityManagerNetworkMonitor_Factory create(Provider<Context> contextProvider,
      Provider<CoroutineDispatcher> ioDispatcherProvider) {
    return new ConnectivityManagerNetworkMonitor_Factory(contextProvider, ioDispatcherProvider);
  }

  public static ConnectivityManagerNetworkMonitor newInstance(Context context,
      CoroutineDispatcher ioDispatcher) {
    return new ConnectivityManagerNetworkMonitor(context, ioDispatcher);
  }
}
