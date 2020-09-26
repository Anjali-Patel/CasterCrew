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

public class ArtistProfileAdapter extends RecyclerView.Adapter<ArtistProfileAdapter.SubCategoryViewHolder>{

private Context context;
private OnItemClickListener listener;
private ArrayList<recent_featured_videos_list> arrayList;

public ArtistProfileAdapter(OnItemClickListener listener, ArrayList<recent_featured_videos_list> arrayList) {
        this.listener = listener;
        this.arrayList = arrayList;
        }

@NonNull
@Override
public ArtistProfileAdapter.SubCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context)
        .inflate(R.layout.video_audio_banner_layout, parent, false);
        return new ArtistProfileAdapter.SubCategoryViewHolder(view);
        }

@Override
public void onBindViewHolder(@NonNull ArtistProfileAdapter.SubCategoryViewHolder holder, int position) {

final recent_featured_videos_list item = arrayList.get(position);
String a=item.getTitle();
        holder.tvname.setText(a);

        Picasso.with(context).load(item.getVideo_image()).placeholder(context.getResources().getDrawable(R.drawable.video_notavailable_icon)).error(context.getResources().getDrawable(R.drawable.video_notavailable_icon)).into(holder.ivimage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
        Intent intent = new Intent(context, Play_Video_Activity.class);
        intent.putExtra("video_title",item.getTitle());
        intent.putExtra("youtube_url",item.getYoutube_url());
        intent.putExtra("date",item.getPost_dt());
    intent.putExtra("video_id",item.getId());
    intent.putExtra("uid",item.getCreate_by());
    intent.putExtra("industry_id",item.getIndustry_id());
    intent.putExtra("category_id",item.getCatg_id());
    intent.putExtra("artist_section",true);

        context.startActivity(intent);
        }
        });
        }


@Override
public int getItemCount() {
        return arrayList.size();
        }

class SubCategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    TextView tvname;
    ImageView ivimage;

    SubCategoryViewHolder(View itemView) {
        super(itemView);

        tvname = itemView.findViewById(R.id.video_title);
        ivimage = itemView.findViewById(R.id.video_image);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        listener.onItemClick(v, getAdapterPosition());
    }
}
}

