package za.co.jaredfishy.susan.ui.view;

import android.content.Context;
import android.view.Gravity;

import za.co.jaredfishy.susan.R;

public class JZIconButton extends android.support.v7.widget.AppCompatButton {

    private JZMenuItem menuItem;

    public JZIconButton(Context context, JZMenuItem menuItem) {
        super(context);
        this.menuItem = menuItem;

        int padding = getResources().getDimensionPixelSize(R.dimen.ui_jz_icon_button_padding);

        super.setGravity(Gravity.CENTER);
        super.setPadding(padding, padding, padding, padding);
        super.setText(menuItem.getText());
        super.setCompoundDrawablesRelativeWithIntrinsicBounds(menuItem.getResourceId(), 0, 0, 0);
    }

    public JZMenuItem getMenuItem() {
        return menuItem;
    }
}
