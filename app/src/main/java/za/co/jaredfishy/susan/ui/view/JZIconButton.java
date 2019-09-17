package za.co.jaredfishy.susan.ui.view;

import android.content.Context;
import android.support.v7.view.ContextThemeWrapper;
import android.view.Gravity;

import za.co.jaredfishy.susan.R;
import za.co.jaredfishy.susan.domain.JZMenuItem;

public class JZIconButton extends android.support.v7.widget.AppCompatButton {

    private JZMenuItem menuItem;

    public JZIconButton(Context context, JZMenuItem menuItem) {
        super(new ContextThemeWrapper(context, R.style.JZAppTheme_Button));
        this.menuItem = menuItem;

        this.setGravity(Gravity.CENTER);
        this.setText(menuItem.getText());
        this.setCompoundDrawablesRelativeWithIntrinsicBounds(menuItem.getResourceId(), 0, 0, 0);
        this.setBackgroundResource(R.drawable.ui_button);
    }

    public JZMenuItem getMenuItem() {
        return menuItem;
    }
}
