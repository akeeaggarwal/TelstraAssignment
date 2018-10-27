package assignment.telstra.com.Utility;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class AppUtil {



    public static boolean isNetworkAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(
                Context.CONNECTIVITY_SERVICE);

        assert cm != null;
        NetworkInfo wifiNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiNetwork != null && wifiNetwork.isConnected()) {
            return true;
        }

        NetworkInfo mobileNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (mobileNetwork != null && mobileNetwork.isConnected()) {
            return true;
        }

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnected();

    }

    public static void saveDataInPref(Context context,String key, String Value) {
        SharedPreferences.Editor editor= context.getSharedPreferences(AppConstant.PREF_FILENAME,Context.MODE_PRIVATE).edit();
        editor.putString(key, Value);
        editor.apply();
    }

    public static String getDataFromPref(Context context,String key) {
        SharedPreferences pref= context.getSharedPreferences(AppConstant.PREF_FILENAME,Context.MODE_PRIVATE);
        return pref.getString(key, "");
    }
}
