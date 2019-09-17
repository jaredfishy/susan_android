package za.co.jaredfishy.susan.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.widget.LinearLayout;

import za.co.jaredfishy.susan.domain.susan.SusanResponse;
import za.co.jaredfishy.susan.domain.susan.SusanService;
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
    private LinearLayout llServiceAvailability;
    private Callback callback;
    private final ServiceStatusHandler serviceStatusHandler;

    public StatusFragment() {
        super("Status");
        serviceStatusHandler = ServiceStatusHandler.getInstance();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        JZTextView textView = new JZTextView(getActivity());
        textView.setText("Hello :)");
        textView.setGravity(Gravity.CENTER);
        addView(textView);
        addView(new JZTextView(getActivity()));

        llServiceAvailability = new LinearLayout(getActivity());
        llServiceAvailability.setOrientation(LinearLayout.VERTICAL);
        llServiceAvailability.setGravity(Gravity.CENTER);

        addView(llServiceAvailability);
        addView(new JZTextView(getActivity()));

        txtStatus = new JZTextView(getActivity());
        txtStatus.setText("");
        txtStatus.setGravity(Gravity.CENTER);
        addView(txtStatus);
    }

    @Override
    public void onStart() {
        super.onStart();
        serviceStatusHandler.checkAvailableServices(new Callback<SusanResponse>() {
            @Override
            public void done(SusanResponse response) {
                printServiceAvailability();
                txtStatus.setText(response.getMessage());
                if (callback != null)
                    callback.done(null);
            }
        });
    }

    private void setCallback(Callback callback) {
        this.callback = callback;
    }

    private void printServiceAvailability() {

        llServiceAvailability.removeAllViews();

        for (SusanService susanService : SusanService.values()) {
            if (susanService.isVisible()) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(susanService.getDisplayValue());
                stringBuilder.append(" is");
                if (!serviceStatusHandler.isAvailable(susanService))
                    stringBuilder.append(" not available :(");
                else
                    stringBuilder.append(" available");

                JZTextView txtService = new JZTextView(getActivity(), stringBuilder.toString());
                txtService.setGravity(Gravity.CENTER);
                llServiceAvailability.addView(txtService);
            }
        }
    }
}
