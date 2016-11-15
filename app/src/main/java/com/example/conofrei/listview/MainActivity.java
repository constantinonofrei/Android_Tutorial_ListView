package com.example.conofrei.listview;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private Uri picUri;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        RowBean[] items = new RowBean[5];
        items[0]= new RowBean(R.drawable.desert, "Add users");
        items[1]= new RowBean(R.drawable.jellyfish, "play video");
        items[2]= new RowBean(R.drawable.jellyfish, "Camera Activ");
        items[3]= new RowBean(R.drawable.jellyfish, "Api camera");
        items[4]= new RowBean(R.drawable.desert, "Send notification");

        ListAdapter lA = new CustomAdapter(this, items);
        ListView lv = (ListView)findViewById(R.id.listView);
        lv.setAdapter(lA);
        Intent in = new Intent(this, AddUserActivity.class);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItem = (String)adapterView.getSelectedItem();//returens null
                RowBean index = (RowBean)adapterView.getItemAtPosition(i);


                switch (i) {
                    case 0 :
                        Intent ind = new Intent(MainActivity.this, AddUserActivity.class );
                        startActivity(ind);
                        break;
                    case 1 :
                        Intent ind1 = new Intent(MainActivity.this, VideoActivity.class);
                        startActivity(ind1);
                        break;
                    case 2 :
                        Intent ind2= new Intent(MainActivity.this, CameraActivvity.class);
                        startActivity(ind2);
                        break;
                    case 3:
                        Intent ind3 = new Intent("android.media.acion.IMAGE_CAPTURE");
                        File photo = new File(Environment.getExternalStorageDirectory(), "pic.jpg");
                        ind3.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photo));
                        picUri = Uri.fromFile(photo);
                        startActivity(ind3);
                        break;
                    case 4 :
                        Intent ind4= new Intent(MainActivity.this, NotificationActivity.class);
                        startActivity(ind4);
                        break;
                    default:
                        Toast.makeText(MainActivity.this, selectedItem + " " + index.getName() + " undefined selection", Toast.LENGTH_LONG).show();
                }


            }
        });
    }
}
