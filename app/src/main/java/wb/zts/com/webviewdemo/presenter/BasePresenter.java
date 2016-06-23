package wb.zts.com.webviewdemo.presenter;

import android.app.Activity;

import wb.zts.com.webviewdemo.presenter.i_view.IBaseView;

/**
 * Created by Administrator on 2016/6/22.
 */
public class BasePresenter<GV extends IBaseView> {

    protected GV mView;
    /**
     * TODO 这里用是否用Activity待考证
     */
    protected Activity mContext;

    //public static final GuDong mGuDong = MainFactory.getGuDongInstance();

    public BasePresenter(Activity context, GV view) {
        mContext = context;
        mView = view;
    }
}
