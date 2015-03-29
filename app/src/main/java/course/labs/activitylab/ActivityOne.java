package course.labs.activitylab;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ActivityOne extends Activity {

    // Use these as keys when you're saving state between reconfigurations
    private static final String RESTART_KEY = "restart";
    private static final String RESUME_KEY = "resume";
    private static final String START_KEY = "start";
    private static final String CREATE_KEY = "create";

    // String for LogCat documentation
    private final static String TAG = "Lab-ActivityOne";

    // Lifecycle counters
    private int create;
    private int restart;
    private int start;
    private int resume;

    private TextView tvCreate;
    private TextView tvRestart;
    private TextView tvStart;
    private TextView tvResume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);

        tvCreate = (TextView) findViewById(R.id.create);
        tvRestart = (TextView) findViewById(R.id.restart);
        tvStart = (TextView) findViewById(R.id.start);
        tvResume = (TextView) findViewById(R.id.resume);

        Button launchActivityTwoButton = (Button) findViewById(R.id.bLaunchActivityTwo);
        launchActivityTwoButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create an intent stating which Activity you would like to
                // start
                Intent intent = new Intent(ActivityOne.this, ActivityTwo.class);
                // Launch the Activity using the intent
                startActivity(intent);
            }
        });

        // Has previous state been saved?
        if (savedInstanceState != null) {
            create = savedInstanceState.getInt(CREATE_KEY);
            restart = savedInstanceState.getInt(RESTART_KEY);
            start = savedInstanceState.getInt(START_KEY);
            resume = savedInstanceState.getInt(RESUME_KEY);
        }

        // Emit LogCat message
        Log.i(TAG, "Entered the onCreate() method");

        create++;
    }

    // Lifecycle callback overrides

    @Override
    public void onStart() {
        super.onStart();

        // Emit LogCat message
        Log.i(TAG, "Entered the onStart() method");

        start++;
    }

    @Override
    public void onResume() {
        super.onResume();

        // Emit LogCat message
        Log.i(TAG, "Entered the onResume() method");

        resume++;
        displayCounts();
    }

    @Override
    public void onPause() {
        super.onPause();

        // Emit LogCat message
        Log.i(TAG, "Entered the onPause() method");
    }

    @Override
    public void onStop() {
        super.onStop();

        // Emit LogCat message
        Log.i(TAG, "Entered the onStop() method");
    }

    @Override
    public void onRestart() {
        super.onRestart();

        // Emit LogCat message
        Log.i(TAG, "Entered the onRestart() method");

        restart++;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        // Emit LogCat message
        Log.i(TAG, "Entered the onDestroy() method");
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        if(savedInstanceState != null) {
            savedInstanceState.putInt(CREATE_KEY, create);
            savedInstanceState.putInt(RESTART_KEY, restart);
            savedInstanceState.putInt(START_KEY, start);
            savedInstanceState.putInt(RESUME_KEY, resume);
        }
    }

    // Updates the displayed counters
    // This method expects that the counters and TextView variables use the
    // names
    // specified above
    public void displayCounts() {
        tvCreate.setText("onCreate() calls: " + create);
		tvStart.setText("onStart() calls: " + start);
		tvResume.setText("onResume() calls: " + resume);
		tvRestart.setText("onRestart() calls: " + restart);
    }
}
