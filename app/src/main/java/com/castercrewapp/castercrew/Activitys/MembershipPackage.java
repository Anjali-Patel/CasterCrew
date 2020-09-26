package com.castercrewapp.castercrew.Activitys;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.castercrewapp.castercrew.Fragments.AssistedFragment;
import com.castercrewapp.castercrew.Fragments.EliteFragment;
import com.castercrewapp.castercrew.Fragments.SixMonthsFragment;
import com.castercrewapp.castercrew.Fragments.ThreeMonthsFragment;
import com.castercrewapp.castercrew.Fragments.TillMarryFragment;
import com.castercrewapp.castercrew.MainActivity;
import com.castercrewapp.castercrew.R;
import com.castercrewapp.castercrew.utils.AccountUtils;
import com.castercrewapp.castercrew.utils.SharedPreferenceUtils;
import com.google.android.material.tabs.TabLayout;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MembershipPackage extends AppCompatActivity implements PaymentResultListener, ThreeMonthsFragment.OnFragmentInteractionListener, SixMonthsFragment.OnFragmentInteractionListener, TillMarryFragment.OnFragmentInteractionListener, AssistedFragment.OnFragmentInteractionListener {
    TabLayout tabLayout;
    private   String transaction_id="",order_id="",signature="";
    SharedPreferenceUtils preferances;
    String   User_id;

    FrameLayout frameLayout;
    Fragment fragment = null;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_membership_package);
        preferances= SharedPreferenceUtils.getInstance(this);
        User_id=preferances.getStringValue(AccountUtils.PREF_USER_ID,"");
        tabLayout=(TabLayout)findViewById(R.id.mTabLayout);
        frameLayout=(FrameLayout)findViewById(R.id.fragment_container);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Membership Package");

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        fragment = new ThreeMonthsFragment();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        fragmentTransaction.commit();
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // Fragment fragment = null;
                switch (tab.getPosition()) {
                    case 0:
                        fragment = new ThreeMonthsFragment();
                        fragmentManager = getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container, fragment);
                        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        fragmentTransaction.commit();

//                        Intent intent = new Intent(MainActivity.this,HomeFragment.class);
//                        startActivity(intent);
                        //   fragment = new artist_actor();
                        break;
                    case 1:
                        fragment = new ThreeMonthsFragment();
                        fragmentManager = getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container, fragment);
                        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        fragmentTransaction.commit();
                        break;
                    case 2:
                        fragment = new ThreeMonthsFragment();
                        fragmentManager = getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container, fragment);
                        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        fragmentTransaction.commit();
                        break;
                    case 3:
                        fragment = new ThreeMonthsFragment();
                        fragmentManager = getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container, fragment);
                        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        fragmentTransaction.commit();
                        break;
                    case 4:
                        fragment = new ThreeMonthsFragment();
                        fragmentManager = getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container, fragment);
                        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        fragmentTransaction.commit();
                        break;
                    case 5:
                        fragment = new SixMonthsFragment();
                        fragmentManager = getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container, fragment);
                        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        fragmentTransaction.commit();

                        break;
                    case 6:
                        fragment = new TillMarryFragment();
                        fragmentManager = getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container, fragment);
                        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        fragmentTransaction.commit();

                        break;
                    case 7:
                        fragment = new AssistedFragment();
                        fragmentManager = getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container, fragment);
                        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        fragmentTransaction.commit();

                        break;
                    case 8:
                        fragment = new EliteFragment();
                        fragmentManager = getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container, fragment);
                        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        fragmentTransaction.commit();

                        break;

//                    default:
//                        fragment = new ThreeMonthsFragment();
//                        fragmentManager = getSupportFragmentManager();
//                        fragmentTransaction = fragmentManager.beginTransaction();
//                        fragmentTransaction.replace(R.id.fragment_container, fragment);
//                        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//                        fragmentTransaction.commit();


                }
                FragmentManager fm = getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.fragment_container, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                ft.commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_refresh) {
            return true;
        }else if (id == R.id.action_artist){

            Intent intent = new Intent(this, TalentPostListActivity.class);
            // intent.putExtra("cat","Artist");
            startActivity(intent);
//            Bundle bundle1 = new Bundle();
//                bundle1.putString("cat", "Artist");
//                HomeFragment categoryFragment = new HomeFragment();
//                categoryFragment.setArguments(bundle1);
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,categoryFragment).commit();
        }else if (id == R.id.action_profissional){
            Intent intent = new Intent(this, TalentPostListActivity.class);
            intent.putExtra("cat","Professionals");
            startActivity(intent);
        }
        else if (id == R.id.action_vender){
            Intent intent = new Intent(this, TalentPostListActivity.class);
            intent.putExtra("cat","vendors");
            startActivity(intent);
        }

        else if (id == R.id.action_inistitute){
            Intent intent = new Intent(this, TalentPostListActivity.class);
            intent.putExtra("cat","institutes");
            startActivity(intent);
        }else if (id == R.id.action_activist){
            Intent intent = new Intent(this, TalentPostListActivity.class);
            intent.putExtra("cat","activists");
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPaymentSuccess(String s) {
        Toast.makeText(MembershipPackage.this, "Payment Successful: " + s, Toast.LENGTH_LONG).show();


transaction_id=s;
        paymentSuccessful(transaction_id);
    }

    @Override
    public void onPaymentError(int i, String s) {
        Toast.makeText(MembershipPackage.this, "Payment Failure: " + s, Toast.LENGTH_LONG).show();
        if(i== Checkout.NETWORK_ERROR){
            Toast.makeText(MembershipPackage.this, "Payment Failure: " + s, Toast.LENGTH_LONG).show();
        }else if(i==Checkout.INVALID_OPTIONS){
            Toast.makeText(MembershipPackage.this, "Payment Failure: " + s, Toast.LENGTH_LONG).show();
        }else if(i==Checkout.PAYMENT_CANCELED){
            Toast.makeText(MembershipPackage.this, "Payment Failure: " + s, Toast.LENGTH_LONG).show();
        }else if(i==Checkout.TLS_ERROR){
            Toast.makeText(MembershipPackage.this, "Payment Failure: " + s, Toast.LENGTH_LONG).show();

        }


    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
    private void paymentSuccessful(String transaction_id) {
        String url = "https://castercrew.com/mobile_app/payment_resp";
        StringRequest jsonRequest = new StringRequest(com.android.volley.Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject json = new JSONObject(response);
                    int   result=json.getInt("res");
                    if(result==1){
                        Intent intent= new Intent(MembershipPackage.this, SubscriptionDetailActivity.class);
                        intent.putExtra("uid",User_id);
                        intent.putExtra("transaction_id",transaction_id);
                        intent.putExtra("transaction_ammount",String.valueOf(AccountUtils.AMMOUNT));
                        intent.putExtra("validity",AccountUtils.VALIDITY);
                        intent.putExtra("membership",AccountUtils.MEMBERSHIP);
                        startActivity(intent);
                    }else{
                        Toast.makeText(MembershipPackage.this,"Something went wrong", Toast.LENGTH_LONG).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();

                }
            }
        },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("uid",User_id);
                params.put("memship",AccountUtils.MEMBERSHIP);
                params.put("valid_days",AccountUtils.VALIDITY);
                params.put("paid_amount", String.valueOf(AccountUtils.AMMOUNT));
                params.put("razorpay_payment_id",transaction_id);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(MembershipPackage.this);
        requestQueue.add(jsonRequest);
    }

}
