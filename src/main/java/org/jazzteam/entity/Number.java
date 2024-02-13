package org.jazzteam.entity;

import lombok.Data;
import org.jazzteam.FullNumberStringFormat;

@Data
public class Number {
    private long numericalFormat;
    private String stringFormat;

    public Number(long numericalFormat) {
        this.numericalFormat = numericalFormat;
        this.stringFormat = createFullNumberStringFormat();
    }

    public String createFullNumberStringFormat() {
        return new FullNumberStringFormat().create(numericalFormat);
    }
}
