package za.co.jaredfishy.susan.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;

import za.co.jaredfishy.susan.domain.susan.SusanResponse;
import za.co.jaredfishy.susan.handler.status.ServiceStatusHandler;
import za.co.jaredfishy.susan.task.Callback;
import za.co.jaredfishy.susan.ui.view.JZTextView;

public class StatusFragment extends BaseFragment {

    public static StatusFragment newInstance(Callback callback) {
        StatusFragment fragment = new StatusFragment();
        fragment.setCallback(callback);
        return fragment;
    }

    private JZTextView txtStatus;
    private Callback callback;

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
        ServiceStatusHandler statusHandler = ServiceStatusHandler.getInstance();
        statusHandler.checkAvailableServices(new Callback<SusanResponse>() {
            @Override
            public void done(SusanResponse response) {
                txtStatus.setText(response.getMessage());
                if (callback != null)
                    callback.done(null);
            }
        });
    }

    private void setCallback(Callback callback) {
        this.callback = callback;
    }
}
