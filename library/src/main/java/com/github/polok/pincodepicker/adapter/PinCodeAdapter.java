/**
 * Copyright 2015 Marcin Polak
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.polok.pincodepicker.adapter;

import android.content.res.Resources;
import android.support.annotation.ColorRes;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.github.polok.pincodepicker.PinCodeListener;
import com.github.polok.pincodepicker.PinCodeValidation;
import com.github.polok.pincodepicker.PinCodeViewListener;
import com.github.polok.pincodepicker.R;

public class PinCodeAdapter extends AbsPinCodeAdapter<PinCodeAdapter.PinCodeViewHolder> implements PinCodeViewListener {

    private Resources resources;

    private PinCodeListener pinCodeListener;
    private PinCodeValidation pinCodeValidation;

    public PinCodeAdapter(Resources resources, int pinCodeLength) {
        super(pinCodeLength);
        this.resources = resources;
    }

    @Override
    public PinCodeViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View root = inflater.inflate(R.layout.view_pin_code, viewGroup, false);
        PinCodeViewHolder holder = new PinCodeViewHolder(root, this);
        return holder;
    }

    @Override
    public void onBindViewHolder(PinCodeViewHolder pinCodeViewHolder, int position) {
        Character character = pinCodeArray[position];

        if (position == currentPosition) {
            pinCodeViewHolder.etPinCode.setVisibility(View.VISIBLE);
            pinCodeViewHolder.etPinCode.requestFocus();
            showFrontView(pinCodeViewHolder, R.color.view_selected_pin_code);
            return;
        }

        if (character == NULL_CHARACTER) {
            showFrontView(pinCodeViewHolder, R.color.view_empty_pin_code_background);
        } else {
            showBackView(pinCodeViewHolder);
        }

        pinCodeViewHolder.etPinCode.setVisibility(View.GONE);
    }

    private void showBackView(PinCodeViewHolder pinCodeViewHolder) {
        pinCodeViewHolder.rlPinCodeContainer.setBackgroundColor(resources.getColor(R.color.view_fillout_pin_code_background));
        pinCodeViewHolder.rlFront.setVisibility(View.GONE);
        pinCodeViewHolder.rlBack.setVisibility(View.VISIBLE);
    }

    private void showFrontView(PinCodeViewHolder pinCodeViewHolder, @ColorRes int backgroundColorRes) {
        pinCodeViewHolder.rlPinCodeContainer.setBackgroundColor(resources.getColor(backgroundColorRes));
        pinCodeViewHolder.rlFront.setVisibility(View.VISIBLE);
        pinCodeViewHolder.rlBack.setVisibility(View.GONE);
    }

    @Override
    public int getItemCount() {
        return pinCodeArray != null ? pinCodeArray.length : 0;
    }

    @Override
    public void onPinCodeChange(int position, Character pinCodeChar) {
        pinCodeArray[position] = pinCodeChar;

        notifyItemChanged(currentPosition);
        currentPosition = position + 1;
        notifyItemChanged(currentPosition);

        if (hasWholeCode()) {
            String pinCode = String.copyValueOf(pinCodeArray);
            pinCodeListener.onPinCodeInserted(pinCode);

            if(pinCodeValidation != null) {
                validatePinCode(pinCode);
            }
        }
    }

    private void validatePinCode(String pinCode) {
        if(pinCodeValidation.getCorrectPinCode().equals(pinCode)) {
            pinCodeValidation.onPinCodeCorrect(pinCode);
        } else {
            pinCodeValidation.onPinCodeError(pinCode);
        }
    }

    private boolean hasWholeCode() {
        boolean hasWholeCode = true;

        for (char item : pinCodeArray) {
            hasWholeCode = item != NULL_CHARACTER && hasWholeCode;
        }

        return hasWholeCode;
    }

    @Override
    public void onPinCodeClick(int position) {
        notifyItemChanged(currentPosition);
        notifyItemChanged(position);
        currentPosition = position;
    }

    static class PinCodeViewHolder extends AbsPinCodeViewHolder implements View.OnClickListener {

        RelativeLayout rlPinCodeContainer;
        RelativeLayout rlFront;
        RelativeLayout rlBack;

        public PinCodeViewHolder(View itemView, final PinCodeViewListener pinCodeViewListener) {
            super(itemView, pinCodeViewListener, R.id.et_pin_code);

            rlPinCodeContainer = (RelativeLayout) itemView.findViewById(R.id.rl_pin_code_container);
            rlFront = (RelativeLayout) itemView.findViewById(R.id.rl_front);
            rlBack = (RelativeLayout) itemView.findViewById(R.id.rl_back);

            etPinCode.addTextChangedListener(new PinCodeTextWatcher() {
                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    if (charSequence.length() > 0) {
                        pinCodeViewListener.onPinCodeChange(getPosition(), charSequence.charAt(0));
                    }
                }
            });

            itemView.setOnClickListener(this);
        }
    }

    public void setPinCodeListener(PinCodeListener listener) {
        this.pinCodeListener = listener;
    }

    public void setPinCodeValidation(PinCodeValidation validation) {
        this.pinCodeValidation = validation;
    }

    private static abstract class PinCodeTextWatcher implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override
        public void afterTextChanged(Editable editable) {
        }
    }
}
