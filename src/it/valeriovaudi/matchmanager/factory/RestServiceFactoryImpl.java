package it.valeriovaudi.matchmanager.factory;

import android.content.SharedPreferences;
import it.valeriovaudi.matchmanager.service.*;
import it.valeriovaudi.matchmanager.support.preference.PreferenceProvider;

/**
 * Created by Valerio on 02/05/2014.
 */
public class RestServiceFactoryImpl implements RestServiceFactory{

    protected final SharedPreferences sharedPreferences;

    public RestServiceFactoryImpl(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    private GenericService getService(Class<? extends GenericService> clazz) throws IllegalAccessException, InstantiationException {
        String host = PreferenceProvider.getPreference(sharedPreferences, PreferenceProvider.SERVER_IP_ADDRESS_PREFERENCE_KEY);
        String port = PreferenceProvider.getPreference(sharedPreferences, PreferenceProvider.SERVER_PORT_ADDRESS_PREFERENCE_KEY);

        GenericService service = clazz.newInstance();

        service.setHost(host);
        service.setPort(port);

        return service;
    }

    @Override
    public LogInService getLogInService() throws InstantiationException, IllegalAccessException {
        return (LogInService) getService(LogInServiceImpl.class);
    }

    @Override
    public MatchService getMatchService() throws InstantiationException, IllegalAccessException {
        return (MatchService) getService(MatchServiceImpl.class);
    }

    @Override
    public TeamService getTeamService() throws InstantiationException, IllegalAccessException {
        return (TeamService) getService(TeamServiceImpl.class);
    }

    @Override
    public SupportUIService getSupportUIService() throws InstantiationException, IllegalAccessException {
        return (SupportUIService) getService(SupportUIServiceImpl.class);
    }
}
