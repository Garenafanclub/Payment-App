package com.example.easepay;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.razorpay.Checkout;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {
    String TAG= "payment error";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private Handler slideHandler = new Handler();
    Button pay_button;
    ViewPager2 viewPager2;


    View view;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_home, container, false);
        pay_button = v.findViewById(R.id.pay_button);
        viewPager2 = v.findViewById(R.id.viewPager);
        List<SlideItem> sliderItem = new ArrayList<>();

        sliderItem.add(new SlideItem(R.drawable.slide_twelve));
        sliderItem.add(new SlideItem(R.drawable.slide_three));
        sliderItem.add(new SlideItem(R.drawable.slide_four));
        sliderItem.add(new SlideItem(R.drawable.slide_six));
        sliderItem.add(new SlideItem(R.drawable.slide_eight));

        viewPager2.setAdapter(new SlideAdapter(sliderItem,viewPager2));

        viewPager2.setClipToPadding(false);
        viewPager2.setClipChildren(false);
        viewPager2.setOffscreenPageLimit(5);
        viewPager2.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(30));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1-Math.abs(position);
                page.setScaleY(0.85f + r*0.15f);
                slideHandler.removeCallbacks(slideRunnable);
                slideHandler.postDelayed(slideRunnable,2000);
            }
        });
        viewPager2.setPageTransformer(compositePageTransformer);

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }
        });

        Checkout.preload(getActivity());
        pay_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPayment();
            }
        });
        return v;
    }
    private Runnable slideRunnable = new Runnable() {
        @Override
        public void run() {
             viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
        }
    };

    public void onPause()
    {
        super.onPause();
        slideHandler.removeCallbacks(slideRunnable);
    }
    public void onResume() {

        super.onResume();
        slideHandler.postDelayed(slideRunnable,3000);
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
        final Activity activity = getActivity();

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


    public void onPaymentSuccess(String s) {

//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("Payment_ID");
//        builder.setMessage(s);
//        builder.show();
        Toast.makeText(getActivity(), "payment successful", Toast.LENGTH_SHORT).show();
    }


    public void onPaymentError(int i, String s) {
        Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();

    }

}