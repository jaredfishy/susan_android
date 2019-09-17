package za.co.jaredfishy.susan.task.lights;

import za.co.jaredfishy.susan.domain.susan.SusanResponse;
import za.co.jaredfishy.susan.rest.LightService;
import za.co.jaredfishy.susan.task.JZTask;

public abstract class LightPrepareTask extends JZTask<SusanResponse> {

    public LightPrepareTask() {
        super(SusanResponse.class, LightService.get().prepare());
    }
}
