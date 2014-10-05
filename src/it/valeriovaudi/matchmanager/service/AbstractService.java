package it.valeriovaudi.matchmanager.service;

import android.os.AsyncTask;
import it.valeriovaudi.matchmanager.support.ExceptionMessageSupport;
import it.valeriovaudi.matchmanager.support.StringSupport;
import it.valeriovaudi.matchmanager.support.json.ComplexJSON;
import it.valeriovaudi.matchmanager.support.json.ComplexJSONFactory;
import it.valeriovaudi.matchmanager.support.rest.RestUtility;
import it.valeriovaudi.matchmanager.support.rest.RestUtilityImpl;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

/**
 * Created by Valerio on 30/04/2014.
 */
public abstract class AbstractService extends AsyncTask<String, String, String>{
    protected String host;
    protected String port;

    protected  Logger logger = Logger.getLogger(getClass().getName());

    protected static ExceptionMessageSupport exceptionMessageSupport = ExceptionMessageSupport.getExceptionMessageSupportInstance();

    protected static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    protected ComplexJSONFactory complexJSONFactory = new ComplexJSONFactory();

    protected RestUtility restUtility;

    protected StringSupport stringSupport = new StringSupport();

    public void setPort(String port) {
        this.port = port;
    }

    public void setHost(String host) {
        this.host = host;
    }

    protected AbstractService() { this.restUtility=new RestUtilityImpl();}

    protected ComplexJSON doService(String... input){
        ComplexJSON result=new ComplexJSON();

        if((this.host!=null && this.port!=null) &&(!this.host.trim().equals("") && !this.port.trim().equals("")))  {
            try {
                AsyncTask<String, String, String> execute = execute(input);
                result = complexJSONFactory.getComplexJSON(execute.get());
                cancel(true);
            } catch (InterruptedException e) {
                result = complexJSONFactory.createErrorJson(exceptionMessageSupport.printExceptionErrorMessage(e, logger));
            } catch (ExecutionException e) {
                result = complexJSONFactory.createErrorJson(exceptionMessageSupport.printExceptionErrorMessage(e, logger));
            } catch (Exception e){
                result = complexJSONFactory.createErrorJson(exceptionMessageSupport.printExceptionErrorMessage(e, logger));
            }
        }
        return result;
    }

    protected String createURL(String urlFormat,String host,String port,String... paramiters) throws UnsupportedEncodingException {
        String url;
        String[] formatParamiters = new String[paramiters.length +2];

        formatParamiters[0] = host;
        formatParamiters[1] = port;

        for (int i = 2 ; i < formatParamiters.length ; i++){
            formatParamiters[i]= URLEncoder.encode(paramiters[i-2],"ISO-8859-1");
        }
        url = String.format(urlFormat,formatParamiters);
        return url;
    }
}
