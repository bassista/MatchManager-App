package it.valeriovaudi.matchmanager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import it.valeriovaudi.matchmanager.R;
import it.valeriovaudi.matchmanager.controller.LogInController;

/**
 * Created by Valerio on 27/04/2014.
 */
public class LogInActivity extends AbstractActivity {

    private Button preferenceButton;
    private Button logInButton;

    private EditText userNameTextfield;
    private EditText passwordTextfield;

    private LogInController logInController;


    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        initView();
    }


    @Override
    protected void initService(){
        super.initService();
        logInController = new LogInController(sharedPreferences);
    }

    @Override
    protected void initView() {
        preferenceButton = (Button) findViewById(R.id.log_in_form_set_preference);
        logInButton = (Button) findViewById(R.id.submit_log_in);

        userNameTextfield = (EditText) findViewById(R.id.username_textfield);
        passwordTextfield = (EditText) findViewById(R.id.password_textfield);

        preferenceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LogInActivity.this, PreferenceActivity.class);
                startActivity(intent);
            }
        });

        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!logInController.logInService(userNameTextfield, passwordTextfield)){
                    toastFactory.getToast("Log-In Error!!! please insert correct credentials");
                } else {
                    Intent intent = new Intent(LogInActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}

