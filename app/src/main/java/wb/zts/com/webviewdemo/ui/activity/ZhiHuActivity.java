package wb.zts.com.webviewdemo.ui.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.Calendar;

import butterknife.Bind;
import wb.zts.com.webviewdemo.R;
import wb.zts.com.webviewdemo.WebViewApp;
import wb.zts.com.webviewdemo.domain.ZhihuDaily;
import wb.zts.com.webviewdemo.presenter.MainPresenter;
import wb.zts.com.webviewdemo.presenter.ZhiHuPresenter;
import wb.zts.com.webviewdemo.presenter.i_view.IzhiHuView;
import wb.zts.com.webviewdemo.ui.adapter.ZhihuNewsRecycleAdapter;
import wb.zts.com.webviewdemo.util.AndroidUtils;
import wb.zts.com.webviewdemo.util.SnackbarUtil;

public class ZhiHuActivity extends BaseSwipeRefreshActivity<ZhiHuPresenter> implements IzhiHuView {

    @Bind(R.id.zhihu_RecyclerView)
    protected RecyclerView zhihu_RecyclerView;
    private ZhihuNewsRecycleAdapter mZhihuNewsRecycleAdapter;

    private String date;
    private  int data_flag = 1;
    private Calendar dateToGetUrl;

    /************************  BaseSwipeRefreshActivity   ******************************/
    @Override
    protected int getLayout() {
        return R.layout.activity_zhi_hu;
    }

    @Override
    protected void initPresenter() {
        mPresenter = new ZhiHuPresenter(this, this);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setToolbarTitle("知乎日报",true);
        initRecycleView();
        dateToGetUrl = Calendar.getInstance();
    }

    private void initRecycleView() {
        zhihu_RecyclerView.setHasFixedSize(false);
        LinearLayoutManager llm = new LinearLayoutManager(mContext);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        zhihu_RecyclerView.setLayoutManager(llm);
        mZhihuNewsRecycleAdapter = new ZhihuNewsRecycleAdapter(mContext);
        zhihu_RecyclerView.setAdapter(mZhihuNewsRecycleAdapter);
    }

    @Override
    protected void onRefreshStarted() {

         dateToGetUrl.add(Calendar.DAY_OF_YEAR, data_flag-- );
        date = AndroidUtils.Dates.simpleDateFormat.format(dateToGetUrl.getTime());
        mPresenter.getZhiHuDailyNews(date);
    }

    /************************  ISwipeRefreshView   ******************************/


    @Override
    public void showEmptyView() {

    }

    @Override
    public void showErrorView(Throwable throwable) {

        SnackbarUtil.ThemeSnackbar(WebViewApp.AContext,zhihu_RecyclerView,"网络异常，请检查网络");
    }
    /************************  IzhiHuView   ******************************/
    @Override
    public void getZhihuDailyData(ZhihuDaily zhihuDaily) {
         mZhihuNewsRecycleAdapter.addAllData(zhihuDaily.getStories());
    }
}
