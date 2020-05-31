package br.com.consultas;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ListarPacienteActivity extends AppCompatActivity {

    SQLiteDatabase db;
    ListView lvPacientes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_paciente);

        lvPacientes = findViewById(R.id.lvPacientes);

        listarPacientes();

        lvPacientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                View v = lvPacientes.getChildAt(position);
                TextView tvListNome = v.findViewById(R.id.tvListNome);
                TextView tvListgrp_sanguineo = v.findViewById(R.id.tvListgrp_sanguineo);
                TextView tvListLogradouro = v.findViewById(R.id.tvListLogradouro);
                TextView tvListNumero = v.findViewById(R.id.tvListNumero);
                TextView tvListCidade = v.findViewById(R.id.tvListCidade);
                TextView tvListUF = v.findViewById(R.id.tvListUF);
                TextView tvListCelular = v.findViewById(R.id.tvListCelular);
                TextView tvListFixo = v.findViewById(R.id.tvListFixo);

                Intent i = new Intent(getApplicationContext(), EditarMedicoActivity.class);
                i.putExtra("nome", tvListNome.getText().toString());
                i.putExtra("grp_sanguineo", tvListgrp_sanguineo.getText().toString());
                i.putExtra("logradouro", tvListLogradouro.getText().toString());
                i.putExtra("numero", tvListNumero.getText().toString());
                i.putExtra("cidade", tvListCidade.getText().toString());
                i.putExtra("UF", tvListUF.getText().toString());
                i.putExtra("celular", tvListCelular.getText().toString());
                i.putExtra("fixo", tvListFixo.getText().toString());
                startActivity(i);
            }
        });
    }

    private void listarPacientes () {
        db = openOrCreateDatabase("consulta.db", Context.MODE_PRIVATE, null);
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM paciente;");
        Cursor dados = db.rawQuery(sql.toString(), null);
        String[] from = {"_id", "nome", "grp_sanguineo", "logradouro", "numero", "cidade", "UF", "celular", "fixo"};
        int[] to = {R.id.tvListNome, R.id.tvListgrp_sanguineo, R.id.tvListLogradouro, R.id.tvListNumero, R.id.tvListCidade, R.id.tvListUF, R.id.tvListCelular, R.id.tvListFixo};

        SimpleCursorAdapter scAdapter =
                new SimpleCursorAdapter(getApplicationContext(), R.layout.dados, dados, from, to, 0);

        lvPacientes.setAdapter(scAdapter);
        db.close();
    }
}
