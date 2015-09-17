package iam.jsed.betterdays;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v7.widget.ShareActionProvider;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.net.URISyntaxException;

/**
 * A placeholder fragment containing a simple view.
 */
public class ForecastDetailsActivityFragment extends Fragment {

    private final String LOG_TAG = ForecastDetailsActivityFragment.class.getSimpleName();

    private String mForecastString;
    private final String SHARE_HASHTAG = " - #BetterDays";
    public ForecastDetailsActivityFragment() {
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mRootView = inflater.inflate(R.layout.fragment_forecast_details, container, false);

        TextView textView = (TextView) mRootView.findViewById(R.id.textview_forecast_detail);

        Intent intent = getActivity().getIntent();

        if ( intent != null && intent.hasExtra(Intent.EXTRA_TEXT) ) {
            mForecastString = intent.getStringExtra(Intent.EXTRA_TEXT);
            textView.setText(mForecastString);
        }

        return mRootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        //super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_forecast_details, menu);

        MenuItem menuItem = menu.findItem(R.id.action_share);

        ShareActionProvider shareActionProvider =
                (ShareActionProvider) MenuItemCompat.getActionProvider(menuItem);

        if (shareActionProvider != null) {
            shareActionProvider.setShareIntent(createShareIntent());
        } else {
            Log.e(LOG_TAG, "Share Action Provider is null ?");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    private Intent createShareIntent(){
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
        shareIntent.setType("plain/text");
        shareIntent.putExtra(Intent.EXTRA_TEXT, mForecastString + SHARE_HASHTAG);

        return shareIntent;
    }
}
