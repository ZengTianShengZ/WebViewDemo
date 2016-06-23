package wb.zts.com.webviewdemo.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import wb.zts.com.webviewdemo.presenter.BasePresenter;

/**
 * Created by Administrator on 2016/6/22.
 */
public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {

    protected P mPresenter;
    protected abstract int getLayout();
    protected abstract int initPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        initPresenter();
        checkPresenterIsNull();
    }

    private void checkPresenterIsNull(){
        if(mPresenter == null){
            throw new IllegalStateException("please init mPresenter in initPresenter() method ");
        }
    }
}
