package wb.zts.com.webviewdemo.presenter;



import retrofit2.http.GET;
import retrofit2.http.Path;
import wb.zts.com.webviewdemo.domain.ZhihuDaily;
import wb.zts.com.webviewdemo.domain.ZhihuStory;


/**
 * Created by Administrator on 2016/6/3.
 */
public interface ZhihuApi {

    @GET("/api/4/news/latest")
    rx.Observable<ZhihuDaily> getLastDaily();

    @GET("/api/4/news/before/{date}")
    rx.Observable<ZhihuDaily> getTheDaily(@Path("date") String date);

    @GET("/api/4/news/{id}")
    rx.Observable<ZhihuStory> getZhihuStory(@Path("id") String id);

}