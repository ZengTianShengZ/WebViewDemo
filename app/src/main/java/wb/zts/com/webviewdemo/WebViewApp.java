package wb.zts.com.webviewdemo;

import android.app.Application;
import android.content.Context;

/**
 * Created by Administrator on 2016/6/23.
 */
public class WebViewApp extends Application {
    public static Context AContext;
    @Override
    public void onCreate() {
        super.onCreate();
        AContext = this;

    }

}
