package wb.zts.com.webviewdemo.presenter.i_view;

/**
 * ISwipeRefreshView 是 activity 操作 SwipeRefreshView 控件的一个接口
 * Created by Administrator on 2016/6/23.
 */
public interface ISwipeRefreshView extends IBaseView {


    void getDataFinish();

    void showEmptyView();

    void showErrorView(Throwable throwable);

    void showRefresh();

    void hideRefresh();

}

