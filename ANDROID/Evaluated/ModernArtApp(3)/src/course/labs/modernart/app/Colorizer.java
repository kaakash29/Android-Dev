package course.labs.modernart.app;

import java.util.List;
import java.util.Random;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.SeekBar;

public class Colorizer extends SeekBar implements SeekBar.OnSeekBarChangeListener {

	private final List<View> rectangles;

	private final int[] colors = { Color.MAGENTA, Color.CYAN, Color.GREEN, Color.RED, Color.BLUE };

	private boolean firstTime;

	private Random randomize;

	public Colorizer(Context context, List<View> rectangles) {
		super(context);
		this.rectangles = rectangles;
		this.firstTime = true;
		this.randomize = new Random();

		this.setOnSeekBarChangeListener(this);
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		if (firstTime) { // do not run listener when app is started, only when user uses the slider
			firstTime = !firstTime;
		} else {
			for (int i = 0; i < 4; i++) { // not 5, last View is the light grey one which shouldn't colorize
				View rectangle = rectangles.get(i);
				int[] rgb = { Color.red(colors[i]), Color.green(colors[i]), Color.blue(colors[i]) };

				if (i == 2) { // log only upper right rectangle
					Log.d("Colorizer", "progess:" + progress + " color:" + Integer.toHexString(colors[i]) + " r: " + rgb[0] + " g: " + rgb[1] + " b: " + rgb[2]);
				}

				randomize = new Random();
				for (int x = randomize.nextInt(3); x < 3; x++) {
					if (rgb[x] == 0xff) {
						rgb[x] = 0x00;
					} else {
						rgb[x] += 0x0f;
						break;
					}
				}
				colors[i] = Color.rgb(rgb[0], rgb[1], rgb[2]);
				rectangle.setBackgroundColor(colors[i]);
			}
		}
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		return;
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		return;
	}

}
