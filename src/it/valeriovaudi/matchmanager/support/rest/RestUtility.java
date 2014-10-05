package it.valeriovaudi.matchmanager.support.rest;

import java.io.IOException;

/**
 * Created by Valerio on 27/04/2014.
 */
public interface RestUtility {
    String doRestOperation(RestOperation restOperation,String url, Object body) throws IOException;
}
