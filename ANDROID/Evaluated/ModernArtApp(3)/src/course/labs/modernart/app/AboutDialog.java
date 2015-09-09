package course.labs.modernart.app;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

public class AboutDialog extends DialogFragment {

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		// builder
		builder.setMessage("Inspired by the works of artists such as Piet Mondrian and Ben Nicholson. " + " \n " + " \n " + "    Click below to learn more!");

		builder.setPositiveButton("Not Now", new NotNowListener());
		builder.setNegativeButton("Visit MOMA", new VisitUrlListener());

		return builder.show();
	}

	private class VisitUrlListener implements Dialog.OnClickListener {

		@Override
		public void onClick(DialogInterface dialog, int which) {
			Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.moma.org"));
			getActivity().startActivity(intent);
		}
	}

	private class NotNowListener implements Dialog.OnClickListener {

		@Override
		public void onClick(DialogInterface dialog, int which) {
			dialog.cancel();
		}
	}

}
