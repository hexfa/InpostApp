package pl.inpost.recruitmenttask.utils;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("dagger.Reusable")
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class ApiType_Factory implements Factory<ApiType> {
  @Override
  public ApiType get() {
    return newInstance();
  }

  public static ApiType_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static ApiType newInstance() {
    return new ApiType();
  }

  private static final class InstanceHolder {
    private static final ApiType_Factory INSTANCE = new ApiType_Factory();
  }
}
