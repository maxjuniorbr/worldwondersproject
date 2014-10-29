package com.ciandt.cursoandroid.worldwondersapp.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ciandt.cursoandroid.worldwondersapp.R;

public class LoginActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Esconder a barra de ação
        ActionBar actionBar = getActionBar();
        actionBar.hide();
    }

    public void buttonSend(View view) {
        EditText editMail = (EditText) findViewById(R.id.editMail);
        EditText editPassword = (EditText) findViewById(R.id.editPassword);
        String mail = editMail.getText().toString();
        String password = editPassword.getText().toString();

        if (mail.isEmpty()) {
            editMail.setError(String.format("%1$s %2$s", getResources().getString(R.string.mail), getResources().getString(R.string.invalid)));
        }

        if (password.isEmpty()) {
            editMail.setError(String.format("%1$s %2$s", getResources().getString(R.string.password), getResources().getString(R.string.invalid)));
        }

        Toast.makeText(this, String.format("%1$s: %2$s\n%3$s: %4$s", getResources().getString(R.string.mail), mail, getResources().getString(R.string.password), password), Toast.LENGTH_SHORT).show();
    }

    public void buttonRegister(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
