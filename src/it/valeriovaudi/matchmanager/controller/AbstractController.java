package it.valeriovaudi.matchmanager.controller;

import android.content.SharedPreferences;
import it.valeriovaudi.matchmanager.factory.RestServiceFactory;
import it.valeriovaudi.matchmanager.factory.RestServiceFactoryImpl;
import it.valeriovaudi.matchmanager.support.ExceptionMessageSupport;
import it.valeriovaudi.matchmanager.support.StringSupport;
import it.valeriovaudi.matchmanager.support.json.ComplexJSONFactory;

import java.text.SimpleDateFormat;
import java.util.logging.Logger;

/**
 * Created by Valerio on 09/05/2014.
 */
public abstract class AbstractController {
    protected Logger logger = Logger.getLogger(getClass().getName());
    protected SharedPreferences sharedPreferences;
    protected static ExceptionMessageSupport exceptionMessageSupport = ExceptionMessageSupport.getExceptionMessageSupportInstance();
    protected RestServiceFactory restServiceFactory;
    protected SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    protected ComplexJSONFactory complexJSONFactory = new ComplexJSONFactory();
    protected StringSupport stringSupport = new StringSupport();

    protected AbstractController(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
        this.restServiceFactory=new RestServiceFactoryImpl(sharedPreferences);
    }
}
