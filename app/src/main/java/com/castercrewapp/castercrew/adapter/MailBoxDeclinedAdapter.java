package com.castercrewapp.castercrew.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.castercrewapp.castercrew.Listeners.OnItemClickListener;
import com.castercrewapp.castercrew.R;
import com.castercrewapp.castercrew.model.recent_featured_profile_list;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MailBoxDeclinedAdapter  extends RecyclerView.Adapter<MailBoxDeclinedAdapter.SubCategoryViewHolder>{

    private Context context;
    private OnItemClickListener listener;
    private ArrayList<recent_featured_profile_list> arrayList;
    public MailBoxDeclinedAdapter(Context context, ArrayList<recent_featured_profile_list> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MailBoxDeclinedAdapter.SubCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.mail_declined_layout, parent, false);
        return new MailBoxDeclinedAdapter.SubCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubCategoryViewHolder holder, int position) {
        recent_featured_profile_list item = arrayList.get(position);

    }




    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class SubCategoryViewHolder extends RecyclerView.ViewHolder  {
        TextView verified,name,designation,talent_title,message,member_ship;
        ImageView iv_image;


        SubCategoryViewHolder(View itemView) {
            super(itemView);

            verified = itemView.findViewById(R.id.verified);
            iv_image = itemView.findViewById(R.id.iv_image);
            name= itemView.findViewById(R.id.name);
            designation = itemView.findViewById(R.id.designation);
            talent_title = itemView.findViewById(R.id.talent_title);
            message = itemView.findViewById(R.id.message);
            member_ship = itemView.findViewById(R.id.member_ship);


        }


    }
}