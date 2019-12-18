package com.example.project1213;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.BroadcastReceiver;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

import org.litepal.LitePal;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddRecords extends AppCompatActivity {

    private static final String TAG = "AddRecords";

    public static final int TAKE_PHOTO = 1;
    public static final int CHOOSE_PICTURE = 2;

    private ImageView picture;
    private Uri imageUri;
    private String location;
    private boolean useTitle = false;
    private boolean getImage = false;
    private boolean getLocation = false;

    public TextView record_show_loc;

    public LocationClient mLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_records);

        Intent intent = getIntent();
        final String user_login = intent.getStringExtra("user_login");


        final EditText record_text = (EditText) findViewById(R.id.Rec_text);
        final EditText record_title = (EditText) findViewById(R.id.Rec_title);
        Button take_photo = (Button) findViewById(R.id.Rec_take_photo);
        Button record_choose_pic = (Button) findViewById(R.id.Rec_choose_pic);
        Button clear_pic = (Button) findViewById(R.id.Rec_clear_pic);
        Button record_create = (Button) findViewById(R.id.Rec_create);
        final Button record_location = (Button) findViewById(R.id.Rec_location);
        final Button record_clear_location = (Button) findViewById(R.id.Rec_clear_location);

        final Button record_show_map = (Button) findViewById(R.id.Rec_show_map);
        record_show_loc = (TextView) findViewById(R.id.Rec_show_location);
        picture = (ImageView) findViewById(R.id.picture);

        final CheckBox use_title = (CheckBox) findViewById(R.id.use_title);

        // checkBox of use_title
        use_title.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (use_title.isChecked()) {
                    record_title.setVisibility(View.VISIBLE);
                    useTitle = true;
                } else {
                    record_title.setVisibility(View.INVISIBLE);
                    useTitle = false;
                }
            }
        });

        // Button of get location
        record_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mLocationClient = new LocationClient(getApplicationContext());
                mLocationClient.registerLocationListener(new MyLocationListener());

                List<String> permissionList = new ArrayList<>();
                if (ContextCompat.checkSelfPermission(AddRecords.this, Manifest.
                        permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED) {
                    permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
                }
                if (ContextCompat.checkSelfPermission(AddRecords.this, Manifest.
                        permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                    permissionList.add(Manifest.permission.READ_PHONE_STATE);
                }
                if (ContextCompat.checkSelfPermission(AddRecords.this, Manifest.
                        permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {
                    permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
                }
                if (!permissionList.isEmpty()) {
                    String [] permissions = permissionList.toArray(new String[permissionList.size()]);
                    ActivityCompat.requestPermissions(AddRecords.this, permissions, 2);
                    getLocation = true;
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            TextView blank = (TextView)findViewById(R.id.blank_location);
                            //record_show_loc.setText(location);
                            record_location.setVisibility(View.GONE);
                            record_show_map.setVisibility(View.VISIBLE);
                            blank.setVisibility(View.VISIBLE);
                            record_clear_location.setVisibility(View.VISIBLE);
                        }
                    }, 1500);
                } else {
                    requestLocation();
                    getLocation = true;
                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            TextView blank = (TextView)findViewById(R.id.blank_location);
                            //record_show_loc.setText(location);
                            record_location.setVisibility(View.GONE);
                            record_show_map.setVisibility(View.VISIBLE);
                            blank.setVisibility(View.VISIBLE);
                            record_clear_location.setVisibility(View.VISIBLE);
                        }
                    }, 500);
                }
            }
        });

        // Button of clear Location
        record_clear_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!location.isEmpty()){
                    Toast.makeText(AddRecords.this, "Clear location",
                            Toast.LENGTH_SHORT).show();
                }
                location = "";
                getLocation = false;
                record_show_loc.setText(location);
            }
        });

        // Button of show Map
        record_show_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_map = new Intent(AddRecords.this, showMap.class);
                startActivity(intent_map);
            }
        });

        // Button of take a Photo
        take_photo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Create a File Object to store the photo
                File outputImage = new File(getExternalCacheDir(), "output_image.jpg");
                try {
                    if (outputImage.exists()) {
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (Build.VERSION.SDK_INT >= 24) {
                    imageUri = FileProvider.getUriForFile(AddRecords.this,
                            "com.example.project1213.fileprovider", outputImage);
                } else {
                    imageUri = Uri.fromFile(outputImage);
                }
                // 启动相机程序
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, TAKE_PHOTO);
            }
        });

        // Button of clear picture
        clear_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(getImage){
                    Toast.makeText(AddRecords.this, "Clear image",
                            Toast.LENGTH_SHORT).show();
                }
                getImage = false;
                picture.setImageBitmap(null);
            }
        });

        // Button of choose a Picture
        record_choose_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(AddRecords.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.
                        PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(AddRecords.this, new
                            String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else {
                    openAlbum();
                }
            }
        });



        // Button of Create
        record_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日  HH:mm:ss");
                Date curDate = new Date(System.currentTimeMillis());

                String date = formatter.format(curDate);
                String text = record_text.getText().toString();
                String title = record_title.getText().toString();

                if (text.isEmpty() && title.isEmpty() && !getImage && !getLocation) {
                    Toast.makeText(AddRecords.this, "Not allowed to be ALL EMPTY.",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                Record record = new Record();
                if(useTitle){
                    record.setRecordTitle(title);
                }

                record.setRecordText(text);

                record.setRecordDate(date);

                if(getLocation){
                    record.setRecordLocation(location);
                }
                if(getImage) {
                    picture.setDrawingCacheEnabled(true);
                    Bitmap bitmap = picture.getDrawingCache();
                    byte[] image = img(bitmap);
                    record.setRecordImage(image);
                    picture.setDrawingCacheEnabled(false);
                }

                boolean record_save = record.save();

                List<Account> accountList = LitePal
                        .where("userName == ?", user_login)
                        .find(Account.class);

                if (accountList.size() != 1) {
                    Toast.makeText(AddRecords.this, "Account database error",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                Account account = accountList.get(0);
                account.getRecordList().add(record);
                boolean account_upgrade = account.save();

                if (record_save && account_upgrade) {
                    Toast.makeText(AddRecords.this, "Add record successfully.",
                            Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(AddRecords.this,
                            "Add record FAILED for unknown reason.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case TAKE_PHOTO:
                if (resultCode == RESULT_OK) {
                    try {
                        // show the photo
                        Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                        picture.setImageBitmap(bitmap);
                        getImage = true;
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case CHOOSE_PICTURE:
                if (resultCode == RESULT_OK) {
                    // 判断手机系统版本号
                    if (Build.VERSION.SDK_INT >= 19) {
                        // 4.4及以上系统使用这个方法处理图片
                        handleImageOnKitKat(data);
                    } else {
                        // 4.4以下系统使用这个方法处理图片
                        handleImageBeforeKitKat(data);
                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                } else {
                    Toast.makeText(this, "You denied the permission",
                            Toast.LENGTH_SHORT).show();
                }
                break;
            case 2:
                if (grantResults.length > 0) {
                    for (int result : grantResults) {
                        if (result != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(this, "You denied the permission",
                                    Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                    requestLocation();
                } else {
                    Toast.makeText(this, "Unknown Error", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
                break;
        }
    }

    @TargetApi(19)
    private void handleImageOnKitKat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(this, uri)) {
            // 如果是document类型的Uri，则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri.getAuthority())) {
                String id = docId.split(":")[1]; // 解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri.getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // 如果是content类型的Uri，则使用普通方式处理
            imagePath = getImagePath(uri, null);
        } else if ("file".equalsIgnoreCase(uri.getScheme())) {
            // 如果是file类型的Uri，直接获取图片路径即可
            imagePath = uri.getPath();
        }
        displayImage(imagePath); // 根据图片路径显示图片
    }

    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        displayImage(imagePath);
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        // 通过Uri和selection来获取真实的图片路径
        Cursor cursor = getContentResolver().query(uri, null, selection, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void displayImage(String imagePath) {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            picture.setImageBitmap(bitmap);
            getImage = true;
        } else {
            Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();
        }
    }

    private void openAlbum() {
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("image/*");
        startActivityForResult(intent, CHOOSE_PICTURE);
    }

    private byte[] img(Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (getLocation) {
            mLocationClient.stop();
        }
    }

    private void requestLocation() {
        initLocation();
        mLocationClient.start();
    }

    private void initLocation(){
        LocationClientOption option = new LocationClientOption();
//        option.setScanSpan(50000);
        option.setIsNeedAddress(true);
        mLocationClient.setLocOption(option);
    }

    public class MyLocationListener implements BDLocationListener {
        @Override
        public void onReceiveLocation(final BDLocation loc) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    StringBuilder currentPosition = new StringBuilder();
                    //currentPosition.append("纬度：").append(location.getLatitude()).append("\n");
                    //currentPosition.append("经线：").append(location.getLongitude()).append("\n");
                    //currentPosition.append("国家：").append(location.getCountry()).append("\n");
                    currentPosition.append(loc.getProvince()).append(" ");
                    currentPosition.append(loc.getCity()).append(" ");
                    currentPosition.append(loc.getDistrict()).append(" ");
                    currentPosition.append(loc.getStreet()).append(" ");
                    /*
                    currentPosition.append("定位方式：");
                    if (location.getLocType() == BDLocation.TypeGpsLocation) {
                        currentPosition.append("GPS");
                    } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {
                        currentPosition.append("网络");
                    }
                    */
                    record_show_loc.setText(currentPosition);
                    location += currentPosition;
                }
            });
        }
        /*
        @Override
        public void onConnectHotSpotMessage(String s, int i) {
        }
        */
    }
}