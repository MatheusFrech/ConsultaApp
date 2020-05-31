package br.com.consultas;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        criarBD();
    }
    public void abreAddMedico(View v){
        Intent i = new Intent(MainActivity.this, AdicionarMedicoActivity.class);
        startActivity(i);
    };
    public void abreaddPaciente(View v){
        Intent i = new Intent(MainActivity.this, AdicionarPacienteActivity.class);
        startActivity(i);
    };
    public void abreAddConsulta(View v){
        Intent i = new Intent(MainActivity.this, AdicionarConsultaActivity.class);
        startActivity(i);
    };
    public void abreListarConsulta(View v){
        Intent i = new Intent(MainActivity.this, ListarConsultaActivity.class);
        startActivity(i);
    };
    public void abreListaMedico(View v){
        Intent i = new Intent(MainActivity.this, ListarMedicoActivity.class);
        startActivity(i);
    };
    public void abreListaPaciente(View v){
        Intent i = new Intent(MainActivity.this, ListarPacienteActivity.class);
        startActivity(i);
    };



    private void criarBD () {
        db = openOrCreateDatabase("consulta.db", Context.MODE_PRIVATE, null);
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE IF NOT EXISTS medico(");
        sql.append("_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, ");
        sql.append("nome VARCHAR(50), ");
        sql.append("crm VARCHAR(20), ");
        sql.append("logradouro VARCHAR(100), ");
        sql.append("numero MEDIUMINT(8), ");
        sql.append("cidade VARCHAR(30), ");
        sql.append("uf VARCHAR(2), ");
        sql.append("celular VARCHAR(20), ");
        sql.append("fixo VARCHAR(20)");
        sql.append(");");

        sql.append("CREATE TABLE IF NOT EXISTS paciente (");
        sql.append("_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, ");
        sql.append("nome VARCHAR(50), ");
        sql.append("grp_sanguineoTINYINT(1), ");
        sql.append("logradouro VARCHAR(100), ");
        sql.append("numero MEDIUMINT(8), ");
        sql.append("cidade VARCHAR(30), ");
        sql.append("uf VARCHAR(2), ");
        sql.append("celular VARCHAR(20), ");
        sql.append("fixo VARCHAR(20)");
        sql.append(");");

        sql.append("CREATE TABLE IF NOT EXISTS consulta (");
        sql.append("_id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, ");
        sql.append("paciente_idINTEGER NOT NULL, ");
        sql.append("medico_idINTEGERNOT NULL, ");
        sql.append("data_hora_inicioDATETIME, ");
        sql.append("data_hora_fimDATETIME, ");
        sql.append("observacaoVARCHAR(200), ");
        sql.append("FOREIGN KEY(paciente_id) REFERENCES paciente(id), ");
        sql.append("FOREIGN KEY(medico_id) REFERENCES medico(id)");
        sql.append(");");
        try {
            db.execSQL(sql.toString());
        } catch (SQLException e) {
            Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
        db.close();
    }
}
