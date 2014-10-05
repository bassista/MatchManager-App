package it.valeriovaudi.matchmanager.support.preference;

import android.content.SharedPreferences;

/**
 * Created by Valerio on 27/04/2014.
 */
public class PreferenceProvider {
    public static final String PREFERENCE_NAME = "matchManagerSharedPreferences";

    public static final String USER_NAME_PREFERENCE_KEY = "userName";
    public static final String PASSWORD_PREFERENCE_KEY = "password";
    public static final String SERVER_IP_ADDRESS_PREFERENCE_KEY = "serverIpAddress";
    public static final String SERVER_PORT_ADDRESS_PREFERENCE_KEY = "serverPort";


    public static void setPreference(SharedPreferences sharedPreferences,String preferenceKey,String preferenceValue){
        String stringPreference = sharedPreferences.getString(preferenceKey, null);
        if(stringPreference != null){
            if(!stringPreference.equals(preferenceValue)){
                setStringPreference(sharedPreferences,preferenceKey,preferenceValue);
            }
        } else {
            setStringPreference(sharedPreferences,preferenceKey,preferenceValue);
        }
    }

    public static String getPreference(SharedPreferences sharedPreferences,String preferenceKey){
        String stringPreference = sharedPreferences.getString(preferenceKey, null);
        return stringPreference;
    }

    private static void setStringPreference(SharedPreferences sharedPreferences,String preferenceKey,String preferenceValue){
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.putString(preferenceKey,preferenceValue);
        edit.commit();
    }
}
