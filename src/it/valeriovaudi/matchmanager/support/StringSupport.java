package it.valeriovaudi.matchmanager.support;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.logging.Logger;

/**
 * Created by Valerio on 30/07/2014.
 */
public class StringSupport {
    private Logger logger = Logger.getLogger(getClass().getName());
    protected static ExceptionMessageSupport exceptionMessageSupport = ExceptionMessageSupport.getExceptionMessageSupportInstance();

    public String[] createDropDownList(JSONObject jsonObject,String propertyName){
        String[] result = new String[0];
        try {
            result = jsonObject.getJSONArray(propertyName).join(",").split(",");
            for(int index = 0 ; index < result.length ; index++){
                result[index] = result[index].replace("\"","");
            }

        } catch (JSONException e) {
            exceptionMessageSupport.printExceptionErrorMessage(e,logger);
        }
        return result;
    }

}
