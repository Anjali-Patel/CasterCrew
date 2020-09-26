package com.castercrewapp.castercrew.Activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.castercrewapp.castercrew.Interfaces.ApiDao;
import com.castercrewapp.castercrew.MainActivity;
import com.castercrewapp.castercrew.R;
import com.castercrewapp.castercrew.model.Banner;
import com.castercrewapp.castercrew.utils.AccountUtils;
import com.castercrewapp.castercrew.utils.ApiClient;
import com.castercrewapp.castercrew.utils.SharedPreferenceUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;

public class SplashActivity extends AppCompatActivity {
    Handler handler;
ImageView splash_logo;
    SharedPreferenceUtils preferances;
    String email,user_id,photo,name,GoogleSignInEmail,google_photo,google_Name;
    private Set<String> Banners;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        splash_logo=findViewById(R.id.splash_logo);
        preferances= SharedPreferenceUtils.getInstance(this);
        google_photo=preferances.getStringValue(AccountUtils.GOOGLE_PHOTO,"");
                google_Name=preferances.getStringValue(AccountUtils.GOOGLE_NAME,"");
          email=preferances.getStringValue(AccountUtils.PREF_EMAIL,"");
        user_id=preferances.getStringValue(AccountUtils.PREF_USER_ID,"");
        name=preferances.getStringValue(AccountUtils.PREF_NAME,"");
        photo=preferances.getStringValue(AccountUtils.PREF_PHOTO_URL,"");
        GoogleSignInEmail=preferances.getStringValue(AccountUtils.GOOGLE_EMAIL,"");
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

  /*      handler = new Handler();

        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                if(!(email.equalsIgnoreCase(""))) {
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    intent.putExtra("fname",name);
                    intent.putExtra("image_url",photo);
                    intent.putExtra("email",email);
                    startActivity(intent);
                    finish();
                }else if(!user_id.equalsIgnoreCase("")){
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }else if(!GoogleSignInEmail.equalsIgnoreCase("")){
                    Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                    intent.putExtra("fname",google_Name);
                    intent.putExtra("image_url",google_photo);
                    intent.putExtra("email",GoogleSignInEmail);
                    startActivity(intent);
                    finish();
                } else{
                    Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }



//                setPreferenceData();
//                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
//                startActivity(intent);
//                finish();
            }
        },3000);
*/
        final Animation zoomanimation = AnimationUtils.loadAnimation(getBaseContext(),R.anim.im1);
        splash_logo.startAnimation(zoomanimation);
        zoomanimation.setAnimationListener(new Animation.AnimationListener()
        {
            public void onAnimationStart(Animation animation){
                TimerTask timerTask = new TimerTask() {
                    @Override
                    public void run() {
                        if(!(email.equalsIgnoreCase(""))) {
                            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                            intent.putExtra("fname",name);
                            intent.putExtra("image_url",photo);
                            intent.putExtra("email",email);
                            startActivity(intent);
                            finish();
                        }else if(!user_id.equalsIgnoreCase("")){
                            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                        }else if(!GoogleSignInEmail.equalsIgnoreCase("")){
                            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                            intent.putExtra("fname",google_Name);
                            intent.putExtra("image_url",google_photo);
                            intent.putExtra("email",GoogleSignInEmail);
                            startActivity(intent);
                            finish();
                        } else{
                            Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }




                    }
                };
                Timer timer = new Timer();
                timer.schedule(timerTask,3000);

            }

            public void onAnimationEnd(Animation animation){


            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }


        });
    }







    @Override
    protected void onStart() {
        super.onStart();
//        getBannerList();
    }

 /*   private void getBannerList() {

        ApiDao apiService = ApiClient.getClient().create(ApiDao.class);

        Call<List<Banner>> call = apiService.getBannerImages("banners");
        call.enqueue(new Callback<List<Banner>>() {
            @Override
            public void onResponse(@NonNull Call<List<Banner>> call, @NonNull retrofit2.Response<List<Banner>> response) {
                List<Banner> cityList = response.body();

                Banners = new HashSet<>();
                if (cityList != null) {

                    for (Banner banner : cityList) {

                        Banners.add(banner.getImg());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<Banner>> call, @NonNull Throwable t) {
                // Log error here since request failed
            }
        });
    }

    private void setPreferenceData() {
        AccountUtils.setBannerImages(this, Banners);

    }*/
}
