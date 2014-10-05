package it.valeriovaudi.matchmanager.service;

import it.valeriovaudi.matchmanager.support.rest.RestOperation;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by Valerio on 28/04/2014.
 */
public class LogInServiceImpl extends AbstractService implements LogInService {
    private static final String LOG_IN_SERVICE_URL="http://%s:%s/Match-Manager/service/rest/TeamRestService/playerIsRegister";
    public LogInServiceImpl() {super();}

    /**
     * Override this method to perform a computation on a background thread. The
     * specified parameters are the parameters passed to {@link #execute}
     * by the caller of this task.
     * <p/>
     * This method can call {@link #publishProgress} to publish updates
     * on the UI thread.
     *
     * @param params The parameters of the task.
     * @return A result, defined by the subclass of this task.
     * @see #onPreExecute()
     * @see #onPostExecute
     * @see #publishProgress
     */
    @Override
    protected String doInBackground(String... params) {
        String result = "";
        try {
            // create the post body
            JSONObject obj = new JSONObject();

            obj.put("userName",params[0]);
            obj.put("password",params[1]);

            result = restUtility.doRestOperation(RestOperation.POST, createURL(LOG_IN_SERVICE_URL,this.host,this.port), obj);
        } catch (IOException e) {
            exceptionMessageSupport.printExceptionErrorMessage(e,logger);
            result = "false";
        } catch (JSONException e) {
            exceptionMessageSupport.printExceptionErrorMessage(e,logger);
            result = "false";
        }

        return result;
    }

    @Override
    public String logIn(String username, String password) {
        String result;
        try {
            JSONObject jsonObject = (JSONObject) doService(username, password).getJson();
            result = jsonObject.getString("result");
        } catch (Exception e) {
            exceptionMessageSupport.printExceptionErrorMessage(e,logger);
            result ="false";
        }

        return result;
    }

}
