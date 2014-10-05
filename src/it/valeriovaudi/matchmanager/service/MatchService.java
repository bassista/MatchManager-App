package it.valeriovaudi.matchmanager.service;

import java.util.Date;

/**
 * Created by Valerio on 29/04/2014.
 */
public interface MatchService extends GenericService{

    String getAllAviableFootballFields(String userNamePlayer,Date date, String hour);

    String setReservationFootballFields(String userNamePlayer,Date date, String hour, String footballfield,String teamName);

    String getAllMatchReserved(String userNamePlayer,
                                Date date);

    String getAllAvaiableMatch(String userNamePlayer,
                               Date date);


    String getAvaiavleMatch(String userNamePlayer,
                            Date date,
                            String ora);



    String setReservationMatch(String userNamePlayer,
                               Date date,
                               String hour,
                               String footBallField);

}
