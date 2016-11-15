package com.bin.mvpproject.ui.activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.bin.mvpproject.R;
import com.bin.mvpproject.bean.Root;
import com.bin.mvpproject.ui.contract.WeaterContract;
import com.bin.mvpproject.ui.model.WeatherModel;
import com.bin.mvpproject.ui.presenter.WeatherPresenter;

public class MainActivity extends AppCompatActivity implements WeaterContract.View {

    private TextView mTextView;
    private WeatherPresenter mWeatherPresenter;

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        initData();
    }


    private void initView() {
        mTextView = (TextView) findViewById(R.id.text);
        mProgressDialog = new ProgressDialog(this);
    }

    private void initData() {
        mWeatherPresenter = new WeatherPresenter();
        mWeatherPresenter.setUpVM(this, new WeatherModel());
        mWeatherPresenter.getWeatherData();
    }

    @Override
    public void setWeatherData(Root data) {
        mTextView.setText(data.toString());
    }

    @Override
    public void showLoadingProgress() {
        mProgressDialog.setMessage("loading...");
    }

    @Override
    public void dismissLoadingProgress() {
        mProgressDialog.dismiss();
    }

    @Override
    public void showError(String str) {

    }
}
