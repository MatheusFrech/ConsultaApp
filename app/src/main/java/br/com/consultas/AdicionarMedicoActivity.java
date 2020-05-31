package br.com.consultas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdicionarMedicoActivity extends AppCompatActivity {

    SQLiteDatabase db;
    EditText etNome;
    EditText etCRM;
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
        etCRM = findViewById(R.id.etCRM);
        etLogradouro = findViewById(R.id.etLogradouro);
        etNumero = findViewById(R.id.etNumero);
        etCidade = findViewById(R.id.etCidade);
        etUF = findViewById(R.id.etUF);
        etCelular = findViewById(R.id.etCelular);
        etFixo = findViewById(R.id.etFixo);

        Button clickAdicionar = findViewById(R.id.btnAddrMedico);
        clickAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarBD();
            }
        });
    }


    private void salvarBD() {
        String nome = etNome.getText().toString().trim();
        String crm = etCRM.getText().toString().trim();
        String logradouro = etLogradouro.getText().toString().trim();
        String numero = etNumero.getText().toString().trim();
        String cidade = etCidade.getText().toString().trim();
        String uf = etUF.getText().toString().trim();
        String celular = etCelular.getText().toString().trim();
        String fixo = etFixo.getText().toString().trim();
        if(nome.equals("")) {
            Toast.makeText(getApplicationContext(), "Por favor, informe o nome!", Toast.LENGTH_LONG).show();
        } else if (crm.equals("")) {
            Toast.makeText(getApplicationContext(), "Por favor, informe o CRM!", Toast.LENGTH_LONG).show();
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
            sql.append("INSERT INTO medico ");
            sql.append("nome = '" + nome + "', ");
            sql.append("CRM = " + crm + ", ");
            sql.append("logradouro = " + logradouro + ", ");
            sql.append("numero = " + numero + ", ");
            sql.append("cidade = " + cidade + ", ");
            sql.append("uf = " + uf + ", ");
            sql.append("celular = " + celular + ", ");
            sql.append("fixo = " + fixo + "; ");


            try {
                db.execSQL(sql.toString());
                Toast.makeText(getApplicationContext(), "Médico atualizado", Toast.LENGTH_LONG).show();
            } catch (SQLException e) {
                Toast.makeText(getApplicationContext(), "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
            }
            Intent i = new Intent(getApplicationContext(), ListarMedicoActivity.class);
            startActivity(i);
            db.close();
        }
    }
}
