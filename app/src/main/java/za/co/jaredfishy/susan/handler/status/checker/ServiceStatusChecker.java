package za.co.jaredfishy.susan.handler.status.checker;

import za.co.jaredfishy.susan.domain.susan.SusanService;
import za.co.jaredfishy.susan.task.OnAvailabilityChangedListener;

public abstract class ServiceStatusChecker {

    protected final SusanService service;
    private final OnAvailabilityChangedListener onAvailabilityChangedListener;

    protected ServiceStatusChecker(SusanService service, OnAvailabilityChangedListener onAvailabilityChangedListener) {
        this.service = service;
        this.onAvailabilityChangedListener = onAvailabilityChangedListener;
    }

    protected void setAvailability(boolean available) {
        onAvailabilityChangedListener.onAvailabilityChanged(service, available);
    }

    public abstract void checkAvailability();
}
