package pl.inpost.recruitmenttask.databinding;
import pl.inpost.recruitmenttask.R;
import pl.inpost.recruitmenttask.BR;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
@SuppressWarnings("unchecked")
public class FragmentShipmentListBindingImpl extends FragmentShipmentListBinding  {

    @Nullable
    private static final androidx.databinding.ViewDataBinding.IncludedLayouts sIncludes;
    @Nullable
    private static final android.util.SparseIntArray sViewsWithIds;
    static {
        sIncludes = null;
        sViewsWithIds = new android.util.SparseIntArray();
        sViewsWithIds.put(R.id.header, 1);
        sViewsWithIds.put(R.id.menu_button, 2);
        sViewsWithIds.put(R.id.text, 3);
        sViewsWithIds.put(R.id.layout_in_transit, 4);
        sViewsWithIds.put(R.id.divider1_left, 5);
        sViewsWithIds.put(R.id.divider_tex_in_transit, 6);
        sViewsWithIds.put(R.id.divider1_right, 7);
        sViewsWithIds.put(R.id.recycler_shipment_in_transit, 8);
        sViewsWithIds.put(R.id.layout_pickup, 9);
        sViewsWithIds.put(R.id.divider2_left, 10);
        sViewsWithIds.put(R.id.divider_tex_pickup, 11);
        sViewsWithIds.put(R.id.divider2_right, 12);
        sViewsWithIds.put(R.id.recycler_shipment_pickup, 13);
        sViewsWithIds.put(R.id.layout_in_progress, 14);
        sViewsWithIds.put(R.id.divider3_left, 15);
        sViewsWithIds.put(R.id.divider_tex_in_progress, 16);
        sViewsWithIds.put(R.id.divider3_right, 17);
        sViewsWithIds.put(R.id.recycler_shipment_in_progress, 18);
        sViewsWithIds.put(R.id.layout_pending, 19);
        sViewsWithIds.put(R.id.divider4_left, 20);
        sViewsWithIds.put(R.id.divider_tex_pending, 21);
        sViewsWithIds.put(R.id.divider4_right, 22);
        sViewsWithIds.put(R.id.recycler_shipment_pending, 23);
        sViewsWithIds.put(R.id.layout_filter, 24);
        sViewsWithIds.put(R.id.divider_filter_left, 25);
        sViewsWithIds.put(R.id.divider_text_another, 26);
        sViewsWithIds.put(R.id.divider_filter_right, 27);
        sViewsWithIds.put(R.id.recycler_filter, 28);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentShipmentListBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 29, sIncludes, sViewsWithIds));
    }
    private FragmentShipmentListBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.view.View) bindings[5]
            , (android.view.View) bindings[7]
            , (android.view.View) bindings[10]
            , (android.view.View) bindings[12]
            , (android.view.View) bindings[15]
            , (android.view.View) bindings[17]
            , (android.view.View) bindings[20]
            , (android.view.View) bindings[22]
            , (android.view.View) bindings[25]
            , (android.view.View) bindings[27]
            , (android.widget.TextView) bindings[16]
            , (android.widget.TextView) bindings[6]
            , (android.widget.TextView) bindings[21]
            , (android.widget.TextView) bindings[11]
            , (android.widget.TextView) bindings[26]
            , (android.widget.RelativeLayout) bindings[1]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[24]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[14]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[4]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[19]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[9]
            , (android.widget.ImageButton) bindings[2]
            , (androidx.recyclerview.widget.RecyclerView) bindings[28]
            , (androidx.recyclerview.widget.RecyclerView) bindings[18]
            , (androidx.recyclerview.widget.RecyclerView) bindings[8]
            , (androidx.recyclerview.widget.RecyclerView) bindings[23]
            , (androidx.recyclerview.widget.RecyclerView) bindings[13]
            , (android.widget.TextView) bindings[3]
            );
        this.mboundView0 = (androidx.constraintlayout.widget.ConstraintLayout) bindings[0];
        this.mboundView0.setTag(null);
        setRootTag(root);
        // listeners
        invalidateAll();
    }

    @Override
    public void invalidateAll() {
        synchronized(this) {
                mDirtyFlags = 0x1L;
        }
        requestRebind();
    }

    @Override
    public boolean hasPendingBindings() {
        synchronized(this) {
            if (mDirtyFlags != 0) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean setVariable(int variableId, @Nullable Object variable)  {
        boolean variableSet = true;
            return variableSet;
    }

    @Override
    protected boolean onFieldChange(int localFieldId, Object object, int fieldId) {
        switch (localFieldId) {
        }
        return false;
    }

    @Override
    protected void executeBindings() {
        long dirtyFlags = 0;
        synchronized(this) {
            dirtyFlags = mDirtyFlags;
            mDirtyFlags = 0;
        }
        // batch finished
    }
    // Listener Stub Implementations
    // callback impls
    // dirty flag
    private  long mDirtyFlags = 0xffffffffffffffffL;
    /* flag mapping
        flag 0 (0x1L): null
    flag mapping end*/
    //end
}