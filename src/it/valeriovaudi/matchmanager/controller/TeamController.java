package it.valeriovaudi.matchmanager.controller;

import android.content.SharedPreferences;
import it.valeriovaudi.matchmanager.service.TeamService;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Valerio on 28/07/2014.
 */
public class TeamController extends AbstractController{
    public TeamController(SharedPreferences sharedPreferences) {super(sharedPreferences);}

    public String[] getAllTeam(String userNamePlayer){
        String[] result= new String[0];
        try {
            TeamService teamService = restServiceFactory.getTeamService();
            String allTeam = teamService.getAllTeam(userNamePlayer);
            JSONObject jsonObject = new JSONObject(allTeam);
            result = stringSupport.createDropDownList(jsonObject,"teams");
        } catch (InstantiationException e) {
            exceptionMessageSupport.printExceptionErrorMessage(e,logger);
        } catch (IllegalAccessException e) {
            exceptionMessageSupport.printExceptionErrorMessage(e, logger);
        } catch (JSONException e) {
            exceptionMessageSupport.printExceptionErrorMessage(e, logger);
        }
        return result;
    }
}