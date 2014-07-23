package com.project.kws.newsfeed.factory;
import android.app.Activity;
import android.graphics.Typeface;
/**
 * Created by Fajar on 7/23/2014.
 */
public class Font {
    public static final String ARVO_REG = "fonts/Arvo-Regular.ttf";
    public static final String ROBOTOSLAB_BOLD = "fonts/RobotoSlab-Bold.ttf";
    public static final String ROBOTOSLAB_LIGHT = "fonts/RobotoSlab-Light.ttf";
    public static final String ROBOTOSLAB_REG = "fonts/RobotoSlab-Regular.ttf";
    public static final String ROBOTOSLAB_THIN = "fonts/RobotoSlab-Thin.ttf";

    private static Font instance = null;
    protected Font() {}
    public static Typeface getFont(Activity activity, String fontType) {
        if(instance == null) {
            instance = new Font();
        }
        return instance.generateFont(activity, fontType);
    }
    private Typeface generateFont(Activity activity, String fontType){
        Typeface tf = Typeface.createFromAsset(activity.getAssets(), fontType);
        return tf;
    }
}
