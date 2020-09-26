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

public class AssistedServiceAdapter  extends PagerAdapter {

    private ArrayList<Integer> images;
    private LayoutInflater inflater;
    private Context context;

    public AssistedServiceAdapter(Context context, ArrayList<Integer> images) {
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
        View myImageLayout = (View) inflater.inflate(R.layout.assisted_service_layout ,view, false);
        ImageView myImage = (ImageView) myImageLayout
                .findViewById(R.id.image);
        myImage.setImageResource(images.get(position));
        TextView tvDesc=(TextView)myImageLayout.findViewById(R.id.tvDesc);
        if(position==0){
            tvDesc.setText("\"Assisted Service and in particular the Relationship manager.Nazneen made our dreams a reality.Thank You\"");
        }
        else if(position==1){
            tvDesc.setText("\"When time was a major constraint.Assisted service came to the rescue.We found the perfect artist for our industry.Great service\"");
        }else if(position==2){
            tvDesc.setText("\"Excellent service by Assisted service.We found the right artist for our Company.Highly recommended.\"");
        }
        view.addView(myImageLayout, 0);
        return myImageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }
}