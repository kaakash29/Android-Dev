package course.labs.modernartui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.SeekBar;

public class ModernArtUIActivity extends Activity {
	private static final String TAG = "ModernArtUIActivity";

	protected static final String MOMA_URI = "http://www.moma.org/";

	private final int _itemIds[] = {R.id.item_1, R.id.item_2, R.id.item_3,
			R.id.item_4, R.id.item_5};

	private final int _colors[] = {R.color.color_1, R.color.color_2,
			R.color.color_3, R.color.color_4, R.color.color_5};

	private SeekBar _seekBar;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_modern_art_ui);

		ChangeColors(0);

		_seekBar = (SeekBar) findViewById(R.id.seek_bar);

		_seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				ChangeColors(progress);
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
			}
		});
	}

	private void ChangeColors(int progress) {
		for (int index = 0; index < _itemIds.length; index++) {
			LinearLayout item = (LinearLayout) findViewById(_itemIds[index]);
			item.setBackgroundColor(getColor(index, progress));
		}
	}

	private int getColor(int index, int progress) {

		int color = getResources().getColor(_colors[index]);

		float[] hsv = new float[3];
		Color.RGBToHSV(Color.red(color), Color.green(color), Color.blue(color), hsv);

		hsv[0] = roundH(hsv[0] + progress, 360f);
		hsv[1] = round(hsv[1] + progress / 300f, 2f);
		hsv[2] = round(hsv[2] + progress / 300f, 2f);

		return Color.HSVToColor(hsv);
	}

	private float roundH(float value, float round) {
		if (value > round) {
			value -= round;
		}
		return value;
	}

	private float round(float value, float round) {
		if (value > round) {
			value = round - value;
		}
		return value;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.more_information) {
			Builder builder = new AlertDialog.Builder(this);
			builder.setIcon(R.drawable.ic_launcher)
					.setMessage(R.string.dialog_message)
					.setNegativeButton(R.string.dialog_yes,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog, int id) {
									Intent intent = new Intent( Intent.ACTION_VIEW, Uri.parse(MOMA_URI));
									startActivity(intent);
								}
							})
					.setPositiveButton(R.string.dialog_no,
							new DialogInterface.OnClickListener() {
								public void onClick( final DialogInterface dialog, int id) {
									dialog.dismiss();
								}
							});
			builder.create().show();
		}
		return super.onOptionsItemSelected(item);
	}
}
