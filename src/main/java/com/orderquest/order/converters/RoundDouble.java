package com.orderquest.order.converters;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class RoundDouble {

    public double round(double value, int points) {
        BigDecimal bigDecimal = new BigDecimal(value);
        bigDecimal = bigDecimal.setScale(points, RoundingMode.HALF_UP);
        return bigDecimal.doubleValue();
    }
}
