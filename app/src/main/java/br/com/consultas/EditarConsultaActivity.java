package br.com.consultas;

import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditarConsultaActivity extends AppCompatActivity {

    SQLiteDatabase db;
    EditText etPaciente_id;
    EditText etMedico_id;
    EditText etdata_hora_inicio;
    EditText etdata_hora_fim;
    EditText etObservacao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_consulta);

        etPaciente_id = findViewById(R.id.etPaciente_id);
        etMedico_id = findViewById(R.id.etMedico_id);
        etdata_hora_inicio = findViewById(R.id.etdata_hora_inicio);
        etdata_hora_fim = findViewById(R.id.etdata_hora_fim);
        etObservacao = findViewById(R.id.etObservacao);





        Intent valores = getIntent();
        etPaciente_id.setText(valores.getStringExtra("paciente_id"));
        etMedico_id.setText(valores.getStringExtra("medico_id"));
        etdata_hora_inicio.setText(valores.getStringExtra("data_hora_inicio"));
        etdata_hora_fim.setText(valores.getStringExtra("data_hora_fim"));
        etObservacao.setText(valores.getStringExtra("observacao"));





        final String id = valores.getStringExtra("id");

        Button clickEditar = findViewById(R.id.btnEditar);
        clickEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarBD(id);
            }
        });

        Button clickExcluir = findViewById(R.id.btnExcluir);
        clickExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                excluirBD(id);
            }
        });

    }

    private void salvarBD(String id) {
        String paciente_id = etPaciente_id.getText().toString().trim();
        String medico_id = etMedico_id.getText().toString().trim();
        String data_hora_inicio = etdata_hora_inicio.getText().toString().trim();
        String data_hora_fim = etdata_hora_fim.getText().toString().trim();
        String observacao = etObservacao.getText().toString().trim();

        if(paciente_id.equals("")) {
            Toast.makeText(getApplicationContext(), "Por favor, informe o id do paciente!", Toast.LENGTH_LONG).show();
        } else if (medico_id.equals("")) {
            Toast.makeText(getApplicationContext(), "Por favor, informe id do médico!", Toast.LENGTH_LONG).show();
        }
        else if (data_hora_inicio.equals("")) {
            Toast.makeText(getApplicationContext(), "Por favor, informe a data e hora de inicio!", Toast.LENGTH_LONG).show();
        }
        else if (data_hora_fim.equals("")) {
            Toast.makeText(getApplicationContext(), "Por favor, informe a data e hora de termino", Toast.LENGTH_LONG).show();
        }
        else if (observacao.equals("")) {
            Toast.makeText(getApplicationContext(), "Por favor, informe a observação!", Toast.LENGTH_LONG).show();
        }
        else {
            db = openOrCreateDatabase("consulta.db", Context.MODE_PRIVATE, null);
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE consulta SET ");
            sql.append("paciente_id = '" + paciente_id + "', ");
            sql.append("medico_id = " + medico_id + ", ");
            sql.append("data_hora_inicio = " + data_hora_inicio + ", ");
            sql.append("data_hora_fim = " + data_hora_fim + ", ");
            sql.append("observacao = " + observacao + ", ");
            sql.append("WHERE _id = " + id + ";");

            try {
                db.execSQL(sql.toString());
                Toast.makeText(getApplicationContext(), "Consulta atualizada", Toast.LENGTH_LONG).show();
            } catch (SQLException e) {
                Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
            Intent i = new Intent(getApplicationContext(), ListarMedicoActivity.class);
            startActivity(i);
            db.close();
        }
    }

    private void excluirBD(String id) {
        db = openOrCreateDatabase("consulta.db", Context.MODE_PRIVATE, null);
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM medico ");
        sql.append("WHERE _id = " + id + ";");
        try {
            db.execSQL(sql.toString());
            Toast.makeText(getApplicationContext(), "Médico excluído", Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
        Intent i = new Intent(getApplicationContext(), ListarMedicoActivity.class);
        startActivity(i);
        db.close();
    }
}
