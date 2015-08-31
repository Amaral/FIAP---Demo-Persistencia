package br.com.felipeamaral.demopersistencia.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.felipeamaral.demopersistencia.model.Pessoa;

public class ListaAdapter extends BaseAdapter {

    private Context context;
    private List<Pessoa> pessoas;

    public ListaAdapter(Context context, List<Pessoa> pessoas) {
        this.context = context;
        this.pessoas = pessoas;
    }

    @Override
    public int getCount() {
        return pessoas.size();
    }

    @Override
    public Object getItem(int position) {
        return pessoas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null)  {
            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_1, parent, false);
        }

        TextView tvName = (TextView) convertView.findViewById(android.R.id.text1);
        tvName.setText(pessoas.get(position).getNome());

        return convertView;
    }
}
