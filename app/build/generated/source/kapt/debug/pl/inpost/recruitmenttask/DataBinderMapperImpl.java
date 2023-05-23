package pl.inpost.recruitmenttask;

import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import androidx.databinding.DataBinderMapper;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.ViewDataBinding;
import java.lang.IllegalArgumentException;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import pl.inpost.recruitmenttask.databinding.ActivityHomeBindingImpl;
import pl.inpost.recruitmenttask.databinding.FragmentShipmentListBindingImpl;
import pl.inpost.recruitmenttask.databinding.ShipmentListItemBindingImpl;

public class DataBinderMapperImpl extends DataBinderMapper {
  private static final int LAYOUT_ACTIVITYHOME = 1;

  private static final int LAYOUT_FRAGMENTSHIPMENTLIST = 2;

  private static final int LAYOUT_SHIPMENTLISTITEM = 3;

  private static final SparseIntArray INTERNAL_LAYOUT_ID_LOOKUP = new SparseIntArray(3);

  static {
    INTERNAL_LAYOUT_ID_LOOKUP.put(pl.inpost.recruitmenttask.R.layout.activity_home, LAYOUT_ACTIVITYHOME);
    INTERNAL_LAYOUT_ID_LOOKUP.put(pl.inpost.recruitmenttask.R.layout.fragment_shipment_list, LAYOUT_FRAGMENTSHIPMENTLIST);
    INTERNAL_LAYOUT_ID_LOOKUP.put(pl.inpost.recruitmenttask.R.layout.shipment_list_item, LAYOUT_SHIPMENTLISTITEM);
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View view, int layoutId) {
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = view.getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
        case  LAYOUT_ACTIVITYHOME: {
          if ("layout/activity_home_0".equals(tag)) {
            return new ActivityHomeBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for activity_home is invalid. Received: " + tag);
        }
        case  LAYOUT_FRAGMENTSHIPMENTLIST: {
          if ("layout/fragment_shipment_list_0".equals(tag)) {
            return new FragmentShipmentListBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for fragment_shipment_list is invalid. Received: " + tag);
        }
        case  LAYOUT_SHIPMENTLISTITEM: {
          if ("layout/shipment_list_item_0".equals(tag)) {
            return new ShipmentListItemBindingImpl(component, view);
          }
          throw new IllegalArgumentException("The tag for shipment_list_item is invalid. Received: " + tag);
        }
      }
    }
    return null;
  }

  @Override
  public ViewDataBinding getDataBinder(DataBindingComponent component, View[] views, int layoutId) {
    if(views == null || views.length == 0) {
      return null;
    }
    int localizedLayoutId = INTERNAL_LAYOUT_ID_LOOKUP.get(layoutId);
    if(localizedLayoutId > 0) {
      final Object tag = views[0].getTag();
      if(tag == null) {
        throw new RuntimeException("view must have a tag");
      }
      switch(localizedLayoutId) {
      }
    }
    return null;
  }

  @Override
  public int getLayoutId(String tag) {
    if (tag == null) {
      return 0;
    }
    Integer tmpVal = InnerLayoutIdLookup.sKeys.get(tag);
    return tmpVal == null ? 0 : tmpVal;
  }

  @Override
  public String convertBrIdToString(int localId) {
    String tmpVal = InnerBrLookup.sKeys.get(localId);
    return tmpVal;
  }

  @Override
  public List<DataBinderMapper> collectDependencies() {
    ArrayList<DataBinderMapper> result = new ArrayList<DataBinderMapper>(1);
    result.add(new androidx.databinding.library.baseAdapters.DataBinderMapperImpl());
    return result;
  }

  private static class InnerBrLookup {
    static final SparseArray<String> sKeys = new SparseArray<String>(1);

    static {
      sKeys.put(0, "_all");
    }
  }

  private static class InnerLayoutIdLookup {
    static final HashMap<String, Integer> sKeys = new HashMap<String, Integer>(3);

    static {
      sKeys.put("layout/activity_home_0", pl.inpost.recruitmenttask.R.layout.activity_home);
      sKeys.put("layout/fragment_shipment_list_0", pl.inpost.recruitmenttask.R.layout.fragment_shipment_list);
      sKeys.put("layout/shipment_list_item_0", pl.inpost.recruitmenttask.R.layout.shipment_list_item);
    }
  }
}
