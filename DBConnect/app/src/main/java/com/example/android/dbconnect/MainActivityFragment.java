package com.example.android.dbconnect;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    public ArrayAdapter<String> mForecastAdapter;
    public MainActivityFragment() {
    }

    private void updatePage(){
        JSONParser jsonParser=new JSONParser();
        jsonParser.execute();
    }
    /**
     * Called when the Fragment is visible to the user.  This is generally
     * tied to {@link Activity#onStart() Activity.onStart} of the containing
     * Activity's lifecycle.
     */
    @Override
    public void onStart() {
        super.onStart();
        updatePage();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root= inflater.inflate(R.layout.fragment_main, container, false);
        mForecastAdapter = new ArrayAdapter<String>(getActivity(), R.layout.list_item_forcast,R.id.list_item_forecast_textview);
        final ListView listView=(ListView) root.findViewById(R.id.listview_forecast);
        listView.setAdapter(mForecastAdapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            /**
//             * Callback method to be invoked when an item in this AdapterView has
//             * been clicked.
//             * <p/>
//             * Implementers can call getItemAtPosition(position) if they need
//             * to access the data associated with the selected item.
//             *
//             * @param parent   The AdapterView where the click happened.
//             * @param view     The view within the AdapterView that was clicked (this
//             *                 will be a view provided by the adapter)
//             * @param position The position of the view in the adapter.
//             * @param id       The row id of the item that was clicked.
//             */
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String forecast=mForecastAdapter.getItem(position);
//                Toast toast = Toast.makeText(getActivity().getApplicationContext(), forecast, Toast.LENGTH_SHORT);
//                toast.show();
//                Intent intent=new Intent(getActivity(),DetailActivity.class)
//                        .putExtra(Intent.EXTRA_TEXT,forecast);
//                startActivity(intent);
//            }
//        });


        return root;
    }

    class JSONParser extends AsyncTask<Void, Void, ArrayList> {
        /**
         * Override this method to perform a computation on a background thread. The
         * specified parameters are the parameters passed to {@link #execute}
         * by the caller of this task.
         * <p/>
         * This method can call {@link #publishProgress} to publish updates
         * on the UI thread.
         *
         * @param params The parameters of the task.
         * @return A result, defined by the subclass of this task.
         * @see #onPreExecute()
         * @see #onPostExecute
         * @see #publishProgress
         */
        @Override
        protected ArrayList doInBackground(Void... params) {
            ArrayList items = new ArrayList();

            try {
                URL url = new URL
                        // TODO - change line below to your own domain
                        ("http://alpha.logicbag.com/student.php");
                HttpURLConnection urlConnection =
                        (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.connect();

                BufferedReader bufferedReader =
                        new BufferedReader(new InputStreamReader(
                                urlConnection.getInputStream()));

                String next;
                while ((next = bufferedReader.readLine()) != null){
                    JSONArray ja = new JSONArray(next);

                    for (int i = 0; i < ja.length(); i++) {
                        JSONObject jo = (JSONObject) ja.get(i);
                        items.add(jo.getString("firstname"));
                    }
                }
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return items;
        }

        /**
         * <p>Runs on the UI thread after {@link #doInBackground}. The
         * specified result is the value returned by {@link #doInBackground}.</p>
         * <p/>
         * <p>This method won't be invoked if the task was cancelled.</p>
         *
         * @param strings The result of the operation computed by {@link #doInBackground}.
         * @see #onPreExecute
         * @see #doInBackground
         * @see #onCancelled(Object)
         */
        @Override
        protected void onPostExecute(ArrayList strings) {
                if(strings!=null){
                    mForecastAdapter.clear();
                    mForecastAdapter.addAll(strings);
            }
        }
    }
}
