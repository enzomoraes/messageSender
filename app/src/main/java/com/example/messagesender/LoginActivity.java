package com.example.messagesender;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText mEditEmail;
    private EditText mEditSenha;
    private Button mBtnLogin;
    private TextView mTxtAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEditEmail = findViewById(R.id.editEmail);
        mEditSenha = findViewById(R.id.editSenha);
        mBtnLogin = findViewById(R.id.btnLogin);
        mTxtAccount = findViewById(R.id.txtAccount);

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEditEmail.getText().toString();
                String senha = mEditSenha.getText().toString();
                Log.i("Teste", email);
                Log.i("Teste", senha);
            }
        });

        mTxtAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

}
