package za.co.jaredfishy.susan.ui.view;

import android.content.Context;
import android.support.v7.view.ContextThemeWrapper;
import android.view.View;

import za.co.jaredfishy.susan.R;

public class JZButton extends android.support.v7.widget.AppCompatButton {

    public JZButton(Context context) {
        super(new ContextThemeWrapper(context, R.style.JZAppTheme_Button));
        init();
    }

    public JZButton(Context context, String text) {
        this(context);
        super.setText(text);
    }

    private void init() {
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                JZButton.this.onClick();
            }
        });
    }

    public void onClick() {

    }
}
