package wb.zts.com.webviewdemo.presenter;

import android.app.Activity;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import wb.zts.com.webviewdemo.domain.ZhihuStory;
import wb.zts.com.webviewdemo.presenter.i_view.IzhiHuStoryView;
import wb.zts.com.webviewdemo.presenter.i_view.IzhiHuView;
import wb.zts.com.webviewdemo.util.WebUtil;

/**
 * Created by Administrator on 2016/6/24.
 */
public class ZhiHuStroyPresenter  extends BasePresenter<IzhiHuStoryView> {

    private String getShareUrl;
    private ZhihuStory mZhihuStory;

    public ZhiHuStroyPresenter(Activity context, IzhiHuStoryView view) {
        super(context, view);
    }

    public void setWebView(WebView wvZhihu) {
        WebSettings settings = wvZhihu.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        settings.setLoadWithOverviewMode(true);
        settings.setBuiltInZoomControls(true);
        //settings.setUseWideViewPort(true);造成文字太小
        settings.setDomStorageEnabled(true);
        settings.setDatabaseEnabled(true);
        //settings.setAppCachePath(getCacheDir().getAbsolutePath() + "/webViewCache");
        settings.setAppCacheEnabled(true);
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        wvZhihu.setWebChromeClient(new WebChromeClient());
    }
    public void getZhihuStory(String id) {
        mView.showRefresh();
        Subscription s = ZhihuRequest.getZhihuApi().getZhihuStory(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ZhihuStory>() {
                    @Override
                    public void onCompleted() {
                        mView.getDataFinish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showErrorView(e);
                    }

                    @Override
                    public void onNext(ZhihuStory zhihuStory) {
                        mView.doNextData(zhihuStory);
                    }
                });

    }

    public void setDataToWebView(WebView wvZhihu, ZhihuStory zhihuStory) {
        this.mZhihuStory = zhihuStory;
        Log.i("LoadUrl",".........getShareUrl..........."+zhihuStory.getShareUrl());
        if(zhihuStory.getTitle()!=null){
            Log.i("getTitle","....getTitle......"+zhihuStory.getTitle());
        }
       // news_imgview.setImageURI(Uri.parse(zhihuStory.getImage()));
       // url = zhihuStory.getShareUrl();
        if (TextUtils.isEmpty(zhihuStory.getBody())) {
            this.getShareUrl = zhihuStory.getShareUrl();
            wvZhihu.loadUrl(getShareUrl );
        } else {
            String data = WebUtil.buildHtmlWithCss(zhihuStory.getBody(), zhihuStory.getCss(),false);
            wvZhihu.loadDataWithBaseURL(WebUtil.BASE_URL, data, WebUtil.MIME_TYPE, WebUtil.ENCODING, WebUtil.FAIL_URL);
        }
    }

    public void LoadUrl(WebView wvZhihu){
        if(getShareUrl!=null){
            wvZhihu.loadUrl(getShareUrl );
            Log.i("LoadUrl",".........LoadUrl..........."+getShareUrl);
        }

    }
    public void setZhihuStoryToWebView(WebView wvZhihu,boolean isNightMode){
        if(mZhihuStory.getTitle()!=null){
            Log.i("getTitle","....getTitle......"+mZhihuStory.getTitle());
        }
        // news_imgview.setImageURI(Uri.parse(zhihuStory.getImage()));
        // url = zhihuStory.getShareUrl();
        if (TextUtils.isEmpty(mZhihuStory.getBody())) {
            this.getShareUrl = mZhihuStory.getShareUrl();
            wvZhihu.loadUrl(getShareUrl );
        } else {
            String data = WebUtil.buildHtmlWithCss(mZhihuStory.getBody(), mZhihuStory.getCss(),isNightMode);
            wvZhihu.loadDataWithBaseURL(WebUtil.BASE_URL, data, WebUtil.MIME_TYPE, WebUtil.ENCODING, WebUtil.FAIL_URL);
        }
    }

}
