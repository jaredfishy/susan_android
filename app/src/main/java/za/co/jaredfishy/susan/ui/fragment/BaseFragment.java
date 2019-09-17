package za.co.jaredfishy.susan.ui.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import za.co.jaredfishy.susan.R;
import za.co.jaredfishy.susan.ui.activity.BaseActivity;

public abstract class BaseFragment extends Fragment {

    protected BaseActivity context;
    private LinearLayout fragmentRoot;
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
        return fragmentRoot;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (title != null)
            getActivity().setTitle(title);
    }

    protected void setupRoot() {
        fragmentRoot = new LinearLayout(context);
        fragmentRoot.setOrientation(LinearLayout.VERTICAL);
        fragmentRoot.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        int horizontalMargin = getResources().getDimensionPixelSize(R.dimen.activity_horizontal_margin);
        int verticalMargin = getResources().getDimensionPixelSize(R.dimen.activity_vertical_margin);
        fragmentRoot.setPadding(verticalMargin, horizontalMargin, verticalMargin, horizontalMargin);
    }

    protected void addView(View view) {
        fragmentRoot.addView(view);
    }

    public void setContext(BaseActivity context) {
        this.context = context;
    }
}
