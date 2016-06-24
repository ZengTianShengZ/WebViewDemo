package wb.zts.com.webviewdemo.util;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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
    public static final class Dates {
        public static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd", Locale.US);
        @SuppressWarnings("deprecation")
        public static final Date birthday = new java.util.Date(113, 4, 19); // May 19th, 2013
    }
}
