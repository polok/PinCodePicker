package com.github.polok.pincodepicker.adapter;

import android.support.v7.widget.RecyclerView;

import com.github.polok.pincodepicker.PinCodeViewListener;

public abstract class AbsPinCodeAdapter<T extends AbsPinCodeViewHolder> extends RecyclerView.Adapter<T> implements PinCodeViewListener {

    public static final char NULL_CHARACTER = '\u0000';
    protected static int currentPosition = -1;
    protected char[] pinCodeArray;

    protected AbsPinCodeAdapter(int pinCodeLength) {
        this.pinCodeArray = new char[pinCodeLength];
    }
}
