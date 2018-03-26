package com.li.xiaomi.xiaomi_ec.sign;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.AppCompatTextView;
import android.util.Patterns;
import android.view.View;

import com.li.xiaomi.xiaomi_core.delegates.LatteDelegate;
import com.li.xiaomi.xiaomi_core.utils.CheckStringEmptyUtils;
import com.li.xiaomi.xiaomi_core.utils.LogUtils;
import com.li.xiaomi.xiaomi_ec.R;

/**
 * 作者：dell or Xiaomi Li
 * 时间： 2018/3/26
 * 内容：
 * 最后修改：
 */

public class SignUpDelegate extends LatteDelegate implements View.OnClickListener {
    final String TAG = "SignUpDelegate";
    AppCompatEditText mNameEd;
    AppCompatEditText mPhoneEd;
    AppCompatEditText mEmailEd;
    AppCompatEditText mPasswordEd;
    AppCompatEditText mRePasswordEd;

    AppCompatButton mSignBtu;
    AppCompatTextView mLinkTv;

    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }

    @Override
    public void onBingView(@Nullable Bundle savedInstanceState, View rootView) {
        initView(rootView);
    }

    private void initView(View rootView) {
        mNameEd = rootView.findViewById(R.id.ed_signup_name);
        mPhoneEd = rootView.findViewById(R.id.ed_signup_phone);
        mEmailEd = rootView.findViewById(R.id.ed_signup_email);
        mPasswordEd = rootView.findViewById(R.id.ed_signup_password);
        mRePasswordEd = rootView.findViewById(R.id.ed_signup_re_password);
        mSignBtu = rootView.findViewById(R.id.but_sign_up);
        mLinkTv = rootView.findViewById(R.id.tv_sign_in_link);

        mSignBtu.setOnClickListener(this);
        mLinkTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == mSignBtu.getId()) {
            if (check()) {
                LogUtils.Loge(TAG, "注册按钮");
            }
        } else if (v.getId() == mLinkTv.getId()) {
            LogUtils.Loge(TAG, "去登录");
            startWithPop(new SignInDelegate());//去登录页面
        }

    }


    private boolean check() {
        final String name = mNameEd.getText().toString().trim();
        final String phone = mPhoneEd.getText().toString().trim();
        final String email = mEmailEd.getText().toString().trim();
        final String password = mPasswordEd.getText().toString().trim();
        final String rePassword = mRePasswordEd.getText().toString().trim();
        boolean flag = true;
        if (CheckStringEmptyUtils.IsEmpty(name)) {
            flag = false;
            mNameEd.setError("名称不能为空");
        } else {
            mNameEd.setError(null);
        }

        if (phone.length() != 11) {
            flag = false;
            mPhoneEd.setError("请输入11位电话号码");
        } else {
            mPhoneEd.setError(null);
        }

        if (CheckStringEmptyUtils.IsEmpty(email) || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            flag = false;
            mEmailEd.setError("邮箱格式不正确");
        } else {
            mEmailEd.setError(null);
        }

        if (password.length() < 6) {
            flag = false;
            mPasswordEd.setError("请输入至少6位密码");
        } else {
            mPasswordEd.setError(null);
        }

        if (CheckStringEmptyUtils.IsEmpty(rePassword)||!rePassword.equals(password)) {
            flag = false;
            mRePasswordEd.setError("请输入至少6位密码");
        } else {
            mRePasswordEd.setError(null);
        }
        return flag;
    }


}
