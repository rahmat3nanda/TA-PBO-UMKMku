package id.owndigital.umkmku.core.tools;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.provider.Settings;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;

import id.owndigital.umkmku.core.datasource.SPData;
import okhttp3.RequestBody;

public class Helper {
    private Activity activity;
    private SPData spData;

    public Helper(Activity activity) {
        this.activity = activity;
        this.spData = SPData.getInstance(activity);
    }

    public void movePage(Class<?> nextActivity){
        activity.startActivity(new Intent(activity, nextActivity));
        activity.finish();
    }

    public void newPage(Class<?> nextActivity){
        activity.startActivity(new Intent(activity, nextActivity));
    }

    public void closeKeyboard() {
        View view = this.activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) this.activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            assert imm != null;
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public RequestBody stringToReqBody(String s) {
        return RequestBody.create(okhttp3.MultipartBody.FORM, s);
    }

    public void openSettingsOfApp() {
        Intent intent = new Intent();
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", this.activity.getPackageName(), null);
        intent.setData(uri);
        this.activity.startActivity(intent);
    }

    public String greetingText() {
        String greeting = "Selamat ";
        int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);

        if (hour > 0 && hour < 12) {
            greeting += "Pagi";
        } else if (hour >= 12 && hour < 15) {
            greeting += "Siang";
        } else if (hour >= 15 && hour < 18) {
            greeting += "Sore";
        } else {
            greeting += "Malam";
        }

        greeting += ", " + spData.getNama().substring(0, spData.getNama().indexOf(' '));
        return greeting;
    }

    public int dpToPx(int dp){
        Resources r = activity.getResources();
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                r.getDisplayMetrics()
        );
    }
}
