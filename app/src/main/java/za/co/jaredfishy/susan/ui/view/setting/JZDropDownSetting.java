package za.co.jaredfishy.susan.ui.view.setting;

import android.content.Context;

import java.util.Arrays;

import za.co.jaredfishy.susan.ui.view.JZDropDown;

public class JZDropDownSetting extends JZBaseSetting {
    private JZDropDown dropDown;

    public JZDropDownSetting(Context context, String label, String[] options, String selected) {
        super(context, label);
        super.setOrientation(VERTICAL);

        int index = Arrays.asList(options).indexOf(selected);
        dropDown = new JZDropDown(context, options);
        if (index >= 0)
            dropDown.setSelection(index);
        super.addView(dropDown);
    }

    public String getSetting() {
        return dropDown.getSelectedItem();
    }
}
