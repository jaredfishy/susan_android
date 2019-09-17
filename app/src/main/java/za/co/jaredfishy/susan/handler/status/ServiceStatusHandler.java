package za.co.jaredfishy.susan.handler.status;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import za.co.jaredfishy.susan.domain.susan.SusanResponse;
import za.co.jaredfishy.susan.domain.susan.SusanService;
import za.co.jaredfishy.susan.handler.status.checker.ServerStatusCheckerFactory;
import za.co.jaredfishy.susan.handler.status.checker.ServiceStatusChecker;
import za.co.jaredfishy.susan.task.Callback;
import za.co.jaredfishy.susan.task.OnAvailabilityChangedListener;
import za.co.jaredfishy.susan.util.TimestampUtil;

public class ServiceStatusHandler {

    private static ServiceStatusHandler instance = null;

    public static ServiceStatusHandler getInstance() {
        if (instance == null)
            instance = new ServiceStatusHandler();
        return instance;
    }

    private ServerStatusCheckerFactory factory;
    private Map<SusanService, Boolean> serviceAvailability;

    private ServiceStatusHandler() {
        this.serviceAvailability = new HashMap<>();
        this.factory = new ServerStatusCheckerFactory();
        init();
    }

    private void init() {
        for (SusanService service : SusanService.values())
            serviceAvailability.put(service, false);
    }

    public boolean isAvailable(SusanService service) {
        if (service == SusanService.NONE)
            return true;
        else
            return serviceAvailability.get(service);
    }

    public void checkAvailableServices(final Callback<SusanResponse> callback) {

        final List<SusanService> checkProgress = new ArrayList<>();
        final OnAvailabilityChangedListener onAvailabilityChangedListener = new OnAvailabilityChangedListener() {
            @Override
            public void onAvailabilityChanged(SusanService service, boolean available) {
                if (available)
                    System.out.println(service + " is available");
                else
                    System.out.println(service + " is unavailable");
                serviceAvailability.put(service, available);
                checkProgress.remove(service);

                if (checkProgress.size() == 0 && callback != null)
                    callback.done(new SusanResponse("Everything looks to be working :)", TimestampUtil.getTimestampAsString()));
            }
        };

        for (SusanService service : SusanService.values()) {
            ServiceStatusChecker checker = factory.get(service, onAvailabilityChangedListener);
            if (checker != null) {
                checkProgress.add(service);
                checker.checkAvailability();
            }
        }

    }
}
