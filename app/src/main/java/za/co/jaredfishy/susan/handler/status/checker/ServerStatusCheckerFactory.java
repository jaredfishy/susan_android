package za.co.jaredfishy.susan.handler.status.checker;

import za.co.jaredfishy.susan.domain.susan.SusanService;
import za.co.jaredfishy.susan.task.OnAvailabilityChangedListener;

public class ServerStatusCheckerFactory {

    public ServiceStatusChecker get(SusanService service, OnAvailabilityChangedListener onAvailabilityChangedListener) {
        switch (service) {
            case LIGHTS:
                return new LightsServiceStatusChecker(onAvailabilityChangedListener);
            case NONE:
                return new AlwaysAvailableServiceStatusChecker(onAvailabilityChangedListener);
        }
        return null;
    }

}
