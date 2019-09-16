package za.co.jaredfishy.susan.ui.view;

import android.content.Context;
import android.support.v7.view.ContextThemeWrapper;
import android.widget.ArrayAdapter;

import za.co.jaredfishy.susan.R;

public class JZDropDown extends android.support.v7.widget.AppCompatSpinner {

    private String[] options;

    public JZDropDown(Context context, String[] options) {
        super(new ContextThemeWrapper(context, R.style.JZAppTheme));
        this.options = options;
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.setAdapter(adapter);
    }

    public String getSelectedItem() {
        return options[super.getSelectedItemPosition()];
    }
}
