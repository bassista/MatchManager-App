package it.valeriovaudi.matchmanager.service;

import it.valeriovaudi.matchmanager.support.rest.RestOperation;

import java.io.IOException;

/**
 * Created by Valerio on 28/07/2014.
 */
public class TeamServiceImpl extends AbstractService implements TeamService {
    private static final String GET_AVAIABLE_ALL_TEAM_SERVICE_URL="http://%s:%s/Match-Manager/service/rest/TeamRestService/getAllTeamName?userNamePlayer=%s";

    public TeamServiceImpl() {super();}

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

    @Override
    public String getAllTeam(String userNamePlayer) {
        return doService(GET_AVAIABLE_ALL_TEAM_SERVICE_URL, userNamePlayer).getJson().toString();
    }
}
