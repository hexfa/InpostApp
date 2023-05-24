package pl.inpost.recruitmenttask.viewmodel;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import pl.inpost.recruitmenttask.model.repository.ShipmentRepository;
import pl.inpost.recruitmenttask.model.repository.ShipmentRepositoryDao;

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

  private final Provider<ShipmentRepositoryDao> shipmentRepositoryDaoProvider;

  public ShipmentListViewModel_Factory(Provider<ShipmentRepository> shipmentRepositoryProvider,
      Provider<ShipmentRepositoryDao> shipmentRepositoryDaoProvider) {
    this.shipmentRepositoryProvider = shipmentRepositoryProvider;
    this.shipmentRepositoryDaoProvider = shipmentRepositoryDaoProvider;
  }

  @Override
  public ShipmentListViewModel get() {
    return newInstance(shipmentRepositoryProvider.get(), shipmentRepositoryDaoProvider.get());
  }

  public static ShipmentListViewModel_Factory create(
      Provider<ShipmentRepository> shipmentRepositoryProvider,
      Provider<ShipmentRepositoryDao> shipmentRepositoryDaoProvider) {
    return new ShipmentListViewModel_Factory(shipmentRepositoryProvider, shipmentRepositoryDaoProvider);
  }

  public static ShipmentListViewModel newInstance(ShipmentRepository shipmentRepository,
      ShipmentRepositoryDao shipmentRepositoryDao) {
    return new ShipmentListViewModel(shipmentRepository, shipmentRepositoryDao);
  }
}
