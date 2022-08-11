package com.example.clinic;

import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.Date;

public class CustomDialog extends Dialog {
    public CustomDialog(@NonNull Context context, String workingHoursText) {
        super(context);
        setCancelable(true);
        setContentView(R.layout.dialog);

        TextView dialogText = findViewById(R.id.dialogText);
        if (new WorkingHours().isWorkingHoursInTime(workingHoursText, new Date())) {
            dialogText.setText(context.getResources().getString(R.string.dialogTextInhours));
        }
        else {
            dialogText.setText(context.getResources().getString(R.string.dialogTextOuthours));
        }
    }
}
