package com.castercrewapp.castercrew.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.castercrewapp.castercrew.Activitys.LoginActivity;
import com.castercrewapp.castercrew.Activitys.NewsDetailActivity;
import com.castercrewapp.castercrew.Listeners.OnItemClickListener;
import com.castercrewapp.castercrew.R;
import com.castercrewapp.castercrew.model.ExpandedMenuModel;
import com.castercrewapp.castercrew.model.NotificationModel;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.SubCategoryViewHolder>{
    private Context context;
    ViewGroup viewGroup;
    private String user_id;
    private OnItemClickListener listener;
    private ArrayList<NotificationModel> arrayList;
    public NotificationAdapter(Context context, ArrayList<NotificationModel> arrayList,String user_id ) {
        this.context = context;
        this.user_id=user_id;
        this.arrayList = arrayList;
    }
    @NonNull
    @Override
    public NotificationAdapter.SubCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.notifications_layout, parent, false);
        return new NotificationAdapter.SubCategoryViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull NotificationAdapter.SubCategoryViewHolder holder, int position) {
        NotificationModel item = arrayList.get(position);
        holder.description.setText(item.getAction());
        holder.view_prfile1.setText(item.getSeen());


        holder.time.setText(item.getTime());
//        holder.description.setText(item.getNewsDescription());
//        Picasso.with(context).load(item.getNewsImage()).error(R.drawable.viewpagerbackground).into(holder.news_pic);

    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class SubCategoryViewHolder extends RecyclerView.ViewHolder  {
        TextView time,view_prfile1,description;
        ImageView image;

        SubCategoryViewHolder(View itemView) {
            super(itemView);

            time = itemView.findViewById(R.id.time);
            view_prfile1 = itemView.findViewById(R.id.view_prfile1);
            image = itemView.findViewById(R.id.image);
            description = itemView.findViewById(R.id.description);


        }


    }
}
