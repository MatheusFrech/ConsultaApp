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

public class EditarPacienteActivity extends AppCompatActivity {

    SQLiteDatabase db;
    EditText etNome;
    EditText etGrp_sanguineo;
    EditText etLogradouro;
    EditText etNumero;
    EditText etCidade;
    EditText etUF;
    EditText etCelular;
    EditText etFixo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_medico);

        etNome = findViewById(R.id.etPaciente_id);
        etGrp_sanguineo = findViewById(R.id.etMedico_id);
        etLogradouro = findViewById(R.id.etdata_hora_inicio);
        etNumero = findViewById(R.id.etdata_hora_fim);
        etCidade = findViewById(R.id.etObservacao);
        etUF = findViewById(R.id.etUF);
        etCelular = findViewById(R.id.etCelular);
        etFixo = findViewById(R.id.etFixo);




        Intent valores = getIntent();
        etNome.setText(valores.getStringExtra("nome"));
        etGrp_sanguineo.setText(valores.getStringExtra("grp_sanguineo"));
        etLogradouro.setText(valores.getStringExtra("logradouro"));
        etNumero.setText(valores.getStringExtra("numero"));
        etCidade.setText(valores.getStringExtra("cidade"));
        etUF.setText(valores.getStringExtra("UF"));
        etCelular.setText(valores.getStringExtra("celular"));
        etFixo.setText(valores.getStringExtra("fixo"));




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
        String nome = etNome.getText().toString().trim();
        String grp_sanguineo = etGrp_sanguineo.getText().toString().trim();
        String logradouro = etLogradouro.getText().toString().trim();
        String numero = etNumero.getText().toString().trim();
        String cidade = etCidade.getText().toString().trim();
        String uf = etUF.getText().toString().trim();
        String celular = etCelular.getText().toString().trim();
        String fixo = etFixo.getText().toString().trim();
        if(nome.equals("")) {
            Toast.makeText(getApplicationContext(), "Por favor, informe o nome!", Toast.LENGTH_LONG).show();
        } else if (grp_sanguineo.equals("")) {
            Toast.makeText(getApplicationContext(), "Por favor, informe o grupo sanguineo!", Toast.LENGTH_LONG).show();
        }
        else if (logradouro.equals("")) {
            Toast.makeText(getApplicationContext(), "Por favor, informe o logradouro!", Toast.LENGTH_LONG).show();
        }
        else if (numero.equals("")) {
            Toast.makeText(getApplicationContext(), "Por favor, informe o numero!", Toast.LENGTH_LONG).show();
        }
        else if (cidade.equals("")) {
            Toast.makeText(getApplicationContext(), "Por favor, informe a cidade!", Toast.LENGTH_LONG).show();
        }
        else if (uf.equals("")) {
            Toast.makeText(getApplicationContext(), "Por favor, informe a uf!", Toast.LENGTH_LONG).show();
        }
        else if (celular.equals("")) {
            Toast.makeText(getApplicationContext(), "Por favor, informe o celular!", Toast.LENGTH_LONG).show();
        }
        else if (fixo.equals("")) {
            Toast.makeText(getApplicationContext(), "Por favor, informe o fixo!", Toast.LENGTH_LONG).show();
        }
        else {
            db = openOrCreateDatabase("consulta.db", Context.MODE_PRIVATE, null);
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE paciente SET ");
            sql.append("nome = '" + nome + "', ");
            sql.append("grp_sanguineo = " + grp_sanguineo + ", ");
            sql.append("logradouro = " + logradouro + ", ");
            sql.append("numero = " + numero + ", ");
            sql.append("cidade = " + cidade + ", ");
            sql.append("uf = " + uf + ", ");
            sql.append("celular = " + celular + ", ");
            sql.append("fixo = " + fixo + ", ");
            sql.append("WHERE _id = " + id + ";");

            try {
                db.execSQL(sql.toString());
                Toast.makeText(getApplicationContext(), "Paciente atualizado", Toast.LENGTH_LONG).show();
            } catch (SQLException e) {
                Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
            Intent i = new Intent(getApplicationContext(), ListarMedicoActivity.class);
            startActivity(i);
            db.close();
        }
    }

    private void excluirBD(String id) {
        db = openOrCreateDatabase("paciente.db", Context.MODE_PRIVATE, null);
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM paciente ");
        sql.append("WHERE _id = " + id + ";");
        try {
            db.execSQL(sql.toString());
            Toast.makeText(getApplicationContext(), "Paciente excluído", Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
        Intent i = new Intent(getApplicationContext(), ListarMedicoActivity.class);
        startActivity(i);
        db.close();
    }
}
