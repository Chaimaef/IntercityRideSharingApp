package ca.mcgill.ecse321.intercityridesharingsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import ca.mcgill.ecse321.intercityridesharingsystem.R;

public class passengerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger);
        Button v = findViewById(R.id.journeys);
        final TextView b = findViewById(R.id.jounery1);
//        v.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                HttpUtils.get("userg/sun", new RequestParams(), new JsonHttpResponseHandler(){
//                    @Override
//                    public void onFinish() {
//                        String surl = "http://localhost:8080/userg/sun";
//                        URL url = null;
//                        HttpURLConnection connection  = null;
//                        String content = "", line;
//                        try {
//                            url = new URL(surl);
//                        } catch (MalformedURLException e) {
//                            e.printStackTrace();
//                        }
//                        try {
//                            connection = (HttpURLConnection) url.openConnection();
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        BufferedReader rd = null;
//                        try {
//                            rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                            while ((line = rd.readLine()) != null) {
//                                content += line + "\n";
//                            }
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                        b.setText(content);
//                    }
//
//                });
//    }
//    });
    }
}
