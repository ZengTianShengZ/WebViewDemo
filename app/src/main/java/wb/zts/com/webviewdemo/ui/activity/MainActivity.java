package wb.zts.com.webviewdemo.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.webkit.WebView;

import butterknife.Bind;
import wb.zts.com.webviewdemo.R;
import wb.zts.com.webviewdemo.WebViewApp;
import wb.zts.com.webviewdemo.presenter.MainPresenter;
import wb.zts.com.webviewdemo.presenter.WbDialogPresenter;
import wb.zts.com.webviewdemo.presenter.i_view.IWebView;
import wb.zts.com.webviewdemo.util.AndroidUtils;
import wb.zts.com.webviewdemo.util.SnackbarUtil;

public class MainActivity extends BaseSwipeRefreshActivity<MainPresenter> implements IWebView {

    @Bind(R.id.main_webview)
    protected WebView main_webview;

    private String URL_str = "https://www.baidu.com/";
    /************************  BaseSwipeRefreshActivity   ******************************/
    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initPresenter() {
        mPresenter = new MainPresenter(this, this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbarTitle("WebViewDemo-",true);

        mPresenter.setUpWebView(main_webview);
        mPresenter.loadUrl(main_webview,URL_str);

        Log.i("activity","............MainActivity..............");
    }

    /************************  ISwipeRefreshView   ******************************/
    @Override
    protected void onRefreshStarted() {

    }

    @Override
    public void showEmptyView() {

    }

    @Override
    public void showErrorView(Throwable throwable) {

    }

   /************************  IWebView   ******************************/
    @Override
    public void appendMoreDataToView() {

    }

    @Override
    public void showLoadErrorMessage(String message) {
        SnackbarUtil.ThemeSnackbar(WebViewApp.AContext,main_webview,"网络异常，请检查网络");
    }



    /************************  activity  ******************************/

    @Override
    protected int getMenuRes() {
        return R.menu.menu_main;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_about_demo:
                int accentColor = AndroidUtils.getAccentColor(getApplicationContext());
                WbDialogPresenter.create("关于demo", "about_demo.html", accentColor)
                        .show(getSupportFragmentManager(), "about_demo");
                break;
            case R.id.action_about_me:

                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if ( main_webview.canGoBack()) {
            main_webview.goBack();// 返回前一个页面
            return ;
        }
        super.onBackPressed();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && main_webview.canGoBack()) {
            main_webview.goBack();// 返回前一个页面
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
