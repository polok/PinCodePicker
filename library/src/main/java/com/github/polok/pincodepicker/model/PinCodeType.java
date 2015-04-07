package com.github.polok.pincodepicker.model;

import android.text.InputType;

public enum PinCodeType {

    NUMBER(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD),
    TEXT(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);

    private int inputType;

    private PinCodeType(int inputType) {
        this.inputType = inputType;
    }

    public int getInputType() {
        return inputType;
    }

    public static PinCodeType typeFromName(String pincodeType) {
        if (pincodeType == null || pincodeType.isEmpty()) {
            return NUMBER;
        }

        for (PinCodeType type : values()) {
            if (type.name().equalsIgnoreCase(pincodeType)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Not supported pin code type");
    }

}