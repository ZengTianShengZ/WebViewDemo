package wb.zts.com.webviewdemo.ui.adapter;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import wb.zts.com.webviewdemo.R;
import wb.zts.com.webviewdemo.domain.ZhihuDailyItem;
import wb.zts.com.webviewdemo.ui.activity.ZhihuStoryActivity;

/**
 * Created by Administrator on 2016/6/24.
 */
public class ZhihuNewsRecycleAdapter extends BaseRecycleViewAdapter {

    private List<ZhihuDailyItem> mZhihuDailyItemList  = new ArrayList<ZhihuDailyItem>();;
    
    public ZhihuNewsRecycleAdapter(Context context ) {
        super(context, R.layout.item_zhihu_news);
        initData();
    }

    private void initData() {
        mDatas = mZhihuDailyItemList;
    }


    @Override
    public void convert(RecycleViewHolder holder, Object obj, int holderPosition) {
        final ZhihuDailyItem item = (ZhihuDailyItem)obj;

        TextView question_title = holder.getView( R.id.iten_zhihu_news_question_title);
        TextView daily_title = holder.getView( R.id.iten_zhihu_news_daily_title);

        question_title.setText(item.getTitle()+"");

        daily_title.setText(item.getDate()+"");

        Picasso.with(mContext)
                .load(item.getImages()[0])
                .into((ImageView) holder.getView(R.id.zhihu_news_img), new Callback() {
                    @Override
                    public void onSuccess() {

                    }
                    @Override
                    public void onError() {

                    }
                });


        holder.mConvertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("mConvertView","......mConvertView......");
                Intent intent = new Intent(mContext, ZhihuStoryActivity.class);
                // Calling startActivity() from outside of an Activity  context requires the FLAG_ACTIVITY_NEW_TASK flag.
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("id", item.getId());
                intent.putExtra("title", item.getTitle());
                mContext.startActivity(intent);
            }
        });
    }

}
