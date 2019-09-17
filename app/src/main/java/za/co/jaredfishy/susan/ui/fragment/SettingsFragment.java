package za.co.jaredfishy.susan.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;

import za.co.jaredfishy.susan.ui.view.JZButton;
import za.co.jaredfishy.susan.ui.view.JZTextView;
import za.co.jaredfishy.susan.ui.view.setting.JZDropDownSetting;

public class SettingsFragment extends BaseFragment {

    public static SettingsFragment newInstance() {
        return new SettingsFragment();
    }

    private JZDropDownSetting setting;

    public SettingsFragment() {
        super("Settings");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        JZTextView textView = new JZTextView(getActivity(), "Coming soon...");
        addView(textView);

//        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(getActivity());
//        String theme = sharedPref.getString("theme", "Dark");
//
//        setting = new JZDropDownSetting(getActivity(), "Theme", new String[]{"Dark", "Light"}, theme);
//        addView(setting);
//
        JZButton btnSave = new JZButton(getActivity(), "Back") {
            public void onClick() {

//                SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getActivity());
//                SharedPreferences.Editor editor1 = settings.edit();
//                editor1.putString("theme", setting.getSetting());
//                editor1.commit();

                context.back();

            }
        };
        addView(btnSave);
    }
}
