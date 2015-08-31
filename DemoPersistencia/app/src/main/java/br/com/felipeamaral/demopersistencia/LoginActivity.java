package br.com.felipeamaral.demopersistencia;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;


public class LoginActivity extends ActionBarActivity {

    private final String PREFERENCE = "DemoPersistencia";
    private final String PREF_NOME = "nome";
    private final String PREF_PASS = "senha";
    private final String PREF_MANTER_CONECTADO = "manterconectado";

    private EditText etNome;
    private EditText etSenha;
    private CheckBox cbManterConectado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etNome = (EditText) findViewById(R.id.etNome);
        etSenha = (EditText) findViewById(R.id.etSenha);
        cbManterConectado = (CheckBox) findViewById(R.id.cbManterConectado);

        SharedPreferences configLogin = getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
        etNome.setText(configLogin.getString(PREF_NOME, ""));
        etSenha.setText(configLogin.getString(PREF_PASS, ""));

        if(configLogin.getBoolean(PREF_MANTER_CONECTADO, false)) {
            if(isUsuarioValido(etNome.getText().toString(),etSenha.getText().toString())) {
                abrirTela();
            }

        }

    }

    public void connectar(View v) {
        if(isUsuarioValido(etNome.getText().toString(),etSenha.getText().toString())) {
            if(cbManterConectado.isChecked()) {
                SharedPreferences configLogin = getSharedPreferences(PREFERENCE, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = configLogin.edit();
                editor.putString(PREF_NOME,etNome.getText().toString());
                editor.putString(PREF_PASS,etSenha.getText().toString());
                editor.putBoolean(PREF_MANTER_CONECTADO,cbManterConectado.isChecked());
                editor.commit();
            }
            abrirTela();
        }else {
            Toast.makeText(this, getString(R.string.msg_usuario_invalido), Toast.LENGTH_LONG).show();
        }
    }

    private void abrirTela() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

    private boolean isUsuarioValido(String name, String senha) {
        if(name.equals("fiap") && senha.equals("123")) {
            return true;
        }
        return false;
    }

}
