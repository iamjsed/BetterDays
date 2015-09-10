package iam.jsed.betterdays;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import iam.jsed.betterdays.utils.ForecastDataHelper;

/**
 * A placeholder fragment containing a simple view.
 */
public class ForecastActivityFragment extends Fragment {


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
    public void onStart() {
        super.onStart();
        updateForecastList();
    }

    private void updateForecastList() {
        FetchWeatherForecastTask fetchWeatherForecastTask = new FetchWeatherForecastTask();
        fetchWeatherForecastTask.execute("Makati");
    }

    public class FetchWeatherForecastTask extends AsyncTask<String, Void, String[]> {

        @Override
        protected void onPostExecute(String[] strings) {
            super.onPostExecute(strings);

            mForecastArrayAdapter.clear();
            mForecastArrayAdapter.addAll(new ArrayList<String>(Arrays.asList(strings)));

        }

        @Override
        protected String[] doInBackground(String... strings) {

            // TODO: Externalize the method of fetching and parsing the json weather data.

            return new ForecastDataHelper().getWeeklyForecast(strings[0]);

        }
    }
}
