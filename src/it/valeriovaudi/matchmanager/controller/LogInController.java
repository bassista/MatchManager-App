package it.valeriovaudi.matchmanager.controller;

import android.content.SharedPreferences;
import android.widget.TextView;
import it.valeriovaudi.matchmanager.service.LogInService;
import it.valeriovaudi.matchmanager.support.preference.PreferenceProvider;

/**
 * Created by Valerio on 09/05/2014.
 */
public class LogInController extends AbstractController{
    public LogInController(SharedPreferences sharedPreferences) {super(sharedPreferences);}

    public boolean logInService(TextView userNameTextfield,TextView passwordTextfield){
        String logIn="";
        boolean result=false;
        String userNamePreference = userNameTextfield.getText().toString();
        String passwordPreference = passwordTextfield.getText().toString();
        try {
            LogInService logInService = restServiceFactory.getLogInService();
            logIn = logInService.logIn(userNamePreference,passwordPreference);
        } catch (InstantiationException e) {
            exceptionMessageSupport.printExceptionErrorMessage(e,logger);
        } catch (IllegalAccessException e) {
            exceptionMessageSupport.printExceptionErrorMessage(e,logger);
        }

        if(logIn.equals("true")){
            result=true;
            PreferenceProvider.setPreference(sharedPreferences,PreferenceProvider.USER_NAME_PREFERENCE_KEY,userNamePreference);
        }

        return result;
    }
}
