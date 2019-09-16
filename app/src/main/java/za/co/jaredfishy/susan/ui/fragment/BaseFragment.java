package za.co.jaredfishy.susan.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import za.co.jaredfishy.susan.ui.activity.BaseActivity;
import za.co.jaredfishy.susan.util.DimensionUtil;

public abstract class BaseFragment extends Fragment {

    protected BaseActivity context;
    protected LinearLayout llRoot;
    protected String title;

    public BaseFragment(String title) {
        this.title = title;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupRoot();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return llRoot;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (title != null)
            getActivity().setTitle(title);
    }

    protected void setupRoot() {
        llRoot = new LinearLayout(context);
        llRoot.setOrientation(LinearLayout.VERTICAL);
        int size = DimensionUtil.getPixels(context, 10);
        llRoot.setPadding(size, size, size, size);
    }

    protected void addView(View view) {
        llRoot.addView(view);
    }

    public void setContext(BaseActivity context) {
        this.context = context;
    }
}
