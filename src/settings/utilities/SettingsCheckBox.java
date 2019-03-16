package settings.utilities;

/**
 * 
 * Enumeration that contains the standard text of the CheckBoxes of the settings.
 *
 */
public enum SettingsCheckBox {

    /**
     * standard checked CheckBox text.
     */
    CHECKED("ON"),

    /**
     * standard unchecked CheckBox text.
     */
    UNCHECKED("OFF");

    private final String checkBoxText;

    SettingsCheckBox(final String checkBoxText) {
        this.checkBoxText = checkBoxText;
    }

    /**
     * 
     * @return 
     *          the CheckBox text that must display.
     */
    public String getText() {
        return this.checkBoxText;
    }
}
