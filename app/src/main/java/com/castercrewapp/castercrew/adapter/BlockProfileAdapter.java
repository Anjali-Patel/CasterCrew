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

public class BlockProfileAdapter extends RecyclerView.Adapter<BlockProfileAdapter.SubCategoryViewHolder>{

    private Context context;
    private OnItemClickListener listener;
    private ArrayList<recent_featured_profile_list> arrayList;

    public BlockProfileAdapter(Context context, ArrayList<recent_featured_profile_list> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public BlockProfileAdapter.SubCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.block_profile_layout, parent, false);
        return new BlockProfileAdapter.SubCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BlockProfileAdapter.SubCategoryViewHolder holder, int position) {
        recent_featured_profile_list item = arrayList.get(position);

    }




    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class SubCategoryViewHolder extends RecyclerView.ViewHolder  {
        TextView designation,name,verified,member_ship;
        ImageView profile_pic;
        Button block_unblock;

        SubCategoryViewHolder(View itemView) {
            super(itemView);

            profile_pic = itemView.findViewById(R.id.profile_pic);
            block_unblock = itemView.findViewById(R.id.block_unblock);
            designation= itemView.findViewById(R.id.designation);
            name = itemView.findViewById(R.id.name);
            verified = itemView.findViewById(R.id.verified);
            member_ship=itemView.findViewById(R.id.member_ship);

        }


    }
}
