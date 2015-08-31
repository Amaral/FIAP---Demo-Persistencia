package br.com.felipeamaral.demopersistencia;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import br.com.felipeamaral.demopersistencia.adapter.ListaAdapter;
import br.com.felipeamaral.demopersistencia.dao.PessoaDAO;
import br.com.felipeamaral.demopersistencia.model.Pessoa;


public class MainActivity extends ActionBarActivity {

    private EditText etNome2;
    private EditText etIdade;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNome2 = (EditText) findViewById(R.id.etNome2);
        etIdade = (EditText) findViewById(R.id.etIdade);
        listView = (ListView) findViewById(R.id.lvDados);


    }

    public void salvar(View v) {
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(etNome2.getText().toString());
        pessoa.setIdade(Integer.parseInt(etIdade.getText().toString()));

        PessoaDAO dao = new PessoaDAO(getApplicationContext());
        dao.salvar(pessoa);

        Toast.makeText(this, "Gravado com sucesso", Toast.LENGTH_LONG).show();
        limpar();
        listar(dao.listaAll());
    }

    public void cancelar(View v) {

    }
    public void listar(List<Pessoa> lista) {
        ListaAdapter adapter = new ListaAdapter(this, lista);
        listView.setAdapter(adapter);
    }
    public void limpar() {
        etNome2.setText("");
        etIdade.setText("");
    }
}
