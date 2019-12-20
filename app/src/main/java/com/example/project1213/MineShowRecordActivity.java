package com.example.project1213;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.litepal.LitePal;

public class MineShowRecordActivity extends AppCompatActivity {

    private static final String TAG = "MineShowRecordActivity";

    private Record record;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_record);

        Toolbar toolbar = (Toolbar) findViewById(R.id.show_rec_toolbar);
        toolbar.setTitle("Traveller");
        toolbar.inflateMenu(R.menu.process_record);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.update_record:
                        Intent intent = new Intent(MineShowRecordActivity.this, AddRecords.class);
                        intent.putExtra("record_id", record.getId());
                        startActivity(intent);
                        finish();
                        break;

                    case R.id.delete_record:
                        AlertDialog.Builder dialog = new AlertDialog.Builder (MineShowRecordActivity.this);
                        dialog.setTitle("Warning...");
                        dialog.setMessage("Are you sure you want to DELETE this record ?");
                        dialog.setCancelable(false);
                        dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                LitePal.delete(Record.class, record.getId());
                                Toast.makeText(MineShowRecordActivity.this, "Delete successfully",
                                        Toast.LENGTH_SHORT).show();
                                finish();
                            }
                        });
                        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(MineShowRecordActivity.this, "Cancel",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                        dialog.show();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });

        Intent intent = getIntent();
        record = (Record) intent.getSerializableExtra("click_rec");

        TextView show_record_date = (TextView) findViewById(R.id.show_record_date);
        TextView show_record_loc = (TextView) findViewById(R.id.show_record_loc);
        TextView show_record_title = (TextView) findViewById(R.id.show_record_title);
        TextView show_record_text = (TextView) findViewById(R.id.show_record_text);
        ImageView show_record_image = (ImageView) findViewById(R.id.show_record_image);

        String date = record.getRecordDate();
        if(date == null) {
            date = "";
        }
        show_record_date.setText(date);
        show_record_loc.setText(record.getRecordLocation());
        show_record_title.setText(record.getRecordTitle());
        show_record_text.setText(record.getRecordText());
        show_record_text.setMovementMethod(ScrollingMovementMethod.getInstance());

        //show_record_image
        byte[] im = record.getRecordImage();
        if (im != null && im.length != 0) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(im, 0, im.length);
            show_record_image.setImageBitmap(bitmap);
        }
        else{
            show_record_image.setVisibility(View.GONE);
        }

    }
}
