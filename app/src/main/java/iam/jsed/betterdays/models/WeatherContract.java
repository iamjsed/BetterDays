package iam.jsed.betterdays.models;


import android.provider.BaseColumns;
import android.text.format.Time;

/**
 * Created by iamjsed on 19/09/2015.
 */
public class WeatherContract {

    public static long normalizeDate (long startDate) {
        Time time = new Time();
        time.setToNow();
        int julianDay = Time.getJulianDay(startDate, time.gmtoff);
        return  julianDay;
    }

    public static final class LocationEntry implements BaseColumns {
        public static final String TABLE_NAME = "location";

        public static final String COLUMN_LOCATION_ID = "location_id";
        public static final String COLUMN_LOCATION_SETTING = "location_setting";

        public static final String COLUMN_LONGITUDE = "lon";
        public static final String COLUMN_LATITUDE = "lat";
        public static final String COLUMN_CITY_NAME = "city_name";

    }

    public static final class WeatherEntry implements BaseColumns {
        public static final String TABLE_NAME = "weather";

        public static final String COLUMN_LOC_KEY = "location_id";
        public static final String COLUMN_DATE = "date";
        public static final String COLUMN_WEATHER_ID = "weather_id";
        public static final String COLUMN_SHORT_DESC = "short_dec";
        public static final String COLUMN_MIN = "min";
        public static final String COLUMN_MAX = "max";
        public static final String COLUMN_HUMIDITY = "humidity";
        public static final String COLUMN_PRESSURE = "pressure";
        public static final String COLUMN_WIND_SPEED = "wind";
        public static final String COLUMN_DEGREES = "degrees";

    }
}
