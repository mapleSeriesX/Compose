package com.example.core.common.analytics;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class LogAnalyticsHelper_Factory implements Factory<LogAnalyticsHelper> {
  @Override
  public LogAnalyticsHelper get() {
    return newInstance();
  }

  public static LogAnalyticsHelper_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static LogAnalyticsHelper newInstance() {
    return new LogAnalyticsHelper();
  }

  private static final class InstanceHolder {
    static final LogAnalyticsHelper_Factory INSTANCE = new LogAnalyticsHelper_Factory();
  }
}
