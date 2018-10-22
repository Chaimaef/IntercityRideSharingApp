package ca.mcgill.ecse321.intercityridesharingsystem;

import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;
import android.widget.TextView;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

import ca.mcgill.ecse321.intercityridesharingsystem.R;
import cz.msebera.android.httpclient.Header;

public class driverActivity extends AppCompatActivity {
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
        //refreshErrorMessage();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver);
        Button advertiseJourney = findViewById(R.id.buttonAdvertise);
        final EditText time = findViewById(R.id.startTime);
        final EditText stops = findViewById(R.id.stops);
        final EditText price = findViewById(R.id.prices);
        final EditText vehicle = findViewById(R.id.vehicles);
        final EditText seating = findViewById(R.id.seatings);
        final EditText driver = findViewById(R.id.drivers);
        //final TextView error = findViewById(R.id.error);
        advertiseJourney.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //error = "";
                String timeMessage = time.getText().toString();
                String stopMessage = stops.getText().toString();
                String priceMessage = price.getText().toString();
                String vehicleMessage = vehicle.getText().toString();
                String seatingMessage = seating.getText().toString();
                String driverMessage = driver.getText().toString();
                HttpUtils.post("creatj/" + timeMessage +"/" +  stopMessage +"/" + priceMessage +"/" + vehicleMessage +"/" + seatingMessage +"/" + driverMessage, new RequestParams(), new JsonHttpResponseHandler() {
                    @Override
                    public void onFinish() {
//                        time.setText("");
//                        stops.setText("");
//                        price.setText("");
//                        vehicle.setText("");
//                        seating.setText("");
//                        driver.setText("");
                    }
                    @Override
                    public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                        //super.onFailure(statusCode, headers, throwable, errorResponse);

                         try {
                            error += errorResponse.get("message").toString();
                        } catch (JSONException e) {
                           error += e.getMessage();
                           Log.d("error", error);
                      }
                        refreshErrorMessage();
                    }

                });
            }
        });
    }
}
