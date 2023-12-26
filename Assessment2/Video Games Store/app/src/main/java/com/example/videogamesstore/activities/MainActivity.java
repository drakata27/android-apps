package com.example.videogamesstore.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.videogamesstore.R;
import com.example.videogamesstore.adapters.CartAdapter;
import com.example.videogamesstore.databinding.ActivityMainBinding;
import com.example.videogamesstore.fragments.AccountFragment;
import com.example.videogamesstore.fragments.CartFragment;
import com.example.videogamesstore.fragments.HomeFragment;
import com.example.videogamesstore.interfaces.CartTotalListener;
import com.example.videogamesstore.models.Games;
import com.example.videogamesstore.models.StripeService;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.stripe.android.PaymentConfiguration;
import com.stripe.android.paymentsheet.PaymentSheet;
import com.stripe.android.paymentsheet.PaymentSheetResult;

import org.json.JSONException;
import org.json.JSONObject;

import io.github.cdimascio.dotenv.Dotenv;

public class MainActivity extends AppCompatActivity implements CartTotalListener {
    private ActivityMainBinding binding;
    private CartAdapter cartAdapter;

    //Stripe
    private StripeService stripeService;
    String publishableKey;
    String secretKey;

    String customerId;
    String ephemeralKey;
    String clientSecret;

    PaymentSheet paymentSheet;
    String total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragments(new HomeFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            if(item.getItemId() == R.id.home) {
                replaceFragments(new HomeFragment());
            } else if (item.getItemId() == R.id.cart) {
                replaceFragments(new CartFragment());
            } else if (item.getItemId() == R.id.account) {
                replaceFragments(new AccountFragment());
            }
            return true;
        });

        FirebaseRecyclerOptions<Games> options =
                new FirebaseRecyclerOptions.Builder<Games>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("AddToCart"), Games.class)
                        .build();

        cartAdapter = new CartAdapter(options,this);

        //Stripe
        Dotenv dotenv = Dotenv.configure()
                .directory("/assets")
                .filename("env")
                .load();

        publishableKey = dotenv.get("STRIPE_PUBLISHABLE_KEY");
        secretKey = dotenv.get("STRIPE_SECRET_KEY");


        // Create an instance of the CartFragment
        CartFragment cartFragment = new CartFragment();

        // Use FragmentManager to replace the current fragment with the CartFragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout_start, cartFragment);
        fragmentTransaction.addToBackStack(null); // Optional: Adds the transaction to the back stack
        fragmentTransaction.commit();

        Intent intent = getIntent();
        String s = intent.getStringExtra("total");

        binding.testTextview.setText("2400");
        total = binding.testTextview.getText().toString();
        Log.d("s in main", ""+s);
        Log.d("cartAdapter get total", ""+cartAdapter.getTotal());

        stripeService = new StripeService(this,secretKey,customerId, total);

        PaymentConfiguration.init(this, publishableKey);
        paymentSheet = new PaymentSheet(this, this::onPaymentResult);

        createCustomer();

    }

    private void replaceFragments(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout_start, fragment);
        fragmentTransaction.commit();
    }

    //Stripe
    public void paymentFlow() {
        if (customerId != null && ephemeralKey != null) {
            if (clientSecret != null) {
                paymentSheet.presentWithPaymentIntent(clientSecret, new PaymentSheet.Configuration("Video Games Store",
                        new PaymentSheet.CustomerConfiguration(customerId, ephemeralKey)));
            } else {
                Log.d("Null values", "clientSecret is null");
                Toast.makeText(this, "Client Secret is null", Toast.LENGTH_SHORT).show();
            }
        } else {
            Log.d("Null values", "customerId: " + customerId + " ephemeralKey: " + ephemeralKey);
        }
    }

    public void onPaymentResult(PaymentSheetResult paymentSheetResult) {

        if (paymentSheetResult instanceof PaymentSheetResult.Completed) {
            Toast.makeText(this, "Payment Successful!", Toast.LENGTH_SHORT).show();
            restartActivity();
        } else if (paymentSheetResult instanceof PaymentSheetResult.Failed) {
            PaymentSheetResult.Failed failedResult = (PaymentSheetResult.Failed) paymentSheetResult;
            Toast.makeText(this, "Payment Failed", Toast.LENGTH_SHORT).show();
            Log.d("Payment Failed: ", failedResult.getError().toString());
        }
    }

    public void createCustomer() {
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
                error -> Log.e("Customer", "Error creating customer: " + error, error)
        );
    }

    public void getEphemeralKey() {
        stripeService = new StripeService(this,secretKey,customerId, total);
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
                error -> Log.e("Ephemeral Key", "Error getting ephemeral key: " + error)
        );
    }

    public void getClientSecret() {
        stripeService = new StripeService(this,secretKey,customerId, total);
        stripeService.getClientSecret(
                response -> {
                    try {
                        JSONObject object = new JSONObject(response);

                        clientSecret = object.getString("client_secret");

                        Log.d("Client Secret", ""+clientSecret);

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }
                },
                error -> Log.e("Client Secret", "Error getting client secret: " + error)
        );
    }

    public void restartActivity() {
        @SuppressLint("UnsafeIntentLaunch") Intent intent = getIntent();
        finish();
        startActivity(intent);
    }
    @Override
    public void onCartTotalUpdated(double total) {
        Log.d("Cart Total", "Updated total: " + total);
    }
}