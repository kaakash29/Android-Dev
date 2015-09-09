package course.examples.audiovideo.camera;

import java.io.File;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class welcome extends Activity {

	/** Called when the activity is first created. */
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		
	    super.onCreate(savedInstanceState);
	    
	    System.out.println("Welcome activity called");

		setContentView(R.layout.welcome);

		Button selfie = (Button) findViewById(R.id.button1);
		
		Button viewer = (Button) findViewById(R.id.button2);

				
		File mydir = getApplicationContext().getDir("users", Context.MODE_PRIVATE);  //Creating an internal dir
		System.out.println("Creating the directory : " + mydir.toString());
		if(!mydir.exists())
		{
			System.out.println("Creating the directory : " + mydir.toString());
		    mydir.mkdirs();
		} 
				
		selfie.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0){
				Intent cameraIntent = new Intent(welcome.this, AudioVideoCameraActivity.class);
        		startActivity(cameraIntent);
			}
		});
			
		viewer.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0){
				Intent gridIntent = new Intent(welcome.this, imageGridActivity.class);
        		startActivity(gridIntent);
				
			}
		});
	}
}