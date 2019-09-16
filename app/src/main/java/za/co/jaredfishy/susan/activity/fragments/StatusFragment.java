package za.co.jaredfishy.susan.activity.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;

import za.co.jaredfishy.susan.domain.SusanResponse;
import za.co.jaredfishy.susan.task.LightPokeTask;
import za.co.jaredfishy.susan.ui.JZMenuItem;
import za.co.jaredfishy.susan.ui.JZTextView;

public class StatusFragment extends BaseFragment {

    public static StatusFragment newInstance(OnMenuItemAvailabilityChangeListener changeListener){
        StatusFragment statusFragment = new StatusFragment();
        statusFragment.setOnMenuItemAvailabilityChangeListener(changeListener);
        return statusFragment;
    }

    public static abstract class OnMenuItemAvailabilityChangeListener {
        public abstract void availabilityChanged(JZMenuItem menuItem);
    }

    private JZTextView txtStatus;
    private OnMenuItemAvailabilityChangeListener listener;

    public StatusFragment() {
        super("Status");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        JZTextView textView = new JZTextView(getActivity());
        textView.setText("Hello :)");
        textView.setGravity(Gravity.CENTER);
        llRoot.addView(textView);
        llRoot.addView(new JZTextView(getActivity()));

        txtStatus = new JZTextView(getActivity());
        txtStatus.setText("");
        txtStatus.setGravity(Gravity.CENTER);
        llRoot.addView(txtStatus);
    }

    @Override
    public void onStart() {
        super.onStart();
        poke();
    }

    private void poke() {
        LightPokeTask pokeTask = new LightPokeTask() {

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                txtStatus.setText("Checking if the server is available...");
            }

            @Override
            protected void onPostExecute(SusanResponse pokeResponse) {
                if (pokeResponse != null) {
                    JZMenuItem.LIGHTS.setAvailable(true);
                    txtStatus.setText("Everything looks to be working.");
                } else {
                    JZMenuItem.LIGHTS.setAvailable(false);
                    txtStatus.setText("Server is unavailable :(");
                }
                if (listener != null)
                    listener.availabilityChanged(JZMenuItem.LIGHTS);
            }
        };
        pokeTask.execute();
    }

    public void setOnMenuItemAvailabilityChangeListener(OnMenuItemAvailabilityChangeListener listener) {
        this.listener = listener;
    }
}
