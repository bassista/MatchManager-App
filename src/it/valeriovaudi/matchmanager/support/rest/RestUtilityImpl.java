package it.valeriovaudi.matchmanager.support.rest;

import it.valeriovaudi.matchmanager.support.ExceptionMessageSupport;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RestUtilityImpl implements RestUtility{

    private static Logger logger = Logger.getLogger(RestUtilityImpl.class.getName());

    protected static ExceptionMessageSupport exceptionMessageSupport = ExceptionMessageSupport.getExceptionMessageSupportInstance();

    @Override
    public String doRestOperation(RestOperation restOperation,String url, Object body) throws IOException{
        String contentCharSet = null;

        try{
            DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
            HttpUriRequest httpOperation = null;

            if(restOperation == RestOperation.GET){
                httpOperation = new HttpGet(url);

            } else if (restOperation == RestOperation.POST){
                httpOperation = new HttpPost(url);
                ((HttpPost) httpOperation).setEntity(new StringEntity(body.toString()));
            }
            httpOperation.addHeader("content-type", "application/json");
            HttpResponse response = defaultHttpClient.execute(httpOperation);
            contentCharSet = EntityUtils.toString(response.getEntity());
        }catch (Exception e){
            exceptionMessageSupport.printExceptionErrorMessage(e,logger);
        }

        return contentCharSet;
    }

    private String logResponse(HttpEntity httpEntity) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        InputStream content = httpEntity.getContent();
        if(content !=null) {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(content));

            String line;
            line = bufferedReader.readLine();

            while (line!=null){
                stringBuilder.append(line);
                line = bufferedReader.readLine();
            }
        } else {
            logger.log(Level.INFO,"responseBodyAsStream is null");
        }
        return stringBuilder.toString();
    }

}
