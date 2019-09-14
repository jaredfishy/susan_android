package za.co.jaredfishy.susan.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.TypedValue;

public class DimensionUtil {


    public static int getDimension(Context context, int resourceId) {
        int[] textSizeAttr = new int[]{resourceId};
        int indexOfAttrTextSize = 0;
        TypedValue typedValue = new TypedValue();
        TypedArray a = context.obtainStyledAttributes(typedValue.data, textSizeAttr);
        int textSize = a.getDimensionPixelSize(indexOfAttrTextSize, -1);
        a.recycle();
        return textSize;
    }

    public static float getPixels(Context context, float dip) {
        float px = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dip,
                context.getResources().getDisplayMetrics()
        );
        return px;
    }

    public static int getPixels(Context context, int dip) {
        float fpx = getPixels(context, (float) dip);
        return (int) Math.floor(fpx + 0.5);
    }
}
