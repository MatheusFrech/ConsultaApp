package br.com.consultas;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AdicionarConsultaActivity extends AppCompatActivity {

    SQLiteDatabase db;
    EditText etPaciente_id;
    EditText etMedico_Id;
    EditText etdata_hora_inicio;
    EditText etdata_hora_fim;
    EditText etObservacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_consulta);

        etPaciente_id = findViewById(R.id.etPaciente_id);
        etMedico_Id = findViewById(R.id.etMedico_id);
        etdata_hora_inicio = findViewById(R.id.etdata_hora_inicio);
        etdata_hora_fim = findViewById(R.id.etdata_hora_fim);
        etObservacao = findViewById(R.id.etObservacao);




        Button clickAdicionar = findViewById(R.id.btnAddConsulta);
        clickAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarBD();
            }
        });
    }

    private void salvarBD () {
        String paciente_id = etPaciente_id.getText().toString().trim();
        String medico_id = etMedico_Id.getText().toString().trim();
        String data_hora_inicio = etdata_hora_inicio.getText().toString().trim();
        String data_hora_fim = etdata_hora_fim.getText().toString().trim();
        String observacao = etObservacao.getText().toString().trim();
        if(paciente_id.equals("")) {
            Toast.makeText(getApplicationContext(), "Por favor, informe o id do paciente!", Toast.LENGTH_LONG).show();
        } else if (medico_id.equals("")) {
            Toast.makeText(getApplicationContext(), "Por favor, informe o id do médico!", Toast.LENGTH_LONG).show();
        } else if (data_hora_inicio.equals("")) {
            Toast.makeText(getApplicationContext(), "Por favor, informe a hora de inicio!", Toast.LENGTH_LONG).show();
        } else if (data_hora_fim.equals("")) {
            Toast.makeText(getApplicationContext(), "Por favor, informe a hora de término!", Toast.LENGTH_LONG).show();
        } else if (observacao.equals("")) {
            Toast.makeText(getApplicationContext(), "Por favor, informe a observação!", Toast.LENGTH_LONG).show();
        }else {
            db = openOrCreateDatabase("consulta.db", Context.MODE_PRIVATE, null);
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO consulta(paciente_id, medico_id,data_hora_inicio, data_hora_fim, observacao) VALUES (");
            sql.append("'" + paciente_id + "', ");
            sql.append(medico_id + ", ");
            sql.append("'" + data_hora_inicio + "'");
            sql.append("'" + data_hora_fim + "'");
            sql.append("'" + observacao + "'");
            sql.append(");");

            try {
                db.execSQL(sql.toString());
                Toast.makeText(getApplicationContext(), "Consulta inserida", Toast.LENGTH_LONG).show();
            } catch (SQLException e) {
                Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
            etPaciente_id.setText("");
            etMedico_Id.setText("");
            etdata_hora_inicio.setText("");
            etdata_hora_fim.setText("");
            etObservacao.setText("");
            db.close();

        }
    }
}
