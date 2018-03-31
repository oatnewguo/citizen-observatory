package wentaoguo.example.com.citizenobservatory;

import android.app.Application;
import android.net.Uri;

public class MyApplication extends Application
{
    /*latitude and longitude stored as Strings in order to avoid imprecision of floating-point data
    types*/
    private String latitude;
    private String longitude;
    private Uri video;

    public String getLatitude()
    {
        return latitude;
    }

    public void setLatitude(String lat)
    {
        this.latitude = lat;
    }

    public String getLongitude()
    {
        return longitude;
    }

    public void setLongitude(String lon)
    {
        this.longitude = lon;
    }

    public Uri getVideo()
    {
        return video;
    }

    public void setVideo(Uri vid)
    {
        this.video = vid;
    }
}
