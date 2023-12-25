package com.example.stripetutorial;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.stripetutorial.databinding.ActivityMainBinding;
import com.stripe.android.PaymentConfiguration;
import com.stripe.android.paymentsheet.PaymentSheet;
import com.stripe.android.paymentsheet.PaymentSheetResult;

import org.json.JSONException;
import org.json.JSONObject;

import io.github.cdimascio.dotenv.Dotenv;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private StripeService stripeService;
    String publishableKey;
    String secretKey;

    String customerId;
    String ephemeralKey;
    String clientSecret;
    String total;
    private transient PaymentSheet paymentSheet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Dotenv dotenv = Dotenv.configure()
                .directory("/assets")
                .filename("env")
                .load();

        publishableKey = dotenv.get("STRIPE_PUBLISHABLE_KEY");
        secretKey = dotenv.get("STRIPE_SECRET_KEY");


        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        total = binding.total.getText().toString();
        stripeService = new StripeService(this,secretKey,customerId, total);

        PaymentConfiguration.init(this, publishableKey);
        paymentSheet = new PaymentSheet(this, this::onPaymentResult);
        binding.payBtn.setOnClickListener(view -> paymentFlow());

        createCustomer();
    }

    private void paymentFlow() {
        if (customerId != null && ephemeralKey != null) {
            paymentSheet.presentWithPaymentIntent(clientSecret, new PaymentSheet.Configuration("Aleks", new PaymentSheet.CustomerConfiguration(
                    customerId,
                    ephemeralKey
            )));
        } else {
            Log.d("Null values", "customerId: " + customerId + " ephemeralKey: "+ ephemeralKey);
        }
    }

    private void onPaymentResult(PaymentSheetResult paymentSheetResult) {

        if (paymentSheetResult instanceof PaymentSheetResult.Completed) {
            Toast.makeText(this, "Payment Successful!", Toast.LENGTH_SHORT).show();
            restartActivity();
        } else if (paymentSheetResult instanceof PaymentSheetResult.Failed) {
            PaymentSheetResult.Failed failedResult = (PaymentSheetResult.Failed) paymentSheetResult;
            Toast.makeText(this, "Payment Failed", Toast.LENGTH_SHORT).show();
            Log.d("Payment Failed: ", failedResult.getError().toString());
        }
    }

    private void createCustomer() {
        stripeService.createCustomer(
                response -> {
                    try {
                        JSONObject object = new JSONObject(response);
                        customerId = object.getString("id");
                        Log.d("Customer created", customerId);
                        Log.d("secret key created", secretKey);
                        getEphemeralKey();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                },
                error -> Log.e("StripeTutorial", "Error creating customer: " + error, error)
        );
    }

    private void getEphemeralKey() {
        stripeService = new StripeService(this,secretKey,customerId,total);
        stripeService.getEphemeralKey(
                response -> {
                    try {
                        JSONObject object = new JSONObject(response);
                        ephemeralKey = object.getString("id");
                        getClientSecret();
                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                },
                error -> Log.e("StripeTutorial", "Error getting ephemeral key: " + error)
        );
    }

    private void getClientSecret() {
        stripeService = new StripeService(this,secretKey,customerId,total);
        stripeService.getClientSecret(
                response -> {
                    try {
                        JSONObject object = new JSONObject(response);

                        clientSecret = object.getString("client_secret");

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                },
                error -> Log.e("StripeTutorial", "Error getting client secret: " + error)
        );
    }

    private void restartActivity() {
        @SuppressLint("UnsafeIntentLaunch") Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
}