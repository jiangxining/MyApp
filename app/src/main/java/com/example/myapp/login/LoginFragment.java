package com.example.myapp.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.myapp.MainActivity;
import com.example.myapp.R;
import com.example.myapp.WAApplication;
import com.example.myapp.base.BaseFragment;
import com.example.myapp.data.entity.User;
import com.example.myapp.view.LoadingPage;
import com.google.android.material.textfield.TextInputLayout;

public class LoginFragment extends BaseFragment implements LoginContract.ILoginView {

    private static final int MODE_LOGIN = 1;
    private static final int MODE_REGISTER =-1;

    private LoginContract.ILoginPresenter mPresenter;


    private TextInputLayout mIetUsername;
    private TextInputLayout mIetPassword;
    private TextInputLayout mIetRePassword;
    private EditText mEtPassword;
    private EditText mEtUserName;
    private EditText mEtRePassword;
    private Button mBtnSubmit;
    private TextView mTvBottomHint;

    private int mCurrentMode = 0;




      @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setPresenter(new LoginPresenter());

    }

    @Override
    public boolean isNeedAnimation() {
          return false;
    }


    @Override
    protected boolean isNeedToAddBackStack() {
        return false;
    }



    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
    }

    private View initView(View v){

        mIetUsername = v.findViewById(R.id.login_ietl_username);
        mIetPassword = v.findViewById(R.id.login_ietl_password);
        mIetRePassword = v.findViewById(R.id.login_ietl_repassword);

        mEtPassword = v.findViewById(R.id.login_et_password);
        mEtUserName = v.findViewById(R.id.login_et_username);
        mEtRePassword = v.findViewById(R.id.login_et_repassword);
        mBtnSubmit = v.findViewById(R.id.login_btn_submit);
        mTvBottomHint = v.findViewById(R.id.login_tv_bottom_hint);



        mTvBottomHint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switchMode(-mCurrentMode);
            }
        });


        mBtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mCurrentMode == MODE_LOGIN){
                    mPresenter.login(getViewText(mEtUserName), getViewText(mEtPassword));
                }else{
                    mPresenter.register(getViewText(mEtUserName), getViewText(mEtPassword),getViewText(mEtRePassword));
                }

            }
        });



        mEtPassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    mPresenter.login(getViewText(mEtUserName), getViewText(mEtPassword));
                    return true;
                }
                return false;
            }
        });


        mEtRePassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_DONE){
                    mPresenter.register(getViewText(mEtUserName), getViewText(mEtPassword),getViewText(mEtRePassword));
                    return true;
                }
                return false;
            }
        });

        switchMode(MODE_LOGIN);


        return v;
    }



    private String getViewText(EditText editText){
          return editText.getText().toString().trim();
    }


    private void switchMode(int mode){
        if(mCurrentMode == mode){
            return;
        }
        if(mode == MODE_LOGIN){
            mIetRePassword.setVisibility(View.GONE);
            mBtnSubmit.setText(R.string.login_text_login);
            mTvBottomHint.setText(R.string.login_text_login_hint);
            mCurrentMode = MODE_LOGIN;
        }else{
            mIetRePassword.setVisibility(View.VISIBLE);
            mBtnSubmit.setText(R.string.login_text_register);
            mTvBottomHint.setText(R.string.login_text_register_hint);
            mCurrentMode = MODE_REGISTER;
        }
    }

    @Override
    public void onSuccess(User user) {
          dismissLoadingPage();
          startActivity(new Intent(getContext(), MainActivity.class));
          WAApplication.mIsLogin = true;
          getActivity().finish();
    }

    @Override
    public void onFail(String msg) {
        showToast(msg);
        WAApplication.mIsLogin = false;
        dismissLoadingPage();



    }

    @Override
    public void setPresenter(LoginContract.ILoginPresenter presenter) {
        mPresenter = presenter;
        mPresenter.attachView(this);
    }

    @Override
    public Context getContextObject() {
        return getContext();
    }
}
