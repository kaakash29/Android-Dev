package course.examples.ui.linearlayout;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class AboutDialogFragment extends DialogFragment {
	
	public static AboutDialogFragment newInstance() {
		return new AboutDialogFragment();
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		return new AlertDialog.Builder(getActivity())
				.setMessage("Developed by @TechCurrentz \n\n Click Below to Know More :")
				
				// User cannot dismiss dialog by hitting back button
				.setCancelable(false)
				
				// Set up No Button
				.setNegativeButton(R.string.dialog_not_now,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog,
									int id) {
								//TO DO : Add code to do something on clicking negative button
								//Do nothing
							}
						})
						
				// Set up Yes Button
				.setPositiveButton("Know More !!",
						new DialogInterface.OnClickListener() {
							public void onClick(
									final DialogInterface dialog, int id) {
								//TO DO : Add code to do something on clicking positive button
								Intent visit = new Intent( Intent.ACTION_VIEW, Uri.parse("http://www.techaaku.info") );
						        Intent chooser = Intent.createChooser( visit, "Open Link With : ");
						        startActivity(chooser);
							}
						}).create();
	}
}
