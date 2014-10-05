package it.valeriovaudi.matchmanager.factory;

import android.content.Context;
import android.widget.TextView;

/**
 * Created by Valerio on 30/07/2014.
 */
public class TextViewFactory extends AbstractUiFactory {
    public TextViewFactory(Context context) {
        super(context);
    }

    public TextView createTextViev(String data){
        TextView textView = new TextView(context);
        textView.setText(data);

        return textView;
    }
}
