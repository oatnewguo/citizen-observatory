package wentaoguo.example.com.citizenobservatory;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

public class ObservationActivity extends AppCompatActivity
{
    static final int REQUEST_LOCATION = 1;
    static final int REQUEST_VIDEO = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_observation);
    }

    public void recordLocation(View view)
    {
        Intent locationIntent = new Intent(this, LocationActivity.class);
        startActivityForResult(locationIntent, REQUEST_LOCATION);
    }

    public void recordVideo(View view)
    {
        Intent videoIntent = new Intent(this, VideoActivity.class);
        startActivityForResult(videoIntent, REQUEST_VIDEO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        if (requestCode == REQUEST_LOCATION && resultCode == RESULT_OK)
        {
            String latitude = ((MyApplication) this.getApplication()).getLatitude();
            String longitude = ((MyApplication) this.getApplication()).getLongitude();
            if(latitude != null && longitude != null)
            {
                TextView location = findViewById(R.id.textLocation);
                location.setText("Location: " + latitude + ", " + longitude);
            }
        }
        else if (requestCode == REQUEST_VIDEO && resultCode == RESULT_OK)
        {
            Uri video = ((MyApplication) this.getApplication()).getVideo();
            if(video != null)
            {
                VideoView videoView = findViewById(R.id.videoView);
                videoView.setVideoURI(video);
                videoView.start();
            }
        }
    }

    public void sendResults(View view)
    {
        String latitude = ((MyApplication) this.getApplication()).getLatitude();
        String longitude = ((MyApplication) this.getApplication()).getLongitude();
        if(latitude == null || longitude == null)
        {
            Toast.makeText(getApplicationContext(), "Location data could not be found.", Toast.LENGTH_SHORT).show();
            return;
        }

        Uri video = ((MyApplication) this.getApplication()).getVideo();
        if(video == null)
        {
            Toast.makeText(getApplicationContext(), "Video could not be found.", Toast.LENGTH_SHORT).show();
            return;
        }

        String[] emailRecipients = {"wgtc2015@mymail.pomona.edu"};
        String subject = "Eclipse observations";

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("text/plain");                                                //https://en.wikipedia.org/wiki/MIME
        emailIntent.putExtra(Intent.EXTRA_EMAIL, emailRecipients);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_STREAM, video);
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Taken at " + latitude.toString() + ", " + longitude.toString());

        if (emailIntent.resolveActivity(getPackageManager()) != null)
        {
            startActivity(emailIntent);
        }
    }
}
