package course.labs.asynctasklab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

public class DownloaderTaskFragment extends Fragment {

	private DownloadFinishedListener mCallback;
	private Context mContext;
	
	@SuppressWarnings ("unused")
	private static final String TAG = "Lab-Threads";
	
	public static String bundle2string(Bundle bundle) {
	    String string = "Bundle{";
	    for (String key : bundle.keySet()) {
	        string += " " + key + " => " + bundle.get(key) + ";";
	    }
	    string += " }Bundle";
	    return string;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// Preserve across reconfigurations
		setRetainInstance(true);
		
		// TODO: Create new DownloaderTask that "downloads" data
		DownloaderTask downloaderTask=new DownloaderTask();
        		
		// TODO: Retrieve arguments from DownloaderTaskFragment
		// Prepare them for use with DownloaderTask. 
		
		Bundle appArgs = getArguments();
		System.out.println ("AAKUDEBUG : Bundle To String : " + bundle2string(appArgs));
		ArrayList<Integer> resourceList = appArgs.getIntegerArrayList("friends");
	
		// TODO: Start the DownloaderTask 
		// Params = ArrayList of ResourceId's
		// Progress = Void
		// Result = Array of string
		downloaderTask.execute(resourceList);

	}

	// Assign current hosting Activity to mCallback
	// Store application context for use by downloadTweets()
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		mContext = activity.getApplicationContext(); 

		// Make sure that the hosting activity has implemented
		// the correct callback interface.
		try {
			mCallback = (DownloadFinishedListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement DownloadFinishedListener");
		}
	}

	// Null out mCallback
	@Override
	public void onDetach() {
		super.onDetach();
		mCallback = null;
	}

	// TODO: Implement an AsyncTask subclass called DownLoaderTask.
	// This class must use the downloadTweets method (currently commented
	// out). Ultimately, it must also pass newly available data back to 
	// the hosting Activity using the DownloadFinishedListener interface.

	public class DownloaderTask extends AsyncTask <ArrayList<Integer>, Void, String[]> {
		

		@Override
		protected String[] doInBackground(ArrayList<Integer>... params) {
			// TODO Auto-generated method stub
			
			ArrayList<Integer> resourceIds = params[0];
            Integer[] resourceArray = new Integer[resourceIds.size()]; //initialize empty resourceArray 
            
            //creating the array from ArrayList 
            for(int i=0;i<resourceIds.size();i++){
            	resourceArray[i] = resourceIds.get(i);
            }
            
            return downloadTweets(resourceArray);
		}
		
		
		@Override
        protected void onPostExecute(String[] strings) {
            mCallback.notifyDataRefreshed(strings);
        }

        // TODO: Uncomment this helper method
		// Simulates downloading Twitter data from the network
         private String[] downloadTweets(Integer resourceIDS[]) {
			final int simulatedDelay = 2000;
			String[] feeds = new String[resourceIDS.length];
			try {
				for (int idx = 0; idx < resourceIDS.length; idx++) {
					InputStream inputStream;
					BufferedReader in;
					try {
						// Pretend downloading takes a long time
						Thread.sleep(simulatedDelay);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					inputStream = mContext.getResources().openRawResource(
							resourceIDS[idx]);
					in = new BufferedReader(new InputStreamReader(inputStream));

					String readLine;
					StringBuffer buf = new StringBuffer();

					while ((readLine = in.readLine()) != null) {
						buf.append(readLine);
					}

					feeds[idx] = buf.toString();

					if (null != in) {
						in.close();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}

			return feeds;
		}

}
}