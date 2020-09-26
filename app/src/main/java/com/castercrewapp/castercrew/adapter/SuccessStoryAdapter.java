package com.castercrewapp.castercrew.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.castercrewapp.castercrew.Activitys.Play_Video_Activity;
import com.castercrewapp.castercrew.Listeners.OnItemClickListener;
import com.castercrewapp.castercrew.R;
import com.castercrewapp.castercrew.model.recent_featured_videos_list;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SuccessStoryAdapter extends RecyclerView.Adapter<SuccessStoryAdapter.SubCategoryViewHolder>{
    private Context context;
    private OnItemClickListener listener;
    private ArrayList<recent_featured_videos_list> arrayList;
    public SuccessStoryAdapter(Context context, ArrayList<recent_featured_videos_list> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public SuccessStoryAdapter.SubCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.success_story_layout, parent, false);
        return new SuccessStoryAdapter.SubCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SuccessStoryAdapter.SubCategoryViewHolder holder, int position) {

        final recent_featured_videos_list item = arrayList.get(position);

        holder.tvname.setText(item.getTitle());
        holder.date.setText(item.getPost_dt());

        Picasso.with(context).load(item.getVideo_image()).placeholder(context.getResources().getDrawable(R.drawable.circular_play_button)).error(context.getResources().getDrawable(R.drawable.circular_play_button)).into(holder.ivimage);
        Picasso.with(context).load("https://castercrew.com/media/pics/9_5d00d494c6776.jpg").into(holder.profile_pic);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Play_Video_Activity.class);
                intent.putExtra("video_title",item.getTitle());
                intent.putExtra("youtube_url",item.getYoutube_url());
                intent.putExtra("date",item.getPost_dt());
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class SubCategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvname,date;
        ImageView ivimage,profile_pic;

        SubCategoryViewHolder(View itemView) {
            super(itemView);
            tvname = itemView.findViewById(R.id.video_title);
            ivimage = itemView.findViewById(R.id.video_image);
            date=itemView.findViewById(R.id.date);
            profile_pic=itemView.findViewById(R.id.profile_pic);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(v, getAdapterPosition());
        }
    }
}

