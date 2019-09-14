package za.co.jaredfishy.susan.activity.lights;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import za.co.jaredfishy.susan.R;
import za.co.jaredfishy.susan.activity.BaseActivity;
import za.co.jaredfishy.susan.domain.SusanResponse;
import za.co.jaredfishy.susan.task.LightDiscoveryAllTask;
import za.co.jaredfishy.susan.task.LightOffTask;
import za.co.jaredfishy.susan.task.LightOnTask;
import za.co.jaredfishy.susan.task.LightPrepareTask;

public class LightsActivity extends BaseActivity {

    private Button btnLightsOn;
    private Button btnLightsOff;
    private Button btnDiscoverAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lights);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        btnLightsOn = findViewById(R.id.button_lights_on);
        btnLightsOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LightOnTask task = new LightOnTask() {
                    @Override
                    protected void onPostExecute(SusanResponse susanResponse) {
                        Toast.makeText(LightsActivity.this, susanResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                };
                task.execute();
            }
        });

        btnLightsOff = findViewById(R.id.button_lights_off);
        btnLightsOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LightOffTask task = new LightOffTask() {
                    @Override
                    protected void onPostExecute(SusanResponse susanResponse) {
                        Toast.makeText(LightsActivity.this, susanResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                };
                task.execute();

            }
        });

        btnDiscoverAll = findViewById(R.id.button_lights_discover_all);
        btnDiscoverAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LightDiscoveryAllTask task = new LightDiscoveryAllTask() {
                    @Override
                    protected void onPostExecute(SusanResponse susanResponse) {
                        Toast.makeText(LightsActivity.this, susanResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        prepare();
                    }
                };
                task.execute();

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
    protected void onResume() {
        super.onResume();
        prepare();
    }

    private void prepare(){
        LightPrepareTask task = new LightPrepareTask() {
            @Override
            protected void onPostExecute(SusanResponse susanResponse) {
                Toast.makeText(LightsActivity.this, susanResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };
        task.execute();
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
