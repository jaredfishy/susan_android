package za.co.jaredfishy.susan.domain;

import za.co.jaredfishy.susan.R;
import za.co.jaredfishy.susan.domain.susan.SusanService;

public enum JZMenuItem {

    HOME(1, "Home", R.drawable.ic_service_home_32dp, 1, SusanService.NONE),
    STATUS(2, "Status", R.drawable.ic_service_status_32dp, 1, SusanService.NONE),
    LIGHTS(3, "Lights", R.drawable.ic_service_lights_32dp, 1, SusanService.LIGHTS),
    SETTINGS(4, "Settings", 0, 2, SusanService.NONE),
    TEST(5, "Test", 0, 2, SusanService.NONE);

    private int id;
    private String text;
    private int resourceId;
    private int group;
    private SusanService service;

    JZMenuItem(int id, String text, int resourceId, int group, SusanService service) {
        this.id = id;
        this.text = text;
        this.resourceId = resourceId;
        this.group = group;
        this.service = service;
    }

    public static JZMenuItem fromResourceId(int resourceId) {
        for (JZMenuItem menuItem : JZMenuItem.values())
            if (menuItem.getId() == resourceId)
                return menuItem;
        return null;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public int getResourceId() {
        return resourceId;
    }

    public int getGroup() {
        return group;
    }

    public SusanService getService() {
        return service;
    }
}
