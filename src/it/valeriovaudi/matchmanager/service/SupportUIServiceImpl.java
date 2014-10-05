package it.valeriovaudi.matchmanager.service;

import it.valeriovaudi.matchmanager.support.rest.RestOperation;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Valerio on 01/05/2014.
 * {
 *  "campi":["A","B","C","D"],
 *  "ore":["17:00","17:30","18:00","18:30","19:00","19:30","20:00","20:30","21:00","21:30","22:00","22:30"]
 * }
 */
public class SupportUIServiceImpl extends AbstractService implements SupportUIService {
    private static final String GET_UI_DATA="http://%s:%s/Match-Manager/service/rest/CommonDataRestService/getAvaiableData";

    public SupportUIServiceImpl() {super();}

    @Override
    protected String doInBackground(String... params) {
        String result;
        try {
            result = restUtility.doRestOperation(RestOperation.GET, createURL(GET_UI_DATA, this.host, this.port), new Object());
        } catch (IOException e) {
            exceptionMessageSupport.printExceptionErrorMessage(e,logger);
            result = "";
        }
        return result;
    }

    @Override
    public Map<String, Object> getAvaiableData() {
        Map<String,Object> result = new HashMap<String, Object>();
        JSONObject jsonObject = (JSONObject) doService().getJson();

        result.put("footballFields",stringSupport.createDropDownList(jsonObject,"footballFields"));
        result.put("hours",stringSupport.createDropDownList(jsonObject,"hours"));

        return result;
    }
}
