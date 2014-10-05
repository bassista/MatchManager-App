package it.valeriovaudi.matchmanager.support;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Valerio on 09/05/2014.
 */
public class ExceptionMessageSupport {
    private static ExceptionMessageSupport exceptionMessageSupport = new ExceptionMessageSupport();

    public static ExceptionMessageSupport getExceptionMessageSupportInstance(){
        return exceptionMessageSupport;
    }

    private ExceptionMessageSupport(){}

    public static String printExceptionErrorMessage(Exception e,Logger logger){
        String logMessage =  e.getCause() + ": " + e.getMessage();
        logger.log(Level.INFO,logMessage);

        return logMessage;
    }

}
