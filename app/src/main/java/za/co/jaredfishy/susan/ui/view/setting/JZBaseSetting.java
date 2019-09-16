package za.co.jaredfishy.susan.ui.view.setting;

import android.content.Context;
import android.support.v7.view.ContextThemeWrapper;
import android.widget.LinearLayout;

import za.co.jaredfishy.susan.R;
import za.co.jaredfishy.susan.ui.view.JZTextView;
import za.co.jaredfishy.susan.util.DimensionUtil;

public abstract class JZBaseSetting extends LinearLayout {

    private static int size = -1;

    public JZBaseSetting(Context context, String label) {
        super(new ContextThemeWrapper(context, R.style.JZAppTheme));
        if(size==-1)
            size = DimensionUtil.getPixels(context, 8);

        JZTextView textView = new JZTextView(context, label);
        textView.setPadding(size,0,0,0);
        super.addView(textView);
    }

    public abstract String getSetting();
}
