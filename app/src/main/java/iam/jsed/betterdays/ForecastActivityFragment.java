package iam.jsed.betterdays;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import iam.jsed.betterdays.utils.ForecastDataHelper;

/**
 * A placeholder fragment containing a simple view.
 */
public class ForecastActivityFragment extends Fragment {

    private final String LOG_TAG = ForecastActivityFragment.class.getSimpleName();

    ArrayAdapter<String> mForecastArrayAdapter;

    public ForecastActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mRootView = inflater.inflate(R.layout.fragment_forecast, container, false);

        String[] fakeData = {"ABC", "DEF", "GHI"};

        mForecastArrayAdapter =
                new ArrayAdapter<String>(
                    getActivity(),
                    R.layout.forecast_listitem,
                    R.id.listitem_forecast_textview,
                    new ArrayList<String>());

        ListView listView = (ListView) mRootView.findViewById(R.id.forecast_listview);
        listView.setAdapter(mForecastArrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String listItemText = (String) adapterView.getItemAtPosition(i);
                Toast.makeText(getActivity(), listItemText, Toast.LENGTH_SHORT).show();

                Intent mShowForecastDetailIntent = new Intent(getActivity(), ForecastDetailsActivity.class);
                mShowForecastDetailIntent.putExtra(Intent.EXTRA_TEXT, listItemText);

                startActivity(mShowForecastDetailIntent);
            }
        });

        return mRootView;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();
        updateForecastList();
    }

    private void updateForecastList() {

        SharedPreferences sharedPrefrence = PreferenceManager.getDefaultSharedPreferences(getActivity());


        String[] pref = new String[2];

        pref[0] = sharedPrefrence.getString(
                getString(R.string.pref_location_key),
                getString(R.string.pref_location_default));

        pref[1] = sharedPrefrence.getString(
                getString(R.string.pref_unit_key),
                getString(R.string.pref_unit_default));

        Log.d(LOG_TAG, "Location: " + pref[0] + ", Unit: " + pref[1]);

        FetchWeatherForecastTask fetchWeatherForecastTask = new FetchWeatherForecastTask();
        fetchWeatherForecastTask.execute(pref);
    }

    public class FetchWeatherForecastTask extends AsyncTask<String, Void, String[]> {



        @Override
        protected void onPostExecute(String[] strings) {
            super.onPostExecute(strings);

            mForecastArrayAdapter.clear();
            mForecastArrayAdapter.addAll(new ArrayList<String>(Arrays.asList(strings)));

        }

        @Override
        protected String[] doInBackground(String[] strings) {

            // TODO: Externalize the method of fetching and parsing the json weather data.

            ForecastDataHelper forecastData = new ForecastDataHelper(strings[0], strings[1]);

            return forecastData.getWeeklyForecast();

        }
    }
}
