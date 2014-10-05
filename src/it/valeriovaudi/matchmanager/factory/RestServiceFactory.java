package it.valeriovaudi.matchmanager.factory;

import it.valeriovaudi.matchmanager.service.LogInService;
import it.valeriovaudi.matchmanager.service.MatchService;
import it.valeriovaudi.matchmanager.service.SupportUIService;
import it.valeriovaudi.matchmanager.service.TeamService;

/**
 * Created by Valerio on 04/05/2014.
 */
public interface RestServiceFactory {

    LogInService getLogInService() throws InstantiationException, IllegalAccessException;

    MatchService getMatchService() throws InstantiationException, IllegalAccessException;

    TeamService getTeamService() throws InstantiationException, IllegalAccessException;

    SupportUIService getSupportUIService() throws InstantiationException, IllegalAccessException;

}
