package course.examples.ui.modernart;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TableLayout;
import android.widget.TextView;

public class ModernArtUiActivity extends Activity {
	private static final String TAG = "ModernArtUiActivity";
	private final String URL = "http://www.moma.org/search?query=rothko";
	private final String CHOOSER_TEXT = "Load " + URL + " with:";

	private DialogFragment dialog;

	private TextView square2;
	private TextView square3l;
	private TextView square3c;
	private TextView square3r;
	private TextView square1l;
	private TextView square1r;
	private TextView square5;
	private TextView square0;
	private TableLayout bg;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		bg = (TableLayout) findViewById(R.id.tableLayout);
		square2 = (TextView) findViewById(R.id.square2);
		square3l = (TextView) findViewById(R.id.square3l);
		square3c = (TextView) findViewById(R.id.square3c);
		square3r = (TextView) findViewById(R.id.square3r);
		square1l = (TextView) findViewById(R.id.square1l);
		square1r = (TextView) findViewById(R.id.square1r);
		square5 = (TextView) findViewById(R.id.square5);
		square0 = (TextView) findViewById(R.id.square0);

		int white = mixColor(0, 0.0f, 0.92f);
		square5.setBackgroundColor(white); // does not change

		final SeekBar sk = (SeekBar) findViewById(R.id.colorSlider);
		sk.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// do nothing
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// do nothing
			}

			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				int hue = progress;
				
				int tone = mixColor(hue, 0.9f, 0.8f);
				bg.setBackgroundColor(tone);

				int shade = mixColor(hue, 0.7f, 0.6f);
				square2.setBackgroundColor(shade);

				int complement = (hue + 19) % 360;

				int beigel = mixColor(complement + 1, 0.73f, 0.82f);
				square3l.setBackgroundColor(beigel);
				int beigec = mixColor(complement + 2, 0.72f, 0.82f);
				square3c.setBackgroundColor(beigec);
				int beiger = mixColor(complement, 0.73f, 0.82f);
				square3r.setBackgroundColor(beiger);

				int black1 = mixColor(hue + 5, 0.41f, 0.15f);
				square1l.setBackgroundColor(black1);
				int black2 = mixColor(hue + 10, 0.55f, 0.11f);
				square1r.setBackgroundColor(black2);

				int tint = mixColor(complement, 0.16f, 0.90f);
				square0.setBackgroundColor(tint);
			}
		});
	}

	public int mixColor(int hue, float sat, float brt) {
		float[] hsv = { hue, sat, brt };
		int color = Color.HSVToColor(hsv);
		return color;
	}

	// Create Options Menu
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.top_menu, menu);
		return true;
	}

	// Process clicks on Options Menu items
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.option_more_info:
			// Create an instance of the dialog fragment and show it
			dialog = InfoDialogFragment.newInstance();
			dialog.show(getFragmentManager(), "Alert");

			return true;
		default:
			return false;
		}
	}

	private void startBrowserActivity() {

		// Create a base intent for viewing a URL
		Uri webpage = Uri.parse(URL);
		Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);

		// Create chooser intent
		Intent chooserIntent = Intent.createChooser(webIntent, CHOOSER_TEXT);
		startActivity(chooserIntent);

	}

	protected void dialogClick(boolean b) {
		if (b) {
			startBrowserActivity();
		}
	}

	public static class InfoDialogFragment extends DialogFragment {

		public static InfoDialogFragment newInstance() {
			return new InfoDialogFragment();
		}

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			// Use the Builder class for convenient dialog construction
			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			Log.i(TAG, "Created dialog builder");
			builder.setMessage(R.string.dialog_msg)
//				.setCancelable(false)
				.setPositiveButton(R.string.visit_moma,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								// start browser activity and navigate to moma
								((ModernArtUiActivity) getActivity()).dialogClick(true);
							}
						})
				.setNegativeButton(R.string.not_now,
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								// User cancelled the dialog
								((ModernArtUiActivity) getActivity()).dialogClick(false);
							}
						});
			// Create the AlertDialog object and return it
			return builder.create();
		}
	}
}
