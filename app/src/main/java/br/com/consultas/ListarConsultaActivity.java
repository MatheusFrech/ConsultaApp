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

public class ListarConsultaActivity extends AppCompatActivity {

    SQLiteDatabase db;
    ListView lvConsulta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_consulta);

        lvConsulta = findViewById(R.id.lvConsulta);

        listarConsulta();

        lvConsulta.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                View v = lvConsulta.getChildAt(position);
                TextView tvListPaciente_id = v.findViewById(R.id.tvListPaciente_id);
                TextView tvListMedico_id = v.findViewById(R.id.tvListMedico_id);
                TextView tvListdata_hora_inicio = v.findViewById(R.id.tvListdata_hora_inicio);
                TextView tvListdata_hora_fim = v.findViewById(R.id.tvListdata_hora_fim);
                TextView tvListObservacao = v.findViewById(R.id.tvListObservacao);


                Intent i = new Intent(getApplicationContext(), EditarMedicoActivity.class);
                i.putExtra("paciente_id", tvListPaciente_id.getText().toString());
                i.putExtra("medico_id", tvListMedico_id.getText().toString());
                i.putExtra("data_hora_inicio", tvListdata_hora_inicio.getText().toString());
                i.putExtra("data_hora_fim", tvListdata_hora_fim.getText().toString());
                i.putExtra("observacao", tvListObservacao.getText().toString());
                startActivity(i);
            }
        });
    }

    private void listarConsulta () {
        db = openOrCreateDatabase("consulta.db", Context.MODE_PRIVATE, null);
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM consulta;");
        Cursor dados = db.rawQuery(sql.toString(), null);
        String[] from = {"paciente_id", "medico_id", "data_hora_inicio", "data_hora_fim", "observacao"};
        int[] to = {R.id.tvListPaciente_id, R.id.tvListMedico_id, R.id.tvListdata_hora_inicio, R.id.tvListdata_hora_fim, R.id.tvListObservacao};

        SimpleCursorAdapter scAdapter =
                new SimpleCursorAdapter(getApplicationContext(), R.layout.dados, dados, from, to, 0);

        lvConsulta.setAdapter(scAdapter);
        db.close();
    }
}
