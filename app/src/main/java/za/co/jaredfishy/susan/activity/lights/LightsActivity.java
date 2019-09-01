package za.co.jaredfishy.susan.activity.lights;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.Map;

import za.co.jaredfishy.susan.R;
import za.co.jaredfishy.susan.activity.BaseActivity;
import za.co.jaredfishy.susan.components.LightHandlerAdapter;
import za.co.jaredfishy.susan.domain.Light;
import za.co.jaredfishy.susan.handler.LightHandler;
import za.co.jaredfishy.susan.task.LightDiscoveryTask;

public class LightsActivity extends BaseActivity {

    private LightHandler lightHandler;
    private LightHandlerAdapter lightHandlerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lights);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnAddClick();
            }
        });

        lightHandler = new LightHandler();
        lightHandlerAdapter = new LightHandlerAdapter(lightHandler, new LightHandlerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Light light) {
                toggleLight(light);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.listview);
        recyclerView.setAdapter(lightHandlerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
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

    private void btnAddClick() {
        LightDiscoveryTask discovery = new LightDiscoveryTask() {
            @Override
            protected void onPostExecute(Map<String, Light> lights) {
                if (lights != null) {
                    for (Light light : lights.values()) {
                        lightHandler.add(light);
                    }
                    lightHandlerAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(LightsActivity.this, "No lights found", Toast.LENGTH_SHORT).show();
                }
            }
        };
        discovery.execute("");
    }

    private void toggleLight(Light light) {
        Toast.makeText(LightsActivity.this, "Coming soon!", Toast.LENGTH_SHORT).show();
        // TODO: implement POST support
    }
}
