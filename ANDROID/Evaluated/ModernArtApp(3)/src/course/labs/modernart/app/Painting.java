package course.labs.modernart.app;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.FragmentManager;
import android.graphics.Color;
import android.os.Bundle;
import android.util.AndroidRuntimeException;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;

public class Painting extends Activity {
	private static final int RECT_AMOUNT = 5;

	private List<View> rectList;

	private LinearLayout mainLayout;

	private LinearLayout canvasLayout;
	private LinearLayout leftLayout;
	private LinearLayout rightLayout;

	private View leftUpperView;
	private View leftDividerView;
	private View leftLowerView;

	private View verticalDividerView;

	private View rightUpperView;
	private View rightUpperDividerView;
	private View rightMiddleView;
	private View rightLowerDividerView;
	private View rightLowerView;

	private Colorizer colorizer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mainLayout = new LinearLayout(this);
		mainLayout.setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT));
		mainLayout.setOrientation(LinearLayout.VERTICAL);
		mainLayout.setWeightSum(1f);

		canvasLayout = new LinearLayout(mainLayout.getContext());
		canvasLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 1f));
		canvasLayout.setOrientation(LinearLayout.HORIZONTAL);
		canvasLayout.setWeightSum(7f);

		leftLayout = new LinearLayout(canvasLayout.getContext());
		leftLayout.setLayoutParams(new LayoutParams(0, LayoutParams.MATCH_PARENT, 3f));
		leftLayout.setOrientation(LinearLayout.VERTICAL);
		leftLayout.setWeightSum(2f);

		leftUpperView = new View(leftLayout.getContext());
		leftUpperView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 0, 1f));
		leftUpperView.setBackgroundColor(Color.RED);

		leftDividerView = new View(leftLayout.getContext());
		leftDividerView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 5));
		leftDividerView.setBackgroundColor(Color.BLACK);

		leftLowerView = new View(leftLayout.getContext());
		leftLowerView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 0, 1f));
		leftLowerView.setBackgroundColor(Color.YELLOW);

		verticalDividerView = new View(canvasLayout.getContext());
		verticalDividerView.setLayoutParams(new LayoutParams(5, LayoutParams.MATCH_PARENT));
		verticalDividerView.setBackgroundColor(Color.BLACK);

		rightLayout = new LinearLayout(canvasLayout.getContext());
		rightLayout.setLayoutParams(new LayoutParams(0, LayoutParams.MATCH_PARENT, 4f));
		rightLayout.setOrientation(LinearLayout.VERTICAL);
		rightLayout.setWeightSum(3f);

		rightUpperView = new View(rightLayout.getContext());
		rightUpperView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 0, 1f));
		rightUpperView.setBackgroundColor(Color.GREEN);

		rightUpperDividerView = new View(rightLayout.getContext());
		rightUpperDividerView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 5));
		rightUpperDividerView.setBackgroundColor(Color.BLACK);

		rightMiddleView = new View(rightLayout.getContext());
		rightMiddleView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 0, 1f));
		rightMiddleView.setBackgroundColor(Color.rgb(255, 251, 255)); // very light grey

		rightLowerDividerView = new View(rightLayout.getContext());
		rightLowerDividerView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 5));
		rightLowerDividerView.setBackgroundColor(Color.BLACK);

		rightLowerView = new View(rightLayout.getContext());
		rightLowerView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, 0, 1f));
		rightLowerView.setBackgroundColor(Color.BLUE);

		setRectanges(leftUpperView, leftLowerView, rightUpperView, rightLowerView, rightMiddleView);

		colorizer = new Colorizer(mainLayout.getContext(), rectList);
		colorizer.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, 0f));
		colorizer.setMax(10);
		colorizer.setBackgroundColor(Color.BLACK);

		leftLayout.addView(leftUpperView);
		leftLayout.addView(leftDividerView);
		leftLayout.addView(leftLowerView);

		rightLayout.addView(rightUpperView);
		rightLayout.addView(rightUpperDividerView);
		rightLayout.addView(rightMiddleView);
		rightLayout.addView(rightLowerDividerView);
		rightLayout.addView(rightLowerView);

		canvasLayout.addView(leftLayout);
		canvasLayout.addView(verticalDividerView);
		canvasLayout.addView(rightLayout);

		mainLayout.addView(canvasLayout);
		mainLayout.addView(colorizer);

		setContentView(mainLayout);
	}

	public void setRectanges(View... rectangles) {
		if (rectangles != null) {
			if (rectangles.length == RECT_AMOUNT) {
				rectList = new ArrayList<View>(RECT_AMOUNT);

				for (View rectangle : rectangles) {
					rectList.add(rectangle);
				}
			} else {
				throw new AndroidRuntimeException("you need: " + RECT_AMOUNT + "rectangles, but you have: " + rectangles.length);
			}
		} else {
			throw new AndroidRuntimeException("sigh... null is not an object");
		}
	}

	public List<View> getRectangles() {
		return rectList;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();

		inflater.inflate(R.menu.menu_about, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		if (id == R.id.action_settings) {
			FragmentManager manager = getFragmentManager();
			AboutDialog about = new AboutDialog();

			about.show(manager, "choose from here");
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
