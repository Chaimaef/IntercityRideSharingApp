package ca.mcgill.ecse321.intercityridesharingsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //buttons on the first entry user interface
        Button admin = findViewById(R.id.buttonAdmin);
        Button driver = findViewById(R.id.buttonDriver);
        Button passenger = findViewById(R.id.buttonPassenger);
        //click listener for buttons
        admin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setContentView(R.layout.activity_admin);
            }
        });
        driver.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, driverActivity.class);
                startActivity(intent);
            }
        });
        passenger.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, passengerActivity.class);
                startActivity(intent);
            }
        });
    }

}
