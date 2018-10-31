package ca.mcgill.ecse321.intercityridesharingsystem_android_passenger;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
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

import ca.mcgill.ecse321.intercityridesharingsystem_android_passenger.R;
import cz.msebera.android.httpclient.Header;

public class searchJourneyActivity extends AppCompatActivity {
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
        setContentView(R.layout.acivity_search_journey);
        Button allJourney = findViewById(R.id.journeys);
        Button sortJourney = findViewById(R.id.sortedjourneys);
        Button newSearch = findViewById(R.id.startnew);
        final LinearLayout journeyList = findViewById(R.id.jouneryLayout);
        final Context c = getApplicationContext();
        final EditText start = findViewById(R.id.inputstart);
        final EditText destination = findViewById(R.id.input);
        final EditText cartype = findViewById(R.id.cartype);
        final EditText price = findViewById(R.id.stopPrice);
        final EditText seating = findViewById(R.id.seatings);
        final EditText starttime = findViewById(R.id.time);

        newSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start.setText("");
                destination.setText("");
                cartype.setText("");
                price.setText("");
                seating.setText("");
                starttime.setText("");
                if((journeyList).getChildCount() > 0){
                    (journeyList).removeAllViews();
                }


            }
        });
        allJourney.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String startCity = start.getText().toString();
                String destinaitonCity = destination.getText().toString();
                //"/journeysort/{start}/{destination}/{carType}/{price}/{seating}/{date}"
                HttpUtils.get("journeyg/" + startCity + "/" + destinaitonCity, new RequestParams(), new TextHttpResponseHandler() {
                    @Override
                    public void onFinish() {
                        super.onFinish();
                        start.setText("");
                        destination.setText("");
                        cartype.setText("");
                        price.setText("");
                        seating.setText("");
                        starttime.setText("");

                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String response) {
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
                        }

                    }
                    @Override
                    public void onFailure(int statusCode, Header[] headers, String response, Throwable throwable) {
                        Log.d("get method got an error", error+response);
                        refreshErrorMessage();
                    }

                });
            }
        });

        sortJourney.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String startCity = start.getText().toString();
                String destinaitonCity = destination.getText().toString();
                String car = cartype.getText().toString();
                String maxprice = price.getText().toString();
                String seatings = seating.getText().toString();
                String time = starttime.getText().toString();
                //"/journeysort/{start}/{destination}/{carType}/{price}/{seating}/{date}"
                HttpUtils.get("journeysort/" + startCity + "/" + destinaitonCity + "/" + car + "/" + maxprice + "/" + seatings + "/" + time, new RequestParams(), new TextHttpResponseHandler() {
                    @Override
                    public void onFinish() {
                        super.onFinish();
                        start.setText("");
                        destination.setText("");
                        cartype.setText("");
                        price.setText("");
                        seating.setText("");
                        starttime.setText("");

                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String response) {
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
                        }

                    }
                    @Override
                    public void onFailure(int statusCode, Header[] headers, String response, Throwable throwable) {
                        Log.d("get method got an error", error+response);
                        refreshErrorMessage();
                    }

                });

            }
        });
    }
}