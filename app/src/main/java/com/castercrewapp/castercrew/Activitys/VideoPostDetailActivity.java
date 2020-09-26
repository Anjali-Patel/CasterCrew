package com.castercrewapp.castercrew.Activitys;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.castercrewapp.castercrew.MainActivity;
import com.castercrewapp.castercrew.R;
import com.castercrewapp.castercrew.model.CategoryModel;
import com.castercrewapp.castercrew.model.IndustryModel;
import com.castercrewapp.castercrew.utils.AccountUtils;
import com.castercrewapp.castercrew.utils.SharedPreferenceUtils;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VideoPostDetailActivity extends AppCompatActivity {
    String User_id;
    Uri resultUri;
    FrameLayout progressBarHolder;

    String imageurl="",strEmbedVideo="",SelectedIndustry="",SelectedCategory="";
    ImageView camera_gallery_video;
    RelativeLayout camera_gallery_vid;
    Dialog Cameradialog;
    private static final int   CAMERA=5;
    private static final int SELECT_VIDEO = 2;
    TextView textCount;
    Spinner designation,industry_category,category;
    String[] designationList = { "Select Designation", "Director", "Producer", "Hero", "Heroine","Dance Master","Stunts","Other Crew"};

    private static final int REQUEST_CAPTURE_IMAGE = 100;
    private static final int RESULT_LOAD_IMAGE = 1;
    String strvideo_url="",strTitle="",strDescription="",strDirector="",strProducer,strHero,strHeroin,strDancemaster,strStunts,strOthercrew,strRatingBar;
    final int PIC_CROP = 1;
    Button submit;
    RatingBar ratingBar;
    ImageView img1;
    private int imageNo;
TextView photo1;
EditText video_url,title,description,director,producer,hero,heroin,dance_master,stunts,other_crew,EmbeddedVideo;
    RecyclerView Images_listview;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<String> ImagesList = new ArrayList<String>();
    String imageFilePath="",selectedDesignation="";
    LinearLayout linear_chooseVideo;
    SharedPreferenceUtils preferances;
    ArrayList<String> selectIndustry;
    ArrayList<IndustryModel> IndustryModelArraylist;
    ArrayList<IndustryModel> IndustryModelArraylistTemp;
    ArrayList<String> select_category;
    ArrayList<CategoryModel> CategoryModelArraylist;
    ArrayList<CategoryModel>CategoryModelArraylistTemp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_post_detail);
        preferances= SharedPreferenceUtils.getInstance(this);
        User_id=preferances.getStringValue(AccountUtils.PREF_USER_ID,"");
        IndustryModelArraylist=new ArrayList<>();
        progressBarHolder=findViewById(R.id.progressBarHolder);
        IndustryModelArraylistTemp=new ArrayList<>();
        CategoryModelArraylist=new ArrayList<>();
        CategoryModelArraylistTemp=new ArrayList<>();
        select_category=new ArrayList<>();
        selectIndustry=new ArrayList<>();
        selectIndustry.add("Select industry ");
        select_category.add("select category");
        selectCategory();
        selectIndustry();
        industry_category=findViewById(R.id.industry_category);
        category=findViewById(R.id.category);
        designation=findViewById(R.id.designation);
        camera_gallery_video=findViewById(R.id.camera_gallery_video);
        EmbeddedVideo=findViewById(R.id.EmbeddedVideo);
        camera_gallery_vid=findViewById(R.id.camera_gallery_vid);
        linear_chooseVideo=findViewById(R.id.linear_chooseVideo);
        textCount=findViewById(R.id.textCount);

        Images_listview=findViewById(R.id.Images_listview);
        Images_listview.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),3);
        Images_listview.setLayoutManager(gridLayoutManager);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        Images_listview.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new MultipleImagesAdapter(getApplicationContext(), ImagesList);
        Images_listview.setAdapter(mAdapter);
        img1=findViewById(R.id.img1);

        video_url=findViewById(R.id.video_url);
        title=findViewById(R.id.title);
        description=findViewById(R.id.description);
        director=findViewById(R.id.director);

        photo1=findViewById(R.id.photo1);

        submit=findViewById(R.id.submit);
        ratingBar=findViewById(R.id.ratingBar);

        Toolbar toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,designationList);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        designation.setAdapter(aa);

        designation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

             selectedDesignation=designationList[position];

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ArrayAdapter<String> CategoryryAdapter = new ArrayAdapter<String>(VideoPostDetailActivity.this, R.layout.support_simple_spinner_dropdown_item, select_category);
        CategoryryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(CategoryryAdapter);
        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                SelectedCategory = parent.getItemAtPosition(position).toString();
                for (int i = 0; i < CategoryModelArraylist.size(); i++) {
                    if (CategoryModelArraylist.get(i).getCategoryName().contains(parent.getItemAtPosition(position).toString())) {
                        CategoryModelArraylistTemp.add(CategoryModelArraylist.get(i));
                        SelectedCategory = CategoryModelArraylist.get(position - 1).getCategoryId();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        ArrayAdapter<String> IndustryAdapter = new ArrayAdapter<String>(VideoPostDetailActivity.this, R.layout.support_simple_spinner_dropdown_item, selectIndustry);
        IndustryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        industry_category.setAdapter(IndustryAdapter);
        industry_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                SelectedCategory = parent.getItemAtPosition(position).toString();
                for (int i = 0; i < IndustryModelArraylist.size(); i++) {
                    if (IndustryModelArraylist.get(i).getIndustryName().contains(parent.getItemAtPosition(position).toString())) {
                        IndustryModelArraylistTemp.add(IndustryModelArraylist.get(i));
                        SelectedIndustry = IndustryModelArraylist.get(position - 1).getIndustryId();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        description.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                int length = description.length();
                String convert = String.valueOf(length);
                textCount.setText(convert+"/"+"150");
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int length = description.length();
                String convert = String.valueOf(length);
                textCount.setText(convert+"/"+"150");
            }
            @Override
            public void afterTextChanged(Editable s) { }
        });
        linear_chooseVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                EmbeddedVideo.setVisibility(View.VISIBLE);

                Cameradialog = new Dialog(VideoPostDetailActivity.this, android.R.style.Theme_Translucent_NoTitleBar);
                Cameradialog.setContentView(R.layout.custom_dialog);
                TextView tv_gallery = Cameradialog.findViewById(R.id.tv_gallery);

                final RelativeLayout gallery_layout = Cameradialog.findViewById(R.id.gallery_layout);
                RelativeLayout you_tube = Cameradialog.findViewById(R.id.you_tube);
                TextView tv_camera = Cameradialog.findViewById(R.id.tv_camera);
                RelativeLayout  embed_video  = Cameradialog.findViewById(R.id.embed_code);
                RelativeLayout   camera_layout = Cameradialog.findViewById(R.id.camera_layout);

                you_tube.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EmbeddedVideo.setVisibility(View.GONE);
                        camera_gallery_vid.setVisibility(View.GONE);
                        video_url.setVisibility(View.VISIBLE);
                        imageurl="";
                        strEmbedVideo="";
                        strvideo_url=video_url.getText().toString().trim();
                        Cameradialog.dismiss();
                    }
                });
                gallery_layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EmbeddedVideo.setVisibility(View.GONE);
                        video_url.setVisibility(View.GONE);
                        strvideo_url="";
                        strEmbedVideo="";
                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(intent, SELECT_VIDEO);
                        Cameradialog.dismiss();

                    }
                });
                camera_layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EmbeddedVideo.setVisibility(View.GONE);
                        video_url.setVisibility(View.GONE);
                        strvideo_url="";
                        strEmbedVideo="";
                        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                        startActivityForResult(intent, CAMERA);
                        Cameradialog.dismiss();


                    }
                });
                embed_video.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        video_url.setVisibility(View.GONE);

                        strEmbedVideo=EmbeddedVideo.getText().toString().trim();
                        strvideo_url="";
                        imageurl="";
                        camera_gallery_vid.setVisibility(View.GONE);

                        EmbeddedVideo.setVisibility(View.VISIBLE);
                        Cameradialog.dismiss();


                    }
                });
                RelativeLayout dialogMainLayout = Cameradialog.findViewById(R.id.dialog_main_layout);
                dialogMainLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cameradialog.dismiss();
                    }
                });
                Cameradialog.show();
            }
        });



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strRatingBar=String.valueOf(ratingBar.getRating());
                strvideo_url=video_url.getText().toString().trim();
                strTitle=title.getText().toString().trim();
                strDescription=description.getText().toString().trim();
                strDirector=director.getText().toString().trim();

                if(strTitle.equalsIgnoreCase("")){
                    Toast.makeText(VideoPostDetailActivity.this,"Please Enter Title",Toast.LENGTH_LONG).show();
                }else if(strDescription.equalsIgnoreCase("")){
                    Toast.makeText(VideoPostDetailActivity.this,"Please Enter Description",Toast.LENGTH_LONG).show();
                }else if(strDescription.length()<150){
                    Toast.makeText(VideoPostDetailActivity.this,"Minimum length of video should be 150",Toast.LENGTH_LONG).show();
                }else if(strRatingBar.equalsIgnoreCase("")){
                    Toast.makeText(VideoPostDetailActivity.this,"Please Enter RatingBar",Toast.LENGTH_LONG).show();
                }else if(strvideo_url.equalsIgnoreCase("")&&strEmbedVideo.equalsIgnoreCase("")&&imageurl.equalsIgnoreCase("")){
                    Toast.makeText(VideoPostDetailActivity.this,"Please Enter Video URL",Toast.LENGTH_LONG).show();
                }else if(strDirector.equalsIgnoreCase("")){
                    Toast.makeText(VideoPostDetailActivity.this,"Please Enter Designation  Name",Toast.LENGTH_LONG).show();
                }else if(String.valueOf(resultUri).equalsIgnoreCase("")){
                    Toast.makeText(VideoPostDetailActivity.this,"Please select  image",Toast.LENGTH_LONG).show();
                }else if(designation.getSelectedItem().toString().trim().equalsIgnoreCase("Select Designation ")) {
                    Toast.makeText(VideoPostDetailActivity.this, "Please Select designation", Toast.LENGTH_SHORT).show();
                } else if(category.getSelectedItem().toString().trim().equalsIgnoreCase("Select Category")) {
                    Toast.makeText(VideoPostDetailActivity.this, "Please Select Category Id", Toast.LENGTH_SHORT).show();
                }else if(industry_category.getSelectedItem().toString().trim().equalsIgnoreCase("Select Industry")) {
                    Toast.makeText(VideoPostDetailActivity.this, "Please Select industry Id", Toast.LENGTH_SHORT).show();
                }
                else{
                AddVideoData();
                    progressBarHolder.setVisibility(View.VISIBLE);
                }
            }
        });

        photo1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageNo=1;
                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .start(VideoPostDetailActivity.this);
            }
        });

    }

    private void selectIndustry() {
        String url = "https://castercrew.com/mobileapp/get_industries";
        StringRequest jsonRequest = new StringRequest(com.android.volley.Request.Method.GET, url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject json = new JSONObject(response);
                            JSONArray e = json.getJSONArray("industries_list");
                            for (int i = 0; i < e.length(); i++) {
                                JSONObject industry_obj = e.getJSONObject(i);
                                IndustryModel model = new IndustryModel();
                                model.setIndustryId(industry_obj.getString("id"));
                                model.setIndustryName(industry_obj.getString("industry_name"));
//                                model.setStateStatus(e.getString("status"));
                                IndustryModelArraylist.add(model);
                            }
                            for (int i = 0; i < IndustryModelArraylist.size(); i++) {
                                final IndustryModel Items = IndustryModelArraylist.get(i);
                                selectIndustry.add(Items.getIndustryName());
                            }

                        } catch (JSONException e) {
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

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(VideoPostDetailActivity.this);
        requestQueue.add(jsonRequest);

    }

    private void selectCategory() {
        String url = "https://castercrew.com/mobileapp/audio_categories";
        StringRequest jsonRequest = new StringRequest(Request.Method.GET, url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject json = new JSONObject(response);
                            JSONArray e = json.getJSONArray("categories");
                            for (int i = 0; i < e.length(); i++) {
                                JSONObject industry_obj = e.getJSONObject(i);
                                CategoryModel model = new CategoryModel();
                                model.setCategoryId(industry_obj.getString("id"));
                                model.setCategoryName(industry_obj.getString("catg_name"));
//                                model.setStateStatus(e.getString("status"));
                                CategoryModelArraylist.add(model);
                            }
                            for (int i = 0; i < CategoryModelArraylist.size(); i++) {
                                final CategoryModel Items = CategoryModelArraylist.get(i);
                                select_category.add(Items.getCategoryName());
                            }


                        } catch (JSONException e) {
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

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(VideoPostDetailActivity.this);
        requestQueue.add(jsonRequest);
    }

    private void AddVideoData() {
        String url = "https://castercrew.com/mobileapp/add_video";
        StringRequest jsonRequest = new StringRequest(com.android.volley.Request.Method.POST, url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressBarHolder.setVisibility(View.GONE);

                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            int result = jsonObject.getInt("error");
                            if (result==0) {
                                Toast.makeText(VideoPostDetailActivity.this,"News Posted  Successfully",Toast.LENGTH_LONG).show();
                                Intent intent= new Intent(VideoPostDetailActivity.this, MainActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(VideoPostDetailActivity.this,"Something went wrong",Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();

                        }
                    }
                },
                new com.android.volley.Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressBarHolder.setVisibility(View.GONE);
                        Toast.makeText(VideoPostDetailActivity.this,"Something went wrong",Toast.LENGTH_LONG).show();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("catg_id",SelectedCategory);
                params.put("industry_id",SelectedIndustry);
                params.put("title",strTitle);
                params.put("description", strDescription);
                params.put("name",strDirector);
                params.put("uid", User_id);
                params.put("designation",selectedDesignation);
                params.put("image",String.valueOf(resultUri));
                if(!imageurl.isEmpty()){
                    params.put("youtube_url",imageurl );

                }else if(!strEmbedVideo.isEmpty()){
                    params.put("youtube_url",strEmbedVideo );

                }else if(strvideo_url.isEmpty()){
                    params.put("youtube_url",strvideo_url );

                }
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(VideoPostDetailActivity.this);
        requestQueue.add(jsonRequest);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
             resultUri = result.getUri();
            switch (imageNo){
                case (1):
                    photo1.setVisibility(View.GONE);
                    img1.setVisibility(View.VISIBLE);
                    img1.setImageURI(resultUri);
                    break;

            }
        }
        if (requestCode == CAMERA) {
            Uri selectedImageUri =data.getData();
            imageurl = getPath(VideoPostDetailActivity.this,selectedImageUri).replaceAll(" ","%20");
            camera_gallery_vid.setVisibility(View.VISIBLE);
            camera_gallery_video.setVisibility(View.VISIBLE);

            Bitmap thumb = ThumbnailUtils.createVideoThumbnail(imageurl, MediaStore.Video.Thumbnails.MINI_KIND);
            camera_gallery_video.setImageBitmap(thumb);

        }

        if (data != null) {
            if (requestCode == SELECT_VIDEO) {
                Uri selectedImageUri =data.getData();
                imageurl = getPath(VideoPostDetailActivity.this,selectedImageUri).replaceAll(" ","%20");
                camera_gallery_vid.setVisibility(View.VISIBLE);
                camera_gallery_video.setVisibility(View.VISIBLE);

                Bitmap thumb = ThumbnailUtils.createVideoThumbnail(imageurl, MediaStore.Video.Thumbnails.MINI_KIND);
                camera_gallery_video.setImageBitmap(thumb);


            }
        }
    }
    public static String getPath(final Context context, final Uri uri) {
        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
        Log.i("URI", uri + "");
        String result = uri + "";
        // DocumentProvider
        //  if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
        if (isKitKat && (result.contains("media.documents"))) {
            String[] ary = result.split("/");
            int length = ary.length;
            String imgary = ary[length - 1];
            final String[] dat = imgary.split("%3A");
            final String docId = dat[1];
            final String type = dat[0];
            Uri contentUri = null;
            if ("image".equals(type)) {
                contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            } else if ("video".equals(type)) {
                contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
            } else if ("audio".equals(type)) {
            }
            final String selection = "_id=?";
            final String[] selectionArgs = new String[]{
                    dat[1]
            };
            return getDataColumn(context, contentUri, selection, selectionArgs);
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            return getDataColumn(context, uri, null, null);
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }
        return null;
    }
    public static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {

        Cursor cursor = null;
        final String column = "_data";
        final String[] projection = {
                column};
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);


                return cursor.getString(column_index);
            }


            /*String[] mediaColumns = {MediaStore.Video.Media.SIZE};
            Cursor cursor = getContext().getContentResolver().query(videoUri, mediaColumns, null, null, null);
            cursor.moveToFirst();
            int sizeColInd = cursor.getColumnIndex(mediaColumns[0]);
            long fileSize = cursor.getLong(sizeColInd);
            cursor.close();*/
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }
    public class MultipleImagesAdapter extends RecyclerView.Adapter<MultipleImagesAdapter.MyViewHolder> {
        Context context1;
        private ArrayList<String> MultipleImagesList;

        public MultipleImagesAdapter(Context context, ArrayList<String> ImagesList) {
            context1 = context;
            MultipleImagesList = ImagesList;
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            ImageView adapterImage;

            public MyViewHolder(View v) {
                super(v);
                adapterImage = (ImageView) v.findViewById(R.id.adapterImageview);
            }
        }

        @NonNull
        @Override
        public MultipleImagesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            // create a new view
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.multiple_image_listitem, parent, false);
            return new MultipleImagesAdapter.MyViewHolder(v);
        }
        @Override
        public void onBindViewHolder(@NonNull MultipleImagesAdapter.MyViewHolder holder, final int position) {
            Glide.with(context1).load(/*"file:" +*/ MultipleImagesList.get(position)).into(holder.adapterImage);
          /*  holder.adapterImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (position == MultipleImagesList.size() - 1)
                        dispatchTakePictureIntent();
                    Glide.with(context1).load("file:" + MultipleImagesList.get(position)).into(iv_add_photo);

                }
            });*/
        }

        @Override
        public int getItemCount() {
            return MultipleImagesList.size();
        }
    }

}
