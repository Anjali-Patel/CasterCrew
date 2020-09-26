package com.castercrewapp.castercrew.Activitys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
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
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.castercrewapp.castercrew.MainActivity;
import com.castercrewapp.castercrew.R;
import com.castercrewapp.castercrew.model.IndustryModel;
import com.castercrewapp.castercrew.utils.AccountUtils;
import com.castercrewapp.castercrew.utils.SharedPreferenceUtils;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NewsPostActivity extends AppCompatActivity {

    String imageurl = "";
    ImageView camera_gallery_video;
    RelativeLayout camera_gallery_vid;
    Dialog Cameradialog;
    private static final int CAMERA = 5;
    private static final int SELECT_VIDEO = 2;
    FrameLayout progressBarHolder;
    TextView textCount;
    private int imageNo;

    String strDescription = "", strTitle = "", strVideo = "";
    Button submit;
    EditText description, title, video_url, EmbeddedVideo;
    LinearLayout l_add_photo;
    RecyclerView Images_listview;
    ImageView iv_add_photo;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<String> ImagesList = new ArrayList<String>();
    String imageFilePath = "", User_id;
    public int PIC_CODE = 1;
    private static final int REQUEST_CAPTURE_IMAGE = 100;
    private static final int RESULT_LOAD_IMAGE = 1;
    String ImageName;
    ImageView img0;
    Spinner status, industries_list;
    String SelectedStatus = "", SelectedIndustry = "";
    String[] StatusList = {"Select Status", "publisshed", "unpublished"};
    ArrayList<String> selectIndustry;
    ArrayList<IndustryModel> IndustryModelArraylist;
    ArrayList<IndustryModel> IndustryModelArraylistTemp;
    SharedPreferenceUtils preferances;
    LinearLayout linear_chooseVideo;

    @Override
    protected void onStart() {
        super.onStart();
        getIndustry();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_post);
        camera_gallery_video = findViewById(R.id.camera_gallery_video);
        EmbeddedVideo = findViewById(R.id.EmbeddedVideo);
        camera_gallery_vid = findViewById(R.id.camera_gallery_vid);
        linear_chooseVideo = findViewById(R.id.linear_chooseVideo);


        preferances = SharedPreferenceUtils.getInstance(NewsPostActivity.this);
        img0 = findViewById(R.id.img0);
        User_id = preferances.getStringValue(AccountUtils.PREF_USER_ID, "");
        submit = findViewById(R.id.submit);
        progressBarHolder = findViewById(R.id.progressBarHolder);
        textCount = findViewById(R.id.textCount);
        status = findViewById(R.id.status);
        industries_list = findViewById(R.id.industries_list);
        description = findViewById(R.id.description);
        title = findViewById(R.id.title);
        video_url = findViewById(R.id.video_url);
        l_add_photo = findViewById(R.id.l_add_photo);
        iv_add_photo = findViewById(R.id.iv_add_photo);
        Images_listview = findViewById(R.id.Images_listview);
        IndustryModelArraylist = new ArrayList<>();
        IndustryModelArraylistTemp = new ArrayList<>();
        selectIndustry = new ArrayList<>();
        selectIndustry.add("Select Industry");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Images_listview.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 3);
        Images_listview.setLayoutManager(gridLayoutManager);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

//        Images_listview.setItemAnimator(new DefaultItemAnimator());
//        mAdapter = new MultipleImagesAdapter(getApplicationContext(), ImagesList);
//        Images_listview.setAdapter(mAdapter);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        l_add_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageNo = 7;
                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .start(NewsPostActivity.this);
//                dispatchTakePictureIntent();
            }
        });
        linear_chooseVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                EmbeddedVideo.setVisibility(View.VISIBLE);

                Cameradialog = new Dialog(NewsPostActivity.this, android.R.style.Theme_Translucent_NoTitleBar);
                Cameradialog.setContentView(R.layout.custom_dialog);
                TextView tv_gallery = Cameradialog.findViewById(R.id.tv_gallery);

                final RelativeLayout gallery_layout = Cameradialog.findViewById(R.id.gallery_layout);
                RelativeLayout you_tube = Cameradialog.findViewById(R.id.you_tube);
                TextView tv_camera = Cameradialog.findViewById(R.id.tv_camera);
                RelativeLayout embed_video = Cameradialog.findViewById(R.id.embed_code);
                RelativeLayout camera_layout = Cameradialog.findViewById(R.id.camera_layout);

                you_tube.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EmbeddedVideo.setVisibility(View.GONE);
                        camera_gallery_vid.setVisibility(View.GONE);
                        video_url.setVisibility(View.VISIBLE);
                        Cameradialog.dismiss();
                    }
                });
                gallery_layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EmbeddedVideo.setVisibility(View.GONE);
                        video_url.setVisibility(View.GONE);
                        camera_gallery_vid.setVisibility(View.VISIBLE);

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
                        camera_gallery_vid.setVisibility(View.VISIBLE);

                        Intent intent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
                        startActivityForResult(intent, CAMERA);
                        Cameradialog.dismiss();


                    }
                });
                embed_video.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        video_url.setVisibility(View.GONE);


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








      /*  video_url.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                l_add_photo.setVisibility(View.GONE);
            }
        });*/
        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, StatusList);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        status.setAdapter(aa);
        status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                SelectedStatus = StatusList[position];
                if (StatusList[position].equalsIgnoreCase("published")) {
                    SelectedStatus = "1";
                } else {
                    SelectedStatus = "0";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        ArrayAdapter<String> IndustryAdapter = new ArrayAdapter<String>(NewsPostActivity.this, R.layout.support_simple_spinner_dropdown_item, selectIndustry);
        IndustryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        industries_list.setAdapter(IndustryAdapter);
        industries_list.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
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
                textCount.setText(convert + "/" + "150");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int length = description.length();
                String convert = String.valueOf(length);
                textCount.setText(convert + "/" + "150");
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strDescription = description.getText().toString().trim();
                strTitle = title.getText().toString().trim();
                strVideo = video_url.getText().toString().trim();
                if (strTitle.equalsIgnoreCase("")) {
                    Toast.makeText(NewsPostActivity.this, "Please Enter Title", Toast.LENGTH_LONG).show();
                } else if (strDescription.equalsIgnoreCase("")) {
                    Toast.makeText(NewsPostActivity.this, "Please Enter Description", Toast.LENGTH_LONG).show();
                } else if (strDescription.length() < 150) {
                    Toast.makeText(NewsPostActivity.this, "Minimum length of description should be 150", Toast.LENGTH_LONG).show();
                } else if (industries_list.getSelectedItem().toString().trim().equalsIgnoreCase("Select Industry")) {
                    Toast.makeText(NewsPostActivity.this, "Please Select Industry", Toast.LENGTH_SHORT).show();
                }
                        /*else if(strVideo.equalsIgnoreCase("")){
                            Toast.makeText(NewsPostActivity.this,"Please Enter Video Link",Toast.LENGTH_LONG).show();
                        }*/
                else {
                    postNewsData();
                    progressBarHolder.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    private void postNewsData() {
        String url = "https://castercrew.com/mobileapp/post_news";
        StringRequest jsonRequest = new StringRequest(com.android.volley.Request.Method.POST, url,
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressBarHolder.setVisibility(View.GONE);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String result = jsonObject.getString("error");
                            if (result.equalsIgnoreCase("false")) {
                                Toast.makeText(NewsPostActivity.this, "News Posted  Successfully", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(NewsPostActivity.this, MainActivity.class);
                                startActivity(intent);
                            } else {
                                Toast.makeText(NewsPostActivity.this, "Something went wrong", Toast.LENGTH_LONG).show();
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

                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("uid", User_id);
                params.put("industry_id", SelectedIndustry);
                params.put("title", strTitle);
                params.put("subject", strVideo);
                params.put("description", strDescription);
                params.put("tags", "news");
                params.put("status", SelectedStatus);
                int Imagecount = 0;
                for (int i = 0; i < ImagesList.size(); i++) {
                    params.put("photo" + (Imagecount + 1), ImagesList.get(i));

//                    nameValuePairs.add(new BasicNameValuePair("photo"+(Imagecount+1), ImagesList.get(i)));
                    Imagecount++;
                }
//                params.put("photo", "");
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(jsonRequest);

    }

    private void getIndustry() {
        String url = "https://castercrew.com/mobileapp/get_industries";

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url).get().build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                call.cancel();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                ResponseBody responseBody = response.body();
                final String myResponse = responseBody.string();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONObject json = new JSONObject(myResponse);
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
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });
            }
        });
    }

/*
    private void openPickerDialog(Context context) {
        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.custom_dialog_box);
        dialog.setTitle("Add Photo");
        Button btnExit = (Button) dialog.findViewById(R.id.btnExit);
        btnExit.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                finish();
                dialog.dismiss();
            }
        });
        dialog.findViewById(R.id.btnChoosePath)
                .setOnClickListener(new View.OnClickListener() {
                    @Override public void onClick(View v) {
                        activeGallery();
                        dialog.dismiss();
                    }
                });
        dialog.findViewById(R.id.btnTakePhoto)
                .setOnClickListener(new View.OnClickListener() {
                    @Override public void onClick(View v) {
                        dispatchTakePictureIntent();
                        dialog.dismiss();
                    }
                });

        // show dialog on screen
        dialog.show();
    }

    private void activeGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);

        startActivityForResult(intent, RESULT_LOAD_IMAGE);

    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
            }
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(NewsPostActivity.this, "gss.com.bsell.GenericProvider", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_CAPTURE_IMAGE);
            }
        }
    }

    private File createImageFile() throws IOException {
        File storageDir = Environment.getExternalStorageDirectory();
        File f = new File(storageDir + "/OLXImages");

        if (!f.exists()) {
            f.mkdirs();
        }
        ImageName = "Image" + PIC_CODE + ".png";
        PIC_CODE++;
        File f1 = new File(f, ImageName);
        imageFilePath = f1.getAbsolutePath();
        return f1;
    }
*/

    @Override
    public void onActivityResult(int requestcode, int resultcode, Intent data) {
        super.onActivityResult(requestcode, resultcode, data);
        if (requestcode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultcode == Activity.RESULT_OK) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            Uri resultUri = result.getUri();
            switch (imageNo) {
                case (7):
                    img0.setVisibility(View.VISIBLE);
                    img0.setImageURI(resultUri);
                    break;


            }
        }
        if (requestcode == CAMERA) {
            Uri selectedImageUri = data.getData();
            imageurl = getPath(NewsPostActivity.this, selectedImageUri).replaceAll(" ", "%20");
            camera_gallery_vid.setVisibility(View.VISIBLE);
            camera_gallery_video.setVisibility(View.VISIBLE);
            Bitmap thumb = ThumbnailUtils.createVideoThumbnail(imageurl, MediaStore.Video.Thumbnails.MINI_KIND);
            camera_gallery_video.setImageBitmap(thumb);
        }
        if (data != null) {
            if (requestcode == SELECT_VIDEO) {
                Uri selectedImageUri = data.getData();
                imageurl = getPath(NewsPostActivity.this, selectedImageUri).replaceAll(" ", "%20");
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
}

//    public class MultipleImagesAdapter extends RecyclerView.Adapter<MultipleImagesAdapter.MyViewHolder> {
//        Context context1;
//        private ArrayList<String> MultipleImagesList;
//
//        public MultipleImagesAdapter(Context context, ArrayList<String> ImagesList) {
//            context1 = context;
//            MultipleImagesList = ImagesList;
//        }
//
//        public class MyViewHolder extends RecyclerView.ViewHolder {
//            ImageView adapterImage;
//
//            public MyViewHolder(View v) {
//                super(v);
//                adapterImage = (ImageView) v.findViewById(R.id.adapterImageview);
//            }
//        }
//
//        @NonNull
//        @Override
//        public MultipleImagesAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//            // create a new view
//            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.multiple_image_listitem, parent, false);
//            return new MultipleImagesAdapter.MyViewHolder(v);
//        }
//
//        @Override
//        public void onBindViewHolder(@NonNull MultipleImagesAdapter.MyViewHolder holder, final int position) {
//            Glide.with(context1).load(/*"file:" +*/ MultipleImagesList.get(position)).into(holder.adapterImage);
//          /*  holder.adapterImage.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if (position == MultipleImagesList.size() - 1)
//                        dispatchTakePictureIntent();
//                    Glide.with(context1).load("file:" + MultipleImagesList.get(position)).into(iv_add_photo);
//
//                }
//            });*/
//        }
//
//        @Override
//        public int getItemCount() {
//            return MultipleImagesList.size();
//        }
//    }

