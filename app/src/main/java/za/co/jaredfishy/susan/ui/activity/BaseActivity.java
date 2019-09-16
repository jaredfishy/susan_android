package za.co.jaredfishy.susan.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import za.co.jaredfishy.susan.R;
import za.co.jaredfishy.susan.ui.fragment.BaseFragment;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
//            getWindow().setEnterTransition(new Slide(Gravity.END));
//            getWindow().setExitTransition(new Slide(Gravity.START));
//        }
    }

//    @Override
//    public void startActivity(Intent intent) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            super.startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());
//        } else {
//            super.startActivity(intent);
//        }
//    }

    public void displayFragment(BaseFragment fragment) {
        displayFragment(fragment, true);
    }

    public void displayFragment(BaseFragment fragment, boolean addToBackStack) {
        fragment.setContext(this);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.main_fragment_container, fragment);
        if (addToBackStack)
            transaction.addToBackStack(null);
        transaction.commit();
    }

    public void back() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.popBackStack();

    }
}
