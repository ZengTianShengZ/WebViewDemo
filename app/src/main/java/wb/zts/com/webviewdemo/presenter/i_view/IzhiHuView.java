package wb.zts.com.webviewdemo.presenter.i_view;

import wb.zts.com.webviewdemo.domain.ZhihuDaily;

/**
 * Created by Administrator on 2016/6/23.
 */
public interface IzhiHuView extends ISwipeRefreshView  {

    void getZhihuDailyData(ZhihuDaily zhihuDaily);
}
