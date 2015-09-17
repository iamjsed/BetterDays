package iam.jsed.betterdays;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import java.net.URLEncoder;

public class ForecastActivity extends AppCompatActivity {

    private final String LOG_TAG = ForecastActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forecast);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_forecast, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (item.getItemId() == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        } else if ( item.getItemId() == R.id.action_view_in_map ) {
            openPreferedLocationInMap();
        }

        return super.onOptionsItemSelected(item);
    }

    private void openPreferedLocationInMap() {
        SharedPreferences sharedPrefrence = PreferenceManager.getDefaultSharedPreferences(this);
        String location = sharedPrefrence.getString(
                getString(R.string.pref_location_key),
                getString(R.string.pref_location_default));

        Uri geoLocation = Uri.parse("geo:0,0?").buildUpon()
                .appendQueryParameter("q", URLEncoder.encode(location))
                .build();

        Intent getLocationIntent = new Intent(Intent.ACTION_VIEW);
        getLocationIntent.setData(geoLocation);

        if ( getLocationIntent.resolveActivity(getPackageManager()) != null ) {
            startActivity(getLocationIntent);
        } else {
            Log.d(LOG_TAG, "Unable to handle request for " + location);
        }
    }

}
