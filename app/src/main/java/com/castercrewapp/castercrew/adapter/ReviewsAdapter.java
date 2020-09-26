package com.castercrewapp.castercrew.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.castercrewapp.castercrew.Activitys.LoginActivity;
import com.castercrewapp.castercrew.Activitys.Play_Video_Activity;
import com.castercrewapp.castercrew.Listeners.OnItemClickListener;
import com.castercrewapp.castercrew.R;
import com.castercrewapp.castercrew.model.recent_featured_videos_list;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.SubCategoryViewHolder>{
private String user_id;
    private Context context;
    private OnItemClickListener listener;
    private ArrayList<recent_featured_videos_list> arrayList;
    ViewGroup viewGroup;
    public ReviewsAdapter(Context context, ArrayList<recent_featured_videos_list> arrayList,String user_id) {
        this.context = context;
        this.user_id=user_id;
        this.arrayList = arrayList;
    }


    @NonNull
    @Override
    public ReviewsAdapter.SubCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.reviews_list_layout, parent, false);
        return new ReviewsAdapter.SubCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewsAdapter.SubCategoryViewHolder holder, int position) {

        final recent_featured_videos_list item = arrayList.get(position);

        holder.tvname.setText(item.getTitle());
        holder.date.setText(item.getPost_dt());
        holder.shortlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user_id.equalsIgnoreCase("")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    //ViewGroup viewGroup =context. findViewById(android.R.id.content);
                    View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.skip_poppup_layout, viewGroup, false);
                    TextView no=dialogView.findViewById(R.id.no);
                    TextView yes=dialogView.findViewById(R.id.yes);
                    builder.setView(dialogView);
                    final AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                    yes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent= new Intent(context, LoginActivity.class);
                            context.startActivity(intent);
                            alertDialog.dismiss();
                        }});
                    no.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.dismiss();
                        }});
                }else{

                }
            }
        });
        holder.download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user_id.equalsIgnoreCase("")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    //ViewGroup viewGroup =context. findViewById(android.R.id.content);
                    View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.skip_poppup_layout, viewGroup, false);
                    TextView no=dialogView.findViewById(R.id.no);
                    TextView yes=dialogView.findViewById(R.id.yes);
                    builder.setView(dialogView);
                    final AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                    yes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent= new Intent(context, LoginActivity.class);
                            context.startActivity(intent);
                            alertDialog.dismiss();
                        }});
                    no.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.dismiss();
                        }});
                }else{
                    Toast.makeText(context,"Downloading Review.......",Toast.LENGTH_LONG).show();

                }
            }
        });
        Picasso.with(context).load(item.getYoutube_url()).placeholder(context.getResources().getDrawable(R.drawable.video_notavailable_icon)).error(context.getResources().getDrawable(R.drawable.video_notavailable_icon)).into(holder.ivimage);
        Picasso.with(context).load("https://castercrew.com/media/pics/9_5d00d494c6776.jpg").into(holder.profile_pic);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user_id.equalsIgnoreCase("")){
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    //ViewGroup viewGroup =context. findViewById(android.R.id.content);
                    View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.skip_poppup_layout, viewGroup, false);
                    TextView no=dialogView.findViewById(R.id.no);
                    TextView yes=dialogView.findViewById(R.id.yes);
                    builder.setView(dialogView);
                    final AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                    yes.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent= new Intent(context, LoginActivity.class);
                            context.startActivity(intent);
                            alertDialog.dismiss();
                        }});
                    no.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alertDialog.dismiss();
                        }});
                }else{
                    Intent intent = new Intent(context, Play_Video_Activity.class);
                    intent.putExtra("video_title",item.getTitle());
                    intent.putExtra("youtube_url",item.getYoutube_url());
                    intent.putExtra("date",item.getPost_dt());
                    intent.putExtra("story",item.getStory());
                    intent.putExtra("review_image",true);
                    context.startActivity(intent);
                }

            }
        });
    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    class SubCategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvname,date;
        ImageView ivimage,profile_pic,download,shortlist;

        SubCategoryViewHolder(View itemView) {
            super(itemView);
            shortlist=itemView.findViewById(R.id.shortlist);
            download=itemView.findViewById(R.id.download);
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

