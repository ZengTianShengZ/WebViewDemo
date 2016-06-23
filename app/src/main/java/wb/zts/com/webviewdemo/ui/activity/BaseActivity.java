package wb.zts.com.webviewdemo.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.Bind;
import butterknife.ButterKnife;
import wb.zts.com.webviewdemo.R;
import wb.zts.com.webviewdemo.presenter.BasePresenter;

/**
 * Created by Administrator on 2016/6/22.
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {

    @Bind(R.id.toolbar)
    protected Toolbar mToolbar;

    protected P mPresenter;
    protected abstract int getLayout();
    protected abstract void initPresenter();

    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        ButterKnife.bind(this);
        initPresenter();
        checkPresenterIsNull();
        initToolBar();

        Log.i("activity","............BaseActivity..............");
    }
    protected int getMenuRes(){
        return -1;
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(getMenuRes()<0)return true;
        getMenuInflater().inflate(getMenuRes(), menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                //don't use finish() and use onBackPressed() will be a good practice , trust me !
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void checkPresenterIsNull(){
        if(mPresenter == null){
            throw new IllegalStateException("please init mPresenter in initPresenter() method ");
        }
    }


    final private void initToolBar() {
        if(mToolbar == null){
            throw new NullPointerException("please add a Toolbar in your layout.");
        }
        setSupportActionBar(mToolbar);
    }

    public void setToolbarTitle(String strTitle,boolean showHome){
        setTitle(strTitle);
        getSupportActionBar().setDisplayShowHomeEnabled(showHome);
        getSupportActionBar().setDisplayHomeAsUpEnabled(showHome);
    }
}
