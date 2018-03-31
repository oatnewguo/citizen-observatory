package wentaoguo.example.com.citizenobservatory;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity
{
    static final int REQUEST_VIDEO_CAPTURE = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
    }

    public void recordVideo(View view)
    {
        Intent videoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        if (videoIntent.resolveActivity(getPackageManager()) != null)
        {
            startActivityForResult(videoIntent, REQUEST_VIDEO_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK)
        {
            Uri video = intent.getData();
            ((MyApplication) this.getApplication()).setVideo(video);
            VideoView videoView = findViewById(R.id.videoView);
            videoView.setVideoURI(video);
            videoView.start();
        }
    }

    public void submit(View view)
    {
        Uri video = ((MyApplication) this.getApplication()).getVideo();
        if(video == null)
        {
            Toast.makeText(getApplicationContext(), "Video could not be found.", Toast.LENGTH_SHORT).show();
            return;
        }

        ((MyApplication) this.getApplication()).setVideo(video);

        setResult(RESULT_OK);
        finish();
    }
}
