package vera.moon.com.c8_json;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Call thread
        DownloadJSON thread = new DownloadJSON();
        thread.execute("http://echo.jsontest.com/key/value/one/two");
    }

    //Create read to read the JSON..................................
    public class DownloadJSON extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {

            String result = "";
            URL url;
            HttpURLConnection urlConnection = null;

            try{
                url = new URL(strings[0]);

                urlConnection = (HttpURLConnection) url.openConnection();

                InputStream in = urlConnection.getInputStream();

                InputStreamReader reader = new InputStreamReader(in);

                int read = reader.read();

                while (read != -1) {

                    char car = (char)read;
                    result += car;
                    read = reader.read();
                }

                return result;
            }

            catch(Exception e){
                e.printStackTrace();
            }
            return null;
        }

        //This method is a method when is call when "doinBackground" has completed
        @Override
        protected void onPostExecute(String s){
            super.onPostExecute(s);
            try {

                //JSONObject to get the data from the JSON
                JSONObject json = new JSONObject(s);

                //uSE getString to get a particular string
                String one = json.getString("one");

                //JSONArray
                JSONArray array = new JSONArray(one);
                for(int i=0;i<array.length();i++){
                    JSONObject obj = array.getJSONObject(i);
                }

                Log.w("Data", one);
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }
    }
}
