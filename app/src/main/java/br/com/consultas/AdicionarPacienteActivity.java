package br.com.consultas;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AdicionarPacienteActivity extends AppCompatActivity {

    SQLiteDatabase db;
    EditText etNome;
    EditText etgrp_sanguineo;
    EditText etLogradouro;
    EditText etNumero;
    EditText etCidade;
    EditText etUF;
    EditText etCelular;
    EditText etFixo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_medico);

        etNome = findViewById(R.id.etNome);
        etgrp_sanguineo = findViewById(R.id.etgrp_sanguineo);
        etLogradouro = findViewById(R.id.etLogradouro);
        etNumero = findViewById(R.id.etNumero);
        etCidade = findViewById(R.id.etCidade);
        etUF = findViewById(R.id.etUF);
        etCelular = findViewById(R.id.etCelular);
        etFixo = findViewById(R.id.etFixo);



        Button clickAdicionar = findViewById(R.id.btnAddPaciente);
        clickAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarBD();
            }
        });
    }

    private void salvarBD () {
        String nome = etNome.getText().toString().trim();
        String grp_sanguineo = etgrp_sanguineo.getText().toString().trim();
        String logradouro = etLogradouro.getText().toString().trim();
        String numero = etNumero.getText().toString().trim();
        String Cidade = etCidade.getText().toString().trim();
        String UF = etUF.getText().toString().trim();
        String Celular = etCelular.getText().toString().trim();
        String Fixo = etFixo.getText().toString().trim();
        if(nome.equals("")) {
            Toast.makeText(getApplicationContext(), "Por favor, informe o nome!", Toast.LENGTH_LONG).show();
        } else if (grp_sanguineo.equals("")) {
            Toast.makeText(getApplicationContext(), "Por favor, informe a grupo sanguineo!", Toast.LENGTH_LONG).show();
        } else if (logradouro.equals("")) {
            Toast.makeText(getApplicationContext(), "Por favor, informe o logradouro!", Toast.LENGTH_LONG).show();
        } else if (numero.equals("")) {
            Toast.makeText(getApplicationContext(), "Por favor, informe o número!", Toast.LENGTH_LONG).show();
        } else if (Cidade.equals("")) {
            Toast.makeText(getApplicationContext(), "Por favor, informe a cidade!", Toast.LENGTH_LONG).show();
        }else if (UF.equals("")) {
            Toast.makeText(getApplicationContext(), "Por favor, informe a UF!", Toast.LENGTH_LONG).show();
        } else if (Celular.equals("")) {
            Toast.makeText(getApplicationContext(), "Por favor, informe o celular!", Toast.LENGTH_LONG).show();
        } else if (Fixo.equals("")) {
            Toast.makeText(getApplicationContext(), "Por favor, informe o fixo!", Toast.LENGTH_LONG).show();
        }else {
            db = openOrCreateDatabase("escolas.db", Context.MODE_PRIVATE, null);
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO paciente(nome, grp_sanguineo, logradouro, numero, cidade, UF, celular, fixo) VALUES (");
            sql.append("'" + nome + "', ");
            sql.append("'" + grp_sanguineo + "', ");
            sql.append("'" + logradouro + "',");
            sql.append("'" + numero + "',");
            sql.append("'" + Cidade + "',");
            sql.append("'" + UF + "',");
            sql.append("'" + Celular + "',");
            sql.append("'" + Fixo + "'");
            sql.append(");");

            try {
                db.execSQL(sql.toString());
                Toast.makeText(getApplicationContext(), "Aluno inserido", Toast.LENGTH_LONG).show();
            } catch (SQLException e) {
                Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
            etNome.setText("");
            etgrp_sanguineo.setText("");
            etLogradouro.setText("");
            etNumero.setText("");
            etCidade.setText("");
            etUF.setText("");
            etCelular.setText("");
            etFixo.setText("");
            db.close();

        }
    }
}
