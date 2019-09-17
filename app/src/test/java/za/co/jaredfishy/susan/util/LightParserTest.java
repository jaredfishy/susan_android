package za.co.jaredfishy.susan.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

import za.co.jaredfishy.susan.domain.lights.Light;

public class LightParserTest {

    @Test
    public void parse() {

        String json = "{\n" +
                "        \"id\": \"0x0000000007f15dfb\",\n" +
                "        \"location\": {\n" +
                "            \"ip\": \"192.168.0.101\"\n" +
                "        }\n" +
                "    }";

        Light light = LightParser.parse(json);
        Assert.assertEquals("0x0000000007f15dfb", light.getId());
        Assert.assertEquals("192.168.0.101", light.getIp());

    }

    @Test
    public void parseList() {

        String json = "{\"0x0000000007f15dfb\":{\"id\":\"0x0000000007f15dfb\",\"location\":{\"ip\":\"192.168.0.101\"}},\"0x0000000007f16b5c\":{\"id\":\"0x0000000007f16b5c\",\"location\":{\"ip\":\"192.168.0.103\"}},\"0x0000000007eb6498\":{\"id\":\"0x0000000007eb6498\",\"location\":{\"ip\":\"192.168.0.100\"}}}\n";

        Map<String, Light> lightMap = LightParser.parseMap(json);
        Assert.assertEquals(3, lightMap.size());
        {
            Light light = lightMap.get("0x0000000007f15dfb");
            Assert.assertEquals("0x0000000007f15dfb", light.getId());
            Assert.assertEquals("192.168.0.101", light.getIp());
        }

        {
            Light light = lightMap.get("0x0000000007f16b5c");
            Assert.assertEquals("0x0000000007f16b5c", light.getId());
            Assert.assertEquals("192.168.0.103", light.getIp());
        }

        {
            Light light = lightMap.get("0x0000000007eb6498");
            Assert.assertEquals("0x0000000007eb6498", light.getId());
            Assert.assertEquals("192.168.0.100", light.getIp());
        }

    }
}