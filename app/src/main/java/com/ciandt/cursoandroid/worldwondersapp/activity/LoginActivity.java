package com.ciandt.cursoandroid.worldwondersapp.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.ciandt.cursoandroid.worldwondersapp.R;
import com.ciandt.cursoandroid.worldwondersapp.infrastructure.Constants;
import com.ciandt.cursoandroid.worldwondersapp.manager.LoginManager;

public class LoginActivity extends Activity {

    // Autentica o usuário
    private void loginUser(String userEmail, String userPassword) {
        LoginManager loginManager = new LoginManager(this);

        if (loginManager.loginUser(userEmail, userPassword) != null) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, String.format("%1$s %2$s", getResources().getString(R.string.userOrPassword), getResources().getString(R.string.invalid)), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Esconde a barra de ação desta tela
        ActionBar actionBar = getActionBar();
        actionBar.hide();
    }

    public void buttonSend(View view) {
        // Carrega os componentes
        EditText editEmail = (EditText) findViewById(R.id.editEmail);
        EditText editPassword = (EditText) findViewById(R.id.editPassword);

        // Carrega os valores dos componentes
        String email = editEmail.getText().toString();
        String password = editPassword.getText().toString();

        Boolean fieldError = Boolean.FALSE;

        // Validação dos valores recuperados dos componentes
        if (email.isEmpty()) {
            editEmail.setError(String.format("%1$s %2$s", getResources().getString(R.string.mail), getResources().getString(R.string.invalid)));
            fieldError = Boolean.TRUE;
        }

        if (password.isEmpty()) {
            editPassword.setError(String.format("%1$s %2$s", getResources().getString(R.string.password), getResources().getString(R.string.invalid)));
            fieldError = Boolean.TRUE;
        }

        if (!fieldError) {
            loginUser(email, password);
        }
    }

    public void buttonRegister(View view) {
        // Carrega os componentes
        EditText editEmail = (EditText) findViewById(R.id.editEmail);
        String email = editEmail.getText().toString();

        // Instância do intent para chamada a outra activity
        Intent intent = new Intent(this, RegisterActivity.class);

        // Caso o email não seja nulo envio o mesmo para a próxima activity
        if (email != null) {
            Bundle bundle = new Bundle();
            bundle.putString("email", email);
            intent.putExtras(bundle);
        }

        // Chama a nova activity passando a constante que representa a activity login
        startActivityForResult(intent, Constants.RequestCode.LOGIN_ACTIVITY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Caso a chamada a esta activity tenha sucesso no cadastro
        // autentica o usuário e direciona a activity main
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == Constants.RequestCode.LOGIN_ACTIVITY) {
                if (data != null) {
                    String email = data.getStringExtra("email");
                    String password = data.getStringExtra("password");
                    loginUser(email, password);
                }
            }
            // Caso o usuário pressione o back mostra o toast cancelado
        } else if (resultCode == Activity.RESULT_CANCELED) {
            Toast.makeText(this, getResources().getString(R.string.cancel), Toast.LENGTH_SHORT).show();
        }
    }
}
