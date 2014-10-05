package it.valeriovaudi.matchmanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import it.valeriovaudi.matchmanager.R;
import it.valeriovaudi.matchmanager.support.preference.PreferenceProvider;
/**
 * Created by Valerio on 27/04/2014.
 */
public class PreferenceActivity extends AbstractActivity {
    private Button saveButton;

    private EditText serverIpTextfield;
    private EditText serverPortTextfield;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.preference);
        initView();
    }

    @Override
    protected void initView(){
        super.init();
//    init the components
        saveButton = (Button) findViewById(R.id.preference_save);

        serverIpTextfield = (EditText) findViewById(R.id.server_ip_textfield);
        serverPortTextfield = (EditText) findViewById(R.id.server_port_textfield);

        serverIpTextfield.setText(PreferenceProvider.getPreference(sharedPreferences, PreferenceProvider.SERVER_IP_ADDRESS_PREFERENCE_KEY));
        serverPortTextfield.setText(PreferenceProvider.getPreference(sharedPreferences, PreferenceProvider.SERVER_PORT_ADDRESS_PREFERENCE_KEY));

//    set the listener
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            PreferenceProvider.setPreference(sharedPreferences, PreferenceProvider.SERVER_IP_ADDRESS_PREFERENCE_KEY, serverIpTextfield.getText().toString());
            PreferenceProvider.setPreference(sharedPreferences, PreferenceProvider.SERVER_PORT_ADDRESS_PREFERENCE_KEY, serverPortTextfield.getText().toString());

            Intent intent = new Intent(PreferenceActivity.this, LogInActivity.class);

            startActivity(intent);
            }
        });
    }

}