package it.valeriovaudi.matchmanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import it.valeriovaudi.matchmanager.R;
import it.valeriovaudi.matchmanager.controller.MatchController;
import it.valeriovaudi.matchmanager.service.SupportUIService;
import it.valeriovaudi.matchmanager.support.SupportedOperation;
import it.valeriovaudi.matchmanager.support.preference.PreferenceProvider;

import java.util.Date;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Created by Valerio on 30/04/2014.
 */
public class ShearchMatchActivity extends AbstractActivityWithMenu {
    private Logger logger = Logger.getLogger(getClass().getName());

    private DatePicker datePicker;
    private Spinner hoursSpinner;
    private Button submit;

    private String hour;
    private Date date;

    private String userName;

    private Map<String, Object> uiValue;

    private MatchController matchController;
    private SupportUIService supportUIService;
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shearchmatch);

        initView();
    }

    protected void init(){
        super.init();
        userName = PreferenceProvider.getPreference(sharedPreferences,PreferenceProvider.USER_NAME_PREFERENCE_KEY);
    }

    @Override
    protected void initView(){
        uiValue =  supportUIService.getAvaiableData();
        datePicker = (DatePicker) findViewById(R.id.datePicker);
        hoursSpinner = (Spinner) findViewById(R.id.hourSpinner);
        submit = (Button) findViewById(R.id.submitShearchAvaiableMatch);

        addDropDownListContent(hoursSpinner,"hours");
        hour = hoursSpinner.getAdapter().getItem(0).toString();

        hoursSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                hour = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date = new Date(datePicker.getCalendarView().getDate());
                Bundle extras = getIntent().getExtras();

                SupportedOperation supportedOperation = (SupportedOperation) extras.get("operation");
                if(supportedOperation==SupportedOperation.JOIN_IN_MATH){
                    String json = matchController.getAvaiavleMatch(userName, date, hour);
                    Intent intent = new Intent(ShearchMatchActivity.this,SelectMatchActivity.class);

                    Bundle bundle = new Bundle();
                    bundle.putString("content",json);
                    intent.putExtras(bundle);

                    startActivity(intent);
                } else {
                    Intent intent = new Intent(ShearchMatchActivity.this,CreateNewMatchActivity.class);

                    Bundle bundle = new Bundle();
                    bundle.putString("hour",hour);
                    bundle.putSerializable("date", date);
                    intent.putExtras(bundle);

                    startActivity(intent);
                }

            }
        });
    }

    protected void initService() {
        try{
            matchController = new MatchController(sharedPreferences);
            supportUIService = restServiceFactory.getSupportUIService();
        } catch (IllegalAccessException e) {
            exceptionMessageSupport.printExceptionErrorMessage(e,logger);
        } catch (InstantiationException e) {
            exceptionMessageSupport.printExceptionErrorMessage(e,logger);
        }
    }

    private void addDropDownListContent(Spinner spinner,String doprDownListValuesKey){
        spinnerFactory.addDropDownListContent(spinner, (String[]) uiValue.get(doprDownListValuesKey));
    }
}
