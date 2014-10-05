package it.valeriovaudi.matchmanager.controller;

import android.content.SharedPreferences;
import it.valeriovaudi.matchmanager.service.MatchService;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

/**
 * Created by Valerio on 09/05/2014.
 */
public class MatchController extends AbstractController{

    public MatchController(SharedPreferences sharedPreferences) {super(sharedPreferences);}

    public String getAllMatchReserved(String userNamePlayer, Date date){
        String result="";
        try {
            MatchService matchService = restServiceFactory.getMatchService();
            result = matchService.getAllMatchReserved(userNamePlayer,date);
        } catch (InstantiationException e) {
            exceptionMessageSupport.printExceptionErrorMessage(e,logger);
        } catch (IllegalAccessException e) {
            exceptionMessageSupport.printExceptionErrorMessage(e, logger);
        }
        return result;
    }

    public String getAllAvaiableMatch(String userNamePlayer, Date date){
        String result="";
        try {
            MatchService matchService = restServiceFactory.getMatchService();
            result = matchService.getAllAvaiableMatch(userNamePlayer,date);
        } catch (InstantiationException e) {
            exceptionMessageSupport.printExceptionErrorMessage(e,logger);
        } catch (IllegalAccessException e) {
            exceptionMessageSupport.printExceptionErrorMessage(e, logger);
        }
        return result;
    }

    public String getAvaiavleMatch(String userNamePlayer, Date date, String ora){
        String result="";
        try {
            MatchService matchService = restServiceFactory.getMatchService();
            result = matchService.getAvaiavleMatch(userNamePlayer,date,ora);
        } catch (InstantiationException e) {
            exceptionMessageSupport.printExceptionErrorMessage(e,logger);
        } catch (IllegalAccessException e) {
            exceptionMessageSupport.printExceptionErrorMessage(e, logger);
        }
        return result;
    }

    public boolean setReservationMatch(String userNamePlayer, Date date, String hour, String footBallField){
        boolean result = false;
        try {
            MatchService matchService = restServiceFactory.getMatchService();
            String reservationMatch = matchService.setReservationMatch(userNamePlayer,date,hour,footBallField);

            JSONObject jsonObject = new JSONObject(reservationMatch);
            result = jsonObject.getBoolean("result");
        } catch (InstantiationException e) {
            exceptionMessageSupport.printExceptionErrorMessage(e,logger);
        } catch (IllegalAccessException e) {
            exceptionMessageSupport.printExceptionErrorMessage(e, logger);
        } catch (JSONException e) {
            exceptionMessageSupport.printExceptionErrorMessage(e, logger);
        }
        return result;
    }

    public String[] getAllAviableFootballFields(String userNamePlayer,Date date, String hour){
        String[] result = new String[0];
        try {
            MatchService matchService = restServiceFactory.getMatchService();
            String reservationMatch = matchService.getAllAviableFootballFields(userNamePlayer, date, hour);
            JSONObject jsonObject = new JSONObject(reservationMatch);
            result = stringSupport.createDropDownList(jsonObject,"footballFields");
        } catch (InstantiationException e) {
            exceptionMessageSupport.printExceptionErrorMessage(e,logger);
        } catch (IllegalAccessException e) {
            exceptionMessageSupport.printExceptionErrorMessage(e, logger);
        } catch (JSONException e) {
            exceptionMessageSupport.printExceptionErrorMessage(e, logger);
        }
        return result;
    }

    public boolean setReservationFootballFields(String userNamePlayer,Date date, String hour, String footballfield,String teamName){
        String result = "";
        try {
            MatchService matchService = restServiceFactory.getMatchService();
            String reservationMatch = matchService.setReservationFootballFields(userNamePlayer, date, hour, footballfield,teamName);
            JSONObject jsonObject = new JSONObject(reservationMatch);
            result = jsonObject.getString("result");
        } catch (InstantiationException e) {
            exceptionMessageSupport.printExceptionErrorMessage(e,logger);
        } catch (IllegalAccessException e) {
            exceptionMessageSupport.printExceptionErrorMessage(e, logger);
        } catch (Exception e) {
            exceptionMessageSupport.printExceptionErrorMessage(e, logger);
        }
        return Boolean.parseBoolean(result);
    }

}
