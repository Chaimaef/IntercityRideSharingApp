package ca.mcgill.ecse321.intercityridesharingsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class createJourneyActivity extends AppCompatActivity {
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

    //Allows a driver to add a new journey advertisement

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_journey);
        Button advertiseJourney = findViewById(R.id.buttonAdvertise);
        final EditText time = findViewById(R.id.startTime);
        final EditText stops = findViewById(R.id.stops);
        final EditText price = findViewById(R.id.prices);
        final EditText vehicle = findViewById(R.id.vehicles);
        final EditText seating = findViewById(R.id.seatings);
        final EditText driver = findViewById(R.id.drivers);
        advertiseJourney.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Gets text field contents to use them for the create journey method in the backend

                String timeMessage = time.getText().toString();
                String stopMessage = stops.getText().toString();
                String priceMessage = price.getText().toString();
                String vehicleMessage = vehicle.getText().toString();
                String seatingMessage = seating.getText().toString();
                String driverMessage = driver.getText().toString();
                HttpUtils.post("createj/" + timeMessage +"/" +  stopMessage +"/" + priceMessage +"/" + vehicleMessage +"/" + seatingMessage +"/" + driverMessage, new RequestParams(), new JsonHttpResponseHandler() {
                    @Override
                    public void onFinish() {
                        super.onFinish();
                        time.setText("");
                        stops.setText("");
                        price.setText("");
                        vehicle.setText("");
                        seating.setText("");
                        driver.setText("");
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
}
