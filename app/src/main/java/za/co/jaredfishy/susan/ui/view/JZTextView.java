package za.co.jaredfishy.susan.ui.view;

import android.content.Context;
import android.support.v7.view.ContextThemeWrapper;

import za.co.jaredfishy.susan.R;

public class JZTextView extends android.support.v7.widget.AppCompatTextView {

    public JZTextView(Context context) {
        super(new ContextThemeWrapper(context, R.style.JZAppTheme));
    }

    public JZTextView(Context context, String text) {
        this(context);
        super.setText(text);
    }
}
