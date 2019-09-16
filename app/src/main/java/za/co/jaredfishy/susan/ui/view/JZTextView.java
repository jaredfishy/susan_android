package za.co.jaredfishy.susan.ui.view;

import android.content.Context;

public class JZTextView extends android.support.v7.widget.AppCompatTextView {

    public JZTextView(Context context) {
        super(context);
    }

    public JZTextView(Context context, String text) {
        this(context);
        super.setText(text);
    }
}
