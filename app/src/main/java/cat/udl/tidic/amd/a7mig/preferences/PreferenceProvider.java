package cat.udl.tidic.amd.a7mig.preferences;

import android.content.Context;
import android.content.SharedPreferences;

import cat.udl.tidic.amd.a7mig.GameActivity;

public class PreferenceProvider {

    public static String SHARED_PREFERENCES = "mPreferences";

    private static SharedPreferences sPreferences;

    public static SharedPreferences providePreferences() {
        return sPreferences;
    }
    public static void init(Context context) {

        sPreferences = context.getSharedPreferences(SHARED_PREFERENCES, Context.MODE_PRIVATE);
        GameActivity.Banca(); //fem crida Requisit 1
    }
}
