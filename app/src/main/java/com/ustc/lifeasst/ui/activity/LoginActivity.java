package com.ustc.lifeasst.ui.activity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaDataSource;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ustc.lifeasst.R;
import com.ustc.lifeasst.global.GlobalConstant;
import com.ustc.lifeasst.utils.PrefUtils;

public class LoginActivity extends Activity implements View.OnClickListener{

    private EditText accountEt;
    private EditText pwdEt;
    private Button subBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        accountEt = (EditText) findViewById(R.id.accountEt);
        pwdEt = (EditText) findViewById(R.id.pwdEt);
        subBtn = (Button) findViewById(R.id.subBtn);

        subBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (accountEt.getText().length() == 0 || pwdEt.getText().length() == 0) {
            Toast.makeText(this,"用户名或密码为空！",Toast.LENGTH_SHORT).show();
        } else {
            if (accountEt.getText().toString().equals(GlobalConstant.DefaultUser) && pwdEt.getText().toString().equals(GlobalConstant.DefaultPassword)) {
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                PrefUtils.setString(this, GlobalConstant.Account,GlobalConstant.DefaultUser);
                PrefUtils.setString(this, GlobalConstant.Password,GlobalConstant.DefaultPassword);
                PrefUtils.setBoolean(this, GlobalConstant.LoginResult, true);
                finish();
            } else {
                Toast.makeText(this,"用户名或密码错误，请重新输入！",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
