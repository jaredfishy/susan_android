package za.co.jaredfishy.susan.task;

import za.co.jaredfishy.susan.domain.SusanResponse;
import za.co.jaredfishy.susan.rest.LightService;

public class LightDiscoveryAllTask extends LightTask<SusanResponse> {

    public LightDiscoveryAllTask() {
        super(SusanResponse.class);
    }

    @Override
    protected SusanResponse executeServiceCall(String... string) {
        return execute(LightService.get().discoverAll());
    }
}
