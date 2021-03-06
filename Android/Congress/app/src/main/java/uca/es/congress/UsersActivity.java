package uca.es.congress;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class UsersActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Button btnStart;
    private Button addButton;
    private static ArrayList<Users> users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);
        // Referenciamos al RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        addButton = (Button) findViewById(R.id.Add);
        // Mejoramos rendimiento con esta configuración
        mRecyclerView.setHasFixedSize(true);

        users = new ArrayList<Users>();

        // Creamos un LinearLayoutManager para gestionar el item.xml creado antes
        mLayoutManager = new LinearLayoutManager(this);
        // Lo asociamos al RecyclerView
        mRecyclerView.setLayoutManager(mLayoutManager);

        //mobiles.add(new Mobiles("hola", "hola", 2));

        new LongRunningGetIO().execute();


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FormActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if(id == R.id.see_users)
        {
            Intent intent = new Intent(this, UsersActivity.class);
            startActivity(intent);
        }

        if(id == R.id.program)
        {
            Intent intent = new Intent(this, ProgramActivity.class);
            startActivity(intent);
        }

        if(id == R.id.dates)
        {
            Intent intent = new Intent(this, DatesActivity.class);
            startActivity(intent);
        }

        if(id == R.id.map)
        {
            Intent intent = new Intent(this, LocationActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    public class LongRunningGetIO extends AsyncTask<Void, Void, String> {

        @Override
        protected String doInBackground(Void... params) {
            String text = null;

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url("http://192.168.1.10:8080/users")
                    .build();
            try {
                Response res = client.newCall(request).execute();
                return res.body().string();
            } catch (Exception e) {
                return e.toString();
            }
        }
        //return text

        @Override
        protected void onPostExecute(String results) {
            JSONArray json = null;
            if (results == null) {
            }
            if (results != null) {
                try {
                    json = new JSONArray(results);
                    for (int i = 0; i < json.length(); i++) {
                        users.add(new Users(json.getJSONObject(i).get("firstname").toString(),
                                json.getJSONObject(i).get("lastname").toString(),
                                json.getJSONObject(i).get("DNI").toString(),
                                Integer.parseInt(json.getJSONObject(i).get("telephone").toString()),
                                json.getJSONObject(i).get("email").toString(),
                                json.getJSONObject(i).get("start date").toString(),
                                json.getJSONObject(i).get("finish date").toString(),
                                Integer.parseInt(json.getJSONObject(i).get("Inscription type").toString())));
                    }

                    mAdapter = new UsersAdapter(users);

                    mRecyclerView.setAdapter(mAdapter);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

    }
    }
