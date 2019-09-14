package za.co.jaredfishy.susan.ui;

public enum JZMenuItem {

    STATUS(1, "Status",1, true),
    LIGHTS(2, "Lights",1, false),
    SETTINGS(3, "Settings",2, true);

    private int resourceId;
    private String text;
    private int group;
    private boolean available;

    JZMenuItem(int resourceId, String text, int group, boolean available) {
        this.resourceId = resourceId;
        this.text = text;
        this.group = group;
        this.available = available;
    }

    public static JZMenuItem fromResourceId(int resourceId) {
        for (JZMenuItem menuItem : JZMenuItem.values())
            if (menuItem.getResourceId() == resourceId)
                return menuItem;
        return null;
    }

    public int getResourceId() {
        return resourceId;
    }

    public String getText() {
        return text;
    }

    public int getGroup() {
        return group;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}
