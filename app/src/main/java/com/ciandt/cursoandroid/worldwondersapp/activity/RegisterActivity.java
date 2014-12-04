package com.ciandt.cursoandroid.worldwondersapp.activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.ciandt.cursoandroid.worldwondersapp.R;
import com.ciandt.cursoandroid.worldwondersapp.entity.User;
import com.ciandt.cursoandroid.worldwondersapp.infrastructure.Constants;
import com.ciandt.cursoandroid.worldwondersapp.manager.RegisterManager;


public class RegisterActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Recuperar parâmetros enviados da tela login
        if (getIntent().hasExtra("email")) {
            EditText editEmail = (EditText) findViewById(R.id.editEmail);
            String email = getIntent().getStringExtra("email");
            editEmail.setText(email);
        }

        //Esconder a barra de ação
        ActionBar actionBar = getActionBar();
        actionBar.hide();

        setListener();
    }

    private void setListener() {
        Spinner spinCountry = (Spinner) findViewById(R.id.spinnerCountry);
        spinCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                onChangeCountrySpinner();
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void onChangeCountrySpinner() {
        AutoCompleteTextView autoCompleteLanguage = (AutoCompleteTextView) findViewById(R.id.autoCompleteLanguage);
        Spinner spinCoutry = (Spinner) findViewById(R.id.spinnerCountry);
        String selectedCountry = spinCoutry.getSelectedItem().toString();
        String[] countries = new String[0];

        if (selectedCountry.equals(Constants.Entity.User.TEXT_COUNTRY_BRASIL)) {
            countries = getResources().getStringArray(R.array.array_language_br);
        } else if (selectedCountry.equals(Constants.Entity.User.TEXT_COUNTRY_CANADA)) {
            countries = getResources().getStringArray(R.array.array_language_ca);
        } else if (selectedCountry.equals(Constants.Entity.User.TEXT_COUNTRY_CHINA)) {
            countries = getResources().getStringArray(R.array.array_language_ch);
        }

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, countries);
        autoCompleteLanguage.setText("");
        autoCompleteLanguage.setAdapter(adapter);
    }

    public void buttonRegister(View view) {
        // Carrega os componentes
        EditText editName = (EditText) findViewById(R.id.editName);
        EditText editMail = (EditText) findViewById(R.id.editEmail);
        EditText editPassword = (EditText) findViewById(R.id.editPassword);
        EditText editConfirm = (EditText) findViewById(R.id.editConfirm);
        Spinner spinCountry = (Spinner) findViewById(R.id.spinnerCountry);
        EditText editLanguage = (EditText) findViewById(R.id.autoCompleteLanguage);
        RadioGroup radioGroupSex = (RadioGroup) findViewById(R.id.radioGroupSex);
        CheckBox checkNotification = (CheckBox) findViewById(R.id.checkNotification);

        // Carrega os valores dos componentes
        String name = editName.getText().toString();
        String mail = editMail.getText().toString();
        String password = editPassword.getText().toString();
        String confirm = editConfirm.getText().toString();
        //String country = spinCountry.getSelectedItem().toString();
        String language = editLanguage.getText().toString();
        Integer groupSex = radioGroupSex.getCheckedRadioButtonId();

        Boolean fieldError = Boolean.FALSE;

        // Validação dos valores recuperados dos componentes
        if (name.isEmpty()) {
            editName.setError(String.format("%1$s %2$s", getResources().getString(R.string.name), getResources().getString(R.string.required)));
            fieldError = Boolean.TRUE;
        }

        if (mail.isEmpty()) {
            editMail.setError(String.format("%1$s %2$s", getResources().getString(R.string.mail), getResources().getString(R.string.required)));
            fieldError = Boolean.TRUE;
        }

        if (password.isEmpty()) {
            editPassword.setError(String.format("%1$s %2$s", getResources().getString(R.string.password), getResources().getString(R.string.required)));
            fieldError = Boolean.TRUE;
        }

        if (confirm.isEmpty()) {
            editConfirm.setError(String.format("%1$s %2$s", getResources().getString(R.string.confirm), getResources().getString(R.string.required)));
            fieldError = Boolean.TRUE;
        }

        if (!password.equals(confirm)) {
            editPassword.setError(getResources().getString(R.string.passwordDifferentConfirm));
            editConfirm.setError(getResources().getString(R.string.passwordDifferentConfirm));
            fieldError = Boolean.TRUE;
        }

        if (language.isEmpty()) {
            editLanguage.setError(String.format("%1$s %2$s", getResources().getString(R.string.language), getResources().getString(R.string.required)));
            fieldError = Boolean.TRUE;
        }

        if (groupSex == -1) {
            // TODO
            // Change do spinner
            // RadioButton radioFemale = (RadioButton) findViewById(R.id.radioFemale);
            // radioFemale.setError(String.format("%1$s %2$s", getResources().getString(R.string.sex), getResources().getString(R.string.required)));
            Toast.makeText(this, String.format("%1$s %2$s", getResources().getString(R.string.sex), getResources().getString(R.string.required)), Toast.LENGTH_SHORT).show();
            fieldError = Boolean.TRUE;
        }

        if (!fieldError) {
            User user = new User();
            user.setUserName(editName.getText().toString());
            user.setUserEmail(editMail.getText().toString());
            user.setUserPassword(editPassword.getText().toString());
            user.setUserCountry(spinCountry.getSelectedItem().toString());
            user.setUserLanguage(editLanguage.getText().toString());
            user.setUserGender(((RadioButton) findViewById(groupSex)).getText().toString());
            user.setUserNotification(checkNotification.isChecked() ? 1 : 0);

            RegisterManager registerManager = new RegisterManager(this);

            if (registerManager.registerUser(user)) {
                Intent intent = new Intent(this, RegisterActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("email", mail);
                bundle.putString("password", password);
                intent.putExtras(bundle);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        }
    }
}
