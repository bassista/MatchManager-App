package it.valeriovaudi.matchmanager.factory;

import android.content.Context;

/**
 * Created by Valerio on 30/07/2014.
 */
public abstract class AbstractUiFactory {
    protected Context context;

    public  AbstractUiFactory(Context context){
        this.context = context;
    }
}
