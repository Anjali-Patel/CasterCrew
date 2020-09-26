package com.castercrewapp.castercrew.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.castercrewapp.castercrew.R;

import java.util.ArrayList;

import androidx.viewpager.widget.PagerAdapter;

public class QuickTourAdapter extends PagerAdapter {

    private ArrayList<Integer> images;
    private LayoutInflater inflater;
    private Context context;

    public QuickTourAdapter(Context context, ArrayList<Integer> images) {
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
        View myImageLayout = (View) inflater.inflate(R.layout.sliding_images ,view, false);
        ImageView myImage = (ImageView) myImageLayout
                .findViewById(R.id.image);
        myImage.setImageResource(images.get(position));
        TextView tvDesc=(TextView)myImageLayout.findViewById(R.id.tvDesc);
        if(position==0){
            tvDesc.setText("Instant update to find your right match");
        }
        else if(position==1){
            tvDesc.setText("View matches based on your preferences");
        }else if(position==2){
            tvDesc.setText("View notification about your matches");
        }else if(position==3){
            tvDesc.setText("Search for matches based on specific criteria");
        }
        else if(position==4){
            tvDesc.setText("Respond to member requests");
        }
        else if(position==5){
            tvDesc.setText("Chat with matches");
        }
        view.addView(myImageLayout, 0);
        return myImageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
}
