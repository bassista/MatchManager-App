package it.valeriovaudi.matchmanager.activity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import it.valeriovaudi.matchmanager.R;
import it.valeriovaudi.matchmanager.support.preference.PreferenceProvider;
import it.valeriovaudi.matchmanager.widget.listener.JoinInMatchButtonListener;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Valerio on 02/05/2014.
 */
public class SelectMatchActivity extends AbstractActivityWithMenu {
    private String userName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selectmatch);

        userName = PreferenceProvider.getPreference(sharedPreferences,PreferenceProvider.USER_NAME_PREFERENCE_KEY);
        Bundle extras = getIntent().getExtras();
        JSONArray jsonArray = (JSONArray) complexJSONFactory.getComplexJSON(extras.getString("content")).getJson();

        for(int i = 0 ; i < jsonArray.length() ; i++){
            try {
                createMatchView((JSONObject) jsonArray.get(i));
            } catch (JSONException e) {
                exceptionMessageSupport.printExceptionErrorMessage(e,logger);
            }
        }
    }

    private void createMatchView(JSONObject matchJson){
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.selectmatchlinearlayout);
        try {
            linearLayout.addView(textViewFactory.createTextViev("Date: " + matchJson.getString("data")));
            linearLayout.addView(textViewFactory.createTextViev("Football Field: " + matchJson.getString("campo")));
            linearLayout.addView(textViewFactory.createTextViev("Hour: " + matchJson.getString("ora")));

            createTeamView(matchJson.getJSONObject("squadra1"),linearLayout);
            createTeamView(matchJson.getJSONObject("squadra2"),linearLayout);

            Button joinInMatch = new Button(this);
            joinInMatch.setText("Join In Match");
            joinInMatch.setOnClickListener(new JoinInMatchButtonListener(this,sharedPreferences,userName,matchJson.getString("ora"),matchJson.getString("campo"),matchJson.getString("data")));

            linearLayout.addView(joinInMatch);

        } catch (JSONException e) {
            exceptionMessageSupport.printExceptionErrorMessage(e,logger);
        }
    }

    private void createTeamView(JSONObject teamJosn,LinearLayout linearLayout) throws JSONException {
        linearLayout.addView(textViewFactory.createTextViev("Team Name: " + teamJosn.get("teamName")));
        String giocatoreFormat = "First Name: %s Last Name: %s";

        JSONArray giocatori = teamJosn.getJSONArray("giocatori");
        JSONObject giocatore;
        for (int i = 0 ; i < giocatori.length() ; i++){
            giocatore = giocatori.getJSONObject(i);
            linearLayout.addView(textViewFactory.createTextViev(String.format(giocatoreFormat,giocatore.getString("nome"),giocatore.getString("cognome"))));
        }
    }
}