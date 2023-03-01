package com.menumer.model.enums;

import org.apache.commons.lang3.EnumUtils;

public enum QuantityUnit {
    TEA_SPOON("Tea Spoon"), TABLE_SPOON("Table Spoon"), CUP("Cup");
    private final String quantityUnit;

    QuantityUnit(String quantityUnit) {
        this.quantityUnit = quantityUnit;
    }

    public static QuantityUnit getQuantityUnit(String unit) {
        return EnumUtils.getEnumIgnoreCase(QuantityUnit.class, unit);
    }
}
