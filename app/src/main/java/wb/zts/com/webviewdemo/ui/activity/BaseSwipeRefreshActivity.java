package wb.zts.com.webviewdemo.ui.activity;

import android.os.Bundle;
import android.support.annotation.CheckResult;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;

import butterknife.Bind;
import wb.zts.com.webviewdemo.R;
import wb.zts.com.webviewdemo.WebViewApp;
import wb.zts.com.webviewdemo.presenter.BasePresenter;
import wb.zts.com.webviewdemo.presenter.i_view.ISwipeRefreshView;
import wb.zts.com.webviewdemo.util.SnackbarUtil;

/**
 * Created by Administrator on 2016/6/22.
 */
public abstract class BaseSwipeRefreshActivity<P extends BasePresenter> extends BaseActivity<P> implements ISwipeRefreshView {
    @Bind(R.id.swipe_refresh_layout)
    protected SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initSwipeRefreshLayout();
        Log.i("activity","............BaseSwipeRefreshActivity..............");
    }

    private void initSwipeRefreshLayout(){

        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorPrimaryDark, R.color.colorAccent);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (prepareRefresh()) {
                    onRefreshStarted();
                } else {
                    //产生一个加载数据的假象
                     hideRefresh();
                }
            }
        });
    }
    protected boolean prepareRefresh(){
        return true;
    }
    protected abstract void onRefreshStarted();

    @Override
    public void hideRefresh() {
        // 防止刷新消失太快，让子弹飞一会儿. do not use lambda!!
        mSwipeRefreshLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mSwipeRefreshLayout != null){
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }
        },1000);
    }

    @Override
    public void getDataFinish() {
        hideRefresh();
        SnackbarUtil.ThemeSnackbar(WebViewApp.AContext,mSwipeRefreshLayout,"数据加载完成");
    }

    @CheckResult
    protected boolean isRefreshing(){
        return mSwipeRefreshLayout.isRefreshing();
    }
    @Override
    public void showRefresh() {
        mSwipeRefreshLayout.setRefreshing(true);
    }
}
