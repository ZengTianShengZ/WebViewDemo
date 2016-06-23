package wb.zts.com.webviewdemo.ui.fragment;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import wb.zts.com.webviewdemo.R;
import wb.zts.com.webviewdemo.WebViewApp;
import wb.zts.com.webviewdemo.presenter.WbDialogPresenter;
import wb.zts.com.webviewdemo.presenter.i_view.IWbDialogView;
import wb.zts.com.webviewdemo.util.SnackbarUtil;

/**
 * Created by Administrator on 2016/6/23.
 */
public class WebViewDialog extends DialogFragment implements IWbDialogView {

    private WbDialogPresenter mPresenter;
    private WebView mWebView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new WbDialogPresenter(getActivity(),this);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final View customView;
        try {
            customView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_webview, null);
            mWebView = (WebView) customView.findViewById(R.id.dialog_webview);
            mWebView.addJavascriptInterface(new WebAppInterface(customView.getContext()),"Android");
        } catch (InflateException e) {
            throw new IllegalStateException("This device does not support Web Views.");
        }

        return mPresenter.makeOkDialog(this,customView);
    }


    public class WebAppInterface {
        Context mContext;

        /** Instantiate the interface and set the context */
        WebAppInterface(Context c) {
            mContext = c;
        }

        /** Show a toast from the web page */
        @JavascriptInterface
        public void showToast(String toast) {

        }

        /** Show a dialog about app **/
        @JavascriptInterface
        public void showAbout(){
         }

        /** Show a dialog about gank site **/
        @JavascriptInterface
        public void showAboutGank(){

            SnackbarUtil.ThemeSnackbar(WebViewApp.AContext,mWebView,"showAboutGank");
         }
        @JavascriptInterface
        public void showAboutVersion(){
         }
    }
}
