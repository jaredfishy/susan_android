package za.co.jaredfishy.susan.domain.susan;

public enum SusanService {
    LIGHTS("Lights Service"),
    NONE(null);

    private String displayValue;

    SusanService(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }

    public boolean isVisible() {
        return displayValue != null;
    }
}
