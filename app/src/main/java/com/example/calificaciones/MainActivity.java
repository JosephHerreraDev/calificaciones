package com.example.calificaciones;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Database myDb;
    Button btn;
    Float promedio;
    ArrayAdapter adapter;
    List<String> calificacionesConNombre = new ArrayList<String>();
    ListView lv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        myDb = new Database(this);
        btn = findViewById(R.id.btn);
        lv1 = findViewById(R.id.lv1);

        Intent intent = new Intent(MainActivity.this, Agregar.class);

        btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startActivity(intent);
            }
        });

        consultar();
    }

    public void consultar (){
        String nombre = "";
        Float cal1 = 0f;
        Float cal2= 0f;
        Float cal3= 0f;

        Cursor res = myDb.getData();

        if(res.getCount() == 0){
            Toast.makeText(this, "No hay alumnos aun", Toast.LENGTH_SHORT).show();
            return;
        }

        while(res.moveToNext()){
            nombre = res.getString(1);
            cal1 = res.getFloat(2);
            cal2 = res.getFloat(3);
            cal3 = res.getFloat(4);

            promedio = (cal1 + cal2 + cal3)/3;
            calificacionesConNombre.add(nombre + " | " + promedio.toString());
        }



        adapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, calificacionesConNombre);
        lv1.setAdapter(adapter);

        //res.getString(1);
    }
}