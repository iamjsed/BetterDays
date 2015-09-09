package iam.jsed.betterdays;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.net.URISyntaxException;

/**
 * A placeholder fragment containing a simple view.
 */
public class ForecastDetailsActivityFragment extends Fragment {

    public ForecastDetailsActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View mRootView = inflater.inflate(R.layout.fragment_forecast_details, container, false);

        TextView textView = (TextView) mRootView.findViewById(R.id.textview_forecast_detail);

        Intent intent = getActivity().getIntent();

        if ( intent != null && intent.hasExtra(Intent.EXTRA_TEXT) ) {
            String forecast_datail_text = intent.getStringExtra(Intent.EXTRA_TEXT);
            textView.setText(forecast_datail_text);
        }

        return mRootView;
    }
}
