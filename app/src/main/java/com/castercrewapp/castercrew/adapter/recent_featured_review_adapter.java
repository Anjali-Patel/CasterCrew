package com.castercrewapp.castercrew.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.castercrewapp.castercrew.Listeners.OnItemClickListener;
import com.castercrewapp.castercrew.R;
import com.castercrewapp.castercrew.model.recent_featured_reviews_list;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class recent_featured_review_adapter extends RecyclerView.Adapter<recent_featured_review_adapter.SubCategoryViewHolder>{

    private Context context;
    private OnItemClickListener listener;
    private ArrayList<recent_featured_reviews_list> arrayList;

    public recent_featured_review_adapter(OnItemClickListener listener, ArrayList<recent_featured_reviews_list> arrayList) {
        this.listener = listener;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public recent_featured_review_adapter.SubCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.recent_featured_video_design, parent, false);
        return new recent_featured_review_adapter.SubCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull recent_featured_review_adapter.SubCategoryViewHolder holder, int position) {

        final recent_featured_reviews_list item = arrayList.get(position);

        holder.tvname.setText(item.getTitle());

        Picasso.with(context).load(item.getImage()).into(holder.ivimage);




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

            tvname = itemView.findViewById(R.id.name);
            ivimage = itemView.findViewById(R.id.iv_image);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            listener.onItemClick(v, getAdapterPosition());
        }
    }
}
