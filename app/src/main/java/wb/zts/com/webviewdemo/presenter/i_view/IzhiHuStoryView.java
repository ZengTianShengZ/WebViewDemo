package wb.zts.com.webviewdemo.presenter.i_view;

import wb.zts.com.webviewdemo.domain.ZhihuStory;

/**
 * Created by Administrator on 2016/6/24.
 */
public interface IzhiHuStoryView  extends ISwipeRefreshView {

    void doNextData(ZhihuStory zhihuStory);
}
