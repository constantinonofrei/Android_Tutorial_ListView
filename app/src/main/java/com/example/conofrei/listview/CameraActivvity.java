package com.example.conofrei.listview;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.provider.MediaStore;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CameraActivvity extends AppCompatActivity {

    private static final int CAMERA_REQUEST = 1888;
    private List<BitmapDrawable> photos = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_activvity);
        Button bt = (Button)findViewById(R.id.btCamera);
        BitmapDrawable drawable = (BitmapDrawable)ContextCompat.getDrawable(getApplicationContext(),R.drawable.desert);
        photos.add(drawable);
        ImageView iv = (ImageView)findViewById(R.id.cameraImageView);
        iv.setImageBitmap(photos.get(photos.size()-1).getBitmap());
        if(!hasCamera()) {
            Toast.makeText(this,"You don;t have a camera", Toast.LENGTH_LONG);
            bt.setEnabled(false);
        }
    }

    public void btCamera(View view) {
        Log.i("CTIN", "btClick");
        Intent int1 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(int1, CAMERA_REQUEST);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.i("CTIN", "onAcivvity Result");
        Toast.makeText(this, "On acivitty resultt : req code" + requestCode + " result " + resultCode + " data:" + data, Toast.LENGTH_SHORT);
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Log.i("CTIN", "requestt ok");

            photos.add(new BitmapDrawable(getResources(),(Bitmap)data.getExtras().get("data")));
            ImageView iv = (ImageView)findViewById(R.id.cameraImageView);
            iv.setImageBitmap(photos.get(photos.size()-1).getBitmap());
        }


      //  super.onActivityResult(requestCode, resultCode, data);
    }

    public void btCameraInvert(View view) {
        ImageView iv = (ImageView)findViewById(R.id.cameraImageView);
        Bitmap photo = invertImage(photos.get(photos.size()-1).getBitmap());
        iv.setImageBitmap(photo);
    }

    public void btCameraOverlay(View view) {
        if(photos.size() < 2) {
            Toast.makeText(this, "You should hve at least 2 photos.", Toast.LENGTH_LONG );
        } else {
            ImageView iv = (ImageView)findViewById(R.id.cameraImageView);

            Drawable[] dArray = new Drawable[2];
            dArray[0] = photos.get(photos.size()-2);
            dArray[1] = photos.get(photos.size()-1);
            LayerDrawable ld = new LayerDrawable(dArray);
            iv.setImageDrawable(ld);
        }

    }

    public void btCameraSave(View view) {
        ImageView iv = (ImageView) findViewById(R.id.cameraImageView);
        Bitmap photo = ((BitmapDrawable)iv.getDrawable()).getBitmap();
        MediaStore.Images.Media.insertImage(getContentResolver(), photo, "title", "descript");
    }


    private Bitmap invertImage(Bitmap originalphoto) {
        Bitmap finalImage = Bitmap.createBitmap(originalphoto.getWidth(), originalphoto.getHeight(), originalphoto.getConfig());
        int A, R, G, B;
        int pixelColor;
        for(int i = 0; i < originalphoto.getHeight(); i++) {
            for(int j = 0; j < originalphoto.getWidth(); j++) {
                pixelColor = originalphoto.getPixel(i, j);
                A = Color.alpha(pixelColor);
                R = 255 - Color.red(pixelColor);
                G = 255 - Color.green(pixelColor);
                B = 255 - Color.blue(pixelColor);
                finalImage.setPixel(i, j, Color.argb(A, R, G, B));
            }
        }

        return finalImage;
    }

    private boolean hasCamera() {
        return getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY);
    }
}
