package za.co.jaredfishy.susan.handler.status.checker;

import za.co.jaredfishy.susan.domain.susan.SusanService;
import za.co.jaredfishy.susan.task.OnAvailabilityChangedListener;

class AlwaysAvailableServiceStatusChecker extends ServiceStatusChecker {
    public AlwaysAvailableServiceStatusChecker(OnAvailabilityChangedListener onAvailabilityChangedListener) {
        super(SusanService.NONE, onAvailabilityChangedListener);

    }

    @Override
    public void checkAvailability() {
        setAvailability(true);
    }
}
