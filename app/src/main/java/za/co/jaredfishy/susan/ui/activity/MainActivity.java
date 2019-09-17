package za.co.jaredfishy.susan.ui.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import za.co.jaredfishy.susan.R;
import za.co.jaredfishy.susan.domain.susan.SusanResponse;
import za.co.jaredfishy.susan.domain.susan.SusanService;
import za.co.jaredfishy.susan.handler.status.ServiceStatusHandler;
import za.co.jaredfishy.susan.task.Callback;
import za.co.jaredfishy.susan.task.JZMenuItemOnClickListener;
import za.co.jaredfishy.susan.task.OnAvailabilityChangedListener;
import za.co.jaredfishy.susan.task.susan.SusanPokeTask;
import za.co.jaredfishy.susan.ui.fragment.BaseFragment;
import za.co.jaredfishy.susan.ui.fragment.HomeFragment;
import za.co.jaredfishy.susan.ui.fragment.LightsFragment;
import za.co.jaredfishy.susan.ui.fragment.SettingsFragment;
import za.co.jaredfishy.susan.ui.fragment.StatusFragment;
import za.co.jaredfishy.susan.domain.JZMenuItem;

public class MainActivity extends BaseActivity implements JZMenuItemOnClickListener, OnAvailabilityChangedListener {

    private static final JZMenuItem[] MENU_ITEMS = new JZMenuItem[]{
            JZMenuItem.HOME,
            JZMenuItem.STATUS,
            JZMenuItem.LIGHTS,
            JZMenuItem.SETTINGS,
    };

    private DrawerLayout mDrawerLayout;
    private NavigationView navMenu;
    private Callback availabilityChangeCallback;

    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_layout);

        mDrawerLayout = findViewById(R.id.drawer_layout);
        navMenu = findViewById(R.id.drawer_menu);

        setupListeners();
        setupDrawer();
        setupMenu();

        displayFragment(HomeFragment.newInstance(this), false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        refreshMenuItems();
    }

    private void setupListeners() {
        availabilityChangeCallback = new Callback() {
            @Override
            public void done(Object response) {
                refreshMenuItems();
            }
        };
    }

    private void setupDrawer() {

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.button_lights_on, R.string.button_lights_off) {
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

    }

    private void setupMenu() {

        navMenu.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                return onJZMenuItemClicked(JZMenuItem.fromResourceId(menuItem.getItemId()));
            }
        });

        ServiceStatusHandler serviceStatusHandler = ServiceStatusHandler.getInstance();

        Menu m = navMenu.getMenu();
        for (JZMenuItem menuItem : MENU_ITEMS) {
            MenuItem item = m.add(menuItem.getGroup(), menuItem.getId(), menuItem.getId(), menuItem.getText());
            if (menuItem.getResourceId() == 0)
                item.setIcon(R.drawable.ic_service_blank_32dp);
            else
                item.setIcon(menuItem.getResourceId());
            item.setEnabled(serviceStatusHandler.isAvailable(menuItem.getService()));
        }
    }

    public void refreshMenuItems() {
        ServiceStatusHandler serviceStatusHandler = ServiceStatusHandler.getInstance();
        Menu m = navMenu.getMenu();
        for (JZMenuItem menuItemEnum : MENU_ITEMS) {
            MenuItem menuItem = m.findItem(menuItemEnum.getId());
            menuItem.setEnabled(serviceStatusHandler.isAvailable(menuItemEnum.getService()));
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

        return onJZMenuItemClicked(JZMenuItem.fromResourceId(item.getItemId()));
    }

    @Override
    public boolean onJZMenuItemClicked(JZMenuItem menuItem) {
        switch (menuItem) {
            case HOME:
                displayFragmentFromMenu(HomeFragment.newInstance(this));
                return true;
            case STATUS:
                displayFragmentFromMenu(StatusFragment.newInstance(availabilityChangeCallback));
                return true;
            case LIGHTS:
                displayFragmentFromMenu(LightsFragment.newInstance());
                return true;
            case SETTINGS:
                displayFragmentFromMenu(SettingsFragment.newInstance());
                return true;
            case TEST:

                SusanPokeTask pokeTask = new SusanPokeTask(){
                    @Override
                    protected void onPostExecute(SusanResponse susanResponse) {
                        super.onPostExecute(susanResponse);
                    }
                };


                return true;
            default:
                return false;
        }
    }

    @Override
    public void onAvailabilityChanged(SusanService service, boolean available) {
        refreshMenuItems();
    }
}
