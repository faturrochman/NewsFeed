package helper;

import android.util.Log;

/**
 * Created by Fajar on 7/23/2014.
 */
public class ImageURLValidation {

    public static final String HTTP = "http://";
    private static ImageURLValidation instance = null;
    protected ImageURLValidation() {}
    public static String validate(String url){
        if(instance == null) {
            instance = new ImageURLValidation();
        }
        url = url.replaceAll("\\\\","");
        if(!url.substring(0,6).equalsIgnoreCase(HTTP)){
            url = HTTP + url;
        }
        Log.i("URL Image", url);
        return url;
    }
}
