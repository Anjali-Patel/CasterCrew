package com.castercrewapp.castercrew.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.castercrewapp.castercrew.Activitys.AuditionSliderPhotoActivity;
import com.castercrewapp.castercrew.R;

import java.util.ArrayList;

import androidx.viewpager.widget.PagerAdapter;

public class AuditionDetailAdapter extends PagerAdapter {

    private ArrayList<Integer> images;
    private LayoutInflater inflater;
    private Context context;

    public AuditionDetailAdapter(Context context, ArrayList<Integer> images) {
        this.context = context;
        this.images=images;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View myImageLayout = (View) inflater.inflate(R.layout.audition_detail_layout ,view, false);
        ImageView myImage = (ImageView) myImageLayout
                .findViewById(R.id.image);
        TextView page_count=myImageLayout.findViewById(R.id.page_count);
        myImage.setImageResource(images.get(position));
        if(position==0){
            page_count.setText("1"+"/"+"5");
        }else if(position==1){
            page_count.setText("2"+"/"+"5");

        }else if(position==2){
            page_count.setText("3"+"/"+"5");

        }else if(position==3){
            page_count.setText("4"+"/"+"5");

        }else if(position==4){
            page_count.setText("5"+"/"+"5");

        }

myImageLayout.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent= new Intent(context, AuditionSliderPhotoActivity.class);
        context.startActivity(intent);
    }
});



        view.addView(myImageLayout, 0);




        return myImageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
}
