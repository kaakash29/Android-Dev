package course.examples.ui.linearlayout;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class MoreInfoDialogFragment extends DialogFragment {
	
	public static MoreInfoDialogFragment newInstance() {
		return new MoreInfoDialogFragment();
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		return new AlertDialog.Builder(getActivity())
				.setMessage(R.string.dialog_text)
				
				// User cannot dismiss dialog by hitting back button
				.setCancelable(false)
				
				// Set up No Button
				.setNegativeButton(R.string.dialog_not_now,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int id) {
								//TO DO : Add code to do something on clicking negative button
								// Do NOthing
							}
						})
						
				// Set up Yes Button
				.setPositiveButton(R.string.dialog_visit,
						new DialogInterface.OnClickListener() {
							public void onClick(
									final DialogInterface dialog, int id) {
								//TO DO : Add code to do something on clicking positive button
								Intent visit = new Intent( Intent.ACTION_VIEW, Uri.parse("http://www.moma.org") );
						        Intent chooser = Intent.createChooser( visit, "Open Link With : ");
						        startActivity(chooser);
							}
						}).create();
	}
}
