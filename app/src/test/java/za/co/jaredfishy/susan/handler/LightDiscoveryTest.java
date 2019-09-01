package za.co.jaredfishy.susan.handler;

import org.junit.Test;

import java.util.Map;

import za.co.jaredfishy.susan.domain.Light;
import za.co.jaredfishy.susan.task.LightDiscoveryTask;

public class LightDiscoveryTest {

    @Test
    public void doInBackground() {

        LightDiscoveryTask bob = new LightDiscoveryTask(){
            @Override
            protected void onPostExecute(Map<String, Light> stringLightMap) {
                System.out.println("got it!");
            }
        };
        bob.execute();

    }
}