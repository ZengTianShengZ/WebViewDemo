package wb.zts.com.webviewdemo.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

import wb.zts.com.webviewdemo.R;

/**
 * Created by Administrator on 2016/6/23.
 */
public class AndroidUtils {
    /**
     * get accent color
     * @param context
     * @return
     */
    public static int getAccentColor(Context context){
        TypedValue typedValue = new TypedValue();
        Resources.Theme theme = context.getTheme();
        theme.resolveAttribute(R.attr.colorPrimary, typedValue, true);
        return typedValue.data;
    }

}
