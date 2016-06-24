package wb.zts.com.webviewdemo.ui.activity;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.view.MenuItem;
import android.webkit.WebView;

import java.util.Calendar;

import butterknife.Bind;
import wb.zts.com.webviewdemo.R;
import wb.zts.com.webviewdemo.WebViewApp;
import wb.zts.com.webviewdemo.domain.ZhihuStory;
import wb.zts.com.webviewdemo.presenter.ZhiHuPresenter;
import wb.zts.com.webviewdemo.presenter.ZhiHuStroyPresenter;
import wb.zts.com.webviewdemo.presenter.i_view.IzhiHuStoryView;
import wb.zts.com.webviewdemo.util.SnackbarUtil;

/**
 * Created by Administrator on 2016/6/24.
 */
public class ZhihuStoryActivity  extends BaseSwipeRefreshActivity<ZhiHuStroyPresenter> implements IzhiHuStoryView {

    @Bind(R.id.main_webview)
    protected WebView wvZhihu;


    private String id;
    private String title;
    private String url;


    /************************  BaseSwipeRefreshActivity   ******************************/
    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initPresenter() {
        mPresenter = new ZhiHuStroyPresenter(this, this);


    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initData();

        mPresenter.setWebView(wvZhihu);
    }
    private void initData() {

        id = getIntent().getStringExtra("id");
        title = getIntent().getStringExtra("title");
        setToolbarTitle(title,true);
        mPresenter.getZhihuStory(id);
    }

    @Override
    protected void onRefreshStarted() {
        mPresenter.getZhihuStory(id);
    }


    @Override
    public void showEmptyView() {

    }

    @Override
    public void showErrorView(Throwable throwable) {
        SnackbarUtil.ThemeSnackbar(WebViewApp.AContext,mToolbar,"网络异常，请检查网络");
    }

    @Override
    public void doNextData(ZhihuStory zhihuStory) {
        mPresenter.setDataToWebView(wvZhihu,zhihuStory);
    }

    @Override
    protected int getMenuRes() {
        return R.menu.menu_zhihu;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.action_no_css:
                mPresenter.LoadUrl(wvZhihu);
                break;
            case R.id.action_have_css:
                mPresenter.setZhihuStoryToWebView(wvZhihu,false);
                break;
            case R.id.action_night:
                mPresenter.setZhihuStoryToWebView(wvZhihu,true);
                break;
            case R.id.action_no_night:
                mPresenter.setZhihuStoryToWebView(wvZhihu,false);
                break;


        }
        return super.onOptionsItemSelected(item);
    }
}
