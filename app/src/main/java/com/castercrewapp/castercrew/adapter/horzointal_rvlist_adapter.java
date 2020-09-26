package com.castercrewapp.castercrew.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.castercrewapp.castercrew.Activitys.LoginActivity;
import com.castercrewapp.castercrew.Listeners.OnItemClickListener;
import com.castercrewapp.castercrew.R;
import com.castercrewapp.castercrew.model.recent_featured_profile_list;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class horzointal_rvlist_adapter  extends RecyclerView.Adapter<horzointal_rvlist_adapter.SubCategoryViewHolder>{
String user_id;
    ViewGroup viewGroup;

    private Context context;
    private OnItemClickListener listener;
    private ArrayList<recent_featured_profile_list> arrayList;

    public horzointal_rvlist_adapter(OnItemClickListener listener, ArrayList<recent_featured_profile_list> arrayList,String user_id) {
        this.listener = listener;
        this.arrayList = arrayList;
        this.user_id=user_id;
    }

    @NonNull
    @Override
    public horzointal_rvlist_adapter.SubCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.home_horizontal_design, parent, false);
        return new horzointal_rvlist_adapter.SubCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final horzointal_rvlist_adapter.SubCategoryViewHolder holder, int position) {

        recent_featured_profile_list item = arrayList.get(position);

        holder.tvname.setText(item.getFull_name());
//        holder.tvdesignation.setText(item.getArtist_name());
//        holder.tvdate.setText(item.getReg_dt());

        Picasso.with(context).load(item.getTalent_pic()).into(holder.ivimage);
//        holder.btnfollowing.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                holder.btnfollowing.setText("Following");
//                holder.btnfollowing.setBackgroundColor(Color.GRAY);
//            }
//        });

    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class SubCategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvname,tvdesignation,tvdate;
        ImageView ivimage;
        Button btnfollowing;

        SubCategoryViewHolder(View itemView) {
            super(itemView);
            tvname = itemView.findViewById(R.id.hr_name);
            ivimage = itemView.findViewById(R.id.hr_image);
            tvdate= itemView.findViewById(R.id.date);
            tvdesignation = itemView.findViewById(R.id.designation);
            btnfollowing = itemView.findViewById(R.id.btn_follow);
            itemView.setOnClickListener(this);
        }

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
                listener.onItemClick(v, getAdapterPosition());

            }
        }
    }
}