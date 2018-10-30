package ca.mcgill.ecse321.intercityridesharingsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //buttons on the first entry user interface
        Button create = findViewById(R.id.buttonCreate);
        Button modify = findViewById(R.id.buttonModify);
        Button close = findViewById(R.id.buttonClose);
        //click listener for buttons
        create.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, createJourneyActivity.class);
                startActivity(intent);
            }
        });
        modify.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, modifyJourneyActivity.class);
                startActivity(intent);
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, closeJourneyActivity.class);
                startActivity(intent);
            }
        });
    }

}
