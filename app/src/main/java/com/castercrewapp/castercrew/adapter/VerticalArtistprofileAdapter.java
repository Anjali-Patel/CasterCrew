package com.castercrewapp.castercrew.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.castercrewapp.castercrew.Activitys.DetailspageActivity;
import com.castercrewapp.castercrew.Activitys.LoginActivity;
import com.castercrewapp.castercrew.Activitys.MembershipPackage;
import com.castercrewapp.castercrew.Listeners.OnItemClickListener;
import com.castercrewapp.castercrew.R;
import com.castercrewapp.castercrew.model.recent_featured_profile_list;
import com.castercrewapp.castercrew.utils.AccountUtils;
import com.castercrewapp.castercrew.utils.SharedPreferenceUtils;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

public class VerticalArtistprofileAdapter extends RecyclerView.Adapter<VerticalArtistprofileAdapter.SubCategoryViewHolder>{
    String user_id;
    public Context context;
    private OnItemClickListener listener;
    private ArrayList<recent_featured_profile_list> arrayList;
    ViewGroup viewGroup;

    public VerticalArtistprofileAdapter(Context context, ArrayList<recent_featured_profile_list> arrayList,String user_id) {
        this.context = context;
        this.arrayList = arrayList;
        this.user_id=user_id;
    }

    @NonNull
    @Override
    public VerticalArtistprofileAdapter.SubCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.recent_featured_profiles_design, parent, false);
        return new VerticalArtistprofileAdapter.SubCategoryViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final VerticalArtistprofileAdapter.SubCategoryViewHolder holder, int position) {

        final recent_featured_profile_list item = arrayList.get(position);

        holder.tvname.setText(item.getFull_name());
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(item.getReg_dt());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String newString = new SimpleDateFormat("MMM dd yyyy hh:mm a").format(date);
        holder.tvdesignation.setText(item.getArtist_name()+"\n"+item.getTalent_title()+"\n"+newString);
//        holder.tvdate.setText(item.getReg_dt());
        holder.tvuid.setText(item.getUid());
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
                    Intent intent = new Intent(context, DetailspageActivity.class);
                    intent.putExtra("name",item.getFull_name()+item.getUid());
                    intent.putExtra("description",item.getAbout_info());
                    intent.putExtra("category",item.getType());
                    intent.putExtra("subcategory",item.getArtist_name());
                    intent.putExtra("talenttitle",item.getTalent_title());
                    intent.putExtra("experience",item.getExperience());
                    intent.putExtra("languageknows",item.getLang_known());
                    intent.putExtra("website",item.getWebsite());
                    intent.putExtra("talentstatus",item.getTalent_status());
                    intent.putExtra("remarks",item.getRemarks());
                    intent.putExtra("industryname",item.getIndustry_name());
                    intent.putExtra("views",item.getViews());
                    intent.putExtra("uid",item.getUid());
                    intent.putExtra("image",item.getTalent_pic());

                    context.startActivity(intent);
                }

            }
        });
        holder.email.setOnClickListener(new View.OnClickListener() {
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    //ViewGroup viewGroup =context. findViewById(androi/d.R.id.content);
                    View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.membership_dialog, viewGroup, false);
                    Button upgrade=dialogView.findViewById(R.id.upgrade);
                    builder.setView(dialogView);
                    final AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                    upgrade.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent= new Intent(context, MembershipPackage.class);
                            context.startActivity(intent);
                            alertDialog.dismiss();
                        }
                    });

                }


            }
        });
        holder.like_icon.setOnClickListener(new View.OnClickListener() {
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
                    String url = "https://castercrew.com/mobile_app/user_likes";
                    StringRequest jsonRequest = new StringRequest(com.android.volley.Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject json = new JSONObject(response);
                                int   result=json.getInt("res");
                                if(result==1){
                                    holder.like_icon.setImageResource(R.drawable.red_heart);
                                    Toast.makeText(context,"Shortlisted successfully", Toast.LENGTH_LONG).show();
                                }else{
                                    holder.like_icon.setImageResource(R.drawable.orange_heart);
                                    Toast.makeText(context,"Already Shortlisted ", Toast.LENGTH_LONG).show();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();

                            }
                        }
                    },
                            new com.android.volley.Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                }
                            }) {
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<>();
                            params.put("from_uid",user_id);
                            params.put("to_uid",item.getUid());
                            params.put("action","180");

                            return params;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(context);
                    requestQueue.add(jsonRequest);

                }



            }
        });
        holder.call.setOnClickListener(new View.OnClickListener() {
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    //ViewGroup viewGroup = context.findViewById(android.R.id.content);
                    View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.membership_dialog, viewGroup, false);
                    Button upgrade=dialogView.findViewById(R.id.upgrade);
                    builder.setView(dialogView);
                    final AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                    upgrade.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent= new Intent(context, MembershipPackage.class);
                            context.startActivity(intent);
                            alertDialog.dismiss();
                        }
                    });
                }

            }
        });
        holder.download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(user_id.equalsIgnoreCase("")){

                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    //ViewGroup viewGroup = context.findViewById(android.R.id.content);
                    View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.membership_dialog, viewGroup, false);
                    Button upgrade=dialogView.findViewById(R.id.upgrade);
                    builder.setView(dialogView);
                    final AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                    upgrade.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            holder.download.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Toast.makeText(context,"Downloading Profile.......",Toast.LENGTH_LONG).show();
                                }
                            });

                            Intent intent= new Intent(context, MembershipPackage.class);
                            context.startActivity(intent);
                            alertDialog.dismiss();
                        }
                    });
                }

            }
        });
//        holder.tvtalent_title.setText(item.getTalent_title());

        Picasso.with(context).load(item.getTalent_pic()).into(holder.ivimage);
        holder.btnfollowing.setOnClickListener(new View.OnClickListener() {
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
                    String url = "https://castercrew.com/mobile_app/follow_unfollow";
                    StringRequest jsonRequest = new StringRequest(com.android.volley.Request.Method.POST, url, new com.android.volley.Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                JSONObject json = new JSONObject(response);
                                int   result=json.getInt("res");
                                if(result==1){
                                    holder.btnfollowing.setText("Following");
                                    Toast.makeText(context,"Following successfully", Toast.LENGTH_LONG).show();
                                }else{
                                    holder.btnfollowing.setText("Follow");
                                    Toast.makeText(context,"Already Following ", Toast.LENGTH_LONG).show();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();

                            }
                        }
                    },
                            new com.android.volley.Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                }
                            }) {
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<>();
                            params.put("from_uid",user_id);
                            params.put("to_uid",item.getUid());
                            params.put("action","180");
                            return params;
                        }
                    };
                    RequestQueue requestQueue = Volley.newRequestQueue(context);
                    requestQueue.add(jsonRequest);

                }
            }
        });

    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class SubCategoryViewHolder extends RecyclerView.ViewHolder  {
        TextView tvname,tvdesignation,tvdate,tvtalent_title,tvuid;
        ImageView ivimage,call,email,download,like_icon;
        TextView btnfollowing;

        SubCategoryViewHolder(View itemView) {
            super(itemView);
            like_icon=itemView.findViewById(R.id.like_icon);
            call = itemView.findViewById(R.id.call);
            email = itemView.findViewById(R.id.email);
            download = itemView.findViewById(R.id.download);

            tvname = itemView.findViewById(R.id.name);
            ivimage = itemView.findViewById(R.id.iv_image);
            tvdate= itemView.findViewById(R.id.date);
            tvdesignation = itemView.findViewById(R.id.designation);
            btnfollowing = itemView.findViewById(R.id.btn_follow);
            tvuid = itemView.findViewById(R.id.uid);
            tvtalent_title = itemView.findViewById(R.id.talent_title);

        }

    }
}