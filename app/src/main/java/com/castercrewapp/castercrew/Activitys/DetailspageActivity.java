package com.castercrewapp.castercrew.Activitys;


import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.castercrewapp.castercrew.Interfaces.ApiDao;
import com.castercrewapp.castercrew.Listeners.OnItemClickListener;
import com.castercrewapp.castercrew.R;
import com.castercrewapp.castercrew.adapter.AuditionPosterAdapter;
import com.castercrewapp.castercrew.adapter.NewsAdapter;
import com.castercrewapp.castercrew.adapter.ReviewSmallCardAdapter;
import com.castercrewapp.castercrew.adapter.horzointal_rvlist_adapter;
import com.castercrewapp.castercrew.adapter.recent_featured_profile_adapter;
import com.castercrewapp.castercrew.connection.NetworkConnection;
import com.castercrewapp.castercrew.model.AuditionPoster_model;
import com.castercrewapp.castercrew.model.ExpandedMenuModel;
import com.castercrewapp.castercrew.model.recent_featured_profile_list;
import com.castercrewapp.castercrew.model.recent_featured_videos_list;
import com.castercrewapp.castercrew.utils.AccountUtils;
import com.castercrewapp.castercrew.utils.ApiClient;
import com.castercrewapp.castercrew.utils.SharedPreferenceUtils;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

public class DetailspageActivity extends AppCompatActivity implements OnItemClickListener, View.OnClickListener {
    String user_id;
    SharedPreferenceUtils preferances;

    TextView religion,caste,sub_caste,education,institution,occupation,organization,employed,annual_income,country,state,city,citizenship;
    String str_religion,str_caste,str_subcaste,str_education,strinstitution,str_occupation,str_organization,str_employed,str_annual_income,str_country,str_state,str_city,str_citizenship;
    TextView basic_name,basic_age,basic_height,basic_weight,basic_maritalStatus,basic_motherTongue,basic_physical_status,basic_bodytypes,basic_complexion,basic_profilecreate_by,basic_eatinghabits,drinking,smoking;
    String str_basic_age,str_basicheight,str_basicweight,str_basicmaritalstatus,str_basic_mothertongue,str_basicphysicalstatus,str_bodytypes,str_basic_complexion,str_profilecreate_by,str_eatinghabits,str_drinking,str_smoking;
//LinearLayout chat,call;
    TextView tvheadname,tvhead_description,tvname,tvcategory,tvsubcategory,tvtalenttitle,tvexperience,
    tvlanguageknows,tvwebsite,tvtalentstatus,tvremarks,tvindustryname,tvuid;
    ViewGroup viewGroup;
    private ArrayList<recent_featured_videos_list> itemlist_videos;;
    String name,description,category,subcategory,talenttitle,uid,experience,languageknows,website
    ,talent_status,remarks,industryname,image;
    ImageView iv_profile,lock1,lock2,lock3,lock4;
    NewsAdapter newsAdapter;
    ArrayList<ExpandedMenuModel> newsArrayList;
    AuditionPosterAdapter auditionPosterAdapter;
    ArrayList<AuditionPoster_model>auditionArrayList;
    ReviewSmallCardAdapter reviewSmallCardAdapter;
    private RecyclerView recyclerView_artist,recyclerView_hr_list,all_review_list,news_list,audition_postList;
    recent_featured_profile_adapter adapter_artist;
    horzointal_rvlist_adapter adapter_hr_list;
    private ArrayList<recent_featured_profile_list> itemList_artist,itemlist_ht_list;
    Button btn_upgrade;
    LinearLayout add_video,add_image,review;
    ImageView insta,you_tube,tweeter,facebook,linkedin;
    ImageView call,chat,like,download,share;
    TextView membership,verified;
    LinearLayout all_reviews,visited_profiles,member_also_visited,news_poster,audition_poster;
//CardView relative1,relative2,relative3;
EditText tweeter_edit_link,socila_facebook,instagram_edit,linkedIn_edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailspage);
        str_religion=getIntent().getStringExtra("");
        str_caste=getIntent().getStringExtra("");
        str_subcaste=getIntent().getStringExtra("");
        str_education=getIntent().getStringExtra("");
        strinstitution=getIntent().getStringExtra("");
        str_occupation=getIntent().getStringExtra("");
        str_organization=getIntent().getStringExtra("");
        str_annual_income=getIntent().getStringExtra("");
        str_country=getIntent().getStringExtra("");
        str_state=getIntent().getStringExtra("");
        str_city=getIntent().getStringExtra("");
        str_citizenship=getIntent().getStringExtra("");
        str_employed=getIntent().getStringExtra("");

        str_basic_age=getIntent().getStringExtra("");
        str_basicheight=getIntent().getStringExtra("");
        str_basicmaritalstatus=getIntent().getStringExtra("");
        str_basic_mothertongue=getIntent().getStringExtra("");
        str_basicphysicalstatus=getIntent().getStringExtra("");
        str_bodytypes=getIntent().getStringExtra("");
        str_basic_complexion=getIntent().getStringExtra("");
        str_profilecreate_by=getIntent().getStringExtra("");
        str_eatinghabits=getIntent().getStringExtra("");
        str_drinking=getIntent().getStringExtra("");
        str_smoking=getIntent().getStringExtra("");
        str_basicweight=getIntent().getStringExtra("");
        basic_name=findViewById(R.id.basic_name);

        basic_age=findViewById(R.id.basic_age);
        basic_height=findViewById(R.id.basic_height);
        basic_weight=findViewById(R.id.basic_weight);
        basic_maritalStatus=findViewById(R.id.basic_maritalStatus);
        basic_motherTongue=findViewById(R.id.basic_motherTongue);
        basic_physical_status=findViewById(R.id.basic_physical_status);
        basic_bodytypes=findViewById(R.id.basic_bodytypes);
        basic_complexion=findViewById(R.id.basic_complexion);
        basic_profilecreate_by=findViewById(R.id.basic_profilecreate_by);
        basic_eatinghabits=findViewById(R.id.basic_eatinghabits);
        drinking=findViewById(R.id.drinking);
        smoking=findViewById(R.id.smoking);

        religion=findViewById(R.id.religion);
        caste=findViewById(R.id.caste);
        sub_caste=findViewById(R.id.sub_caste);
        education=findViewById(R.id.education);
        institution=findViewById(R.id.institution);
        occupation=findViewById(R.id.occupation);
        organization=findViewById(R.id.organization);
        employed=findViewById(R.id.employed);
        annual_income=findViewById(R.id.annual_income);
        country=findViewById(R.id.country);
        state=findViewById(R.id.state);
        city=findViewById(R.id.city);
        citizenship=findViewById(R.id.citizenship);

        preferances= SharedPreferenceUtils.getInstance(getApplicationContext());
        user_id=preferances.getStringValue(AccountUtils.PREF_USER_ID,"");
        audition_poster=findViewById(R.id.audition_poster);
        audition_postList=findViewById(R.id.audition_postList);
        news_list=findViewById(R.id.news_list);
        audition_postList=findViewById(R.id.audition_postList);
        lock1=findViewById(R.id.lock1);
        lock2=findViewById(R.id.lock2);
        lock3=findViewById(R.id.lock3);
        lock4=findViewById(R.id.lock4);
        news_poster=findViewById(R.id.news_poster);
        all_review_list=findViewById(R.id.all_review_list);
        all_reviews=findViewById(R.id.all_reviews);
        visited_profiles=findViewById(R.id.visited_profiles);
        member_also_visited=findViewById(R.id.member_also_visited);
        call=findViewById(R.id.call);
        chat=findViewById(R.id.chat);
        tweeter_edit_link=findViewById(R.id.tweeter_edit_link);
        socila_facebook=findViewById(R.id.socila_facebook);
        instagram_edit=findViewById(R.id.instagram_edit);
        linkedIn_edit=findViewById(R.id.linkedIn_edit);
        verified=findViewById(R.id.verified);
        membership=findViewById(R.id.membership);
        membership.setText("Membership");
        verified.setText("Verified");
        like=findViewById(R.id.like);
        download=findViewById(R.id.download);
        share=findViewById(R.id.share);
        add_image=findViewById(R.id.add_image);
        add_video=findViewById(R.id.add_video);
        review=findViewById(R.id.review);
        insta=findViewById(R.id.insta);
        you_tube=findViewById(R.id.you_tube);
        tweeter=findViewById(R.id.tweeter);
        facebook=findViewById(R.id.facebook);
        linkedin=findViewById(R.id.linkedin);
        newsArrayList= new ArrayList<>();
        itemlist_videos=new ArrayList<>();
        auditionArrayList=new ArrayList<>();
        LinearLayoutManager linearLayoutManagerauditionList = new LinearLayoutManager(DetailspageActivity.this);
        linearLayoutManagerauditionList.setOrientation(LinearLayoutManager.VERTICAL);
        audition_postList.setLayoutManager(linearLayoutManagerauditionList);
        audition_postList.setHasFixedSize(true);
        auditionPosterAdapter = new AuditionPosterAdapter(DetailspageActivity.this, auditionArrayList);
        audition_postList.setAdapter(auditionPosterAdapter);
        LinearLayoutManager linearLayoutManagernews = new LinearLayoutManager(DetailspageActivity.this);
        linearLayoutManagernews.setOrientation(LinearLayoutManager.VERTICAL);
        news_list.setLayoutManager(linearLayoutManagernews);
        news_list.setHasFixedSize(true);
        newsAdapter = new NewsAdapter(DetailspageActivity.this, newsArrayList,user_id);
        news_list.setAdapter(newsAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DetailspageActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        all_review_list.setLayoutManager(linearLayoutManager);
        all_review_list.setHasFixedSize(true);
        reviewSmallCardAdapter = new ReviewSmallCardAdapter(DetailspageActivity.this, itemlist_videos,user_id);
        all_review_list.setAdapter(reviewSmallCardAdapter);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            uid = bundle.getString("uid");
            name = bundle.getString("name");
            description = bundle.getString("description");
            category = bundle.getString("category");
            subcategory = bundle.getString("subcategory");
            talenttitle = bundle.getString("talenttitle");
            experience = bundle.getString("experience");
            languageknows = bundle.getString("languageknows");
            website = bundle.getString("website");
            talent_status = bundle.getString("talentstatus");
            remarks = bundle.getString("remarks");
            industryname = bundle.getString("industryname");
            image = bundle.getString("image");

        }
        linkedIn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailspageActivity.this);
                //ViewGroup viewGroup = context.findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.image_video_membership_dialog, viewGroup, false);
                LinearLayout upgrade=dialogView.findViewById(R.id.unlock_package);

                builder.setView(dialogView);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
                upgrade.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        linkedIn_edit.setText("https://www.linkedin.com/in/anjali-patel-951172137/");
                        Intent intent= new Intent(DetailspageActivity.this, MembershipPackage.class);
                        startActivity(intent);
                        alertDialog.dismiss();
                        lock4.setVisibility(View.GONE);
                        linkedIn_edit.setText("https://www.linkedin.com/in/anjali-patel-951172137/");
                        linkedIn_edit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent myIntent = new Intent(DetailspageActivity.this,SocialWebViewActivity.class);
                                myIntent.putExtra("url","https://www.linkedin.com/in/anjali-patel-951172137/");
                                startActivity(myIntent);
                            }
                        });
                    }
                });
            }
        });
        tweeter_edit_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailspageActivity.this);
                //ViewGroup viewGroup = context.findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.image_video_membership_dialog, viewGroup, false);
                LinearLayout upgrade=dialogView.findViewById(R.id.unlock_package);

                builder.setView(dialogView);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
                upgrade.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent= new Intent(DetailspageActivity.this, MembershipPackage.class);
                        startActivity(intent);
                        alertDialog.dismiss();
                        lock2.setVisibility(View.GONE);
                        tweeter_edit_link.setText("https://twitter.com/profile.php?id=100010511946913");
                        tweeter_edit_link.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent myIntent = new Intent(DetailspageActivity.this,SocialWebViewActivity.class);
                                myIntent.putExtra("url","https://twitter.com/profile.php?id=100010511946913");
                                startActivity(myIntent);
                            }
                        });
                    }
                });

            }
        });
        socila_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    AlertDialog.Builder builder = new AlertDialog.Builder(DetailspageActivity.this);
                    //ViewGroup viewGroup = context.findViewById(android.R.id.content);
                    View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.image_video_membership_dialog, viewGroup, false);
                    LinearLayout upgrade=dialogView.findViewById(R.id.unlock_package);

                    builder.setView(dialogView);
                    final AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                    upgrade.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent= new Intent(DetailspageActivity.this, MembershipPackage.class);
                            startActivity(intent);
                            alertDialog.dismiss();
                            lock1.setVisibility(View.GONE);
                            socila_facebook.setText("https://www.facebook.com/profile.php?id=100010511946913");
                            socila_facebook.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent myIntent = new Intent(DetailspageActivity.this,SocialWebViewActivity.class);
                                    myIntent.putExtra("url","https://www.facebook.com/profile.php?id=100010511946913");
                                    startActivity(myIntent);
                                }
                            });
                        }
                    });


            }
        });
        instagram_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailspageActivity.this);
                //ViewGroup viewGroup = context.findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.image_video_membership_dialog, viewGroup, false);
                LinearLayout upgrade=dialogView.findViewById(R.id.unlock_package);

                builder.setView(dialogView);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
                upgrade.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent= new Intent(DetailspageActivity.this, MembershipPackage.class);
                        startActivity(intent);
                        alertDialog.dismiss();
                        lock3.setVisibility(View.GONE);
                        instagram_edit.setText("https://www.instagram.com/anjalics14.academic/");
                        instagram_edit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent myIntent = new Intent(DetailspageActivity.this,SocialWebViewActivity.class);
                                myIntent.putExtra("url","https://www.instagram.com/anjalics14.academic/");
                                startActivity(myIntent);
                            }
                        });
                    }
                });
            }
        });

        news_poster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(DetailspageActivity.this,NewsPosterResultActivity.class);
                intent.putExtra("name",name);
                intent.putExtra("image",image);
                intent.putExtra("poser_id",uid);
                startActivity(intent);
            }
        });
        audition_poster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(DetailspageActivity.this,AuditionAllActivity.class);
                intent.putExtra("all_audios_tittle",true);

                startActivity(intent);
            }
        });
        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(DetailspageActivity.this,ReviewsAllVideolActivity.class);
                intent.putExtra("all_review_tittle",true);
                startActivity(intent);
            }
        });
        all_reviews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(DetailspageActivity.this,ReviewsAllVideolActivity.class);
                intent.putExtra("all_review_tittle",true);
                startActivity(intent);
            }
        });
        member_also_visited.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(DetailspageActivity.this,AlsoVisitedActivity.class);
//                intent.putExtra("all_review_tittle",true);
                startActivity(intent);
            }
        });
        visited_profiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(DetailspageActivity.this,VisitedProfileActivity.class);
//                intent.putExtra("all_review_tittle",true);
                startActivity(intent);
            }
        });
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(DetailspageActivity.this,ChatActivity.class);
                startActivity(intent);
            }
        });
        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://castercrew.com/mobile_app/user_likes";
                StringRequest jsonRequest = new StringRequest(com.android.volley.Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject json = new JSONObject(response);
                            int   result=json.getInt("res");
                            if(result==1){
                                like.setImageResource(R.drawable.red_heart);
                                Toast.makeText(DetailspageActivity.this,"Shortlisted successfully", Toast.LENGTH_LONG).show();

                            }else{
                                like.setImageResource(R.drawable.orange_heart);
                                Toast.makeText(DetailspageActivity.this,"Already Shortlisted ", Toast.LENGTH_LONG).show();
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
                        params.put("from_uid",user_id);
                        params.put("to_uid",uid);
                        params.put("action","180");

                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(DetailspageActivity.this);
                requestQueue.add(jsonRequest);
            }
        });
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailspageActivity.this);
                //ViewGroup viewGroup = context.findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.membership_dialog, viewGroup, false);
                Button upgrade=dialogView.findViewById(R.id.upgrade);
                ImageView image_view=dialogView.findViewById(R.id.image_view);
                TextView contact_description=dialogView.findViewById(R.id.contact_description);
                contact_description.setText("Contact "+name+"on");
                TextView name1=dialogView.findViewById(R.id.name);
                name1.setText(name);
                Picasso.with(DetailspageActivity.this).load(image).error(R.drawable.men).into(image_view);
                builder.setView(dialogView);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
                upgrade.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent= new Intent(DetailspageActivity.this, MembershipPackage.class);
                        startActivity(intent);
                        alertDialog.dismiss();
                    }
                });
            }
        });
        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailspageActivity.this);
                //ViewGroup viewGroup = context.findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.membership_dialog, viewGroup, false);
                Button upgrade=dialogView.findViewById(R.id.upgrade);
                ImageView image_view=dialogView.findViewById(R.id.image_view);
                TextView contact_description=dialogView.findViewById(R.id.contact_description);
                contact_description.setText("Contact "+name+"on");
                TextView name1=dialogView.findViewById(R.id.name);
                name1.setText(name);
                Picasso.with(DetailspageActivity.this).load(image).error(R.drawable.men).into(image_view);
                builder.setView(dialogView);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
                upgrade.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        download.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Toast.makeText(DetailspageActivity.this,"Downloading Profile........",Toast.LENGTH_LONG).show();
                            }
                        });
                        Intent intent= new Intent(DetailspageActivity.this, MembershipPackage.class);
                        startActivity(intent);
                        alertDialog.dismiss();

                    }
                });
            }
        });

        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String  image_path = item.getFull_name();
//                String website_url=item.getArtist_name()+","+item.getTalent_title()+","+item.getReg_dt();
//                title=Items.getTitle();

                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("image/png");
//                i.putExtra(Intent.EXTRA_SUBJECT, title);
//                i.putExtra(Intent.EXTRA_TEXT, image_path+"\n \n"+website_url);
//                i.putExtra(Intent.EXTRA_TEXT,image_path);
                startActivity(Intent.createChooser(i, "Share "));
            }
        });


        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(DetailspageActivity.this,ReviewsAllVideolActivity.class);
                intent.putExtra("all_review_tittle",true);
                startActivity(intent);            }
        });
        add_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailspageActivity.this);
                //ViewGroup viewGroup = context.findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.image_video_membership_dialog, viewGroup, false);
                LinearLayout upgrade=dialogView.findViewById(R.id.unlock_package);
                builder.setView(dialogView);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
                upgrade.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent= new Intent(DetailspageActivity.this, MembershipPackage.class);
                        startActivity(intent);
                        alertDialog.dismiss();
                    }
                });
            }
        });
        add_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(DetailspageActivity.this, ContinueWatchingActivity.class);
                intent.putExtra("all_videos_tittle",true);
                startActivity(intent);
            }
        });
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailspageActivity.this);
                //ViewGroup viewGroup = context.findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.image_video_membership_dialog, viewGroup, false);
                LinearLayout upgrade=dialogView.findViewById(R.id.unlock_package);
                builder.setView(dialogView);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
                upgrade.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent= new Intent(DetailspageActivity.this, MembershipPackage.class);
                        startActivity(intent);
                        alertDialog.dismiss();
                    }
                });
               /* Intent myIntent = new Intent(DetailspageActivity.this,SocialWebViewActivity.class);
                myIntent.putExtra("url","https://www.facebook.com/");
                startActivity(myIntent);*/
            }
        });

        tweeter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailspageActivity.this);
                //ViewGroup viewGroup = context.findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.image_video_membership_dialog, viewGroup, false);
                LinearLayout upgrade=dialogView.findViewById(R.id.unlock_package);
                builder.setView(dialogView);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
                upgrade.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent= new Intent(DetailspageActivity.this, MembershipPackage.class);
                        startActivity(intent);
                        alertDialog.dismiss();
                    }
                });
               /* Intent myIntent = new Intent(DetailspageActivity.this,SocialWebViewActivity.class);
                myIntent.putExtra("url","https://twitter.com/home");
                startActivity(myIntent);*/
            }
        });
        insta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailspageActivity.this);
                //ViewGroup viewGroup = context.findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.image_video_membership_dialog, viewGroup, false);
                LinearLayout upgrade=dialogView.findViewById(R.id.unlock_package);
                builder.setView(dialogView);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
                upgrade.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent= new Intent(DetailspageActivity.this, MembershipPackage.class);
                        startActivity(intent);
                        alertDialog.dismiss();
                    }
                });
              /*  Intent myIntent = new Intent(DetailspageActivity.this,SocialWebViewActivity.class);
                myIntent.putExtra("url","https://in.pinterest.com/");
                startActivity(myIntent);*/
            }
        });
        linkedin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailspageActivity.this);
                //ViewGroup viewGroup = context.findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.image_video_membership_dialog, viewGroup, false);
                LinearLayout upgrade=dialogView.findViewById(R.id.unlock_package);
                builder.setView(dialogView);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
                upgrade.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent= new Intent(DetailspageActivity.this, MembershipPackage.class);
                        startActivity(intent);
                        alertDialog.dismiss();
                    }
                });
               /* Intent myIntent = new Intent(DetailspageActivity.this,SocialWebViewActivity.class);
                myIntent.putExtra("url","https://plus.google.com/");
                startActivity(myIntent);*/
            }
        });
        you_tube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailspageActivity.this);
                //ViewGroup viewGroup = context.findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.image_video_membership_dialog, viewGroup, false);
                LinearLayout upgrade=dialogView.findViewById(R.id.unlock_package);

                builder.setView(dialogView);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
                upgrade.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent= new Intent(DetailspageActivity.this, MembershipPackage.class);
                        startActivity(intent);
                        alertDialog.dismiss();
                    }
                });
               /* Intent myIntent = new Intent(DetailspageActivity.this,SocialWebViewActivity.class);
                myIntent.putExtra("url","https://plus.google.com/");
                startActivity(myIntent);*/
            }
        });

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(DetailspageActivity.this,ChatActivity.class);
                startActivity(intent);
            }
        });
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailspageActivity.this);
                //ViewGroup viewGroup = context.findViewById(android.R.id.content);
                View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.membership_dialog, viewGroup, false);
                Button upgrade=dialogView.findViewById(R.id.upgrade);
                ImageView image_view=dialogView.findViewById(R.id.image_view);
                TextView contact_description=dialogView.findViewById(R.id.contact_description);
                contact_description.setText("Contact "+name+"on");
                TextView name1=dialogView.findViewById(R.id.name);
                name1.setText(name);
                Picasso.with(DetailspageActivity.this).load(image).error(R.drawable.men).into(image_view);
                builder.setView(dialogView);
                final AlertDialog alertDialog = builder.create();
                alertDialog.show();
                upgrade.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent= new Intent(DetailspageActivity.this, MembershipPackage.class);
                        startActivity(intent);
                        alertDialog.dismiss();
                    }
                });
            }
        });


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//


       // ActionBar actionBar = getSupportActionBar();
     //   if (actionBar != null) {
            CollapsingToolbarLayout ctl = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
            ctl.setTitle(name);
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
           // actionBar.setTitle(name);
      //  }


//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(DetailspageActivity.this, ActivistActivity.class);
//                startActivity(intent);
//            }
//        });

        initlizereartistlist();
        initilizehrview();
        initlizeviews();

        setdata();


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
        }else if (id == android.R.id.home){
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void initlizeviews(){
        tvheadname = findViewById(R.id.head_name);
        tvhead_description = findViewById(R.id.head_description);
        tvname = findViewById(R.id.d_name);
        tvcategory = findViewById(R.id.d_category);
        tvsubcategory= findViewById(R.id.d_subcategory);
        tvtalenttitle = findViewById(R.id.d_talenttitle);
        tvexperience = findViewById(R.id.d_experience);
        tvlanguageknows = findViewById(R.id.d_languageknows);
        tvwebsite = findViewById(R.id.d_website);
        tvtalentstatus = findViewById(R.id.d_status);
        tvremarks = findViewById(R.id.d_remarks);
        tvindustryname = findViewById(R.id.d_industryname);
        tvuid = findViewById(R.id.d_uid);
        iv_profile = findViewById(R.id.iv_profile);

        btn_upgrade = findViewById(R.id.upgrade);
        btn_upgrade.setOnClickListener(this);
    }

    public void setdata() {

     /*   religion.setText(str_religion);
        sub_caste.setText(str_subcaste);
        education.setText(str_education);
        institution.setText(strinstitution);
        occupation.setText(str_occupation);
        organization.setText(str_organization);
        employed.setText(str_employed);
        annual_income.setText(str_annual_income);
        country.setText(str_country);
        state.setText(str_state);
        city.setText(str_city);
        citizenship.setText(str_citizenship);
        caste.setText(str_caste);
        basic_age.setText(str_basic_age);
        basic_height.setText(str_basicheight);
        basic_weight.setText(str_basicweight);
        basic_maritalStatus.setText(str_basicmaritalstatus);
        basic_motherTongue.setText(str_basic_mothertongue);
        basic_physical_status.setText(str_basicphysicalstatus);
        basic_bodytypes.setText(str_bodytypes);
        basic_complexion.setText(str_basic_complexion);
        basic_profilecreate_by.setText(str_profilecreate_by);
        basic_eatinghabits.setText(str_eatinghabits);
        drinking.setText(str_drinking);
        smoking.setText(str_smoking);






        basic_name.setText(name);
*/
        tvname.setText(": "+name);
        tvheadname.setText(name);
        tvuid.setText(": "+uid);
        tvhead_description.setText(description);
        tvcategory.setText(": "+category);
        tvsubcategory.setText(": "+subcategory);
        tvtalenttitle.setText(": "+talenttitle);
        tvexperience.setText(": "+experience);
        tvlanguageknows.setText(": "+languageknows);
        tvwebsite.setText(": "+website);
        tvtalentstatus.setText(": "+talent_status);
        tvremarks.setText(": "+remarks);
        tvindustryname.setText(": "+industryname);

        Picasso.with(DetailspageActivity.this).load(image).into(iv_profile);

    }
    public void initilizehrview(){
        recyclerView_hr_list = findViewById(R.id.h_r_list);
        recyclerView_hr_list.setHasFixedSize(true);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL
                , false);
        recyclerView_hr_list.setLayoutManager(manager);

        itemlist_ht_list = new ArrayList<>();
        adapter_hr_list = new horzointal_rvlist_adapter(this, itemlist_ht_list,user_id);
        recyclerView_hr_list.setAdapter(adapter_hr_list);
    }



    public void initlizereartistlist(){
        recyclerView_artist = findViewById(R.id.rv_professionals_list);
        recyclerView_artist.setHasFixedSize(true);
        @SuppressLint("WrongConstant") RecyclerView.LayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL
                , false);
        recyclerView_artist.setLayoutManager(manager);

        itemList_artist = new ArrayList<>();
        adapter_artist = new recent_featured_profile_adapter(this, itemList_artist,user_id);
        recyclerView_artist.setAdapter(adapter_artist);
    }




    @Override
    public void onStart() {
        super.onStart();
        getartists();
        gethrlist();
        getvideoslist();
        getUserNewsList();
        getUserAuditionList();
    }

    private void getUserAuditionList() {
        auditionArrayList.clear();
        String url = "https://castercrew.com/mobileapp/user_auditions?uid"+"="+uid;
        StringRequest jsonRequest = new StringRequest(com.android.volley.Request.Method.GET, url, new com.android.volley.Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject json = new JSONObject(response);
                    JSONArray jsonArray=json.getJSONArray("user_auditions");
                    for (int j = 0; j < jsonArray.length(); j++) {
                        JSONObject e = jsonArray.getJSONObject(j);
                        AuditionPoster_model model = new AuditionPoster_model();
                        model.setAuditiondate(e.getString("reg_date"));
                        model.setAuditionDescription(e.getString("description"));
                        model.setAuditionImage(e.getString("image"));
                        model.setAudition_title(e.getString("audition_title"));
                        auditionArrayList.add(model);
                    }
                    auditionPosterAdapter = new AuditionPosterAdapter(DetailspageActivity.this, auditionArrayList);
                    LinearLayoutManager    layoutmanager = new LinearLayoutManager(DetailspageActivity.this);
                    audition_postList.setLayoutManager(layoutmanager);
                    audition_postList.setAdapter(auditionPosterAdapter);
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

    private void getUserNewsList() {
        newsArrayList.clear();
        String url = "https://castercrew.com/mobileapp/user_news?uid"+"="+uid;
        StringRequest jsonRequest = new StringRequest(com.android.volley.Request.Method.GET, url, new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject json = new JSONObject(response);
                            JSONArray jsonArray=json.getJSONArray("usernews_list");
                            for (int j = 0; j < jsonArray.length(); j++) {
                                JSONObject e = jsonArray.getJSONObject(j);
                                ExpandedMenuModel model = new ExpandedMenuModel();
                                model.setNewsDate(e.getString("post_dt"));
                                model.setNewsDescription(e.getString("description"));
                                model.setNewsImage(e.getString("photo"));
                                model.setNewstags(e.getString("tags"));
                                model.setNewsTitle(e.getString("title"));
                                model.setIndustry_name(e.getString("industry_name"));
                                model.setCreate_by(e.getString("create_by"));
                                model.setSubject(e.getString("subject"));
                                model.setIndustry_id(e.getString("industry_id"));
                                model.setNewsId(e.getString("id"));
                                model.setNewsStatus(e.getString("status"));
                                newsArrayList.add(model);
                            }
                            newsAdapter = new NewsAdapter(DetailspageActivity.this,newsArrayList,user_id);
                            LinearLayoutManager    layoutmanager = new LinearLayoutManager(DetailspageActivity.this);
                            news_list.setLayoutManager(layoutmanager);
                            news_list.setAdapter(newsAdapter);
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






      /*  String url = "https://castercrew.com/mobileapp/user_news?uid"+"="+uid;
     *//*   OkHttpClient client = new OkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("uid", uid)
                .build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();*//*
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url).get().build();
        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                call.cancel();
            }
            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                ResponseBody responseBody = response.body();
                final String myResponse = responseBody.string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject json = new JSONObject(myResponse);
                            JSONArray jsonArray=json.getJSONArray("usernews_list");
                            for (int j = 0; j < jsonArray.length(); j++) {
                                JSONObject e = jsonArray.getJSONObject(j);
                                ExpandedMenuModel model = new ExpandedMenuModel();
                                model.setNewsDate(e.getString("post_dt"));
                                model.setNewsDescription(e.getString("description"));
                                model.setNewsImage(e.getString("photo"));
                                model.setNewstags(e.getString("tags"));
                                model.setNewsTitle(e.getString("title"));
                                model.setIndustry_name(e.getString("industry_name"));
                                model.setCreate_by(e.getString("create_by"));
                                model.setSubject(e.getString("subject"));
                                model.setIndustry_id(e.getString("industry_id"));
                                model.setNewsId(e.getString("id"));
                                model.setNewsStatus(e.getString("status"));
                                newsArrayList.add(model);
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });*/

    }

    public void getvideoslist(){
        if (!NetworkConnection.isConnected(DetailspageActivity.this)) {
            Toast.makeText(DetailspageActivity.this, "Please Check your Internet", Toast.LENGTH_SHORT).show();
            return;
        }

        final ProgressDialog dialog = new ProgressDialog(DetailspageActivity.this);
        dialog.setMessage("Please Wait.....");

        dialog.show();

        ApiDao apiService = ApiClient.getClient().create(ApiDao.class);

        Call<List<recent_featured_videos_list>> call = apiService.getrecent_feature_videos("reviews");
        call.enqueue(new Callback<List<recent_featured_videos_list>>() {
            @Override
            public void onResponse(@NonNull Call<List<recent_featured_videos_list>> call, @NonNull retrofit2.Response<List<recent_featured_videos_list>> response) {
                List<recent_featured_videos_list> subcategoryList = response.body();

                if (subcategoryList != null) {

                    if (!subcategoryList.isEmpty()){
                        itemlist_videos.clear();
                        itemlist_videos.addAll(subcategoryList);
//                        Collections.reverse(itemList);
                        reviewSmallCardAdapter.notifyDataSetChanged();


                        dialog.dismiss();
                        updateView(0);
                    }else
                        updateView(1);
                }
                else
                    updateView(2);
            }

            @Override
            public void onFailure(@NonNull Call<List<recent_featured_videos_list>>call, @NonNull Throwable t) {
                // Log error here since request failed
                updateView(3);
            }
        });
    }


//    private void updateView(int index){
//
//        switch (index){
//            case 0:
//                break;
//
//            case 1:
//                break;
//
//            case 2:
//                break;
//
//            case 3:
//                break;
//
//        }
//    }

    public void gethrlist(){
        if (!NetworkConnection.isConnected(this)) {
            Toast.makeText(this, "Please Check your Internet", Toast.LENGTH_SHORT).show();
            return;
        }
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Please Wait.....");

        dialog.show();


        ApiDao apiService = ApiClient.getClient().create(ApiDao.class);

        Call<List<recent_featured_profile_list>> call = apiService.getrecent_feature_profile("Artists");
        call.enqueue(new Callback<List<recent_featured_profile_list>>() {
            @Override
            public void onResponse(@NonNull Call<List<recent_featured_profile_list>> call, @NonNull retrofit2.Response<List<recent_featured_profile_list>> response) {
                List<recent_featured_profile_list> subcategoryList = response.body();

                if (subcategoryList != null) {

                    if (!subcategoryList.isEmpty()){
                        itemlist_ht_list.clear();
                        itemlist_ht_list.addAll(subcategoryList);
//                        Collections.reverse(itemList);
                        adapter_hr_list.notifyDataSetChanged();
                        dialog.dismiss();
                        updateView(0);
                    }else
                        updateView(1);
                }
                else
                    updateView(2);
            }

            @Override
            public void onFailure(@NonNull Call<List<recent_featured_profile_list>>call, @NonNull Throwable t) {
                // Log error here since request failed
                updateView(3);
            }
        });
    }


    public void getartists(){

        if (!NetworkConnection.isConnected(this)) {
            Toast.makeText(this, "Please Check your Internet", Toast.LENGTH_SHORT).show();
            return;
        }
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Please Wait.....");

        dialog.show();


        ApiDao apiService = ApiClient.getClient().create(ApiDao.class);

        Call<List<recent_featured_profile_list>> call = apiService.getrecent_feature_profile("Artists");
        call.enqueue(new Callback<List<recent_featured_profile_list>>() {
            @Override
            public void onResponse(@NonNull Call<List<recent_featured_profile_list>> call, @NonNull retrofit2.Response<List<recent_featured_profile_list>> response) {
                List<recent_featured_profile_list> subcategoryList = response.body();

                if (subcategoryList != null) {

                    if (!subcategoryList.isEmpty()){
                        itemList_artist.clear();
                        itemList_artist.addAll(subcategoryList);
//                        Collections.reverse(itemList);
                        adapter_artist.notifyDataSetChanged();
                        dialog.dismiss();
                        updateView(0);
                    }else
                        updateView(1);
                }
                else
                    updateView(2);
            }

            @Override
            public void onFailure(@NonNull Call<List<recent_featured_profile_list>>call, @NonNull Throwable t) {
                // Log error here since request failed
                updateView(3);
            }
        });
    }




    private void updateView(int index){

        switch (index){
            case 0:
                break;

            case 1:
                break;

            case 2:
                break;

            case 3:
                break;

        }
    }

    @Override
    public void onItemClick(View view, int position) {

        recent_featured_profile_list list = itemList_artist.get(position);

        Intent intent = new Intent(this, DetailspageActivity.class);
        intent.putExtra("name",list.getFull_name()+list.getUid());
        intent.putExtra("description",list.getAbout_info());
        intent.putExtra("category",list.getType());
        intent.putExtra("subcategory",list.getArtist_name());
        intent.putExtra("talenttitle",list.getTalent_title());
        intent.putExtra("experience",list.getExperience());
        intent.putExtra("languageknows",list.getLang_known());
        intent.putExtra("website",list.getWebsite());
        intent.putExtra("talentstatus",list.getTalent_status());
        intent.putExtra("remarks",list.getRemarks());
        intent.putExtra("industryname",list.getIndustry_name());
        intent.putExtra("views",list.getViews());
        intent.putExtra("uid",list.getUid());
        intent.putExtra("image",list.getTalent_pic());

        startActivity(intent);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.upgrade:
                upgrade();
                break;
        }

    }

    public void upgrade(){

        Intent intent = new Intent(this,Silver_GoldMembeshipActivity.class);
        startActivity(intent);
    }
}
