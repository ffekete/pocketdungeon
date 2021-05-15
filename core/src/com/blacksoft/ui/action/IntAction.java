package com.blacksoft.ui.action;

import com.badlogic.gdx.scenes.scene2d.actions.TemporalAction;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 * An action that has an int, whose value is transitioned over time.
 *
 * @author Nathan Sweet
 */
public class IntAction extends TemporalAction {
    private int start;
    private int end;
    private int value;
    private Label label;
    private String template;
    private Integer otherValue;

    /**
     * Creates an IntAction that transitions from start to end.
     */
    public IntAction(int start, int end, float duration, Label label, String template, Integer otherValue) {
        super(duration);
        this.start = start;
        this.end = end;
        this.label = label;
        this.template = template;
        this.otherValue = otherValue;
    }

    protected void begin() {
        value = start;
    }

    protected void update(float percent) {

        if(value == end) {
            setDuration(0f);
        }

        if (percent == 0)
            value = start;
        else if (percent == 1)
            value = end;
        else
            value = (int) (start + (end - start) * percent);

        if(otherValue != null) {
            label.setText(String.format(template, value, otherValue));
        } else {
            label.setText(String.format(template, value));
        }
    }

    /**
     * Gets the current int value.
     */
    public int getValue() {
        return value;
    }

    /**
     * Sets the current int value.
     */
    public void setValue(int value) {
        this.value = value;
    }
}
