package com.castercrewapp.castercrew.Activitys;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.castercrewapp.castercrew.R;
import com.castercrewapp.castercrew.model.UserDataModel;

import java.util.ArrayList;
import java.util.Collections;

public class DailyRecommendedActivity extends AppCompatActivity {
    int windowwidth;
    int screenCenter;
    int x_cord, y_cord, x, y;
    int Likes = 0;
    public RelativeLayout parentView;
    float alphaValue = 0;
    private Context context;

    ArrayList<UserDataModel> userDataModelArrayList;

    @SuppressWarnings("deprecation")
    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_recommended);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Daily Recommendation");
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        context = DailyRecommendedActivity.this;

        parentView = (RelativeLayout) findViewById(R.id.main_layoutview);

        windowwidth = getWindowManager().getDefaultDisplay().getWidth();

        screenCenter = windowwidth / 2;

        userDataModelArrayList = new ArrayList<>();

        getArrayData();


        for (int i = 0; i < userDataModelArrayList.size(); i++) {

            LayoutInflater inflate =
                    (LayoutInflater) DailyRecommendedActivity.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            final View containerView = inflate.inflate(R.layout.custom_tinder_layou, null);

            ImageView userIMG = (ImageView) containerView.findViewById(R.id.userIMG);
            RelativeLayout relativeLayoutContainer = (RelativeLayout) containerView.findViewById(R.id.relative_container);


            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);

            containerView.setLayoutParams(layoutParams);

            containerView.setTag(i);
            userIMG.setBackgroundResource(userDataModelArrayList.get(i).getPhoto());

//            m_view.setRotation(i);
//            containerView.setPadding(0, i, 0, 0);

            RelativeLayout.LayoutParams layoutTvParams = new RelativeLayout.LayoutParams(
                    RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);


            // ADD dynamically like TextView on image.
            final TextView tvLike = new TextView(context);
            tvLike.setLayoutParams(layoutTvParams);
            tvLike.setPadding(10, 10, 10, 10);
            tvLike.setBackgroundDrawable(getResources().getDrawable(R.drawable.btnlikeback));
            tvLike.setText("LIKE");
            tvLike.setGravity(Gravity.CENTER);
            tvLike.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            tvLike.setTextSize(25);
            tvLike.setTextColor(ContextCompat.getColor(context, R.color.colorRed));
            tvLike.setX(20);
            tvLike.setY(100);
            tvLike.setRotation(-50);
            tvLike.setAlpha(alphaValue);
            relativeLayoutContainer.addView(tvLike);


//            ADD dynamically dislike TextView on image.
            final TextView tvUnLike = new TextView(context);
            tvUnLike.setLayoutParams(layoutTvParams);
            tvUnLike.setPadding(10, 10, 10, 10);
            tvUnLike.setBackgroundDrawable(getResources().getDrawable(R.drawable.btnlikeback));
            tvUnLike.setText("UNLIKE");
            tvUnLike.setGravity(Gravity.CENTER);
            tvUnLike.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
            tvUnLike.setTextSize(25);
            tvUnLike.setTextColor(ContextCompat.getColor(context, R.color.colorRed));
            tvUnLike.setX(500);
            tvUnLike.setY(150);
            tvUnLike.setRotation(50);
            tvUnLike.setAlpha(alphaValue);
            relativeLayoutContainer.addView(tvUnLike);


            TextView tvName = (TextView) containerView.findViewById(R.id.tvName);
            TextView tvTotLikes = (TextView) containerView.findViewById(R.id.tvTotalLikes);


            tvName.setText(userDataModelArrayList.get(i).getName());
            tvTotLikes.setText(userDataModelArrayList.get(i).getTotalLikes());
            relativeLayoutContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(DailyRecommendedActivity.this,ProfileActivity.class);
                    startActivity(intent);
                }
            });
            // Touch listener on the image layout to swipe image right or left.
            relativeLayoutContainer.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {

                    x_cord = (int) event.getRawX();
                    y_cord = (int) event.getRawY();

                    containerView.setX(0);
                    containerView.setY(0);

                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN:

                            x = (int) event.getX();
                            y = (int) event.getY();


//                            Log.v("On touch", x + " " + y);
                            break;
                        case MotionEvent.ACTION_MOVE:

                            x_cord = (int) event.getRawX();
                            // smoother animation.
                            y_cord = (int) event.getRawY();

                            containerView.setX(x_cord - x);
                            containerView.setY(y_cord - y);


                            if (x_cord >= screenCenter) {
                                containerView.setRotation((float) ((x_cord - screenCenter) * (Math.PI / 32)));
                                if (x_cord > (screenCenter + (screenCenter / 2))) {
                                    tvLike.setAlpha(1);
                                    if (x_cord > (windowwidth - (screenCenter / 4))) {
                                        Likes = 2;
                                    } else {
                                        Likes = 0;
                                    }
                                } else {
                                    Likes = 0;
                                    tvLike.setAlpha(0);
                                }
                                tvUnLike.setAlpha(0);
                            } else {
                                // rotate image while moving
                                containerView.setRotation((float) ((x_cord - screenCenter) * (Math.PI / 32)));
                                if (x_cord < (screenCenter / 2)) {
                                    tvUnLike.setAlpha(1);
                                    if (x_cord < screenCenter / 4) {
                                        Likes = 1;
                                    } else {
                                        Likes = 0;
                                    }
                                } else {
                                    Likes = 0;
                                    tvUnLike.setAlpha(0);
                                }
                                tvLike.setAlpha(0);
                            }

                            break;
                        case MotionEvent.ACTION_UP:

                            x_cord = (int) event.getRawX();
                            y_cord = (int) event.getRawY();

//                            Log.e("X Point", "" + x_cord + " , Y " + y_cord);
                            tvUnLike.setAlpha(0);
                            tvLike.setAlpha(0);

                            if (Likes == 0) {
                                Toast.makeText(context, "NOTHING", Toast.LENGTH_SHORT).show();
//                                Log.e("Event_Status :-> ", "Nothing");
                                containerView.setX(0);
                                containerView.setY(0);
                                containerView.setRotation(0);
                            } else if (Likes == 1) {
                                Toast.makeText(context, "UNLIKE", Toast.LENGTH_SHORT).show();
//                                Log.e("Event_Status :-> ", "UNLIKE");
                                parentView.removeView(containerView);
                            } else if (Likes == 2) {
                                Toast.makeText(context, "LIKED", Toast.LENGTH_SHORT).show();
//                                Log.e("Event_Status :-> ", "Liked");
                                parentView.removeView(containerView);
                            }
                            break;
                        default:
                            break;
                    }
                    return true;
                }
            });

            parentView.addView(containerView);

        }


    }

    private void getArrayData() {

     /*   UserDataModel model = new UserDataModel();
        model.setName("Samantha Reddy  ");
        model.setTotalLikes("28 Yrs, 4 ft 3 in,Kurmi/Kurmi Kshatriya ,Rs.30-35 L/Annam,Bangalore,Karnataka");
        model.setPhoto(R.drawable.samatna_nine);
        userDataModelArrayList.add(model);


        UserDataModel model2 = new UserDataModel();
        model2.setName("Swetha Reddy ");
        model2.setTotalLikes("28 Yrs, 4 ft 3 in,Kurmi/Kurmi Kshatriya ,Rs.30-35 L/Annam,Bangalore,Karnataka");
        model2.setPhoto(R.drawable.samantha_eleven);
        userDataModelArrayList.add(model2);

        UserDataModel model3 = new UserDataModel();
        model3.setName("Aishwarya ");
        model3.setTotalLikes("28 Yrs, 4 ft 3 in,Kurmi/Kurmi Kshatriya ,Rs.30-35 L/Annam,Bangalore,Karnataka");
        model3.setPhoto(R.drawable.samantha_four);
        userDataModelArrayList.add(model3);


        UserDataModel model4 = new UserDataModel();
        model4.setName("Nirmala ");
        model4.setTotalLikes("28 Yrs, 4 ft 3 in,Kurmi/Kurmi Kshatriya ,Rs.30-35 L/Annam,Bangalore,Karnataka");
        model4.setPhoto(R.drawable.samantha_eleven);
        userDataModelArrayList.add(model4);


        UserDataModel model5 = new UserDataModel();
        model5.setName("Pawani");
        model5.setTotalLikes("28 Yrs, 4 ft 3 in,Kurmi/Kurmi Kshatriya ,Rs.30-35 L/Annam,Bangalore,Karnataka");
        model5.setPhoto(R.drawable.samantha_ten);
        userDataModelArrayList.add(model5);

        UserDataModel model6 = new UserDataModel();
        model6.setName("ShriDevi Raman");
        model6.setTotalLikes("28 Yrs, 4 ft 3 in,Kurmi/Kurmi Kshatriya ,Rs.30-35 L/Annam,Bangalore,Karnataka");
        model6.setPhoto(R.drawable.samantha_three);
        userDataModelArrayList.add(model6);


        UserDataModel model7 = new UserDataModel();
        model7.setName("Swetambari ");
        model7.setTotalLikes("28 Yrs, 4 ft 3 in,Kurmi/Kurmi Kshatriya ,Rs.30-35 L/Annam,Bangalore,Karnataka");
        model7.setPhoto(R.drawable.samantha_ten);
        userDataModelArrayList.add(model7);


        UserDataModel model8 = new UserDataModel();
        model8.setName("Meenu ");
        model8.setTotalLikes("28 Yrs, 4 ft 3 in,Kurmi/Kurmi Kshatriya ,Rs.30-35 L/Annam,Bangalore,Karnataka");
        model8.setPhoto(R.drawable.three_samantha);
        userDataModelArrayList.add(model8);

        UserDataModel model9 = new UserDataModel();
        model9.setName("Deepika Rathaur ");
        model9.setTotalLikes("28 Yrs, 4 ft 3 in,Kurmi/Kurmi Kshatriya ,Rs.30-35 L/Annam,Bangalore,Karnataka");
        model9.setPhoto(R.drawable.samantha_ten);
        userDataModelArrayList.add(model9);
*/
        Collections.reverse(userDataModelArrayList);

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

}
