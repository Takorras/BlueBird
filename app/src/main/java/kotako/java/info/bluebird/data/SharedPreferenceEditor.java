package kotako.java.info.bluebird.data;

import android.content.Context;
import android.content.SharedPreferences;
import kotako.java.info.bluebird.MainApplication;

public class SharedPreferenceEditor {
    private SharedPreferences reader;
    private SharedPreferences.Editor editor;

    public SharedPreferenceEditor() {
        getSpInstance();
        editor = reader.edit();
    }

    private SharedPreferences getSpInstance() {
        reader = MainApplication.getInstance().getApplicationContext().getSharedPreferences("AccessKeyData", Context.MODE_PRIVATE);
        return reader;
    }

    public String getString(String key) {
        return reader.getString(key, "none");
    }

    public boolean setString(String key, String data) {
        editor.putString(key, data);
        return editor.commit();
    }

    public boolean contains(String key) {
        return reader.contains(key);
    }
}
