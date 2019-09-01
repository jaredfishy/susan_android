package za.co.jaredfishy.susan.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import za.co.jaredfishy.susan.R;
import za.co.jaredfishy.susan.activity.lights.LightsActivity;
import za.co.jaredfishy.susan.domain.PokeResponse;
import za.co.jaredfishy.susan.task.LightPokeTask;

public class MainActivity extends BaseActivity {

    private TextView txtStatus;
    private Button btnLights;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtStatus = findViewById(R.id.main_status);
        btnLights = findViewById(R.id.button_lights);
        btnLights.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LightsActivity.class);
                startActivity(intent);
            }
        });
        btnLights.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        LightPokeTask pokeTask = new LightPokeTask() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                txtStatus.setText("...");
            }

            @Override
            protected void onPostExecute(PokeResponse pokeResponse) {
                txtStatus.setText("Welcome :)");
                if (pokeResponse != null) {
                    btnLights.setVisibility(View.VISIBLE);
                } else {
                    Toast.makeText(MainActivity.this, "Server is unreachable", Toast.LENGTH_SHORT).show();
                }
            }
        };
        pokeTask.execute();

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

        return super.onOptionsItemSelected(item);
    }
}
