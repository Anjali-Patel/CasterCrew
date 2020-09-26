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
import com.castercrewapp.castercrew.Activitys.Play_Video_Activity;
import com.castercrewapp.castercrew.Listeners.OnItemClickListener;
import com.castercrewapp.castercrew.R;
import com.castercrewapp.castercrew.model.AllAuditionModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

public class AuditionAllAdapter  extends RecyclerView.Adapter<AuditionAllAdapter.SubCategoryViewHolder> {
String user_id;
    ViewGroup viewGroup;

    private Context context;
    private OnItemClickListener listener;
    private ArrayList<AllAuditionModel> arrayList;

    public AuditionAllAdapter(Context context, ArrayList<AllAuditionModel> arrayList,String user_id) {
        this.context = context;
        this.user_id=user_id;
        this.arrayList = arrayList;
    }
    @NonNull
    @Override
    public AuditionAllAdapter.SubCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.review_subcategory_layout, parent, false);
        return new AuditionAllAdapter.SubCategoryViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull AuditionAllAdapter.SubCategoryViewHolder holder, int position) {
        final AllAuditionModel item = arrayList.get(position);
        holder.tvname.setText(item.getAuditionTitle());
        holder.date.setText(item.getAuditionDate());
        Picasso.with(context).load(R.drawable.viewpagerbackground).into(holder.ivimage);
        Picasso.with(context).load("https://castercrew.com/media/pics/9_5d00d494c6776.jpg").into(holder.profile_pic);
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
            Toast.makeText(context,"Downloading All Auditions.........",Toast.LENGTH_LONG).show();

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
                    intent.putExtra("video_title", item.getAuditionTitle());
                    intent.putExtra("date", item.getAuditionDate());
                    intent.putExtra("description", item.getAuditionDescription());
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
        TextView tvname, date;
        ImageView ivimage, profile_pic,download,shortlist;
        SubCategoryViewHolder(View itemView) {
            super(itemView);
            shortlist=itemView.findViewById(R.id.shortlist);
            download=itemView.findViewById(R.id.download);
            tvname = itemView.findViewById(R.id.video_title);
            ivimage = itemView.findViewById(R.id.video_image);
            date = itemView.findViewById(R.id.date);
            profile_pic = itemView.findViewById(R.id.profile_pic);
//            itemView.setOnClickListener(this);
        }

//        @Override
//        public void onClick(View v) {
//            listener.onItemClick(v, getAdapterPosition());
//        }
    }
}
