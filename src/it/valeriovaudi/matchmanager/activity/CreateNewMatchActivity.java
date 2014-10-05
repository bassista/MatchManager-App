package it.valeriovaudi.matchmanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import it.valeriovaudi.matchmanager.R;
import it.valeriovaudi.matchmanager.controller.MatchController;
import it.valeriovaudi.matchmanager.controller.TeamController;
import it.valeriovaudi.matchmanager.support.preference.PreferenceProvider;

import java.util.Date;

/**
 * Created by Valerio on 21/07/2014.
 */
public class CreateNewMatchActivity extends AbstractActivityWithMenu {
    private TeamController teamController;
    private MatchController matchController;

    private String userName;

    private String teamName;
    private String footballField;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createnewmatch);

        initView();
    }

    @Override
    protected void initService(){
        super.initService();
        teamController = new TeamController(sharedPreferences);
        matchController = new MatchController(sharedPreferences);
    }

    @Override
    protected void initView(){
        userName = PreferenceProvider.getPreference(sharedPreferences, PreferenceProvider.USER_NAME_PREFERENCE_KEY);

        Bundle extras = getIntent().getExtras();
        final Date date = (Date) extras.getSerializable("date");
        final String hour = extras.getString("hour");

        TextView textView = (TextView) findViewById(R.id.createnewmatch_hour);
        textView.setText(hour);

        Spinner footballfieldsSpinner = (Spinner) findViewById(R.id.createnewmatch_footballfields);
        String[] allAviableFootballFields = matchController.getAllAviableFootballFields(userName, date, hour);
        spinnerFactory.addDropDownListContent(footballfieldsSpinner, allAviableFootballFields);
        footballField = footballfieldsSpinner.getAdapter().getItem(0).toString();
        footballfieldsSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                footballField = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Spinner teamnameSpinner = (Spinner) findViewById(R.id.createnewmatch_teamname);
        String[] allTeam = teamController.getAllTeam(userName);
        spinnerFactory.addDropDownListContent(teamnameSpinner,allTeam);
        teamName = teamnameSpinner.getAdapter().getItem(0).toString();
        teamnameSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                teamName = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        Button submitButton = (Button) findViewById(R.id.createnewmatch_submit);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean result = matchController.setReservationFootballFields(userName, date, hour, footballField, teamName);
                if(result){
                    toastFactory.getToast("Your booking has been successful");

                    Intent intent = new Intent(CreateNewMatchActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    toastFactory.getToast("Your booking hasn't been successful");
                }
            }
        });
    }
}
