package com.example.project1213;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.icu.text.UnicodeSetSpanner;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;

import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.LitePal;
import org.litepal.tablemanager.Connector;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;


public class Main2Activity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private static final String TAG = "Main2Activity";

    public static final int LOGIN_ACTIVITY = 1;
    public static final int CHOOSE_PICTURE = 2;

    private RadioGroup radioGroup;
    private RadioButton find, mine;
    private MyFragment mineFragment;
    private FindFragment findFragment;

    // 判断是否已登录，若user_login 不为空，则已登录
    public String user_login = "小红";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Traveller");
        toolbar.inflateMenu(R.menu.operation);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.add:
                        if (user_login.isEmpty()) {
                            Toast.makeText(Main2Activity.this, "Please login first",
                                    Toast.LENGTH_LONG).show();
                        } else {
                            Intent intent = new Intent(Main2Activity.this, AddRecords.class);
                            intent.putExtra("user_login", user_login);
                            startActivity(intent);
                        }
                        break;
                    case R.id.tuniu:
                        Intent intent1 = new Intent(Main2Activity.this,WebView.class);
                        intent1.putExtra("website_address", "https://www.tuniu.com");
                        intent1.putExtra("website_name","途牛旅游网");
                        startActivity(intent1);
                        break;
                    case R.id.xiecheng:
                        Intent intent2 = new Intent(Main2Activity.this,WebView.class);
                        intent2.putExtra("website_address", "https://hotels.ctrip.com");
                        intent2.putExtra("website_name","携程");
                        startActivity(intent2);
                        break;
                    case R.id.qunaer:
                        Intent intent3 = new Intent(Main2Activity.this,WebView.class);
                        intent3.putExtra("website_address", "https://www.qunar.com");
                        intent3.putExtra("website_name","去哪儿网");
                        startActivity(intent3);
                        break;
                    case R.id.mafengwo:
                        Intent intent5 = new Intent(Main2Activity.this,WebView.class);
                        intent5.putExtra("website_address", "https://www.mafengwo.cn");
                        intent5.putExtra("website_name","马蜂窝");
                        startActivity(intent5);
                        break;

                    case R.id.about:
                        startActivity(new Intent(Main2Activity.this, About.class));
                        break;
                }
                return true;
            }
        });

        // MineListView


        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        radioGroup.setOnCheckedChangeListener(this);

        find = (RadioButton) findViewById(R.id.find);
        mine = (RadioButton) findViewById(R.id.mine);

        find.setChecked(true);
    }

    public void hideAllFragment(FragmentTransaction transaction) {
        if (findFragment != null) {
            transaction.hide(findFragment);
        }
        if (mineFragment != null) {
            transaction.hide(mineFragment);
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideAllFragment(transaction);
        switch (checkedId) {
            case R.id.find:
                if (findFragment == null) {
                    findFragment = new FindFragment("发现");
                    transaction.add(R.id.fl, findFragment);
                    LinearLayout user_information=(LinearLayout)findViewById(R.id.user_information);
                    user_information.setVisibility(View.INVISIBLE);
                } else {
                    transaction.show(findFragment);
                    LinearLayout user_information=(LinearLayout)findViewById(R.id.user_information);
                    user_information.setVisibility(View.INVISIBLE);
                }
                break;
            case R.id.mine:
                if (mineFragment == null) {
                    mineFragment = new MyFragment("我的");
                    transaction.add(R.id.fl, mineFragment);
                    LinearLayout user_information=(LinearLayout)findViewById(R.id.user_information);
                    user_information.setVisibility(View.VISIBLE);
                    //mineFragment.checkLoginButton(user_login);

                } else {
                    LinearLayout user_information=(LinearLayout)findViewById(R.id.user_information);
                    user_information.setVisibility(View.VISIBLE);
                    //mineFragment.checkLoginButton(user_login);
                    transaction.show(mineFragment);

                }
                break;
        }
        transaction.commit();

        // Button of login
        Button button_log = (Button) findViewById(R.id.log_in/*to_loginActivity*/);
        button_log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Main2Activity.this, LoginActivity.class);
                startActivityForResult(intent, LOGIN_ACTIVITY);
            }
        });

        // Button of test database
        Button button_test = (Button) findViewById(R.id.test_database);
        button_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (user_login.isEmpty()) {
                    Toast.makeText(Main2Activity.this, "Please login first",
                            Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(Main2Activity.this, testDatabase.class);
                    intent.putExtra("test_user", user_login);
                    startActivity(intent);
                }
            }
        });

        // Button to show headshot
        Button button_usr_img = (Button) findViewById(R.id.head/*change_user_image*/);
        button_usr_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ContextCompat.checkSelfPermission(Main2Activity.this,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.
                        PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(Main2Activity.this, new
                            String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                } else {
                    openAlbum();
                }
            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode) {
            case LOGIN_ACTIVITY:
                if (resultCode == RESULT_OK) {
                    user_login = data.getStringExtra("return_username");
                    Toast.makeText(Main2Activity.this, "Welcome " + user_login,
                            Toast.LENGTH_SHORT).show();
                    if (user_login.isEmpty()) {
                        TextView obj=(TextView)findViewById(R.id.my_textView);
                        obj.setText("请登录");
                    } else {
                        TextView obj=(TextView)findViewById(R.id.my_textView);
                        obj.setText(user_login);
                    }
                    //mineFragment.checkLoginButton(user_login);
                    Button loginnn=(Button)findViewById(R.id.log_in);
                    loginnn.setVisibility(View.GONE);

                    List<Account> accountList = LitePal
                            .where("userName == ?", user_login)
                            .find(Account.class);
                    Account account = accountList.get(0);
                    byte[] urim = account.getUserImage();
                    Bitmap bitmap = BitmapFactory.decodeByteArray(urim, 0, urim.length);

                    Button head=(Button)findViewById(R.id.head);
                    head.setBackgroundDrawable(new BitmapDrawable(bitmap));
                    head.setVisibility(View.VISIBLE);
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
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openAlbum();
                } else {
                    Toast.makeText(this, "You denied the permission",
                            Toast.LENGTH_SHORT).show();
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
        saveUserImage(imagePath);
    }

    private void handleImageBeforeKitKat(Intent data) {
        Uri uri = data.getData();
        String imagePath = getImagePath(uri, null);
        saveUserImage(imagePath);
    }

    private void saveUserImage(String imagePath) {
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            byte[] userimg = img(bitmap);
            List<Account> accountList = LitePal
                    .where("userName == ?", user_login)
                    .find(Account.class,true);
            Account account = accountList.get(0);
            account.setUserImage(userimg);
            account.save();

            Button head=(Button)findViewById(R.id.head);
            head.setBackgroundDrawable(new BitmapDrawable(bitmap));

            Toast.makeText(this, "Change avatar successfully", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT).show();
        }
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
}

