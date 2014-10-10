package it.valeriovaudi.matchmanager.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import it.valeriovaudi.matchmanager.R;
import it.valeriovaudi.matchmanager.controller.MatchController;
import it.valeriovaudi.matchmanager.support.preference.PreferenceProvider;
import it.valeriovaudi.matchmanager.widget.listener.JoinInMatchButtonListener;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;

public class MainActivity extends AbstractActivityWithMenu {
    /**
     * Called when the activity is first created.
     */

    private MatchController matchController;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        String format = "Date: %s, Football field: %s, Hour: %s";

        String allAvaiableMatch = matchController.getAllAvaiableMatch(PreferenceProvider.getPreference(sharedPreferences, PreferenceProvider.USER_NAME_PREFERENCE_KEY), new Date());
        String reservedMatch = matchController.getAllMatchReserved(PreferenceProvider.getPreference(sharedPreferences, PreferenceProvider.USER_NAME_PREFERENCE_KEY), new Date());

        initMatchUI("All Avaiable Match List", allAvaiableMatch, format, R.id.avaiableMatchLinearLayout, true);
        initMatchUI("Reserved Match List", reservedMatch, format, R.id.reservedMatchLinearLayout, false);
    }

    private void initMatchUI(String header, String json, String format, int layoutId, boolean prenotable) {
        String userName = PreferenceProvider.getPreference(sharedPreferences, PreferenceProvider.USER_NAME_PREFERENCE_KEY);
        TextView textView;
        Button button;
        LinearLayout linearLayout = (LinearLayout) findViewById(layoutId);
        linearLayout.addView(textViewFactory.createTextViev(header));
        linearLayout.addView(createGenericView());

        try {
            JSONObject jsonObject;
            JSONArray jsonArray = (JSONArray) complexJSONFactory.getComplexJSON(json).getJson();
            for (int index = 0; index < jsonArray.length(); ++index) {
                jsonObject = jsonArray.getJSONObject(index);

                textView = textViewFactory.createTextViev(String.format(format, jsonObject.getString("date"), jsonObject.getString("footballField"), jsonObject.getString("hour")));
                linearLayout.addView(textView);
                if (prenotable) {
                    button = new Button(this);
                    button.setText("Reserve");
                    button.setOnClickListener(new JoinInMatchButtonListener(this, sharedPreferences, userName, jsonObject.getString("hour"), jsonObject.getString("footballField"), jsonObject.getString("date")));
                    linearLayout.addView(button);
                }
            }
        } catch (JSONException e) {
            exceptionMessageSupport.printExceptionErrorMessage(e, logger);
        }
    }

    private View createGenericView() {
        View view = new View(this);
        LinearLayout.LayoutParams viewLp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 5);

        view.setLayoutParams(viewLp);
        view.setBackgroundColor(Color.rgb(51, 51, 51));

        return view;
    }

    @Override
    protected void initService() {
        super.initService();
        matchController = new MatchController(sharedPreferences);
    }

}
