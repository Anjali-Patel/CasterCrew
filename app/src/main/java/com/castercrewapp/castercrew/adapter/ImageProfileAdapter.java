package com.castercrewapp.castercrew.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.castercrewapp.castercrew.Activitys.DetailspageActivity;
import com.castercrewapp.castercrew.Listeners.OnItemClickListener;
import com.castercrewapp.castercrew.R;
import com.castercrewapp.castercrew.model.recent_featured_profile_list;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ImageProfileAdapter extends  RecyclerView.Adapter<ImageProfileAdapter.SubCategoryViewHolder>{

private Context context;
private OnItemClickListener listener;
private ArrayList<recent_featured_profile_list> arrayList;

public ImageProfileAdapter(Context context, ArrayList<recent_featured_profile_list> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        }

  @NonNull
  @Override
  public ImageProfileAdapter.SubCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.home_horizontal_design, parent, false);
        return new ImageProfileAdapter.SubCategoryViewHolder(view);
        }
        @Override
 public void onBindViewHolder(@NonNull final ImageProfileAdapter.SubCategoryViewHolder holder, int position) {
    recent_featured_profile_list  item = arrayList.get(position);
    holder.tvname.setText(item.getFull_name());
//        holder.tvdesignation.setText(item.getArtist_name());
//        holder.tvdate.setText(item.getReg_dt());

        Picasso.with(context).load(item.getTalent_pic()).into(holder.ivimage);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context, DetailspageActivity.class);
                intent.putExtra("uid",item.getId());
                intent.putExtra("name",item.getArtist_name()+item.getId());
                intent.putExtra("description",item.getAbout_info());
                intent.putExtra("category",item.getTalent_title());
                intent.putExtra("subcategory",item.getIndustry_name());
                intent.putExtra("talenttitle",item.getTalent_title());
                intent.putExtra("experience",item.getExperience());
                intent.putExtra("languageknows",item.getLang_known());
                intent.putExtra("website",item.getWebsite());
                intent.putExtra("talentstatus",item.getTalent_status());
                intent.putExtra("remarks",item.getRemarks());
                intent.putExtra("image",item.getTalent_pic());
                intent.putExtra("industryname",item.getIndustry_name());

                context.startActivity(intent);
            }
        });
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

class SubCategoryViewHolder extends RecyclerView.ViewHolder  {
    TextView tvname, tvdesignation, tvdate;
    ImageView ivimage;
    Button btnfollowing;

    SubCategoryViewHolder(View itemView) {
        super(itemView);
        tvname = itemView.findViewById(R.id.hr_name);
        ivimage = itemView.findViewById(R.id.hr_image);
        tvdate = itemView.findViewById(R.id.date);
        tvdesignation = itemView.findViewById(R.id.designation);
        btnfollowing = itemView.findViewById(R.id.btn_follow);

//        itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent= new Intent(context, DetailspageActivity.class);
//                intent.putExtra("uid",)
//                uid = bundle.getString("uid");
//                name = bundle.getString("name");
//                description = bundle.getString("description");
//                category = bundle.getString("category");
//                subcategory = bundle.getString("subcategory");
//                talenttitle = bundle.getString("talenttitle");
//                experience = bundle.getString("experience");
//                languageknows = bundle.getString("languageknows");
//                website = bundle.getString("website");
//                talent_status = bundle.getString("talentstatus");
//                remarks = bundle.getString("remarks");
//                industryname = bundle.getString("industryname");
//                image = bundle.getString("image");
//                context.startActivity(intent);
//
//            }
//        });
    }

//    @Override
//    public void onClick(View v) {
//        listener.onItemClick(v, getAdapterPosition());
//    }


}
}