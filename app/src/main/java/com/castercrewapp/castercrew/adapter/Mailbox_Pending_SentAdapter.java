package com.castercrewapp.castercrew.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.castercrewapp.castercrew.Listeners.OnItemClickListener;
import com.castercrewapp.castercrew.R;
import com.castercrewapp.castercrew.model.recent_featured_profile_list;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class Mailbox_Pending_SentAdapter   extends RecyclerView.Adapter<Mailbox_Pending_SentAdapter.SubCategoryViewHolder>{

    private Context context;
    private OnItemClickListener listener;
    private ArrayList<recent_featured_profile_list> arrayList;

    public Mailbox_Pending_SentAdapter(Context context, ArrayList<recent_featured_profile_list> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Mailbox_Pending_SentAdapter.SubCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.mailbox_pending_sent_layout, parent, false);
        return new Mailbox_Pending_SentAdapter.SubCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Mailbox_Pending_SentAdapter.SubCategoryViewHolder holder, int position) {

        recent_featured_profile_list item = arrayList.get(position);




    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class SubCategoryViewHolder extends RecyclerView.ViewHolder  {
        TextView verified,member_ship,name,designation,talent_title,message;
        ImageView profile_pic;
        Button no,yes;

        SubCategoryViewHolder(View itemView) {
            super(itemView);
            no=itemView.findViewById(R.id.no);
            yes=itemView.findViewById(R.id.yes);

            name = itemView.findViewById(R.id.name);
            profile_pic = itemView.findViewById(R.id.profile_pic);
            verified= itemView.findViewById(R.id.verified);
            member_ship = itemView.findViewById(R.id.member_ship);
            designation = itemView.findViewById(R.id.designation);
            talent_title=itemView.findViewById(R.id.talent_title);
            message=itemView.findViewById(R.id.message);

        }


    }
}