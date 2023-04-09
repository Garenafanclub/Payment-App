package com.example.easepay;

import androidx.appcompat.app.AppCompatActivity;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONObject;


import android.annotation.SuppressLint;

public class PhoneActivity extends AppCompatActivity implements PaymentResultListener {

    String TAG= "payment error";
    Button pay;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        Checkout.preload(getApplicationContext());

        pay = findViewById(R.id.pay_Button);

        /**
         * Preload payment resources
         */
        Checkout.preload(getApplicationContext());

        pay.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startPayment();
            }
        });
    }

    public void startPayment() {

        /**
         * Instantiate Checkout
         */
        Checkout checkout = new Checkout();

        checkout.setKeyID("rzp_test_eZo28HG7lJPB5n");


        /**
         * Set your logo here
         */
        // checkout.setImage(R.drawable.logo);

        /**
         * Reference to current activity
         */
        final Activity activity = this;

        /**
         * Pass your payment options to the Razorpay Checkout as a JSONObject
         */
        try {
            JSONObject options = new JSONObject();

            options.put("name", "EasePay");
            options.put("description", "PAYMENT UPI");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("theme.color", "#ffffff");
            options.put("currency", "INR");

            //here amount is passed in paise. for eg if we want Rs 5 we have to write 500 in "value"
            options.put("amount", "10000");//pass amount in currency subunits
            options.put("prefill.email", "sakshammittal217@gmail.com");
            options.put("prefill.contact","6398152495");
            checkout.open(activity, options);
        } catch(Exception e) {
            Log.e(TAG, "Error in starting Razorpay Checkout", e);
        }
    }

    @Override
    public void onPaymentSuccess(String s) {

//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Payment_ID");
//        builder.setMessage(s);
//        builder.show();
        Toast.makeText(this, "payment successful", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();

    }
}