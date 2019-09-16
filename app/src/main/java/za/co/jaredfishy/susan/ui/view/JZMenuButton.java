package za.co.jaredfishy.susan.ui.view;

import android.content.Context;
import android.view.Gravity;

public class JZMenuButton extends JZButton {

    public JZMenuButton(Context context) {
        super(context);
        init();
    }

    public JZMenuButton(Context context, String text) {
        super(context, text);
        init();
    }

    private void init() {
        this.setGravity(Gravity.START);
    }
}
