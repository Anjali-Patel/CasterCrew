package com.castercrewapp.castercrew.Activitys;

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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.castercrewapp.castercrew.R;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class TalentPostListActivity extends AppCompatActivity  {
    String imageurl = "";

    private int imageNo;
    Dialog Cameradialog;
    private static final int   CAMERA=5;
    private static final int SELECT_VIDEO = 2;
    RelativeLayout camera_gallery_vid;
    ImageView camera_gallery_video;

    LinearLayout l_add_photo,l_add_video;
    RecyclerView Images_listview;
    String strTitle;
EditText title,EmbeddedVideo,video_url;
Button submit;
ImageView img0;
    ImageView iv_add_photo;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    ArrayList<String> ImagesList = new ArrayList<String>();
    String imageFilePath="";
    public int PIC_CODE = 1;
    private static final int REQUEST_CAPTURE_IMAGE = 100;
    private static final int RESULT_LOAD_IMAGE = 1;
    String ImageName;
    Spinner category,subcategory;
    String[] categoryList = { "Select Category", "Artist", "Professionals", "Vendors", "Institutions","Activist","Videos","Audios","Reviews","Auditions"};
    String[] subcategoryList = { "Actor", "Actress", "Anchor", "Child Artist", "Dancer","Model","Singer","Music","Writer","Dubbing Artist","Editor","Director","Producer","Co-ordinator","Production Assistant","Camera men/women","architech","astrologer","carpenter","costume designer","driver","doctor","electrician","Engineer","fashion Designer","Technician","Location/Property","vehicle","Equip","animals","dancer","Acting Training","film making","Beautician/Saloon Training","Costamology Training","Singing","editing","Dubbing","Animations"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_talent_post_list);
        camera_gallery_video=findViewById(R.id.camera_gallery_video);
        camera_gallery_vid=findViewById(R.id.camera_gallery_vid);
        EmbeddedVideo=findViewById(R.id.EmbeddedVideo);
        video_url=findViewById(R.id.video_url);

        category=findViewById(R.id.category);
        l_add_video=findViewById(R.id.l_add_video);
        img0=findViewById(R.id.img0);
        subcategory=findViewById(R.id.subcategory);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,categoryList);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        category.setAdapter(aa);

        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    subcategory.setVisibility(View.VISIBLE);
                    ArrayAdapter aa1 = new ArrayAdapter(TalentPostListActivity.this,android.R.layout.simple_spinner_item,subcategoryList);
                    aa1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    //Setting the ArrayAdapter data on the Spinner
                    subcategory.setAdapter(aa1);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        title=findViewById(R.id.title);
        submit=findViewById(R.id.submit);
        l_add_photo=findViewById(R.id.l_add_photo);
        Images_listview=findViewById(R.id.Images_listview);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        Images_listview.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(),3);
        Images_listview.setLayoutManager(gridLayoutManager);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);

        Images_listview.setItemAnimator(new DefaultItemAnimator());
        mAdapter = new MultipleImagesAdapter(getApplicationContext(), ImagesList);
        Images_listview.setAdapter(mAdapter);
        l_add_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageNo=7;
                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .start(TalentPostListActivity.this);
            }
        });
        l_add_video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                EmbeddedVideo.setVisibility(View.VISIBLE);
                Cameradialog = new Dialog(TalentPostListActivity.this, android.R.style.Theme_Translucent_NoTitleBar);
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
                        Cameradialog.dismiss();
                    }
                });
                gallery_layout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EmbeddedVideo.setVisibility(View.GONE);
                        video_url.setVisibility(View.GONE);
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
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strTitle=title.getText().toString().trim();
                if(strTitle.equalsIgnoreCase("")){
                  Toast.makeText(TalentPostListActivity.this,"Please Enter Title",Toast.LENGTH_LONG).show();
                }else if(imageFilePath.equalsIgnoreCase("")){
                    Toast.makeText(TalentPostListActivity.this,"Please select Image",Toast.LENGTH_LONG).show();
                }else if(category.getSelectedItem().toString().trim().equalsIgnoreCase("Select Category")) {
                    Toast.makeText(TalentPostListActivity.this, "Please Select Category", Toast.LENGTH_SHORT).show();
                }
                else{

                }
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

    }


    @Override
    public void onActivityResult(int requestcode, int resultcode, Intent data) {
        ImagesList.clear();
        super.onActivityResult(requestcode, resultcode, data);

        if (requestcode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE && resultcode == Activity.RESULT_OK) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            Uri resultUri = result.getUri();
            switch (imageNo){
                case (7):
                    img0.setVisibility(View.VISIBLE);
                    img0.setImageURI(resultUri);
                    break;
            }
        }
        if (requestcode == CAMERA) {
            Uri selectedImageUri =data.getData();
            imageurl = getPath(TalentPostListActivity.this,selectedImageUri).replaceAll(" ","%20");
            camera_gallery_vid.setVisibility(View.VISIBLE);
            camera_gallery_video.setVisibility(View.VISIBLE);
            Bitmap thumb = ThumbnailUtils.createVideoThumbnail(imageurl, MediaStore.Video.Thumbnails.MINI_KIND);
            camera_gallery_video.setImageBitmap(thumb);
        }
        if (data != null) {
            if (requestcode == SELECT_VIDEO) {
                Uri selectedImageUri =data.getData();
                imageurl = getPath(TalentPostListActivity.this,selectedImageUri).replaceAll(" ","%20");
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
