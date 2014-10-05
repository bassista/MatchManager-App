package it.valeriovaudi.matchmanager.support.json;

import it.valeriovaudi.matchmanager.support.ExceptionMessageSupport;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.logging.Logger;

/**
 * Created by Valerio on 02/05/2014.
 */
public class ComplexJSONFactory {
    private Logger logger = Logger.getLogger(ComplexJSONFactory.class.getName());
    private static ExceptionMessageSupport exceptionMessageSupport = ExceptionMessageSupport.getExceptionMessageSupportInstance();

    public ComplexJSON getComplexJSON(String body){

        ComplexJSON complexJSON = new ComplexJSON();
        try{
            if(body.startsWith("[") && body.endsWith("]")){
                complexJSON.setJson(new JSONArray(body));
                complexJSON.setJsonType(JSONType.ARRAY_JSON);
            }else {
                complexJSON.setJson(new JSONObject(body));
                complexJSON.setJsonType(JSONType.SINGLE_JSON);
            }
        } catch (Exception e) {
            exceptionMessageSupport.printExceptionErrorMessage(e, logger);
            return null;
        }

        return complexJSON;

    }


    public ComplexJSON createErrorJson(String boby){
        ComplexJSON complexJSON = new ComplexJSON();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("errorMessage",boby);
            complexJSON.setJsonType(JSONType.SINGLE_JSON);
            complexJSON.setJson(jsonObject);

        } catch (JSONException e) {
            exceptionMessageSupport.printExceptionErrorMessage(e, logger);
        }

        return complexJSON;
    }
}
