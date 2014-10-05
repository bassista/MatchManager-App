package it.valeriovaudi.matchmanager.factory;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

/**
 * Created by Valerio on 30/07/2014.
 */
public class SpinnerFactory extends AbstractUiFactory{
    public SpinnerFactory(Context context) {
        super(context);
    }

    public void addDropDownListContent(Spinner spinner,String[] values) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_1, values);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }
}
