package za.co.jaredfishy.susan.handler.status.checker;

import za.co.jaredfishy.susan.domain.susan.SusanResponse;
import za.co.jaredfishy.susan.domain.susan.SusanService;
import za.co.jaredfishy.susan.task.lights.LightPrepareTask;
import za.co.jaredfishy.susan.task.OnAvailabilityChangedListener;

class LightsServiceStatusChecker extends ServiceStatusChecker {

    public LightsServiceStatusChecker(final OnAvailabilityChangedListener onAvailabilityChangedListener) {
        super(SusanService.LIGHTS, onAvailabilityChangedListener);
    }

    @Override
    public void checkAvailability() {
        LightPrepareTask lightPrepareTask = new LightPrepareTask() {
            @Override
            protected void onPostExecute(SusanResponse susanResponse) {
                setAvailability(true);
            }
        };
        lightPrepareTask.execute();
    }
}
