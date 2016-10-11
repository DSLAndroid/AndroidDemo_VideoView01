package com.dsl.androiddemo_videoview01;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

/**
 * 作者：单胜凌
 * 时间：2016.10.11
 * 功能：利用安卓自带控件VideoView实现本地视频的播放
 *
 * Android靠自学！
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private String TAG=MainActivity.class.getSimpleName();

    private VideoView videoView;
    private Button play,pause,repaly;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化控件
        initView();
        videoView.setMediaController(new android.widget.MediaController(this));
        //设置视频播放路径为工程raw下的hello.mp4文件
        videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" +R.raw.hello));
        //---------------------播放状态的监听--------------------------
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Log.i(TAG,"准备完毕、可以播放");
            }
        });
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                Log.i(TAG,"播放完毕！");
            }
        });
        videoView.setOnErrorListener(new MediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(MediaPlayer mp, int what, int extra) {
                Log.i(TAG,"播放失败！");
                return false;
            }
        });

    }

    /**
     * 初始化控件
     */
    private void initView()
    {
        videoView=(VideoView)findViewById(R.id.videoView);
        play = (Button)findViewById(R.id.button1);
        pause = (Button)findViewById(R.id.button2);
        repaly = (Button)findViewById(R.id.button3);

        play.setOnClickListener(this);
        pause.setOnClickListener(this);
        repaly.setOnClickListener(this);
    }

    /**
     * 按钮监听
     * @param v
     */
    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.button1:
                if(!videoView.isPlaying())
                {
                    videoView.start();//开始播放
                }
                break;

            case R.id.button2:
                if(videoView.isPlaying())
                {
                    videoView.pause();//暂停播放
                }
                break;

            case R.id.button3:
                if(videoView.isPlaying())
                {
                    videoView.pause();//先暂停
                    videoView.stopPlayback();//停止播放、释放资源
                    videoView.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" +R.raw.hello));//重新设置资源
                    videoView.start();//开始播放、
                }
                break;
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(videoView!=null)
            videoView.suspend();
    }
}

