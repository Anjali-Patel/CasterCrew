package com.castercrewapp.castercrew.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.castercrewapp.castercrew.Activitys.LoginActivity;
import com.castercrewapp.castercrew.Activitys.NewsDetailActivity;
import com.castercrewapp.castercrew.Listeners.OnItemClickListener;
import com.castercrewapp.castercrew.R;
import com.castercrewapp.castercrew.model.ExpandedMenuModel;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.SubCategoryViewHolder>{
    private Context context;
    ViewGroup viewGroup;
    private String user_id;
    private OnItemClickListener listener;
    private ArrayList<ExpandedMenuModel> arrayList;
    public NewsAdapter(Context context, ArrayList<ExpandedMenuModel> arrayList,String user_id ) {
        this.context = context;
        this.user_id=user_id;
        this.arrayList = arrayList;
    }
    @NonNull
    @Override
    public NewsAdapter.SubCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.news_layout, parent, false);
        return new NewsAdapter.SubCategoryViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.SubCategoryViewHolder holder, int position) {
        ExpandedMenuModel item = arrayList.get(position);
        holder.title.setText(item.getNewsTitle());
        Date datetime = null;
        try {
            datetime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(item.getNewsDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String newString = new SimpleDateFormat("MMM dd yyyy hh:mm a").format(datetime);

        holder.date.setText(newString);
        holder.description.setText(item.getNewsDescription());
        Picasso.with(context).load(item.getNewsImage()).error(R.drawable.viewpagerbackground).into(holder.news_pic);

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
                    ExpandedMenuModel item = arrayList.get(position);
                    Intent intent= new Intent(context, NewsDetailActivity.class);
                    intent.putExtra("date",item.getNewsDate());
                    intent.putExtra("description",item.getNewsDescription());
                    intent.putExtra("image",item.getNewsImage());
                    intent.putExtra("title",item.getNewsTitle());
                    intent.putExtra("tag",item.getNewstags());
                    intent.putExtra("poster_user_id",item.getCreate_by());
                    intent.putExtra("industry_id",item.getIndustry_id());
                    intent.putExtra("subject",item.getSubject());
                    intent.putExtra("news_id",item.getNewsId());
                    intent.putExtra("status",item.getNewsStatus());
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
        TextView title,date,description;
        ImageView news_pic;

        SubCategoryViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            news_pic = itemView.findViewById(R.id.news_pic);
            date = itemView.findViewById(R.id.date);
            description = itemView.findViewById(R.id.description);


        }


    }
}
