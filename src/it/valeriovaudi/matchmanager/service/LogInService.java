package it.valeriovaudi.matchmanager.service;

/**
 * Created by Valerio on 28/04/2014.
 */
public interface LogInService extends GenericService{
    //todo cambiare il tipo di risultato in un dto con esito e descrizione per eventuali errori
    String logIn(String username,String password);
}
