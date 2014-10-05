package it.valeriovaudi.matchmanager.factory;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Valerio on 10/05/2014.
 */
public class ToastFactory extends AbstractUiFactory{

    public ToastFactory(Context context){super(context);}

    public void getToast(String message){
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_LONG);
        toast.show();
    }
}
