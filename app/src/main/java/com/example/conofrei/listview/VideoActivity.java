package com.example.conofrei.listview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity {

    VideoView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        view = (VideoView)findViewById(R.id.videoView);
        view.setVideoPath("https://www.thenewboston.com/forum/project_files/006_testVideo.mp4");
        view.start();
    }
}
