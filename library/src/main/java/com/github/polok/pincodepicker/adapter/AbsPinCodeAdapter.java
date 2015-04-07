package com.github.polok.pincodepicker.adapter;

import android.animation.Animator;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;

import com.github.polok.pincodepicker.PinCodeListener;
import com.github.polok.pincodepicker.PinCodeValidation;
import com.github.polok.pincodepicker.PinCodeViewListener;

public abstract class AbsPinCodeAdapter<T extends AbsPinCodeViewHolder> extends RecyclerView.Adapter<T> implements PinCodeViewListener {

    public static final char NULL_CHARACTER = '\u0000';

    protected static int currentPosition = -1;

    protected PinCodeListener pinCodeListener;
    protected PinCodeValidation pinCodeValidation;

    protected Animator animationCurrent;


    protected char[] pinCodeArray;

    protected AbsPinCodeAdapter(int pinCodeLength) {
        this.pinCodeArray = new char[pinCodeLength];
    }

    protected boolean hasWholeCode() {
        boolean hasWholeCode = true;

        for (char item : pinCodeArray) {
            hasWholeCode = item != NULL_CHARACTER && hasWholeCode;
        }

        return hasWholeCode;
    }

    public void setCurrentPinCodeAnimation(Animator animation) {
        this.animationCurrent = animation;
    }

    public void setPinCodeListener(PinCodeListener listener) {
        this.pinCodeListener = listener;
    }

    public void setPinCodeValidation(PinCodeValidation validation) {
        this.pinCodeValidation = validation;
    }

    public static abstract class PinCodeTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    }

}
