package com.example.lmsa;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class LeavePreferences {
    private static final String PREF_NAME = "LeavePrefs";
    private static final String KEY_LEAVE_LIST = "leaveList";

    public static void setLeaveList(Context context, ArrayList<Leave> leaveList) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(leaveList);
        editor.putString(KEY_LEAVE_LIST, json);
        editor.apply();
    }

    public static ArrayList<Leave> getLeaveList(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(KEY_LEAVE_LIST, null);
        Type type = new TypeToken<ArrayList<Leave>>() {}.getType();
        return gson.fromJson(json, type);
    }
}
