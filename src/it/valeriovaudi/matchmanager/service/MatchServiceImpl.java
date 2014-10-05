package it.valeriovaudi.matchmanager.service;

import it.valeriovaudi.matchmanager.support.rest.RestOperation;

import java.io.IOException;
import java.util.Date;

/**
 * Created by Valerio on 29/04/2014.
 */
public class MatchServiceImpl extends AbstractService implements MatchService {
    private static final String GET_AVAIABLE_ALL_MATCH_SERVICE_URL="http://%s:%s/Match-Manager/service/rest/MatchRestService/getAllAvaiableMatch?userNamePlayer=%s&data=%s";
    private static final String GET_RESERVED_ALL_MATCH_SERVICE_URL="http://%s:%s/Match-Manager/service/rest/MatchRestService/getAllMatchRserved?userNamePlayer=%s&data=%s";

    private static final String GET_AVAIABLE_MATCH_SERVICE_URL="http://%s:%s/Match-Manager/service/rest/MatchRestService/getAvaiableMatch?userNamePlayer=%s&data=%s&ora=%s";
    private static final String SET_RESERVATION_MATCH_SERVICE_URL="http://%s:%s/Match-Manager/service/rest/MatchRestService/setReservationMatch?userNamePlayer=%s&data=%s&ora=%s&campo=%s";

    private static final String GET_AVAIABLE_ALL_FOOTBALL_FIELD_SERVICE_URL="http://%s:%s/Match-Manager/service/rest/MatchRestService/getAllAviableFootballFields?userNamePlayer=%s&data=%s&ora=%s";
    private static final String SET_RESERVATION_FOOTBALL_FIELD_SERVICE_URL="http://%s:%s/Match-Manager/service/rest/MatchRestService/setReservationFootballFields?userNamePlayer=%s&date=%s&hour=%s&footballfield=%s&teamName=%s";

    public MatchServiceImpl() {super();}

    @Override
    public String getAllAviableFootballFields(String userNamePlayer, Date date, String hour) {
        return doService(GET_AVAIABLE_ALL_FOOTBALL_FIELD_SERVICE_URL, userNamePlayer, simpleDateFormat.format(date),hour).getJson().toString();
    }

    @Override
    public String setReservationFootballFields(String userNamePlayer, Date date, String hour, String footballfield, String teamName) {
        return doService(SET_RESERVATION_FOOTBALL_FIELD_SERVICE_URL, userNamePlayer, simpleDateFormat.format(date),hour,footballfield,teamName).getJson().toString();
    }

    @Override
    public String getAllMatchReserved(String userNamePlayer, Date date) {
        return doService(GET_RESERVED_ALL_MATCH_SERVICE_URL, userNamePlayer, simpleDateFormat.format(date)).getJson().toString();
    }

    @Override
    public String getAllAvaiableMatch(String userNamePlayer, Date date) {
        return doService(GET_AVAIABLE_ALL_MATCH_SERVICE_URL, userNamePlayer, simpleDateFormat.format(date)).getJson().toString();
    }

    @Override
    public String getAvaiavleMatch(String userNamePlayer, Date date, String ora) {
        return doService(GET_AVAIABLE_MATCH_SERVICE_URL, userNamePlayer, simpleDateFormat.format(date), ora).getJson().toString();
    }

    @Override
    public String setReservationMatch(String userNamePlayer, Date date, String hour, String footBallField) {
        return doService(SET_RESERVATION_MATCH_SERVICE_URL,userNamePlayer,simpleDateFormat.format(date),hour,footBallField).getJson().toString();
    }

    @Override
    protected String doInBackground(String... params) {
        String result;
        try {
            // extract teh paramiters, actualy separate the url format by the actual paramiters
            String[] parameters = new String[params.length-1];
            System.arraycopy(params,1,parameters,0,params.length-1);

            result = restUtility.doRestOperation(RestOperation.GET, createURL(params[0],this.host,this.port,parameters), new Object());
        } catch (IOException e) {
            exceptionMessageSupport.printExceptionErrorMessage(e,logger);
            result = "false";
        }
        return result;
    }
}
