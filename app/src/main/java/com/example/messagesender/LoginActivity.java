package com.example.messagesender;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

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


                Log.i("Teste", email);
                Log.i("Teste", mEditSenha.getText().toString());

                if (email == null || email.isEmpty() || mEditSenha.getText().toString() == null || mEditSenha.getText().toString().isEmpty()){
                    Toast.makeText(LoginActivity.this, "Senha e email devem ser preenchidos!", Toast.LENGTH_SHORT).show();
                    return;
                }

                FirebaseAuth.getInstance().signInWithEmailAndPassword(email, mEditSenha.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    abrirTelaPrincipal();
                                    Toast.makeText(LoginActivity.this, "Login Realizado", Toast.LENGTH_SHORT).show();
                                    Log.i("Teste", task.getResult().getUser().getUid());
                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(LoginActivity.this, "Erro,  Usuario/Senha inválidos", Toast.LENGTH_SHORT).show();

                                Log.i("Teste", e.getMessage());
                            }
                        });
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
    private void abrirTelaPrincipal(){
        Intent intent = new Intent(LoginActivity.this, MessagesActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }


}
