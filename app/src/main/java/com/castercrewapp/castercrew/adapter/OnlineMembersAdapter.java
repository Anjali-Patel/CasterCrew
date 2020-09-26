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

public class OnlineMembersAdapter extends RecyclerView.Adapter<OnlineMembersAdapter.SubCategoryViewHolder>{
    private Context context;
    private OnItemClickListener listener;
    private ArrayList<recent_featured_profile_list> arrayList;

    public OnlineMembersAdapter(Context context, ArrayList<recent_featured_profile_list> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public OnlineMembersAdapter.SubCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.online_members_layout, parent, false);
        return new OnlineMembersAdapter.SubCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OnlineMembersAdapter.SubCategoryViewHolder holder, int position) {
        recent_featured_profile_list item = arrayList.get(position);

    }




    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class SubCategoryViewHolder extends RecyclerView.ViewHolder  {
        TextView profile_name,profile_id,statement;
        ImageView profile_pic;


        SubCategoryViewHolder(View itemView) {
            super(itemView);

            profile_name = itemView.findViewById(R.id.profile_name);
            profile_pic = itemView.findViewById(R.id.profile_pic);
            profile_id = itemView.findViewById(R.id.profile_id);
            statement = itemView.findViewById(R.id.statement);


        }


    }
}
