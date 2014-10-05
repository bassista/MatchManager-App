package it.valeriovaudi.matchmanager.service;

import java.util.Map;

/**
 * Created by Valerio on 01/05/2014.
 */
public interface SupportUIService extends GenericService{
//    http://localhost:8080/service/rest/CommonDataRestService/getAvaiableData
    Map<String,Object> getAvaiableData();
}
