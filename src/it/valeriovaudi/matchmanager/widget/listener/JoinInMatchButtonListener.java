package it.valeriovaudi.matchmanager.widget.listener;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import it.valeriovaudi.matchmanager.activity.MainActivity;
import it.valeriovaudi.matchmanager.controller.MatchController;
import it.valeriovaudi.matchmanager.factory.ToastFactory;
import it.valeriovaudi.matchmanager.support.ExceptionMessageSupport;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;

/**
 * Created by Valerio on 04/06/2014.
 */
public class JoinInMatchButtonListener implements View.OnClickListener {
    private Logger logger = Logger.getLogger("JoinInMatchButtonListener");

    private MatchController matchService;
    private Activity activity;
    private static ExceptionMessageSupport exceptionMessageSupport = ExceptionMessageSupport.getExceptionMessageSupportInstance();

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");

    private String hour;
    private String footballField;
    private String date;
    private String userName;

    private ToastFactory toastFactory;

    public JoinInMatchButtonListener(Activity activity,
                                     SharedPreferences sharedPreferences,
                                     String userName,
                                     String hour,
                                     String footballField,
                                     String date) {
        matchService = new MatchController(sharedPreferences);
        this.activity = activity;
        this.hour = hour;
        this.footballField = footballField;
        this.date = date;
        this.userName = userName;

        this.toastFactory=new ToastFactory(activity.getApplication());
    }

    @Override
    public void onClick(View v) {
        try {
            boolean result = matchService.setReservationMatch(userName, simpleDateFormat.parse(date), hour, footballField);
            if(result){
                toastFactory.getToast("Your booking has been successful");

                Intent intent = new Intent(activity.getApplicationContext(), MainActivity.class);
                activity.startActivity(intent);
            } else {
                toastFactory.getToast("Your booking hasn't been successful");
            }
        } catch (ParseException e) {
            exceptionMessageSupport.printExceptionErrorMessage(e,logger);
        }
    }
}