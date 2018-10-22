package ca.mcgill.ecse321.intercityridesharingsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;

import ca.mcgill.ecse321.intercityridesharingsystem.R;

public class passengerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger);
        TextView journey= findViewById(R.id.jounery1);
    }
}
