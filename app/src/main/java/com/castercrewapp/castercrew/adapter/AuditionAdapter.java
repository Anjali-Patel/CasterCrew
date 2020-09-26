package com.castercrewapp.castercrew.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.castercrewapp.castercrew.Activitys.AuditionDetailActivity;
import com.castercrewapp.castercrew.Activitys.LoginActivity;
import com.castercrewapp.castercrew.Listeners.OnItemClickListener;
import com.castercrewapp.castercrew.R;
import com.castercrewapp.castercrew.model.recent_featured_videos_list;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

public class AuditionAdapter extends RecyclerView.Adapter<AuditionAdapter.SubCategoryViewHolder> {
private String user_id;
    private Context context;
    private OnItemClickListener listener;
     private ArrayList<recent_featured_videos_list> arrayList;
    ViewGroup viewGroup;
    public AuditionAdapter(Context context, ArrayList<recent_featured_videos_list> arrayList,String user_id) {
        this.listener = listener;
        this.user_id=user_id;
        this.arrayList = arrayList;
        this.context=context;
    }
    @NonNull
    @Override
    public AuditionAdapter.SubCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.small_reviewcard_list_layout, parent, false);
        return new AuditionAdapter.SubCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AuditionAdapter.SubCategoryViewHolder holder, int position) {
        final recent_featured_videos_list item = arrayList.get(position);
        holder.tvname.setText(item.getTitle());
        Picasso.with(context).load(R.drawable.viewpagerbackground).into(holder.ivimage);
        Picasso.with(context).load("https://castercrew.com/media/pics/9_5d00d494c6776.jpg").into(holder.profile_pic);
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
            Toast.makeText(context,"Downloading Audition........",Toast.LENGTH_LONG).show();

        }
    }
   });
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
                    Intent intent = new Intent(context, AuditionDetailActivity.class);
                    intent.putExtra("video_title", item.getTitle());
                    intent.putExtra("date", item.getPost_dt());
                    intent.putExtra("description", item.getStory());
                    context.startActivity(intent);
                }

            }
        });
    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class SubCategoryViewHolder extends RecyclerView.ViewHolder  {
        TextView tvname;
        ImageView ivimage,profile_pic,download,shortlist;

        SubCategoryViewHolder(View itemView) {
            super(itemView);
            shortlist=itemView.findViewById(R.id.shortlist);
            download=itemView.findViewById(R.id.download);
            tvname = itemView.findViewById(R.id.video_title);
            ivimage = itemView.findViewById(R.id.video_image);
            profile_pic=itemView.findViewById(R.id.profile_pic);


//            itemView.setOnClickListener(this);
        }

//        @Override
//        public void onClick(View v) {
//            listener.onItemClick(v, getAdapterPosition());
//        }
    }
}
