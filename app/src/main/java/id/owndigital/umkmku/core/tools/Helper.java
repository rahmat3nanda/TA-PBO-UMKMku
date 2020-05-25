package id.owndigital.umkmku.core.tools;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import okhttp3.RequestBody;

public class Helper {
    private Activity activity;

    public Helper(Activity activity) {
        this.activity = activity;
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
}
