package za.co.jaredfishy.susan.handler.status.checker;

import za.co.jaredfishy.susan.domain.susan.SusanResponse;
import za.co.jaredfishy.susan.domain.susan.SusanService;
import za.co.jaredfishy.susan.task.OnAvailabilityChangedListener;
import za.co.jaredfishy.susan.task.lights.LightPrepareTask;

class LightsServiceStatusChecker extends ServiceStatusChecker {

    public LightsServiceStatusChecker(final OnAvailabilityChangedListener onAvailabilityChangedListener) {
        super(SusanService.LIGHTS, onAvailabilityChangedListener);
    }

    @Override
    public void checkAvailability() {
        LightPrepareTask lightPrepareTask = new LightPrepareTask() {
            @Override
            protected void onSuccess(SusanResponse response) {
                setAvailability(true);
            }

            @Override
            protected void onFail(String response) {
                setAvailability(false);
            }
        };
        lightPrepareTask.execute();
    }
}
