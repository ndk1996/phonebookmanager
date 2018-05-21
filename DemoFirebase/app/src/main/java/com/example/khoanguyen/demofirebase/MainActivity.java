package com.example.khoanguyen.demofirebase;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnInsert;
    private Button btnShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MapView();
        AddButtonListener();


//        FirebaseDatabase db = FirebaseDatabase.getInstance();
//        DatabaseReference dbRef = db.getReference("messages");
//
//        dbRef.setValue("Hello KHOANGUYEN");




    }

    private void MapView(){
        btnInsert = findViewById(R.id.btnInsert);
        btnShow = findViewById(R.id.btnShow);
    }

    private void AddButtonListener(){
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,ShowDataActivity.class );
                startActivity(intent);
            }
        });

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,InsertDataActivity.class );
                startActivity(intent);
            }
        });


    }






}
