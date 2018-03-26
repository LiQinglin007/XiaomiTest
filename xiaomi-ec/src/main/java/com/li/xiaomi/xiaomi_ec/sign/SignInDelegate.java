package com.li.xiaomi.xiaomi_ec.sign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.li.xiaomi.xiaomi_core.delegates.LatteDelegate;
import com.li.xiaomi.xiaomi_core.utils.LogUtils;
import com.li.xiaomi.xiaomi_ec.R;

/**
 * 作者：dell or Xiaomi Li
 * 时间： 2018/3/26
 * 内容：
 * 最后修改：
 */

public class SignInDelegate extends LatteDelegate implements View.OnClickListener {
    private final String TAG = "SignInDelegate";
    AppCompatButton mSignIn;
    AppCompatEditText mPhoneEd;
    AppCompatEditText mPasswordEd;
    AppCompatTextView mSignUpLinkTv;

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBingView(@Nullable Bundle savedInstanceState, View rootView) {
        initView(rootView);
    }

    private void initView(View rootView) {
        mSignIn = rootView.findViewById(R.id.but_sign_in);
        mSignUpLinkTv = rootView.findViewById(R.id.tv_sign_up_link);
        mPhoneEd = rootView.findViewById(R.id.ed_signin_phone);
        mPasswordEd = rootView.findViewById(R.id.ed_signin_password);

        mSignIn.setOnClickListener(this);
        mSignUpLinkTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mSignIn) {
            if (check()) {
                LogUtils.Loge(TAG, "去登录");
            }
        } else if (v == mSignUpLinkTv) {
            startWithPop(new SignUpDelegate());
        }
    }


    private boolean check() {
        boolean flag = true;
        final String phone = mPhoneEd.getText().toString().trim();
        final String password = mPasswordEd.getText().toString().trim();

        if (phone.length() != 11) {
            mPhoneEd.setError("请输入11位手机号码");
            flag = false;
        } else {
            mPhoneEd.setError(null);
        }

        if (password.length() < 6) {
            mPasswordEd.setError("请输入至少6位密码");
            flag = false;
        } else {
            mPasswordEd.setError(null);
        }
        return flag;
    }
}
