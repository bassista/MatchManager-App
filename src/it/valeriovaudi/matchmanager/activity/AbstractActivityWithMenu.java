package it.valeriovaudi.matchmanager.activity;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import it.valeriovaudi.matchmanager.R;
import it.valeriovaudi.matchmanager.support.SupportedOperation;
import it.valeriovaudi.matchmanager.support.preference.PreferenceProvider;

/**
 * Created by Valerio on 31/05/2014.
 */
public abstract class AbstractActivityWithMenu extends AbstractActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mainmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent;
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.getAvaiavleMatchMenuItem:
                intent = new Intent(this, ShearchMatchActivity.class);
                intent.putExtra("operation", SupportedOperation.JOIN_IN_MATH);
                startActivity(intent);
                return true;
            case R.id.createNewMatch:
                intent = new Intent(this, ShearchMatchActivity.class);
                intent.putExtra("operation", SupportedOperation.CREATE_MATCH);
                startActivity(intent);
                return true;

            case R.id.logOutMenuItem:
                PreferenceProvider.setPreference(sharedPreferences,PreferenceProvider.USER_NAME_PREFERENCE_KEY,null);
                PreferenceProvider.setPreference(sharedPreferences, PreferenceProvider.PASSWORD_PREFERENCE_KEY,null);

                intent = new Intent(this, LogInActivity.class);
                startActivity(intent);
                return true;

            case R.id.homeMenuItem:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
