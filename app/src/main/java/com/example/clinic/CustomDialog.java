package com.example.clinic;

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomDialog extends Dialog {

    String workingHoursText;

    public CustomDialog(@NonNull Context context, String workingHoursText) {
        super(context);
        this.workingHoursText = workingHoursText;
        setCancelable(true);
        setContentView(R.layout.dialog);

        TextView dialogText = findViewById(R.id.dialogText);
        if (isWorkingHoursInTime()) {
            dialogText.setText(context.getResources().getString(R.string.dialogTextInhours));
        }
        else {
            dialogText.setText(context.getResources().getString(R.string.dialogTextOuthours));
        }
    }

    boolean isWorkingHoursInTime() {
        Pattern pattern = Pattern.compile("((\\d)?\\d:\\d\\d)");
        Matcher matcher = pattern.matcher(workingHoursText);
        ArrayList<String> times = new ArrayList<>();
        while (matcher.find()) {
            times.add(workingHoursText.substring(matcher.start(), matcher.end()));
        }
        String currentDateAsString = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh:mm", Locale.ENGLISH);
        if (times.size() == 2) {
            try {
                Date startHoursDate = formatter.parse(currentDateAsString + " " + times.get(0));
                Date endHoursDate = formatter.parse(currentDateAsString + " " + times.get(1));
                Date currentDate = new Date();
                if (currentDate.compareTo(startHoursDate) > 0 && currentDate.compareTo(endHoursDate) < 0) {
                    return true;
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return false;
    }



}
