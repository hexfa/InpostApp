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
        sViewsWithIds.put(R.id.text, 2);
        sViewsWithIds.put(R.id.layout_in_transit, 3);
        sViewsWithIds.put(R.id.divider1_left, 4);
        sViewsWithIds.put(R.id.divider_tex_in_transit, 5);
        sViewsWithIds.put(R.id.divider1_right, 6);
        sViewsWithIds.put(R.id.recycler_shipment_in_transit, 7);
        sViewsWithIds.put(R.id.layout_pickup, 8);
        sViewsWithIds.put(R.id.divider2_left, 9);
        sViewsWithIds.put(R.id.divider_tex_pickup, 10);
        sViewsWithIds.put(R.id.divider2_right, 11);
        sViewsWithIds.put(R.id.recycler_shipment_pickup, 12);
        sViewsWithIds.put(R.id.layout_in_progress, 13);
        sViewsWithIds.put(R.id.divider3_left, 14);
        sViewsWithIds.put(R.id.divider_tex_in_progress, 15);
        sViewsWithIds.put(R.id.divider3_right, 16);
        sViewsWithIds.put(R.id.recycler_shipment_in_progress, 17);
        sViewsWithIds.put(R.id.layout_pending, 18);
        sViewsWithIds.put(R.id.divider4_left, 19);
        sViewsWithIds.put(R.id.divider_tex_pending, 20);
        sViewsWithIds.put(R.id.divider4_right, 21);
        sViewsWithIds.put(R.id.recycler_shipment_pending, 22);
    }
    // views
    @NonNull
    private final androidx.constraintlayout.widget.ConstraintLayout mboundView0;
    // variables
    // values
    // listeners
    // Inverse Binding Event Handlers

    public FragmentShipmentListBindingImpl(@Nullable androidx.databinding.DataBindingComponent bindingComponent, @NonNull View root) {
        this(bindingComponent, root, mapBindings(bindingComponent, root, 23, sIncludes, sViewsWithIds));
    }
    private FragmentShipmentListBindingImpl(androidx.databinding.DataBindingComponent bindingComponent, View root, Object[] bindings) {
        super(bindingComponent, root, 0
            , (android.view.View) bindings[4]
            , (android.view.View) bindings[6]
            , (android.view.View) bindings[9]
            , (android.view.View) bindings[11]
            , (android.view.View) bindings[14]
            , (android.view.View) bindings[16]
            , (android.view.View) bindings[19]
            , (android.view.View) bindings[21]
            , (android.widget.TextView) bindings[15]
            , (android.widget.TextView) bindings[5]
            , (android.widget.TextView) bindings[20]
            , (android.widget.TextView) bindings[10]
            , (android.widget.RelativeLayout) bindings[1]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[13]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[3]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[18]
            , (androidx.constraintlayout.widget.ConstraintLayout) bindings[8]
            , (androidx.recyclerview.widget.RecyclerView) bindings[17]
            , (androidx.recyclerview.widget.RecyclerView) bindings[7]
            , (androidx.recyclerview.widget.RecyclerView) bindings[22]
            , (androidx.recyclerview.widget.RecyclerView) bindings[12]
            , (android.widget.TextView) bindings[2]
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