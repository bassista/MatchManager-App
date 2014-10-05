package it.valeriovaudi.matchmanager.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import it.valeriovaudi.matchmanager.factory.*;
import it.valeriovaudi.matchmanager.support.ExceptionMessageSupport;
import it.valeriovaudi.matchmanager.support.json.ComplexJSONFactory;
import it.valeriovaudi.matchmanager.support.preference.PreferenceProvider;

import java.text.SimpleDateFormat;
import java.util.logging.Logger;

/**
 * Created by Valerio on 30/04/2014.
 */
public abstract class AbstractActivity extends Activity {
    protected Logger logger = Logger.getLogger(getClass().getName());

    protected SharedPreferences sharedPreferences;
    protected SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
    protected static ExceptionMessageSupport exceptionMessageSupport = ExceptionMessageSupport.getExceptionMessageSupportInstance();

    protected RestServiceFactory restServiceFactory;
    protected ToastFactory toastFactory;
    protected TextViewFactory textViewFactory;
    protected SpinnerFactory spinnerFactory;

    protected ComplexJSONFactory complexJSONFactory = new ComplexJSONFactory();

    protected void init() {
        //    init the preference
        sharedPreferences = getSharedPreferences(PreferenceProvider.PREFERENCE_NAME,MODE_PRIVATE);
        restServiceFactory = new RestServiceFactoryImpl(sharedPreferences);

        toastFactory = new ToastFactory(getApplicationContext());
        textViewFactory = new TextViewFactory(getApplicationContext());
        spinnerFactory = new SpinnerFactory(getApplicationContext());
    }

    protected void initService() {}

    protected void initView(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();
        initService();
    }
}
