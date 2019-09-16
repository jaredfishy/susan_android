package za.co.jaredfishy.susan.activity.fragments;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import za.co.jaredfishy.susan.domain.SusanResponse;
import za.co.jaredfishy.susan.task.LightDiscoveryAllTask;
import za.co.jaredfishy.susan.task.LightOffTask;
import za.co.jaredfishy.susan.task.LightOnTask;
import za.co.jaredfishy.susan.task.LightPrepareTask;
import za.co.jaredfishy.susan.ui.JZButton;

public class LightsFragment extends BaseFragment {

    public static LightsFragment newInstance(){
        return new LightsFragment();
    }


    private JZButton btnLightsOn;
    private JZButton btnLightsOff;
    private JZButton btnDiscoverAll;

    private boolean prepared = false;

    public LightsFragment() {
        super("Lights");
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        btnLightsOn = new JZButton(getActivity(), "Lights On");
        btnLightsOn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LightOnTask task = new LightOnTask() {
                    @Override
                    protected void onPostExecute(SusanResponse susanResponse) {
                        Toast.makeText(getActivity(), susanResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                };
                task.execute();
            }
        });

        btnLightsOff = new JZButton(context, "Lights Off");
        btnLightsOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LightOffTask task = new LightOffTask() {
                    @Override
                    protected void onPostExecute(SusanResponse susanResponse) {
                        Toast.makeText(getActivity(), susanResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                };
                task.execute();

            }
        });

        btnDiscoverAll = new JZButton(getActivity(), "Discover All");
        btnDiscoverAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LightDiscoveryAllTask task = new LightDiscoveryAllTask() {
                    @Override
                    protected void onPostExecute(SusanResponse susanResponse) {
                        Toast.makeText(getActivity(), susanResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        prepare();
                    }
                };
                task.execute();

            }
        });

        llRoot.addView(btnLightsOn);
        llRoot.addView(btnLightsOff);
        llRoot.addView(btnDiscoverAll);

    }

    @Override
    public void onResume() {
        super.onResume();
        if(!prepared) {
            prepare();
            prepared = true;
        }
    }


    private void prepare() {
        LightPrepareTask task = new LightPrepareTask() {
            @Override
            protected void onPostExecute(SusanResponse susanResponse) {
                Toast.makeText(getActivity(), susanResponse.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };
        task.execute();
    }


}
