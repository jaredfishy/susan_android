package za.co.jaredfishy.susan.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import za.co.jaredfishy.susan.domain.JZMenuItem;
import za.co.jaredfishy.susan.handler.status.ServiceStatusHandler;
import za.co.jaredfishy.susan.task.JZMenuItemOnClickListener;
import za.co.jaredfishy.susan.ui.view.JZIconButton;
import za.co.jaredfishy.susan.ui.view.JZIconGrid;

public class HomeFragment extends BaseFragment {

    public static HomeFragment newInstance(JZMenuItemOnClickListener listener) {
        HomeFragment fragment = new HomeFragment();
        fragment.setMenuItemClickListener(listener);
        return fragment;
    }

    private static final JZMenuItem[] TILES = new JZMenuItem[]{
            JZMenuItem.STATUS, JZMenuItem.LIGHTS,
    };

    private JZMenuItemOnClickListener menuItemClickListener;
    private List<JZIconButton> gridButtons;

    public HomeFragment() {
        super("Home");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        JZIconGrid grid = new JZIconGrid(context);

        gridButtons = new ArrayList<>();
        for (final JZMenuItem menuItem : TILES) {
            JZIconButton jzButton = new JZIconButton(context, menuItem);
            jzButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (menuItemClickListener != null)
                        menuItemClickListener.onJZMenuItemClicked(menuItem);
                }
            });

            gridButtons.add(jzButton);
        }

        grid.addAllViews(gridButtons);
        addView(grid);
    }

    @Override
    public void onResume() {
        super.onResume();
        ServiceStatusHandler serviceStatusHandler = ServiceStatusHandler.getInstance();
        for (JZIconButton iconButton : gridButtons) {
            iconButton.setEnabled(serviceStatusHandler.isAvailable(iconButton.getMenuItem().getService()));
        }
    }

    private void setMenuItemClickListener(JZMenuItemOnClickListener menuItemClickListener) {
        this.menuItemClickListener = menuItemClickListener;
    }
}
