package ca.mcgill.ecse321.intercityridesharingsystem;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import ca.mcgill.ecse321.intercityridesharingsystem.R;
import cz.msebera.android.httpclient.Header;

public class passengerActivity extends AppCompatActivity {
    private String error = null;
    //private Context context = this;
    private void refreshErrorMessage() {
        // set the error message
        TextView tvError = findViewById(R.id.error);
        tvError.setText(error);

        if (error == null || error.length() == 0) {
            tvError.setVisibility(View.GONE);
        } else {
            tvError.setVisibility(View.VISIBLE);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger);
        Button allJourney = findViewById(R.id.journeys);
        final LinearLayout journeyList = findViewById(R.id.jouneryLayout);
        final Context c = getApplicationContext();
        allJourney.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                HttpUtils.get("journeyg/montreal", new RequestParams(), new TextHttpResponseHandler() {
                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String response) {
                       // super.onSuccess(statusCode, headers, response);

                        List<String> journeys = Arrays.asList(response.split("\\s*<br>\\s*"));
                          for(String j : journeys){
                              TextView journey = new TextView(c);
                              journey.setText(j);
                              journey.setLayoutParams(new LinearLayout.LayoutParams(
                                      LinearLayout.LayoutParams.MATCH_PARENT,
                                      LinearLayout.LayoutParams.MATCH_PARENT));
                              journey.setGravity(Gravity.CENTER);
                              journey.setTextColor(Color.parseColor("#000000"));
                              journey.setTextSize(20);
                              journeyList.addView(journey);
                              Log.d("first user id is ", j);
                          }

                    }
                    @Override
                    public void onFailure(int statusCode, Header[] headers, String response, Throwable throwable) {
                      //  super.onFailure(statusCode, headers, response, throwable);
                        //String lines[] = String.split("\\r?\\n", -1);
                        //try {

                        //} catch (JSONException e) {
                          //  error += e.getMessage();
                            Log.d("get method got an error", error+response);
                       // }
                        refreshErrorMessage();
                    }

                });
    }
    });
    }
}
