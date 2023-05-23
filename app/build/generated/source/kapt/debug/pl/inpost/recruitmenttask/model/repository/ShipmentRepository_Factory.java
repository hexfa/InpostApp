package pl.inpost.recruitmenttask.model.repository;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import pl.inpost.recruitmenttask.model.api.ShipmentApi;

@ScopeMetadata("dagger.hilt.android.scopes.ActivityRetainedScoped")
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
public final class ShipmentRepository_Factory implements Factory<ShipmentRepository> {
  private final Provider<ShipmentApi> apiServiceProvider;

  public ShipmentRepository_Factory(Provider<ShipmentApi> apiServiceProvider) {
    this.apiServiceProvider = apiServiceProvider;
  }

  @Override
  public ShipmentRepository get() {
    return newInstance(apiServiceProvider.get());
  }

  public static ShipmentRepository_Factory create(Provider<ShipmentApi> apiServiceProvider) {
    return new ShipmentRepository_Factory(apiServiceProvider);
  }

  public static ShipmentRepository newInstance(ShipmentApi apiService) {
    return new ShipmentRepository(apiService);
  }
}
