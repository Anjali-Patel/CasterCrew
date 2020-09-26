package com.castercrewapp.castercrew;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.castercrewapp.castercrew.Activitys.AssistedServiceActivity;
import com.castercrewapp.castercrew.Activitys.DailyRecommendedActivity;
import com.castercrewapp.castercrew.Activitys.DetailspageActivity;
import com.castercrewapp.castercrew.Activitys.EditProfileActivity;
import com.castercrewapp.castercrew.Activitys.FeedbackActivity;
import com.castercrewapp.castercrew.Activitys.GetTrustedBadgesActivity;
import com.castercrewapp.castercrew.Activitys.HelpCenterActivity;
import com.castercrewapp.castercrew.Activitys.LoginActivity;
import com.castercrewapp.castercrew.Activitys.MembershipPackage;
import com.castercrewapp.castercrew.Activitys.MyChatsActivity;
import com.castercrewapp.castercrew.Activitys.QuickTourActivity;
import com.castercrewapp.castercrew.Activitys.RegistationActivity;
import com.castercrewapp.castercrew.Activitys.SafeCasterCrewActivity;
import com.castercrewapp.castercrew.Activitys.SettingsActivity;
import com.castercrewapp.castercrew.Activitys.SubscriptionDetailActivity;
import com.castercrewapp.castercrew.Activitys.SuccessStoriesActivity;
import com.castercrewapp.castercrew.Activitys.TalentPostListActivity;
import com.castercrewapp.castercrew.Fragments.ActivistFragment;
import com.castercrewapp.castercrew.Fragments.ArtistFragment;
import com.castercrewapp.castercrew.Fragments.AudiosFragment;
import com.castercrewapp.castercrew.Fragments.AuditionsFragment;
import com.castercrewapp.castercrew.Fragments.HomeFragment;
import com.castercrewapp.castercrew.Fragments.InstitutionFragment;
import com.castercrewapp.castercrew.Fragments.MailBoxFragment;
import com.castercrewapp.castercrew.Fragments.MyNewsFragment;
import com.castercrewapp.castercrew.Fragments.NotificationFragment;
import com.castercrewapp.castercrew.Fragments.ProfessionalFragment;
import com.castercrewapp.castercrew.Fragments.ReviewsFragment;
import com.castercrewapp.castercrew.Fragments.ShortlistedMeFragment;
import com.castercrewapp.castercrew.Fragments.ThreeMonthsFragment;
import com.castercrewapp.castercrew.Fragments.VendorsFragment;
import com.castercrewapp.castercrew.Fragments.VideosFragment;
import com.castercrewapp.castercrew.Fragments.ViewedMyProfileFragment;
import com.castercrewapp.castercrew.adapter.AuditionPosterAdapter;
import com.castercrewapp.castercrew.model.AuditionPoster_model;
import com.castercrewapp.castercrew.utils.AccountUtils;
import com.castercrewapp.castercrew.utils.SharedPreferenceUtils;
import com.castercrewapp.castercrew.utils.shared_preference;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.razorpay.Checkout;
import com.razorpay.PaymentResultListener;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MainActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {
    String currentVersion, latestVersion;
    Dialog dialog;
    int id;
    String   User_id;
    ViewGroup viewGroup;
    NavigationView navigationView;

    @Override
    protected void onStart() {
        super.onStart();
        getVersion();
    }

    private void getVersion() {
        String url = " https://castercrew.com/mobile_app/version";
        StringRequest jsonRequest = new StringRequest(com.android.volley.Request.Method.GET, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray json = new JSONArray(response);
                    JSONObject jsonObject=json.getJSONObject(0);
                    latestVersion= jsonObject.getString("version");
                   if(!currentVersion.equalsIgnoreCase(latestVersion)) {
//                       showUpdateDialog();
                   }

                } catch (JSONException e) {
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
//                params.put("uid",uid);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonRequest);
    }

    Toolbar toolbar;
    TabLayout tabLayout;
    FrameLayout frameLayout;
    BottomNavigationView navigation;
    Fragment fragment = null;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    private static final int REQUEST_NO_NETWORK = 1;
    private static final int REQUEST_NETWORK_FAILED = 2;
    shared_preference sp;
    ArrayList<String> helpList;
    boolean f_videio,f_audio,f_review,fnotification,f_vendors,f_institutions,f_activist,f_news,f_auditions,f_mailbox,f_artist,f_professional,f_shortlist,f_viewed_profile,f_editprofile,f_membership;
    String first_name,last_name,email,image_url="";
    SharedPreferenceUtils preferances;
    SubMenu subMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
            setContentView(R.layout.activity_main);
        }catch(Exception e){
            e.printStackTrace();

        }
         toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
//         toolbar = (Toolbar) findViewById(R.id.toolbar);
        PackageManager pm = this.getPackageManager();
        PackageInfo pInfo = null;
        try {
            pInfo =  pm.getPackageInfo(this.getPackageName(),0);
        } catch (PackageManager.NameNotFoundException e1) {
            e1.printStackTrace();
        }
        currentVersion = pInfo.versionName;
        preferances= SharedPreferenceUtils.getInstance(this);
        User_id=preferances.getStringValue(AccountUtils.PREF_USER_ID,"");
        toolbar.setTitle("CasterCrew");
//        setSupportActionBar(toolbar);
        first_name=getIntent().getStringExtra("fname");
//        last_name=getIntent().getStringExtra("lname");

        email=getIntent().getStringExtra("email");
        image_url=getIntent().getStringExtra("image_url");
//        Picasso.get().load(image_url).into();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                MainActivity.this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        loadFragment(new HomeFragment());

         navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setItemIconTintList(null);
         navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setItemIconTintList(null);

//        navigation.setOnNavigationItemSelectedListener(OnNavigationItemSelectedListener);

//        navigation = findViewById(R.id.navigation);
        View header=navigationView.getHeaderView(0);
        ImageView UserImage1=header.findViewById(R.id.UserImage);
        ImageView edit_button=header.findViewById(R.id.dra_profile);
        TextView name1 = (TextView)header.findViewById(R.id.name);
        TextView email1 = (TextView)header.findViewById(R.id.email);
//        UserImage1.setImageResource(R.drawable.logo);

        edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(User_id.equalsIgnoreCase("")){
                    final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("First Register or Login");
                    builder.setMessage("Do you want to Register or Login?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                           Intent intent= new Intent(MainActivity.this,LoginActivity.class);
                           startActivity(intent);
                        }
                    });

                    builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    builder.setCancelable(false);
                    dialog = builder.show();






//                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
//                    //ViewGroup viewGroup =context. findViewById(android.R.id.content);
//                    View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.skip_poppup_layout, viewGroup, false);
//                    TextView no=dialogView.findViewById(R.id.no);
//                    TextView yes=dialogView.findViewById(R.id.yes);
//                    builder.setView(dialogView);
//                    final AlertDialog alertDialog = builder.create();
//                    alertDialog.show();
//                    yes.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            Intent intent= new Intent(MainActivity.this, LoginActivity.class);
//                            startActivity(intent);
//                            alertDialog.dismiss();
//                        }});
//                    no.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            alertDialog.dismiss();
//                        }});
                }else{
                    Intent intent= new Intent(MainActivity.this,EditProfileActivity.class);
                    startActivity(intent);
                }

            }
        });
if(image_url!=null){
    Glide.with(MainActivity.this).load(image_url).into(UserImage1);
      }else{
    Glide.with(MainActivity.this).load(R.drawable.app_logopic).into(UserImage1);
       }
if(!Objects.equals(first_name, "")){
            name1.setText(first_name);
             }else{
            name1.setText(R.string.nav_header_title);
                   }
       if(!Objects.equals(email, "")){
           email1.setText(email);
       }else{
           email1.setText(R.string.nav_header_subtitle);
       }
       navigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_home:
                        fragment = new HomeFragment();
                        fragmentManager = getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container, fragment);
                        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        fragmentTransaction.commit();
                        tabLayout.getTabAt(0).select();
                        toolbar.setTitle("CasterCrew");
                        return true;
                        case R.id.nav_notification:
                            fnotification=true;
                        fragment = new NotificationFragment();
                        fragmentManager = getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container, fragment);
                        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        fragmentTransaction.commit();
                            tabLayout.getTabAt(10).select();
                            return true;

                    case R.id.nav_videos:
                        f_videio=true;
                        fragment = new VideosFragment();
                        fragmentManager = getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container, fragment);
                        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        fragmentTransaction.commit();
//                        tabLayout.getTabAt(6);
                        tabLayout.getTabAt(6).select();
                        return true;
                        case R.id.nav_audios:
                        f_audio=true;
                        fragment = new AudiosFragment();
                        fragmentManager = getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container, fragment);
                        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        fragmentTransaction.commit();
//                        tabLayout.getTabAt(0);
                        tabLayout.getTabAt(7).select();
                            return true;
                    case R.id.dra_reviews:
                        f_review=true;
                        fragment = new ReviewsFragment();
                        fragmentManager = getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container, fragment);
                        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        fragmentTransaction.commit();
                        tabLayout.getTabAt(12).select();
                        return true;



//                        Intent intent6 = new Intent(MainActivity.this, ReviewActivity.class);
//                        startActivity(intent6);

//                    case R.id.action_search:
//                        Intent intent3 = new Intent(MainActivity.this, SearchActivity.class);
//                        startActivity(intent3);
//                        break;

                }

                return false;
            }
        });

//        navigation.setItemIconTintList(null);

        tabLayout=(TabLayout)findViewById(R.id.mTabLayout);
        frameLayout=(FrameLayout)findViewById(R.id.fragment_container);

//        fragment = new HomeFragment();
//        fragmentManager = getSupportFragmentManager();
//        fragmentTransaction = fragmentManager.beginTransaction();
//        fragmentTransaction.replace(R.id.fragment_container, fragment);
//        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//        fragmentTransaction.commit();


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {

            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                // Fragment fragment = null;
                switch (tab.getPosition()) {
                    case 0:
                        toolbar.setTitle("CasterCrew");
                        fragment = new HomeFragment();
                        fragmentManager = getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container, fragment);
                        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        fragmentTransaction.commit();
                        tabLayout.getTabAt(0).select();


//                        Intent intent = new Intent(MainActivity.this,HomeFragment.class);
//                        startActivity(intent);
                     //   fragment = new artist_actor();
                        break;
                    case 1:
                        fragment = new ArtistFragment();
                        fragmentManager = getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container, fragment);
                        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        fragmentTransaction.commit();
                        tabLayout.getTabAt(1).select();





//                        Intent intent1 = new Intent(MainActivity.this,ArtistActivity.class);
//                        startActivity(intent1);
                      //  fragment = new artist_actress();
                        break;
                    case 2:
                        fragment = new ProfessionalFragment();
                        fragmentManager = getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container, fragment);
                        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        fragmentTransaction.commit();
                        tabLayout.getTabAt(2).select();


//                        Intent intent2 = new Intent(MainActivity.this,ProfissionalActivity.class);
//                        startActivity(intent2);
                       // fragment = new artist_anchor();
                        break;
                    case 3:
                        fragment = new VendorsFragment();
                        fragmentManager = getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container, fragment);
                        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        fragmentTransaction.commit();
                        tabLayout.getTabAt(3).select();

//                        Intent intent3 = new Intent(MainActivity.this,VendorsActivity.class);
//                        startActivity(intent3);
                      //  fragment = new artist_childartist();
                        break;
                    case 4:
                        fragment = new InstitutionFragment();
                        fragmentManager = getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container, fragment);
                        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        fragmentTransaction.commit();
                        tabLayout.getTabAt(4).select();

//                        Intent intent4 = new Intent(MainActivity.this, CoachingActivity.class);
//                        startActivity(intent4);
//                      //  fragment = new artist_dancer();
                        break;
                    case 5:
                        f_activist=true;
                        fragment = new ActivistFragment();
                        fragmentManager = getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container, fragment);
                        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        fragmentTransaction.commit();
                        tabLayout.getTabAt(5).select();

//                        Intent intent5 = new Intent(MainActivity.this,ActivistActivity.class);
//                        startActivity(intent5);
                      //  fragment = new artist_model();
                        break;
                    case 6:

                        fragment = new VideosFragment();
                        fragmentManager = getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container, fragment);
                        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        fragmentTransaction.commit();
                        tabLayout.getTabAt(6).select();
                        break;

//                        Intent intent6 = new Intent(MainActivity.this,VideosActivity.class);
//                        startActivity(intent6);
                       // fragment = new artist_production_assistant();

                    case 7:
                         f_audio=true;
                        fragment = new AudiosFragment();
                        fragmentManager = getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container, fragment);
                        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        fragmentTransaction.commit();

                        tabLayout.getTabAt(7).select();


//                        Intent intent6 = new Intent(MainActivity.this,VideosActivity.class);
//                        startActivity(intent6);
                        // fragment = new artist_production_assistant();
                        break;
                    case 8:
                        f_shortlist=true;
                        fragment = new ShortlistedMeFragment();
                        fragmentManager = getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container, fragment);
                        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        fragmentTransaction.commit();
                        tabLayout.getTabAt(8).select();

//                        Intent intent7 = new Intent(MainActivity.this, ShortlistedMeActivity.class);
//                        startActivity(intent7);
                       // fragment = new artist_production_coordinator();
                        break;

                    case 9:
                        f_viewed_profile=true;
                        fragment = new ViewedMyProfileFragment();
                        fragmentManager = getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container, fragment);
                        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        fragmentTransaction.commit();
                        tabLayout.getTabAt(9).select();
                        break;
                    case 10:
                        fnotification=true;
                        fragment = new NotificationFragment();
                        fragmentManager = getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container, fragment);
                        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        fragmentTransaction.commit();
                        tabLayout.getTabAt(10).select();
                        break;

                        case 11:
                        f_news=true;
                        fragment = new MyNewsFragment();
                        fragmentManager = getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container, fragment);
                        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        fragmentTransaction.commit();
                        tabLayout.getTabAt(11).select();
                        break;
                    case 12:
                        f_review=true;
                        fragment = new ReviewsFragment();
                        fragmentManager = getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container, fragment);
                        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        fragmentTransaction.commit();
                        tabLayout.getTabAt(12).select();
                        break;
                    case 13:
                        f_auditions=true;
                        fragment = new AuditionsFragment();
                        fragmentManager = getSupportFragmentManager();
                        fragmentTransaction = fragmentManager.beginTransaction();
                        fragmentTransaction.replace(R.id.fragment_container, fragment);
                        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                        fragmentTransaction.commit();
                        tabLayout.getTabAt(13).select();
                        break;
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
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }  else if(f_artist) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, new HomeFragment());
            transaction.addToBackStack(null);
            toolbar.setTitle("CasterCrew");
            transaction.commit();
            navigation.setSelectedItemId(R.id.nav_home);
            f_artist=false;
            tabLayout.getTabAt(0).select();

        }
        else if(f_editprofile) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, new HomeFragment());
            transaction.addToBackStack(null);
            toolbar.setTitle("CasterCrew");
            transaction.commit();
            navigation.setSelectedItemId(R.id.nav_home);
            f_editprofile=false;
            tabLayout.getTabAt(0).select();
        }
        else if(f_audio) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, new HomeFragment());
            transaction.addToBackStack(null);
            toolbar.setTitle("CasterCrew");
            transaction.commit();
            navigation.setSelectedItemId(R.id.nav_home);
            f_audio=false;
            tabLayout.getTabAt(0).select();
        }  else if(f_videio) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, new HomeFragment());
            transaction.addToBackStack(null);
            toolbar.setTitle("CaterCrew");
            transaction.commit();
            navigation.setSelectedItemId(R.id.nav_home);
            f_videio=false;
            tabLayout.getTabAt(0).select();

        } else if(f_activist) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, new HomeFragment());
            transaction.addToBackStack(null);
            toolbar.setTitle("CaterCrew");
            transaction.commit();
            navigation.setSelectedItemId(R.id.nav_home);
            f_activist=false;
            tabLayout.getTabAt(0).select();

        }
        else if(fnotification) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, new HomeFragment());
            transaction.addToBackStack(null);
            toolbar.setTitle("CaterCrew");
            transaction.commit();
            navigation.setSelectedItemId(R.id.nav_home);
            fnotification=false;
            tabLayout.getTabAt(0).select();


        } else if(f_review) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, new HomeFragment());
            transaction.addToBackStack(null);
            toolbar.setTitle("CaterCrew");
            transaction.commit();
            navigation.setSelectedItemId(R.id.nav_home);
            f_review=false;
            tabLayout.getTabAt(0).select();

        } else if(f_vendors) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, new HomeFragment());
            transaction.addToBackStack(null);
            toolbar.setTitle("CaterCrew");
            transaction.commit();
            navigation.setSelectedItemId(R.id.nav_home);
            f_vendors=false;
            tabLayout.getTabAt(0).select();


        } else if(f_institutions) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, new HomeFragment());
            transaction.addToBackStack(null);
            toolbar.setTitle("CaterCrew");
            transaction.commit();
            navigation.setSelectedItemId(R.id.nav_home);
            f_institutions=false;
            tabLayout.getTabAt(0).select();


        } else if(f_news) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, new HomeFragment());
            transaction.addToBackStack(null);
            toolbar.setTitle("CaterCrew");
            transaction.commit();
            navigation.setSelectedItemId(R.id.nav_home);
            f_news=false;
            tabLayout.getTabAt(0).select();


        } else if(f_auditions) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, new HomeFragment());
            transaction.addToBackStack(null);
            toolbar.setTitle("CaterCrew");
            transaction.commit();
            navigation.setSelectedItemId(R.id.nav_home);
            f_auditions=false;
            tabLayout.getTabAt(0).select();


        } else if(f_professional) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, new HomeFragment());
            transaction.addToBackStack(null);
            toolbar.setTitle("CaterCrew");
            transaction.commit();
            navigation.setSelectedItemId(R.id.nav_home);
            f_professional=false;
            tabLayout.getTabAt(0).select();
        }else if(f_shortlist) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, new HomeFragment());
            transaction.addToBackStack(null);
            toolbar.setTitle("CaterCrew");
            transaction.commit();
            navigation.setSelectedItemId(R.id.nav_home);
            f_shortlist=false;
            tabLayout.getTabAt(0).select();
        } else if(f_viewed_profile) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, new HomeFragment());
            transaction.addToBackStack(null);
            toolbar.setTitle("CaterCrew");
            transaction.commit();
            navigation.setSelectedItemId(R.id.nav_home);
            f_viewed_profile=false;
            tabLayout.getTabAt(0).select();

        } else if(f_mailbox) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment_container, new HomeFragment());
            transaction.addToBackStack(null);
            toolbar.setTitle("CaterCrew");
            transaction.commit();
            navigation.setSelectedItemId(R.id.nav_home);
            f_mailbox=false;
            tabLayout.getTabAt(0).select();



        }

        else if (id == R.id.home || navigation.getSelectedItemId() == R.id.nav_home) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.create();
            builder.setMessage("Do You Want to Exit ?");
            builder.setPositiveButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent(Intent.ACTION_MAIN);
                    intent.addCategory(Intent.CATEGORY_HOME);
                    startActivity(intent);
                }
            });
            builder.show();
        } else {
            if (id != R.id.home || navigation.getSelectedItemId() != R.id.nav_home) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, new HomeFragment());
                transaction.addToBackStack(null);
//                toolbar.setTitle("UABN Home");
                transaction.commit();
                navigation.setSelectedItemId(R.id.nav_home);
//                tabLayout.getTabAt(0).select();


            }
        }
       /* else {
            super.onBackPressed();
        }*/
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_NO_NETWORK ||
                requestCode == REQUEST_NETWORK_FAILED) {

            if (resultCode == Activity.RESULT_OK) {
            }
        }
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
            intent.putExtra("cat","Artist");
            startActivity(intent);
//            Bundle bundle1 = new Bundle();
//                bundle1.putString("cat", "Artist");
//                HomeFragment categoryFragment = new HomeFragment();
//                categoryFragment.setArguments(bundle1);
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,categoryFragment).commit();
        }else if (id == R.id.action_profissional){
            Intent intent1 = new Intent(this, TalentPostListActivity.class);
            intent1.putExtra("cat","Professionals");
            startActivity(intent1);
        }
        else if (id == R.id.action_vender){
            Intent intent2 = new Intent(this, TalentPostListActivity.class);
            intent2.putExtra("cat","vendors");
            startActivity(intent2);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {

//            case R.id.nav_home:
//                fragment = new HomeFragment();
//                fragmentManager = getSupportFragmentManager();
//                fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.fragment_container, fragment);
//                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//                fragmentTransaction.commit();
//                tabLayout.getTabAt(0).select();
//                toolbar.setTitle("CasterCrew");
//                // startActivity(new Intent(this,HomeFragment.class));
//                break;
            case R.id.shortlisted_me:
                f_auditions=true;
                fragment = new AuditionsFragment();
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragmentTransaction.commit();
                tabLayout.getTabAt(8).select();
                break;

            case R.id.Personalized_servic:
                Intent Personalized_servic = new Intent(MainActivity.this, AssistedServiceActivity.class);
                startActivity(Personalized_servic);
                break;
            case R.id.safe_castercrew:
                Intent safe_castercrew = new Intent(MainActivity.this, SafeCasterCrewActivity.class);
                startActivity(safe_castercrew);
                break;
            case R.id.help_cenetr:
                Intent help_cenetr = new Intent(MainActivity.this, HelpCenterActivity.class);
                startActivity(help_cenetr);
                break;
            case R.id.privacy_policy:
                Uri webpage = Uri.parse("https://castercrew.com/profile/content_page/Privacy_Policy/3");
                Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }else{
                    //Page not found
                }
//                Intent privacy_policy = new Intent(MainActivity.this, PrivacyPolicyActivity.class);
//                startActivity(privacy_policy);
                break;
            case R.id.rate_us:
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                //ViewGroup viewGroup =context. findViewById(android.R.id.content);

                View dialogView = LayoutInflater.from(MainActivity.this).inflate(R.layout.rate_us_dialog, viewGroup, false);
                TextView later=dialogView.findViewById(R.id.later);
                TextView rate_now=dialogView.findViewById(R.id.rate_now);
                RatingBar ratingBar=dialogView.findViewById(R.id.ratingbar);

                builder.setView(dialogView);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
                rate_now.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                        rbRatingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
//                            @Override
//                            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
//
//                                Float ratingVal = (Float) rating;
//                                Float ratingvalue = (Float) rbRatingBar.getRating();
//                                Toast.makeText(getApplicationContext(), " Ratings : " + String.valueOf(ratingVal) + "", Toast.LENGTH_SHORT).show();
//                                Toast.makeText(getApplicationContext(), " Ratings1 : " + ratingvalue + "", Toast.LENGTH_SHORT).show();
//                            }
//                        });
                        String  Strrating= String.valueOf(ratingBar.getRating());
                    Float a=    Float.parseFloat(Strrating);
                        if(Strrating.equalsIgnoreCase("0.0")){
                            Toast.makeText(MainActivity.this,"Choose Star for Rating",Toast.LENGTH_LONG).show();
//                            alertDialog.dismiss();
                        }else if(Strrating.equalsIgnoreCase("0.5")||Strrating.equalsIgnoreCase("1.0")||Strrating.equalsIgnoreCase("1.5")||Strrating.equalsIgnoreCase("2.0")||Strrating.equalsIgnoreCase("2.5")||Strrating.equalsIgnoreCase("3.0")){
                            Intent intent= new Intent(MainActivity.this, FeedbackActivity.class);
                            startActivity(intent);
                            alertDialog.dismiss();
                        }else if(Strrating.equalsIgnoreCase("3.5")||Strrating.equalsIgnoreCase("4.0")||Strrating.equalsIgnoreCase("5.0")){
                            Toast.makeText(MainActivity.this,"Our day just got better!Redirecting you to Google play store to rate our app",Toast.LENGTH_LONG).show();
                            Uri webpage = Uri.parse("https://play.google.com/store/apps/details?id=com.castercrewapp.castercrew&hl=en_IN");
                            Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                            if (intent.resolveActivity(getPackageManager()) != null) {
                                startActivity(intent);
                            }else{
                                //Page not found
                            }
                            alertDialog.dismiss();
                        }


                    }
                });
                later.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        alertDialog.dismiss();
                    }
                });




//                Intent rate_us = new Intent(MainActivity.this, GetTrustedBadgesActivity.class);
//                startActivity(rate_us);
                break;
            case R.id.quick_tour:
                Intent quick_tour = new Intent(MainActivity.this, QuickTourActivity.class);
                startActivity(quick_tour);
                break;
            case R.id.trust_badges:
                Intent trust_badges= new Intent(MainActivity.this, GetTrustedBadgesActivity.class);
                startActivity(trust_badges);
                break;
            case R.id.daily_recommendations:
                Intent intent1= new Intent(MainActivity.this, DailyRecommendedActivity.class);
                startActivity(intent1);
                break;

            case R.id.success_stories:
                Intent intent3= new Intent(MainActivity.this, SuccessStoriesActivity.class);
                startActivity(intent3);
                break;
            case R.id.settings:
                Intent intent5= new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent5);
                break;
            case R.id.your_chats:
                Intent intent4= new Intent(MainActivity.this, MyChatsActivity.class);
                startActivity(intent4);
                break;
//                f_editprofile=true;
//                fragment = new EditProfileFragment();
//                fragmentManager = getSupportFragmentManager();
//                fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.fragment_container, fragment);
//                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//                fragmentTransaction.commit();
//                toolbar.setTitle("Edit Profile");
//                tabLayout.getTabAt(0).select();




            case R.id.dra_artist:
                f_artist=true;
                fragment = new ArtistFragment();
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragmentTransaction.commit();
                toolbar.setTitle("Artist");
                tabLayout.getTabAt(1).select();

//                startActivity(new Intent(this, ArtistActivity.class));
                break;
            case R.id.dra_profissional:
                f_professional=true;
                fragment = new ProfessionalFragment();
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragmentTransaction.commit();
                toolbar.setTitle("Professional");
                tabLayout.getTabAt(2).select();
                break;
            case R.id.dra_vendors:
                f_vendors=true;
                fragment = new VendorsFragment();
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragmentTransaction.commit();
                toolbar.setTitle("Vendors");
                tabLayout.getTabAt(3).select();
                break;
            case R.id.dra_institute:
                f_institutions=true;
                fragment = new InstitutionFragment();
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragmentTransaction.commit();
                toolbar.setTitle("Institutions");
                tabLayout.getTabAt(4).select();
                break;
            case R.id.dra_activist:
                f_activist=true;
                fragment = new ActivistFragment();
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragmentTransaction.commit();
                toolbar.setTitle("Activist");
                tabLayout.getTabAt(5).select();


//                Intent intent3 = new Intent(this,ActivistActivity.class);
//                startActivity(intent3);
                break;

            case R.id.dra_reviews:
                f_review=true;
                fragment = new ReviewsFragment();
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragmentTransaction.commit();
                toolbar.setTitle("Reviews");
                tabLayout.getTabAt(12).select();
                break;
//                Intent intent6 = new Intent(this, ReviewActivity.class);
//                startActivity(intent6);

            case R.id.dra_videos:
                f_videio=true;
                fragment = new VideosFragment();
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragmentTransaction.commit();
                toolbar.setTitle("Videos");
                tabLayout.getTabAt(6).select();
                break;

            case R.id.dra_audios:
                      f_audio=true;
                      fragment = new AudiosFragment();
                      fragmentManager = getSupportFragmentManager();
                      fragmentTransaction = fragmentManager.beginTransaction();
                      fragmentTransaction.replace(R.id.fragment_container, fragment);
                      fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                      fragmentTransaction.commit();
                      toolbar.setTitle("Audios");
                       tabLayout.getTabAt(7).select();

//                Intent intent4 = new Intent(this,VideosActivity.class);
//                startActivity(intent4);
                break;
            case R.id.dra_notifications:
                fnotification=true;
                fragment = new NotificationFragment();
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragmentTransaction.commit();
                tabLayout.getTabAt(10).select();
                break;
            case R.id.dra_auditions:
                f_auditions=true;
                fragment = new AuditionsFragment();
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragmentTransaction.commit();
                toolbar.setTitle("Auditions");
                tabLayout.getTabAt(13).select();
                break;
            case R.id.dra_mailbox:
                f_mailbox=true;
                fragment = new MailBoxFragment();
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragmentTransaction.commit();
                toolbar.setTitle("MailBox");
//                tabLayout.getTabAt(0).select();
                break;
            case R.id.dra_news:
                f_news=true;
                fragment = new MyNewsFragment();
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment);
                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                fragmentTransaction.commit();
                toolbar.setTitle("News");
                tabLayout.getTabAt(11).select();
                break;
                case R.id.membership_package:
                Intent membershipPackage= new Intent(MainActivity.this, MembershipPackage.class);
                startActivity(membershipPackage);
//            f_membership=true;
////                fragment = new MembershipPackageFragment();
////                fragmentManager = getSupportFragmentManager();
////                fragmentTransaction = fragmentManager.beginTransaction();
////                fragmentTransaction.replace(R.id.fragment_container, fragment);
////                fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
////                fragmentTransaction.commit();
////                toolbar.setTitle("Membership Package");
////                tabLayout.getTabAt(13).select();
                break;
                case R.id.nav_logout:
                logout();
                default:
                break;
        }
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
      //  transaction.replace(R.id.fragment_container,fragment);
        transaction.commit();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void logout(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.create();
            builder.setMessage("Are you sure you want to logout?");

            builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();

                }
            });
            builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                  String  email_id=preferances.getStringValue(AccountUtils.PREF_EMAIL,"");
                    String  Googleemail_id=preferances.getStringValue(AccountUtils.GOOGLE_EMAIL,"");

//                 String   User_id=preferances.getStringValue(AccountUtils.PREF_USER_ID,"");
//                    SharedPreferences sharedPreferences = MainActivity.this.getSharedPreferences("Castercrew", Context.MODE_PRIVATE);
//                    String id = sharedPreferences.getString("id", "");
                 if(!email_id.equalsIgnoreCase("")){
                     preferances.setValue(AccountUtils.PREF_EMAIL, "");
                     startActivity(new Intent(MainActivity.this, LoginActivity.class));
                     finish();
                 } if(!User_id.equalsIgnoreCase("")){
                     preferances.setValue(AccountUtils.PREF_USER_ID,"");
                     Intent normalIntent= new Intent(MainActivity.this,LoginActivity.class);
                     startActivity(normalIntent);
//                     finish();
//                     SharedPreferences sharedPreferences1 = MainActivity.this.getSharedPreferences("Castercrew", Context.MODE_PRIVATE);
//                     SharedPreferences.Editor editor = sharedPreferences1.edit();
//                     editor.putString("id", "");
//                     editor.commit();

//                     preferances.setValue(AccountUtils.PREF_USER_ID, "");
//                     startActivity(new Intent(MainActivity.this, LoginActivity.class));
//                     finish();

                 } if(!Googleemail_id.equalsIgnoreCase("")){
                     preferances.setValue(AccountUtils.GOOGLE_EMAIL, "");
//                     preferances.setValue(AccountUtils.GOOGLE_NAME, "");
//                     preferances.setValue(AccountUtils.GOOGLE_PHOTO, "");
                     startActivity(new Intent(MainActivity.this, LoginActivity.class));
                     finish();
                 }

//                    sp = new shared_preference(MainActivity.this);
//                    sp.WriteLoginStatus(false);

                }
            });
            builder.show();
    }
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
    }
    private boolean loadFragment(HomeFragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
    private void showUpdateDialog(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("A new version of this app is Available.");
        builder.setMessage("Do you want to update?");
        builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse
                        ("https://play.google.com/store/apps/details?id=com.castercrewapp.castercrew&hl=en_IN")));
                dialog.dismiss();
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.setCancelable(false);
        dialog = builder.show();
    }





}
