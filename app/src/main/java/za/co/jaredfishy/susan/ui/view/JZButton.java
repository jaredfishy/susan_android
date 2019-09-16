package za.co.jaredfishy.susan.ui.view;

import android.content.Context;
import android.view.View;

public class JZButton extends android.support.v7.widget.AppCompatButton {

    public JZButton(Context context) {
        super(context);
        init();
    }

    public JZButton(Context context, String text) {
        this(context);
        super.setText(text);

    }

    private void init(){
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
