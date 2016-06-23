package wb.zts.com.webviewdemo.presenter.i_view;

import java.util.List;

/**
 * IWebView 是获取数据更新 activity 的一个接口
 *
 * Created by Administrator on 2016/6/23.
 */
public interface IWebView extends ISwipeRefreshView {

    // do something to activity ,add data or refresh view
    void appendMoreDataToView();
    void showLoadErrorMessage(String message);
}
