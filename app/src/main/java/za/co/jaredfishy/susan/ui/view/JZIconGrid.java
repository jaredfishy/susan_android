package za.co.jaredfishy.susan.ui.view;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import za.co.jaredfishy.susan.R;
import za.co.jaredfishy.susan.util.GridLayoutIndexGenerator;

public class JZIconGrid extends LinearLayout {

    private Context context;
    private List<LinearLayout> rows;
    private List<View> views;

    public JZIconGrid(Context context) {
        super(context);
        this.context = context;
        this.rows = new ArrayList<>();
        this.views = new ArrayList<>();
        super.setOrientation(VERTICAL);
        super.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    public void addView(View view) {
        views.add(view);
        relayout();
    }

    public void addAllViews(List<? extends View> views) {
        for (View view : views)
            this.views.add(view);
        relayout();
    }

    private void relayout() {
        clearViews();

        int viewCount = views.size();
        List<List<Integer>> layout = GridLayoutIndexGenerator.spreadNicely(viewCount);

        LinearLayout.LayoutParams rowLayoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        rowLayoutParams.weight = 1;
        LinearLayout.LayoutParams colLayoutParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT);
        colLayoutParams.weight = 1;

        for (List<Integer> row : layout) {
            LinearLayout llRow = (LinearLayout) ((Activity) context).getLayoutInflater().inflate(R.layout.ui_grid_row, null);
            llRow.setLayoutParams(rowLayoutParams);
            llRow.setGravity(Gravity.CENTER);
            for (Integer index : row) {
                LinearLayout block = (LinearLayout) ((Activity) context).getLayoutInflater().inflate(R.layout.ui_grid_block, null);
                block.setGravity(Gravity.CENTER);
                block.setLayoutParams(colLayoutParams);
                block.addView(this.views.get(index));
                llRow.addView(block);
            }
            rows.add(llRow);
            super.addView(llRow);
        }
    }

    private void clearViews() {
        for (LinearLayout linearLayout : rows) {
            linearLayout.removeAllViews();
        }
        super.removeAllViews();
        this.rows = new ArrayList<>();
    }

}
