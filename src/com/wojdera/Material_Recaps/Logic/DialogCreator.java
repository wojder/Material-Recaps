package com.wojdera.Material_Recaps.Logic;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import com.wojdera.Material_Recaps.Activities.SlamDunkActivity;
import com.wojdera.Material_Recaps.R;

/**
 * Created by wojder on 20.12.14.
 */
public class DialogCreator extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.dialog_text)
                .setTitle(" ")
                .setIcon(R.drawable.sd_logo)
                .setPositiveButton(R.string.positive, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent sdIntent = new Intent(getActivity(), SlamDunkActivity.class);
                        startActivity(sdIntent);
                    }
                })
                .setNegativeButton(R.string.negative, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                });
        return builder.create();
    }
}
