package ca.mcgill.ecse321.intercityridesharingsystem;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.lang.Object;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class closeJourneyActivity extends AppCompatActivity {
    private String error = null;
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
        setContentView(R.layout.activity_choose_journey_to_modify);
        Button allJourney = findViewById(R.id.alljourneys);
        final TextView driverName= findViewById(R.id.input);
        final Context c = getApplicationContext();
        final LinearLayout journeyList = findViewById(R.id.jouneryLayout);
        allJourney.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final String drivername = driverName.getText().toString();
                HttpUtils.get("journeyd/" + drivername, new RequestParams(), new TextHttpResponseHandler() {
                    @Override
                    public void onFinish() {
                        super.onFinish();
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String response) {

                        List<String> journeys = Arrays.asList(response.split("\\s*<br>\\s*"));
                        for(final String j : journeys){
                            TextView journey = new TextView(c);
                            journey.setText(j);
                            journey.setLayoutParams(new LinearLayout.LayoutParams(
                                    LinearLayout.LayoutParams.MATCH_PARENT,
                                    LinearLayout.LayoutParams.MATCH_PARENT));
                            journey.setGravity(Gravity.CENTER);
                            journey.setTextColor(Color.parseColor("#000000"));
                            journey.setTextSize(20);
                            journeyList.addView(journey);
                            journey.setOnClickListener(new View.OnClickListener() {
                                public void onClick(View v) {
                                 setContentView(R.layout.activity_close_journey);
                                    final TextView chosenJourney = findViewById(R.id.chosenJourney);
                                    chosenJourney.setText(j);
                                    Button close = findViewById(R.id.buttonClose);
                                    close.setOnClickListener(new View.OnClickListener() {
                                        public void onClick(View v) {
                                    final String id =  j.substring((j.indexOf("=")+1), j.indexOf(","));
                                    chosenJourney.setGravity(Gravity.CENTER);
                                    HttpUtils.post("journeyc/" + id, new RequestParams(), new JsonHttpResponseHandler() {
                                        @Override
                                        public void onFinish() {
                                            super.onFinish();
                                            error = "Journey created successfully";
                                            refreshErrorMessage();
                                        }

                                        @Override
                                        public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                                            super.onSuccess(statusCode, headers, response);

                                        }

                                        @Override
                                        public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                                            //super.onFailure(statusCode, headers, throwable, errorResponse);

                                            try {
                                                error += errorResponse.get("message").toString();
                                            } catch (JSONException e) {
                                                error += e.getMessage();
                                            }
                                            refreshErrorMessage();
                                        }

                                    });
                                    }
                                    });

                                }
                            });
                        }
                    }
                    @Override
                    public void onFailure(int statusCode, Header[] headers, String response, Throwable throwable) {
                        refreshErrorMessage();
                    }

                });
            }
        });
    }
}
