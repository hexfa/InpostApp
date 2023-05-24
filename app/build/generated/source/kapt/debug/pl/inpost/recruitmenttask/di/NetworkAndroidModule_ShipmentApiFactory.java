package pl.inpost.recruitmenttask.di;

import android.content.Context;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import pl.inpost.recruitmenttask.model.api.ShipmentApi;
import pl.inpost.recruitmenttask.utils.ApiType;

@ScopeMetadata
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes"
})
public final class NetworkAndroidModule_ShipmentApiFactory implements Factory<ShipmentApi> {
  private final NetworkAndroidModule module;

  private final Provider<Context> contextProvider;

  private final Provider<ApiType> apiTypeProvider;

  public NetworkAndroidModule_ShipmentApiFactory(NetworkAndroidModule module,
      Provider<Context> contextProvider, Provider<ApiType> apiTypeProvider) {
    this.module = module;
    this.contextProvider = contextProvider;
    this.apiTypeProvider = apiTypeProvider;
  }

  @Override
  public ShipmentApi get() {
    return shipmentApi(module, contextProvider.get(), apiTypeProvider.get());
  }

  public static NetworkAndroidModule_ShipmentApiFactory create(NetworkAndroidModule module,
      Provider<Context> contextProvider, Provider<ApiType> apiTypeProvider) {
    return new NetworkAndroidModule_ShipmentApiFactory(module, contextProvider, apiTypeProvider);
  }

  public static ShipmentApi shipmentApi(NetworkAndroidModule instance, Context context,
      ApiType apiType) {
    return Preconditions.checkNotNullFromProvides(instance.shipmentApi(context, apiType));
  }
}
