package com.castercrewapp.castercrew.Fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.castercrewapp.castercrew.Activitys.LoginActivity;
import com.castercrewapp.castercrew.Activitys.PaymentModeActivity;
import com.castercrewapp.castercrew.Activitys.RegistationActivity;
import com.castercrewapp.castercrew.Activitys.SubscriptionDetailActivity;
import com.castercrewapp.castercrew.MainActivity;
import com.castercrewapp.castercrew.R;
import com.castercrewapp.castercrew.utils.AccountUtils;
import com.castercrewapp.castercrew.utils.SharedPreferenceUtils;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AssistedFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AssistedFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AssistedFragment extends Fragment  {
//    public static  int payment_ammount=40000;
private String transaction_id="";
    Button PAY_NOW;
 Activity context;
 ViewGroup viewGroup;
 LinearLayout call,chat;
 Button bt1,bt2,bt3;
    String buttonString1="",buttonString2="",buttinString3="";
    String user_id;
    SharedPreferenceUtils preferances;
    boolean buttonClick1=false;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private int amount_flag;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private OnFragmentInteractionListener mListener;
    public AssistedFragment() {
        // Required empty public constructor
    }
    public static AssistedFragment newInstance(String param1, String param2) {
        AssistedFragment fragment = new AssistedFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_assisted, container, false);
        PAY_NOW=view.findViewById(R.id.PAY_NOW);
        AccountUtils.MEMBERSHIP="Diamond";
        AccountUtils.VALIDITY="90";
        preferances= SharedPreferenceUtils.getInstance(getContext());
        user_id=preferances.getStringValue(AccountUtils.PREF_USER_ID,"");
        call=view.findViewById(R.id.call);
        chat=view.findViewById(R.id.chat);
        bt1=view.findViewById(R.id.bt1);
        bt2=view.findViewById(R.id.bt2);
        bt3=view.findViewById(R.id.bt3);
        buttonString2=bt2.getText().toString().trim();
        AccountUtils.AMMOUNT=40000;
        context=getActivity();
        bt1.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                buttonClick1 = true;

                buttonString1=bt1.getText().toString().trim();
                AccountUtils.AMMOUNT=23000;
                if(buttonClick1){
                    bt1.setBackgroundColor(Color.BLUE);
                    bt2.setBackgroundColor(Color.WHITE);
                    bt3.setBackgroundColor(Color.WHITE);
                    buttonClick1 = false;

                }
                else {
                    bt1.setBackgroundColor(Color.WHITE);
                    buttonClick1 = true;
                }

            }
        });
        bt2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
//                buttonClick2=true;
                buttonClick1 = true;

                buttonString2=bt2.getText().toString().trim();
                AccountUtils.AMMOUNT=40000;
                if(buttonClick1){
                    bt2.setBackgroundColor(Color.BLUE);
                    bt1.setBackgroundColor(Color.WHITE);
                    bt3.setBackgroundColor(Color.WHITE);
                    buttonClick1 = false;

                }
                else {
                    bt2.setBackgroundColor(Color.WHITE);
                    buttonClick1 = true;
                }
            }
        });
        bt3.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                buttonClick1 = true;
                buttinString3=bt3.getText().toString().trim();
                AccountUtils.AMMOUNT=60000;
                if(buttonClick1){
                    bt3.setBackgroundColor(Color.BLUE);
                    bt1.setBackgroundColor(Color.WHITE);
                    bt2.setBackgroundColor(Color.WHITE);
                    buttonClick1 = false;
                }
                else {
                    bt3.setBackgroundColor(Color.WHITE);
                    buttonClick1 = true;
                }

            }
        });
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:" + "9490009305" ));
                startActivity(callIntent);
            }
        });
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                //ViewGroup viewGroup =context. findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.chat_dialog, viewGroup, false);
                ImageView close=dialogView.findViewById(R.id.cross);
                builder.setView(dialogView);
                final ImageView up_expand=dialogView.findViewById(R.id.up_expand);
                up_expand.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        up_expand.setImageResource(R.drawable.expand_down);
                    }
                });
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog.dismiss();
                    }
                });

            }
        });
        PAY_NOW.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user_id.equalsIgnoreCase("")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    //ViewGroup viewGroup =context. findViewById(android.R.id.content);
                    View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.skip_poppup_layout, viewGroup, false);
                    TextView no=dialogView.findViewById(R.id.no);
                    TextView yes=dialogView.findViewById(R.id.yes);
                    builder.setView(dialogView);
                    final AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                    yes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent= new Intent(getActivity(), LoginActivity.class);
                            startActivity(intent);
                            alertDialog.dismiss();
                        }});
                    no.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.dismiss();
                        }});
                }else{
                    if(buttonString1.equalsIgnoreCase("")&&buttonString2.equalsIgnoreCase("")&&buttinString3.equalsIgnoreCase("")){
                        Toast.makeText(getContext(),"Please select package",Toast.LENGTH_LONG).show();
                    }else{
                        startPayment();

                    }
                }
















            }
        });
        return  view;
    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);

        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            try {
                throw new RuntimeException(context.toString()
                        + " must implement OnFragmentInteractionListener");
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            /*throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");*/
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

  /*  @Override
    public void onPaymentSuccess(String s) {
        try {
            transaction_id=s;
            Toast.makeText(getContext(), "Payment Successful: " + transaction_id, Toast.LENGTH_SHORT).show();
            paymentSuccessful(transaction_id);

        } catch (Exception e) {
        }
    }

    @Override
    public void onPaymentError(int i, String s) {
        try {
            Toast.makeText(getContext(), "Payment failed: " + i + " " + s, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
        }
    }*/

    @SuppressLint("ResourceAsColor")


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
    private void startPayment() {
        final Context activity = getContext();
        final Checkout co = new Checkout();
        try {
            JSONObject options = new JSONObject();
            options.put("name", "Razorpay Corp");
            options.put("description", "Demoing Charges");
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.png");
            options.put("currency", "INR");
            double total1=Double.parseDouble(String.valueOf(AccountUtils.AMMOUNT));
            total1=total1*100;
            options.put("amount",total1);
            JSONObject preFill = new JSONObject();
            preFill.put("email", "");
            preFill.put("contact", "");
            options.put("prefill", preFill);
            co.open((Activity) activity, options);
        } catch (Exception e) {
            Toast.makeText(getContext(), "Error in payment: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

}
