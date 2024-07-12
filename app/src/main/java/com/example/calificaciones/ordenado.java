package com.example.calificaciones;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class ordenado extends AppCompatActivity {

    Database myDb;
    ArrayAdapter adapterAprobados;
    ArrayAdapter adapterReprobados;
    List<String> aprobados = new ArrayList<String>();
    List<String> reprobados = new ArrayList<String>();
    ListView lvAprobado;
    ListView lvReprobado;
    Float promedio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ordenado);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        myDb = new Database(this);
        lvAprobado = findViewById(R.id.lvAprobado);
        lvReprobado = findViewById(R.id.lvReprobado);

        String nombre = "";

        Cursor res = myDb.getData();

        if(res.getCount() == 0){
            Toast.makeText(this, "No hay alumnos aun", Toast.LENGTH_SHORT).show();
            return;
        }

        while(res.moveToNext()){
            nombre = res.getString(1);

            promedio = res.getFloat(5);
            if(promedio >= 7){
                aprobados.add(nombre);
            }
            else{
                reprobados.add(nombre);
            }

        }

        adapterAprobados = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                aprobados);
        lvAprobado.setAdapter(adapterAprobados);

        adapterReprobados = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                reprobados);

        lvReprobado.setAdapter(adapterReprobados);
        lvAprobado.setAdapter(adapterAprobados);

    }
}