package course.examples.ui.linearlayout;

import android.animation.ArgbEvaluator;
import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class LinearLayoutActivity extends Activity {
 
	RelativeLayout palette;   // the viewgroup that holds all the coloured tile views
	private static final int MORE_INFO_TAG = 0, ABOUT_TAG = 1;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        // Fetch the view properties from the XML file
        palette = (RelativeLayout) findViewById(R.id.palette);
        // fetch the seek bar reference from the XML file
        SeekBar seekb = (SeekBar) findViewById(R.id.seekBar);
        
        //Set a listener for this seekb
        seekb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
        	
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				// Nothing to do in this method as nothing special is done 
				// when the seek bar is started or touched for teh first time
				
			}

			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				// Nothing to do here as nothing special is done if the 
				// seek bar is stopped or touched for teh last time.
				
			}

			
			public void changeviewBckgGrnd(View smview) {
				ColorDrawable cd = (ColorDrawable) smview.getBackground();
				int clr1 = cd.getColor();
				int clr2 = 0xFFFFFF - clr1;
				int clr_delta = clr2 - clr1;
				int step = clr_delta/1000000;
				 Log.i("AAKUDEBUG","clr1 and clr2 = " + clr1+ "   "+ clr2);
				int newColor = clr1 + step;
				smview.setBackgroundColor(newColor);
			}
			
			
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				// Change the color of the different views based on the progress value 
				// of the seekbar
				 Log.i("AAKUDEBUG","The progress bar is giving teh value = " + progress);
				
				changeviewBckgGrnd(palette.findViewById(R.id.bluebox));
				changeviewBckgGrnd(palette.findViewById(R.id.magentabox));
				changeviewBckgGrnd(palette.findViewById(R.id.purplebox));
				changeviewBckgGrnd(palette.findViewById(R.id.seabox));
				changeviewBckgGrnd(palette.findViewById(R.id.yellowbox));				
			}
        });
        
    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        return true;
    }
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
		Log.i("AAKUDEBUG","  Inside onOptionsItemSelected  ");
	    switch (item.getItemId()) {
	    case R.id.option1:
	    	Log.i("AAKUDEBUG","  Inside onOptionsItemSelected  Option 1 Selected");    
	    	showDialogFragment(MORE_INFO_TAG);
	    	break;
	    case R.id.option2:
	    	Log.i("AAKUDEBUG","  Inside onOptionsItemSelected  Option 2 Selected");
	    	showDialogFragment(ABOUT_TAG);
	    	break;
	    default:
	        return super.onOptionsItemSelected(item);
	    }
	   return true;
	}
	
	public void showDialogFragment(int dialogType) {
		switch (dialogType) 
		{
			case MORE_INFO_TAG:
				// Create a new DialogFragment
				DialogFragment mDialog = MoreInfoDialogFragment.newInstance();

				// Show AlertDialogFragment
				mDialog.show(getFragmentManager(), "Alert");
				break;
			case ABOUT_TAG:
				DialogFragment mAbout = AboutDialogFragment.newInstance();

				// Show AlertDialogFragment
				mAbout.show(getFragmentManager(), "Alert");
				break;
		}
	}
}