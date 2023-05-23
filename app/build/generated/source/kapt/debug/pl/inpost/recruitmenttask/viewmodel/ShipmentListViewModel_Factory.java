package pl.inpost.recruitmenttask.viewmodel;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import pl.inpost.recruitmenttask.model.repository.ShipmentRepository;

@ScopeMetadata
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
public final class ShipmentListViewModel_Factory implements Factory<ShipmentListViewModel> {
  private final Provider<ShipmentRepository> shipmentRepositoryProvider;

  public ShipmentListViewModel_Factory(Provider<ShipmentRepository> shipmentRepositoryProvider) {
    this.shipmentRepositoryProvider = shipmentRepositoryProvider;
  }

  @Override
  public ShipmentListViewModel get() {
    return newInstance(shipmentRepositoryProvider.get());
  }

  public static ShipmentListViewModel_Factory create(
      Provider<ShipmentRepository> shipmentRepositoryProvider) {
    return new ShipmentListViewModel_Factory(shipmentRepositoryProvider);
  }

  public static ShipmentListViewModel newInstance(ShipmentRepository shipmentRepository) {
    return new ShipmentListViewModel(shipmentRepository);
  }
}
