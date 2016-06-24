package wb.zts.com.webviewdemo.presenter;

import android.app.Activity;
import android.util.Log;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import wb.zts.com.webviewdemo.domain.ZhihuDaily;
import wb.zts.com.webviewdemo.domain.ZhihuDailyItem;
import wb.zts.com.webviewdemo.presenter.i_view.IzhiHuView;

/**
 * Created by Administrator on 2016/6/23.
 */
public class ZhiHuPresenter  extends BasePresenter<IzhiHuView> {

    public ZhiHuPresenter(Activity context, IzhiHuView view) {
        super(context, view);

    }


    public void getZhiHuDailyNews(String date) {
        mView.showRefresh();

        ZhihuRequest.getZhihuApi().getTheDaily(date)
                .map(new Func1<ZhihuDaily, ZhihuDaily>() {
                    @Override
                    public ZhihuDaily call(ZhihuDaily zhihuDaily) {
                        Log.i("getZhiHuDailyNews","................map........");
                        String date = zhihuDaily.getDate();
                        for (ZhihuDailyItem zhihuDailyItem : zhihuDaily.getStories()) {
                            zhihuDailyItem.setDate(date);
                        }
                        return zhihuDaily;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ZhihuDaily>() {
                    @Override
                    public void onCompleted() {
                        mView.getDataFinish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        mView.showErrorView(e);
                    }

                    @Override
                    public void onNext(ZhihuDaily zhihuDaily) {
                        mView.getZhihuDailyData(zhihuDaily);
                    }
                });

    }



}
