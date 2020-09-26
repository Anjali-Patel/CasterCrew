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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.castercrewapp.castercrew.Activitys.LoginActivity;
import com.castercrewapp.castercrew.Activitys.MembershipPackage;
import com.castercrewapp.castercrew.Activitys.SubscriptionDetailActivity;
import com.castercrewapp.castercrew.Listeners.OnItemClickListener;
import com.castercrewapp.castercrew.MainActivity;
import com.castercrewapp.castercrew.R;
import com.castercrewapp.castercrew.model.recent_featured_profile_list;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class recent_featured_profile_adapter extends RecyclerView.Adapter<recent_featured_profile_adapter.SubCategoryViewHolder>{
    public Context context;
    private OnItemClickListener listener;
    private ArrayList<recent_featured_profile_list> arrayList;
    ViewGroup viewGroup;
    String user_id;
    public recent_featured_profile_adapter(OnItemClickListener listener, ArrayList<recent_featured_profile_list> arrayList,String user_id) {
        this.listener = listener;
        this.arrayList = arrayList;
        this.user_id=user_id;
    }
    @NonNull
    @Override
    public SubCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.recent_featured_profiles_design, parent, false);
                     return new SubCategoryViewHolder(view);
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final SubCategoryViewHolder holder, int position) {
        final recent_featured_profile_list item = arrayList.get(position);
        holder.tvname.setText(item.getFull_name());
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(item.getReg_dt());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String newString = new SimpleDateFormat("MMM dd yyyy hh:mm a").format(date); // 25-03-2019
        holder.tvdesignation.setText(item.getArtist_name()+"\n"+item.getTalent_title()+"\n"+newString);
//        holder.tvdate.setText(item.getReg_dt());
        holder.tvuid.setText(item.getUid());
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
                    //ViewGroup viewGroup =context. findViewById(android.R.id.content);
                    View dialogView = LayoutInflater.from(v.getContext()).inflate(R.layout.membership_dialog, viewGroup, false);
                    Button upgrade=dialogView.findViewById(R.id.upgrade);
                    ImageView image_view=dialogView.findViewById(R.id.image_view);
                    TextView contact_description=dialogView.findViewById(R.id.contact_description);
                    contact_description.setText("Contact " + item.getFull_name() +  "on");
                    TextView name=dialogView.findViewById(R.id.name);
                    name.setText(item.getFull_name());
                    Picasso.with(context).load(item.getTalent_pic()).into(image_view);
                    builder.setView(dialogView);
                    final AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                    upgrade.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent= new Intent(context, MembershipPackage.class);
                            context.startActivity(intent);
                            alertDialog.dismiss();
                        }});
                }

            }});
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
                    ImageView image_view=dialogView.findViewById(R.id.image_view);
                    TextView name=dialogView.findViewById(R.id.name);
                    TextView contact_description=dialogView.findViewById(R.id.contact_description);
                    contact_description.setText("Contact "+item.getFull_name()+"on");
                    name.setText(item.getFull_name());
                    Picasso.with(context).load(item.getTalent_pic()).into(image_view);

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

//        holder.member_ship.setText("");
//        holder.verified.setText("");
//        holder.online_offline.setImageResource(R.drawable.red_dot);

        holder.download.setOnClickListener(new View.OnClickListener() {
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
                    ImageView image_view=dialogView.findViewById(R.id.image_view);
                    TextView name=dialogView.findViewById(R.id.name);
                    TextView contact_description=dialogView.findViewById(R.id.contact_description);
                    contact_description.setText("Contact "+item.getFull_name()+"on");
                    name.setText(item.getFull_name());
                    Picasso.with(context).load(item.getTalent_pic()).error(R.drawable.men).into(image_view);
                    builder.setView(dialogView);
                    final AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                    upgrade.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent= new Intent(context, MembershipPackage.class);
                            context.startActivity(intent);
                            alertDialog.dismiss();
                            holder.download.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Toast.makeText(context,"Downloading Profile.......",Toast.LENGTH_LONG).show();
                                }
                            });

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

    class SubCategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvname,tvdesignation,tvdate,tvtalent_title,tvuid,member_ship,verified;
        ImageView ivimage,call,email,download,like_icon,online_offline;
        TextView btnfollowing;
        LinearLayout linear_following_button;
        SubCategoryViewHolder(View itemView) {
            super(itemView);
            online_offline=itemView.findViewById(R.id.online_offline);
            member_ship=itemView.findViewById(R.id.member_ship);
            verified=itemView.findViewById(R.id.verified);
            like_icon=itemView.findViewById(R.id.like_icon);
            call = itemView.findViewById(R.id.call);
            email = itemView.findViewById(R.id.email);
            download = itemView.findViewById(R.id.download);
            linear_following_button=itemView.findViewById(R.id.linear_following_button);
            tvname = itemView.findViewById(R.id.name);
            ivimage = itemView.findViewById(R.id.iv_image);
            tvdate= itemView.findViewById(R.id.date);
            tvdesignation = itemView.findViewById(R.id.designation);
            btnfollowing = itemView.findViewById(R.id.btn_follow);
            tvuid = itemView.findViewById(R.id.uid);
            tvtalent_title = itemView.findViewById(R.id.talent_title);

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
