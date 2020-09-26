package com.castercrewapp.castercrew.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.castercrewapp.castercrew.Activitys.AuditionAllActivity;
import com.castercrewapp.castercrew.Listeners.OnItemClickListener;
import com.castercrewapp.castercrew.R;
import com.castercrewapp.castercrew.model.AuditionPoster_model;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AuditionPosterAdapter extends RecyclerView.Adapter<AuditionPosterAdapter.SubCategoryViewHolder> {
    private Context context;
    private OnItemClickListener listener;
    private ArrayList<AuditionPoster_model> arrayList;

    public AuditionPosterAdapter(Context context, ArrayList<AuditionPoster_model> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public AuditionPosterAdapter.SubCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.news_layout, parent, false);
        return new AuditionPosterAdapter.SubCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AuditionPosterAdapter.SubCategoryViewHolder holder, int position) {
        AuditionPoster_model item = arrayList.get(position);
        holder.title.setText(item.getAudition_title());
        holder.date.setText(item.getAuditiondate());
        holder.description.setText(item.getAuditionDescription());
        Picasso.with(context).load(item.getAuditionImage()).error(R.drawable.viewpagerbackground).into(holder.news_pic);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                AuditionPoster_model item = arrayList.get(position);
                Intent intent = new Intent(context, AuditionAllActivity.class);
                intent.putExtra("date", item.getAuditiondate());
                intent.putExtra("description", item.getAuditionDescription());
                intent.putExtra("video_title", item.getAudition_title());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class SubCategoryViewHolder extends RecyclerView.ViewHolder {
        TextView title, date, description;
        ImageView news_pic;

        SubCategoryViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            news_pic = itemView.findViewById(R.id.news_pic);
            date = itemView.findViewById(R.id.date);
            description = itemView.findViewById(R.id.description);


        }


    }
}