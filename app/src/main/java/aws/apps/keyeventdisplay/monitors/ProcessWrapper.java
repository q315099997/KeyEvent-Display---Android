package aws.apps.keyeventdisplay.monitors;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;

/*package*/ class ProcessWrapper {
    private static final String TAG = ProcessWrapper.class.getSimpleName();

    private Process mProcess = null;

    protected boolean execute(String command[]) {
        try {
            mProcess = Runtime.getRuntime().exec(command);
            return true;
        } catch (Exception e) {
            Log.e(TAG, "^ Can't startMonitor: " + Arrays.toString(command) + " " + e.getMessage());
            stop();
            return false;
        }
    }

    protected boolean execute(String command) {
        try {
            mProcess = Runtime.getRuntime().exec(command);
            return true;
        } catch (IOException e) {
            Log.e(TAG, "^ Can't startMonitor: " + command + " " + e.getMessage());
            stop();
            return false;
        }
    }


    public void stop() {
        if (mProcess != null) {
            mProcess.destroy();
            mProcess = null;
        }
    }

    public InputStream getInputStream() {
        return mProcess.getInputStream();
    }

    public OutputStream getOutputStream() {
        return mProcess.getOutputStream();
    }
}
