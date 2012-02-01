package net.sf.openrocket.android.rocket;

import java.io.File;

import net.sf.openrocket.android.util.AndroidLogWrapper;
import net.sf.openrocket.document.OpenRocketDocument;
import net.sf.openrocket.file.DatabaseMotorFinder;
import net.sf.openrocket.file.RocketLoadException;
import net.sf.openrocket.file.openrocket.importt.OpenRocketLoader;
import android.os.AsyncTask;

public class OpenRocketLoaderTask extends AsyncTask<File, Void, OpenRocketLoaderResult> {
	
	/* (non-Javadoc)
	 * @see android.os.AsyncTask#doInBackground(Params[])
	 */
	@Override
	protected OpenRocketLoaderResult doInBackground(File... arg0) {
		AndroidLogWrapper.d(OpenRocketLoaderTask.class, "doInBackgroud");
		
		OpenRocketLoader rocketLoader = new OpenRocketLoader();
		try {
			OpenRocketLoaderResult result = new OpenRocketLoaderResult();
			OpenRocketDocument rocket = rocketLoader.load(arg0[0], new DatabaseMotorFinder());
			result.rocket = rocket;
			result.warnings = result.warnings;
			return result;
		} catch (RocketLoadException ex) {
			AndroidLogWrapper.e(OpenRocketLoaderTask.class, "doInBackground rocketLaoder.load threw", ex);
		}
		return null;
		
	}
	
}
