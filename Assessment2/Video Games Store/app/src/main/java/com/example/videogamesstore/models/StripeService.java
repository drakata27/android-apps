package com.example.videogamesstore.models;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class StripeService {

    private final String secretKey;
    private final RequestQueue requestQueue;
    private final String customerId;
    private final String total;

    public StripeService(Context context, String secretKey, String customerId, String total) {
        this.secretKey = secretKey;
        this.requestQueue = Volley.newRequestQueue(context);
        this.customerId = customerId;
        this.total = total;
    }

    public void createCustomer(Response.Listener<String> onSuccess, Response.ErrorListener onError) {
        String url = "https://api.stripe.com/v1/customers";
        makeStripeRequest(url, onSuccess, onError);
    }

    public void getEphemeralKey(Response.Listener<String> onSuccess, Response.ErrorListener onError) {
        String url = "https://api.stripe.com/v1/ephemeral_keys";
        Log.d("customerId (from stripe class)", ""+customerId);
        makeStripeRequestEphemeral(url, onSuccess, onError, customerId);
    }

    public void getClientSecret(Response.Listener<String> onSuccess, Response.ErrorListener onError) {
        String url = "https://api.stripe.com/v1/payment_intents";
        makeStripeRequestClientSecret(url, onSuccess, onError, total);
    }

    private void makeStripeRequest(String url, Response.Listener<String> onSuccess, Response.ErrorListener onError) {
        StringRequest request = new StringRequest(Request.Method.POST, url, onSuccess, onError) {
            @Override
            public Map<String, String> getHeaders() {
                Map<String, String> headers = new HashMap<>();
                headers.put("Authorization", "Bearer " + secretKey);
                headers.put("Stripe-Version", "2022-11-15");
                return headers;
            }
        };
        requestQueue.add(request);
    }

    private void makeStripeRequestEphemeral(String url, Response.Listener<String> onSuccess, Response.ErrorListener onError, String customerId) {
        if (customerId==null) {
            Log.d("Customer Id", ""+null);
            return;
        }
        StringRequest request = new StringRequest(Request.Method.POST, url, onSuccess, onError) {
            @Override
            public Map<String, String> getHeaders() {

                Map<String, String> header = new HashMap<>();
                header.put("Authorization", "Bearer " + secretKey);
                header.put("Stripe-Version", "2022-11-15");
                return header;
            }
            @NonNull
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("customer", customerId);
                return params;
            }
        };
        requestQueue.add(request);
    }

    private void makeStripeRequestClientSecret(String url, Response.Listener<String> onSuccess, Response.ErrorListener onError, String total) {
        StringRequest request = new StringRequest(Request.Method.POST, url, onSuccess, onError) {
            @Override
            public Map<String, String> getHeaders() {

                Map<String, String> header = new HashMap<>();
                header.put("Authorization", "Bearer " + secretKey);
                return header;
            }
            @NonNull
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("amount", total);
                params.put("customer", customerId);
                params.put("currency", "GBP");
                return params;
            }
        };
        requestQueue.add(request);
    }
}