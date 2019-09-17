package za.co.jaredfishy.susan.task.susan;

import za.co.jaredfishy.susan.domain.susan.SusanResponse;
import za.co.jaredfishy.susan.rest.LightService;
import za.co.jaredfishy.susan.task.JZTask;

public abstract class SusanPokeTask extends JZTask<SusanResponse> {

    public SusanPokeTask() {
        super(SusanResponse.class, LightService.get().poke());
    }
}
