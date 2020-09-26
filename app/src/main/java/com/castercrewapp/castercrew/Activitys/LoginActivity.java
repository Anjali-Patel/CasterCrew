package com.castercrewapp.castercrew.Activitys;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.castercrewapp.castercrew.MainActivity;
import com.castercrewapp.castercrew.R;
import com.castercrewapp.castercrew.utils.AccountUtils;
import com.castercrewapp.castercrew.utils.SharedPreferenceUtils;
import com.castercrewapp.castercrew.utils.ToastMessage;
import com.castercrewapp.castercrew.utils.shared_preference;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {
    public final static int PERMISSIONS_REQUEST_CODE_1 = 2;

    public static final int RC_SIGN_IN = 1;
    private static final String TAG = "AndroidClarified";
    EditText etuserid, etpassword;
    String usermail, password,fcm_token="";
    Button login, registation;
    TextView tvforgotpass;
    //GoogleSignInClient mGoogleSignInClient;
    Spinner spin;
    //    GoogleSignInAccount account;
    String first_name, email, image_url, last_name;
    String verify, user_id;
    Button loginButton;
    CallbackManager callbackManager;
    //    private GoogleSignInClient googleSignInClient;
    SignInButton sign_in_button;
    ArrayList<String> rolelist;
    String roleids,fcmTocken="";
    Button btn_skip;
    SharedPreferenceUtils preferances;
    private GoogleApiClient googleApiClient;
    private ProgressBar pbLoading;
    private shared_preference sharedPreference;
    String[] allReqPermissions =
            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA,
                    Manifest.permission.READ_PHONE_STATE,
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(LoginActivity.this);
        checkAllNecessaryPermissions();

//        AppEventsLogger.activateApp(LoginActivity.this);
        callbackManager = CallbackManager.Factory.create();
        setContentView(R.layout.activity_login);
        preferances = SharedPreferenceUtils.getInstance(this);
        fcmTocken = preferances.getStringValue(AccountUtils.FCM_TOKEN,"");

        loginButton = findViewById(R.id.login_button);
        sign_in_button = findViewById(R.id.google_sign_in_button);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleApiClient = new GoogleApiClient.Builder(LoginActivity.this)
                .enableAutoManage(LoginActivity.this, LoginActivity.this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();


//        loginButton.setReadPermissions(Arrays.asList("email", "public_profile"));
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logOut();
                LoginManager.getInstance().logInWithReadPermissions(LoginActivity.this, Arrays.asList("public_profile", "email"));
            }
        });

        callbackManager = CallbackManager.Factory.create();
        final AccessToken accessToken = AccessToken.getCurrentAccessToken();


        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        loginButton.setText("Continue with Facebook");
//                String id=loginResult.getAccessToken().getUserId();
//                image_url="https://graph.facebook.com/" + id + "/picture?type=normal";
                        getUserProfile(AccessToken.getCurrentAccessToken());

                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(LoginActivity.this, "Oops!something went wrong", Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onError(FacebookException error) {
                        Toast.makeText(LoginActivity.this, "Wrong Credential Either Id or Password", Toast.LENGTH_SHORT).show();

                    }
                }
        );
        sharedPreference = new shared_preference(this);

        if (sharedPreference.readloginstatus()) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

        initlizeviews();
//        facebooklogin();
        setspinner();

//        googleSignInButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });


//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestEmail().requestIdToken(String.valueOf(R.string.Client_ID))
//                .build();
////        googleSignInClient = GoogleSignIn.getClient(this, gso);
//        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSIONS_REQUEST_CODE_1:
                if (!checkPermissionsGranted())
//                    Toast.makeText(getApplicationContext(), "You have not granted all necessary permissions for " +
//                            "Caster to function properly. Grant permissions from settings afterwards.", Toast.LENGTH_LONG).show();
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }
    private boolean checkPermission(String permission) {
        int permissionState = ActivityCompat.checkSelfPermission(this, permission);

        return permissionState == PackageManager.PERMISSION_GRANTED;
    }
    public boolean checkPermissionsGranted (){

        for (String permission : allReqPermissions){
            if (!checkPermission(permission)){
                return false;
            }

        }
        return true;
    }
    public void checkAllNecessaryPermissions () {

        ArrayList<String> requiredPermissionsList = new ArrayList<>();

        for (String permission : allReqPermissions){
            if (!checkPermission(permission)){
                requiredPermissionsList.add(permission);
            }
        }

        if(requiredPermissionsList.size() > 0){
            String[] requiredPermissions = requiredPermissionsList.toArray(new String[requiredPermissionsList.size()]);

            ActivityCompat.requestPermissions(LoginActivity.this, requiredPermissions, PERMISSIONS_REQUEST_CODE_1);
        }

    }

    public void setspinner() {

        rolelist = new ArrayList<>();
        spin = findViewById(R.id.role);

//        loadSpinnerData(url);

        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (!spin.equals("Select Role")) {
                    roleids = String.valueOf(spin.getSelectedItemPosition());

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // DO Nothing here
            }
        });
    }

/*
    public void facebooklogin() {
        loginButton = findViewById(R.id.login_button);

        boolean loggedOut = AccessToken.getCurrentAccessToken() == null;
        if (!loggedOut) {
            image_url= String.valueOf(Profile.getCurrentProfile().getProfilePictureUri(200, 200));
//            Picasso.with(this).load(Profile.getCurrentProfile().getProfilePictureUri(200, 200)).into(imageView);
            Log.d("TAG", "Username is: " + Profile.getCurrentProfile().getName());
            first_name=Profile.getCurrentProfile().getName();

//email=Profil.
            //Using Graph API
            getUserProfile(AccessToken.getCurrentAccessToken());
        }

//
//        if (!loggedOut) {
//          //  Picasso.get().load(Profile.getCurrentProfile().getProfilePictureUri(200, 200)).into(imageView);
//            Log.d("TAG", "Username is: " + Profile.getCurrentProfile().getName());
//
//            //Using Graph API
//            getUserProfile(AccessToken.getCurrentAccessToken());
//        }



        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {

            @Override
            public void onSuccess(LoginResult loginResult) {
                getUserProfile(AccessToken.getCurrentAccessToken());

                // App code
                //loginResult.getAccessToken();
                //loginResult.getRecentlyDeniedPermissions()
                //loginResult.getRecentlyGrantedPermissions()
                boolean loggedIn = AccessToken.getCurrentAccessToken() == null;
                Log.d("API123", loggedIn + " ??");

            }

            @Override
            public void onCancel() {
                Toast.makeText(LoginActivity.this, "Oops!something went wrong", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException exception) {
                Toast.makeText(LoginActivity.this, "Invalid user Id or password", Toast.LENGTH_SHORT).show();
            }
        });
    }
*/

    @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }

//        if (requestCode == RC_SIGN_IN) {
//            // The Task returned from this call is always completed, no need to attach
//            // a listener.
//            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
//            handleSignInResult(task);
//        }

    }

    private void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()) {
            GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(LoginActivity.this);
            if (acct != null) {
                String personName = acct.getDisplayName();
                String personGivenName = acct.getGivenName();
                String personFamilyName = acct.getFamilyName();
                String personEmail = acct.getEmail();
                String personId = acct.getId();
                Uri personPhoto = acct.getPhotoUrl();
                preferances.setValue(AccountUtils.GOOGLE_EMAIL, personEmail);
                preferances.setValue(AccountUtils.GOOGLE_NAME, personName);
                preferances.setValue(AccountUtils.GOOGLE_PHOTO, String.valueOf(personPhoto));
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("fname",personName);
                intent.putExtra("email",personEmail);
                intent.putExtra("image_url",String.valueOf(personPhoto));
                startActivity(intent);
            }else{
                googlelogin();
            }


//            gotoProfile();
        } else {
            Toast.makeText(getApplicationContext(), "Sign in cancel", Toast.LENGTH_LONG).show();
        }
    }

    private void gotoProfile() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);

        startActivity(intent);
    }

    private void getUserProfile(AccessToken currentAccessToken) {
        GraphRequest request = GraphRequest.newMeRequest(
                currentAccessToken, new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Log.d("TAG", "DataFacebook:" + object.toString());
                        try {
                            if (object.has("email")) {
                                first_name = object.getString("first_name");
                                last_name = object.getString("last_name");
                                email = object.getString("email");

                                String id = object.getString("id");
                                image_url = "https://graph.facebook.com/" + id + "/picture?type=normal";
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                                intent.putExtra("fname", first_name + last_name);
//                            intent.putExtra("lname",last_name);
                                intent.putExtra("email", email);
                                intent.putExtra("image_url", image_url);
                                startActivity(intent);
                                preferances.setValue(AccountUtils.PREF_EMAIL, email);
                                preferances.setValue(AccountUtils.PREF_NAME, first_name + last_name);
                                preferances.setValue(AccountUtils.PREF_PHOTO_URL, first_name + last_name);
                            } else {
                                first_name = object.getString("first_name");
                                last_name = object.getString("last_name");
//                                email = object.getString("email");

                                String id = object.getString("id");
                                image_url = "https://graph.facebook.com/" + id + "/picture?type=normal";
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                                intent.putExtra("fname", first_name + last_name);
//                            intent.putExtra("lname",last_name);
                                intent.putExtra("email", "vvvinfotech@android.com");
                                intent.putExtra("image_url", image_url);
                                startActivity(intent);
                                preferances.setValue(AccountUtils.PREF_EMAIL, email);
                                preferances.setValue(AccountUtils.PREF_NAME, first_name + last_name);
                                preferances.setValue(AccountUtils.PREF_PHOTO_URL, first_name + last_name);
                            }
                            /* first_name = object.getString("first_name");
                             last_name = object.getString("last_name");
                             email = object.getString("email");

                            String id = object.getString("id");
                             image_url = "https://graph.facebook.com/" + id + "/picture?type=normal";
                            Intent intent= new Intent(LoginActivity.this,MainActivity.class);

                            intent.putExtra("fname",first_name+last_name);
//                            intent.putExtra("lname",last_name);
                            intent.putExtra("email",email);
                            intent.putExtra("image_url",image_url);
                            startActivity(intent);
                            preferances.setValue(AccountUtils.PREF_EMAIL, email);
                            preferances.setValue(AccountUtils.PREF_NAME, first_name+last_name);
                            preferances.setValue(AccountUtils.PREF_PHOTO_URL, first_name+last_name);*/
//                            preferances.setValue(AccountUtils.PREF_EMAIL, first_name+last_name);


//                            Toast.makeText(LoginActivity.this, email, Toast.LENGTH_SHORT).show();

//                            txtUsername.setText("First Name: " + first_name + "\nLast Name: " + last_name);
//                            txtEmail.setText(email);
//                            Picasso.get().load(image_url).into(imageView);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                });

        Bundle parameters = new Bundle();
        parameters.putString("fields", "first_name,last_name,email,id");
        request.setParameters(parameters);
        request.executeAsync();

    }

    public void initlizeviews() {

        etuserid = findViewById(R.id.userid);
        etpassword = findViewById(R.id.password);
        login = findViewById(R.id.btn_login);
        registation = findViewById(R.id.btn_register);
        tvforgotpass = findViewById(R.id.forgotpassword);
        pbLoading = findViewById(R.id.progressBar1);
        btn_skip = findViewById(R.id.skip);
        tvforgotpass.setOnClickListener(this);
        registation.setOnClickListener(this);
        login.setOnClickListener(this);
        btn_skip.setOnClickListener(this);
//        loginButton.setOnClickListener(this);
        sign_in_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_login:
                validation();
                return;

            case R.id.btn_register:
                Registation();
                return;

            case R.id.google_sign_in_button:
                googlelogin();
                return;

            case R.id.forgotpassword:
                openforgotpass();
                return;

            case R.id.skip:
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("fname", "VVVInfo Tech");
                intent.putExtra("email", "vvvinfotech@android.co");
                intent.putExtra("image_url", "");
                startActivity(intent);
        }
    }


    public void openforgotpass() {
        Intent intent = new Intent(this, Forgot_Password.class);
        startActivity(intent);

    }

    @Override
    protected void onStart() {
        super.onStart();
//        preferances.setValue(AccountUtils.GOOGLE_EMAIL, "");

//         account = GoogleSignIn.getLastSignedInAccount(this);
//         if(account==null){
////             updateUI(account);
//             loginButton.setVisibility(View.VISIBLE);
//         }else{
//             loginButton.setVisibility(View.GONE);
//
//         }
    }

    public void googlelogin() {
//            Intent signInIntent = mGoogleSignInClient.getSignInIntent();
//            startActivityForResult(signInIntent, RC_SIGN_IN);
        Intent intent = Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
        startActivityForResult(intent, RC_SIGN_IN);


    }

//        Intent signInIntent = googleSignInClient.getSignInIntent();
//        startActivityForResult(signInIntent, 101);
//    }

    public void Registation() {

        Intent intent = new Intent(this, RegistationActivity.class);
        startActivity(intent);
    }

    public void validation() {
        usermail = etuserid.getText().toString().trim();
        password = etpassword.getText().toString().trim();

        if (usermail.isEmpty()) {
            ToastMessage.onToast(this, "Please Enter Email", ToastMessage.ERROR);
            //  Toast.makeText(this, "Please Enter User ID", Toast.LENGTH_SHORT).show();
        }else if(!isValidEmail(usermail)){
            ToastMessage.onToast(this, "Please Enter valid Email", ToastMessage.ERROR);
        } else if (password.isEmpty()) {
            ToastMessage.onToast(this, "Please Enter Password", ToastMessage.ERROR);
        }else if(spin.getSelectedItem().toString().trim().equalsIgnoreCase("Select Role")) {
            Toast.makeText(LoginActivity.this, "Please Select Role", Toast.LENGTH_SHORT).show();
        } else {
            senddata();

        }
    }

    public void senddata() {
        String url = "https://castercrew.com/mobileapp/login/do_login";

        isLoading(true);
        //Again creating the string request
        StringRequest jsonRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        isLoading(false);

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            boolean result = jsonObject.getBoolean("error");

                            if (!result) {

                                user_id = jsonObject.getString("user_id");
                                preferances.setValue(AccountUtils.PREF_USER_ID,user_id);
                                verify = jsonObject.getString("mobile_veryfy");
                                onsucess();
                                //Toast.makeText(LoginActivity.this, userid, Toast.LENGTH_SHORT).show();

                            } else {
                                onFailed(1);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            onFailed(2);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        isLoading(false);
                        onFailed(3);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("unm", usermail);
                params.put("pwd", password);
                params.put("userrole", roleids);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //adding the string request to request queue
        requestQueue.add(jsonRequest);
    }

    public void onsucess() {

        ToastMessage.onToast(this, "Login Sucess", ToastMessage.SUCCESS);
        sharedPreference.WriteLoginStatus(true);

        if (verify.equals("verify")) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra("fname", "VVVInfo Tech");
            intent.putExtra("email", usermail);
            intent.putExtra("image_url", "");
            startActivity(intent);
        } else if (verify.equals("not_verify")) {
            Intent intent = new Intent(LoginActivity.this, OTPVerfication.class);
            startActivity(intent);
        }
    }

    private void isLoading(boolean loading) {
        if (loading) {
            login.setVisibility(View.GONE);
            pbLoading.setVisibility(View.VISIBLE);
        } else {
            login.setVisibility(View.VISIBLE);
            pbLoading.setVisibility(View.GONE);
        }
    }

    private void onFailed(int index) {
        switch (index) {
            case 1:
                ToastMessage.onToast(this, "Login Failed", ToastMessage.ERROR);
                // Toast.makeText(this, "Login Failed", Toast.LENGTH_SHORT).show();
                break;

            case 2:
                ToastMessage.onToast(this, "We have some techical issues", ToastMessage.ERROR);
                //Toast.makeText(this, "We have some techinical issues", Toast.LENGTH_SHORT).show();
                break;

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
//        System.gc();

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
    public final static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
}
