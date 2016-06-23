package wb.zts.com.webviewdemo.presenter;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import wb.zts.com.webviewdemo.R;
import wb.zts.com.webviewdemo.presenter.i_view.IWbDialogView;
import wb.zts.com.webviewdemo.ui.fragment.WebViewDialog;

/**
 * Created by Administrator on 2016/6/23.
 */
public class WbDialogPresenter extends BasePresenter<IWbDialogView> {

    public WbDialogPresenter(FragmentActivity activity, IWbDialogView view) {
        super(activity, view);
    }


    private static final String EXTRA_DIALOG_TITLE = "DIALOG_TITLE";
    private static final String EXTRA_HTML_FILE_NAME = "HTML_FILE_NAME";
    private static final String EXTRA_ACCENT_COLOR = "ACCENT_COLOR";

    private static final String KEY_UTF_8 = "UTF-8";

    /**
     * create a custom dialog use web view load layout by html file
     * @param dialogTitle  dialog title
     * @param htmlFileName html file name
     * @param accentColor  accent color
     * @return a instance of CustomWebViewDialog
     */
    public static WebViewDialog create(String dialogTitle, String htmlFileName, int accentColor) {
        WebViewDialog dialog = new WebViewDialog();
        Bundle args = new Bundle();
        args.putString(EXTRA_DIALOG_TITLE, dialogTitle);
        args.putString(EXTRA_HTML_FILE_NAME, htmlFileName);
        args.putInt(EXTRA_ACCENT_COLOR, accentColor);
        dialog.setArguments(args);
        Log.i("CustomWebViewDialog","dialog.setArguments(args).........1");
        return dialog;
    }


    public AlertDialog makeOkDialog(Fragment fragment, View customView) {
        String dialogTitle = fragment.getArguments().getString(EXTRA_DIALOG_TITLE);
        String htmlFileName = fragment.getArguments().getString(EXTRA_HTML_FILE_NAME);
        int accentColor = fragment.getArguments().getInt(EXTRA_ACCENT_COLOR);

        final WebView webView = (WebView) customView.findViewById(R.id.dialog_webview);
        setWebView(webView);
        loadData(webView, htmlFileName, accentColor);

        AlertDialog dialog = new AlertDialog.Builder(mContext)
                .setTitle(dialogTitle)
                .setView(customView)
                .setPositiveButton(android.R.string.ok, null)
                .show();
        Log.i("CustomWebViewDialog","dialog.setArguments(args).........3"+htmlFileName);
        return dialog;
    }

    /**
     * show positive na
     * @param fragment
     * @param customView
     * @return
     */
    public AlertDialog makeMulActionDialog(Fragment fragment, View customView,
                                           String ok, DialogInterface.OnClickListener okListener,
                                           String negative, DialogInterface.OnClickListener negativeListener,
                                           String neutral, DialogInterface.OnClickListener neutralListener) {
        String dialogTitle = fragment.getArguments().getString(EXTRA_DIALOG_TITLE);
        String htmlFileName = fragment.getArguments().getString(EXTRA_HTML_FILE_NAME);
        int accentColor = fragment.getArguments().getInt(EXTRA_ACCENT_COLOR);

        final WebView webView = (WebView) customView.findViewById(R.id.dialog_webview);
        setWebView(webView);
        loadData(webView, htmlFileName, accentColor);

        AlertDialog dialog = new AlertDialog.Builder(mContext)
                .setTitle(dialogTitle)
                .setView(customView)
                .setPositiveButton(ok, okListener)
                .setNegativeButton(negative,negativeListener)
                .setNeutralButton(neutral,neutralListener)
                .show();

        return dialog;
    }

    private void setWebView(WebView webView){
        WebSettings settings = webView.getSettings();
        settings.setDefaultTextEncodingName(KEY_UTF_8);
        settings.setJavaScriptEnabled(true);
    }

    private void loadData(WebView webView,String htmlFileName,int accentColor){
        try {
            StringBuilder buf = new StringBuilder();
            Log.i("loadData","..............loadData.........1..");
            InputStream json = mContext.getAssets().open(htmlFileName);
            Log.i("loadData","..............loadData.........2..");
            BufferedReader in = new BufferedReader(new InputStreamReader(json,KEY_UTF_8));
            Log.i("loadData","..............loadData.........3..");
            String str;
            while ((str = in.readLine()) != null)
                buf.append(str);
            in.close();
            Log.i("loadData","..............loadData..........."+buf.toString());
            String formatLodString = buf.toString()
                    .replace("{style-placeholder}", "body { background-color: #ffffff; color: #000; }")
                    .replace("{link-color}", colorToHex(shiftColor(accentColor, true)))
                    .replace("{link-color-active}", colorToHex(accentColor));

            Log.i("loadData","..............formatLodString..........."+formatLodString);

            webView.loadDataWithBaseURL(null,formatLodString, "text/html", KEY_UTF_8, null);
        } catch (Throwable e) {
            Log.i("loadData","..............Throwable..........."+e);
            webView.loadData("<h1>Unable to load</h1><p>" + e.getLocalizedMessage()+ "</p>", "text/html", KEY_UTF_8);
        }
    }

    private String colorToHex(int color) {
        //Java中十六进制转换 Integer.toHexString()
        return Integer.toHexString(color).substring(2);
    }

    private int shiftColor(int color, boolean up) {
        float[] hsv = new float[3];
        Color.colorToHSV(color, hsv);
        hsv[2] *= (up ? 1.1f : 0.9f); // value component
        return Color.HSVToColor(hsv);
    }
}
