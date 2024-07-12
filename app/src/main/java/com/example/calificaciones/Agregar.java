package com.example.calificaciones;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Agregar extends AppCompatActivity {

    Database myDb;
    EditText etNombre;
    EditText etCal1;
    EditText etCal2;
    EditText etCal3;
    Button btnOrganizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_agregar);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        myDb = new Database(this);
        etNombre = findViewById(R.id.etNombre);
        etCal1 = findViewById(R.id.etCal1);
        etCal2 = findViewById(R.id.etCal2);
        etCal3 = findViewById(R.id.etCal3);
        btnOrganizar = findViewById(R.id.btnOrganizar);

        //myDb.deleteData("1");

        Intent intent = new Intent(Agregar.this, ordenado.class);

        btnOrganizar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(intent);
            }
        });
    }

    public void agregar(View view){
        boolean seInserto = myDb.insertData(etNombre.getText().toString(), Double.parseDouble(etCal1.getText().toString()),
                Double.parseDouble(etCal2.getText().toString()),Double.parseDouble(etCal3.getText().toString()));
        if(seInserto){
            Toast.makeText(this, "Se inserto", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "No se inserto", Toast.LENGTH_SHORT).show();
        }

    }
}