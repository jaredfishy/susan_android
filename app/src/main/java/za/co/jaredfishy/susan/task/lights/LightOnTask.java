package za.co.jaredfishy.susan.task.lights;

import za.co.jaredfishy.susan.domain.susan.SusanResponse;
import za.co.jaredfishy.susan.rest.LightService;
import za.co.jaredfishy.susan.task.JZTask;

public abstract class LightOnTask extends JZTask<SusanResponse> {
    public LightOnTask() {
        super(SusanResponse.class, LightService.get().turnOn());
    }
}
