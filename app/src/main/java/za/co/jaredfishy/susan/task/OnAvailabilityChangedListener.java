package za.co.jaredfishy.susan.task;

import za.co.jaredfishy.susan.domain.susan.SusanService;

public interface OnAvailabilityChangedListener {
    void onAvailabilityChanged(SusanService service, boolean available);
}
