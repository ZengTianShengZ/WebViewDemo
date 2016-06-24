package wb.zts.com.webviewdemo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import wb.zts.com.webviewdemo.R;


/**
 *  RecyclerView.Adapter  基类
 *
 * @ClassName: BaseRecycleViewAdapter
 * @Description: TODO
 * @author zss
 * @date 2016-4-29 PM
 */

public abstract class BaseRecycleViewAdapter<T> extends RecyclerView.Adapter<RecycleViewHolder> {

	//正常条目
	private static final int TYPE_NORMAL_ITEM = 0;
	//加载条目
	private static final int TYPE_LOADING_ITEM = 1;

	protected Context mContext;
	protected int mLayoutId;
	protected List<T> mDatas;
	protected LayoutInflater mInflater;

	public static final int LAST_POSITION = -1;

	public BaseRecycleViewAdapter(Context context, int layoutId ) {
		mContext = context;
		mInflater = LayoutInflater.from(context);
		mLayoutId = layoutId;
		//mDatas = datas;
	}



	@Override
	public int getItemCount() {
		return mDatas.size() == 0 ? 0 : mDatas.size() ;
	}
	@Override
	public long getItemId(int position) {
		return position;
	}


	@Override
	public void onBindViewHolder(final RecycleViewHolder holder, final int position) {
		holder.updatePosition(position);
		convert(holder, mDatas.get(position), holder.getAdapterPosition());
	}

	@Override
	public RecycleViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {

		final RecycleViewHolder viewHolder = RecycleViewHolder.get(mContext, parent, mLayoutId);
		return viewHolder;

	}

	public abstract void convert(RecycleViewHolder holder, T t, int holderPosition);

	public void addAllData(List<T> data){
		mDatas.addAll(data);
		notifyDataSetChanged();
	}
}
