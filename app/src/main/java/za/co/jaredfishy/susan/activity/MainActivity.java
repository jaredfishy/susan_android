package za.co.jaredfishy.susan.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import za.co.jaredfishy.susan.R;
import za.co.jaredfishy.susan.activity.fragments.BaseFragment;
import za.co.jaredfishy.susan.activity.fragments.LightsFragment;
import za.co.jaredfishy.susan.activity.fragments.StatusFragment;
import za.co.jaredfishy.susan.ui.JZMenuItem;

public class MainActivity extends BaseActivity {

    private DrawerLayout mDrawerLayout;
    private LinearLayout llRoot;
    private NavigationView navMenu;

    private StatusFragment.OnMenuItemAvailabilityChangeListener onMenuItemAvailabilityChangeListener;

    private Toolbar toolbar;
    private LinearLayout llContent;

    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drawer_layout);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        llRoot = findViewById(R.id.drawer_root);
        navMenu = findViewById(R.id.drawer_menu);

        setupDrawer();
        setupMenu();
        setupRoot();

        onMenuItemAvailabilityChangeListener = new StatusFragment.OnMenuItemAvailabilityChangeListener() {
            @Override
            public void availabilityChanged(JZMenuItem menuItem) {
                refreshMenuItem(menuItem);
            }
        };

        displayFragment(StatusFragment.newInstance(onMenuItemAvailabilityChangeListener));
    }

    private void setupDrawer() {

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.button_lights_on, R.string.button_lights_off) {
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
//                getSupportActionBar().setTitle("Navigation!");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
//                getSupportActionBar().setTitle("Susan");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

    }

    public void setupRoot() {

        llContent = new LinearLayout(this);
        llContent.setId(contentRootId);
        llContent.setOrientation(LinearLayout.VERTICAL);

        ScrollView scrollView = new ScrollView(this);
        scrollView.addView(llContent);
        llRoot.addView(scrollView);
    }

    private void setupMenu() {

        navMenu.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                return handleMenuItemSelected(menuItem);
            }
        });

        Menu m = navMenu.getMenu();
        for (JZMenuItem menuItem : JZMenuItem.values()) {
            MenuItem item = m.add(menuItem.getGroup(), menuItem.getResourceId(), menuItem.getResourceId(), menuItem.getText());
            item.setEnabled(menuItem.isAvailable());
        }
    }

    private void displayFragmentFromMenu(BaseFragment fragment) {
        mDrawerLayout.closeDrawers();
        displayFragment(fragment);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return handleMenuItemSelected(item);
    }


    private boolean handleMenuItemSelected(MenuItem item) {
        JZMenuItem menuItem = JZMenuItem.fromResourceId(item.getItemId());
        switch (menuItem) {
            case STATUS:
                displayFragmentFromMenu(StatusFragment.newInstance(onMenuItemAvailabilityChangeListener));
                return true;
            case LIGHTS:
                displayFragmentFromMenu(LightsFragment.newInstance());
                return true;
            case SETTINGS:
                Toast.makeText(this, "Coming soon...", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return false;
        }
    }

    public void refreshMenuItem(JZMenuItem menuItem) {
        Menu menu = navMenu.getMenu();
        System.out.println(menuItem.getText() + " is now " + menuItem.isAvailable());
        menu.findItem(menuItem.getResourceId()).setEnabled(menuItem.isAvailable());
    }
}
