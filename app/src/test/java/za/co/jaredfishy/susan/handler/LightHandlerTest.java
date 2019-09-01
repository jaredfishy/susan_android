package za.co.jaredfishy.susan.handler;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import za.co.jaredfishy.susan.domain.Light;
import za.co.jaredfishy.susan.util.DummyLightUtil;

import static com.shazam.shazamcrest.matcher.Matchers.sameBeanAs;

public class LightHandlerTest {

    private LightHandler lightHandler;

    @Before
    public void setUp() throws Exception {
        lightHandler = new LightHandler();
        lightHandler.add(DummyLightUtil.getLight("0"));
    }

    @Test
    public void addLight() {
        lightHandler.add(DummyLightUtil.getLight("1"));

        Light actual = lightHandler.get("1");
        Light expected = DummyLightUtil.getLight("1");
        Assert.assertThat(expected, sameBeanAs(actual));
    }
    @Test
    public void addLightOverwrite() {
        Assert.assertEquals(1, lightHandler.size());
        lightHandler.add(DummyLightUtil.getLight("1"));
        Assert.assertEquals(2, lightHandler.size());
        lightHandler.add(DummyLightUtil.getLight("1"));
        Assert.assertEquals(2, lightHandler.size());

        Light actual = lightHandler.get("1");
        Light expected = DummyLightUtil.getLight("1");
        Assert.assertThat(expected, sameBeanAs(actual));
    }
    @Test
    public void addLightNull() {
        Assert.assertEquals(1, lightHandler.size());
        lightHandler.add(null);
        Assert.assertEquals(1, lightHandler.size());
    }

    @Test
    public void size() {
        Assert.assertEquals(1, lightHandler.size());
        lightHandler.add(DummyLightUtil.getLight("1"));
        Assert.assertEquals(2, lightHandler.size());
    }

    @Test
    public void getById() {
        Light actual = lightHandler.get("0");
        Light expected = DummyLightUtil.getLight("0");
        Assert.assertThat(expected, sameBeanAs(actual));
    }

    @Test
    public void getByIndex() {
        Light actual = lightHandler.get(0);
        Light expected = DummyLightUtil.getLight("0");
        Assert.assertThat(expected, sameBeanAs(actual));
    }
}