package za.co.jaredfishy.susan.task.lights;

import za.co.jaredfishy.susan.domain.susan.SusanResponse;
import za.co.jaredfishy.susan.rest.LightService;
import za.co.jaredfishy.susan.task.JZTask;

public abstract class LightOffTask extends JZTask<SusanResponse> {

    public LightOffTask() {
        super(SusanResponse.class, LightService.get().turnOff());
    }
}
