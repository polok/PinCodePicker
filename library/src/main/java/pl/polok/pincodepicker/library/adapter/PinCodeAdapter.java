/*
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

package pl.polok.pincodepicker.library.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;

import pl.polok.pincodepicker.library.PinCodeViewListener;
import pl.polok.pincodepicker.library.R;

public class PinCodeAdapter extends RecyclerView.Adapter<PinCodeAdapter.PinCodeViewHolder> implements PinCodeViewListener {

    private Character[] pinCodeArray;
    private static int currentPosition = 0;

    public PinCodeAdapter(int pinCodeLength) {
        pinCodeArray = new Character[pinCodeLength];
    }

    @Override
    public PinCodeViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View root = inflater.inflate(R.layout.view_pin_code, viewGroup, false);

        PinCodeViewHolder holder = new PinCodeViewHolder(root, this);
        return holder;
    }

    @Override
    public void onBindViewHolder(PinCodeViewHolder pinCodeViewHolder, int i) {
        Character character = pinCodeArray[i];

        if(i == currentPosition) {
            pinCodeViewHolder.rlPinCodeContainer.setBackgroundResource(R.color.view_selected_pin_code);
            pinCodeViewHolder.rlFront.setVisibility(View.VISIBLE);
            pinCodeViewHolder.rlBack.setVisibility(View.GONE);

            pinCodeViewHolder.etPinCode.setVisibility(View.VISIBLE);
        } else {
            if (character == null) {
                pinCodeViewHolder.rlPinCodeContainer.setBackgroundResource(R.color.view_empty_pin_code_background);
                pinCodeViewHolder.rlFront.setVisibility(View.VISIBLE);
                pinCodeViewHolder.rlBack.setVisibility(View.GONE);
            } else {
                pinCodeViewHolder.rlPinCodeContainer.setBackgroundResource(R.color.view_fillout_pin_code_background);
                pinCodeViewHolder.rlFront.setVisibility(View.GONE);
                pinCodeViewHolder.rlBack.setVisibility(View.VISIBLE);
            }

            pinCodeViewHolder.etPinCode.setVisibility(View.GONE);

        }
    }

    @Override
    public int getItemCount() {
        return pinCodeArray != null ? pinCodeArray.length : 0;
    }

    @Override
    public void onPinCodeChange(int position, Character pinCodeChar) {
        pinCodeArray[position] = pinCodeChar;

        notifyItemChanged(currentPosition);
        ++currentPosition;
        notifyItemChanged(currentPosition);
    }

    @Override
    public void onPinCodeClick(int position) {
        notifyItemChanged(currentPosition);
        notifyItemChanged(position);

        currentPosition = position;
    }

    static class PinCodeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        RelativeLayout rlPinCodeContainer;
        RelativeLayout rlFront;
        RelativeLayout rlBack;
        EditText etPinCode;

        PinCodeViewListener codeChangeListener;

        public PinCodeViewHolder(View itemView, final PinCodeViewListener pinCodeViewListener) {
            super(itemView);

            codeChangeListener = pinCodeViewListener;

            rlPinCodeContainer = (RelativeLayout) itemView.findViewById(R.id.rl_pin_code_container);
            rlFront = (RelativeLayout) itemView.findViewById(R.id.rl_front);
            rlBack = (RelativeLayout) itemView.findViewById(R.id.rl_back);
            etPinCode = (EditText) itemView.findViewById(R.id.et_pin_code);

            etPinCode.addTextChangedListener(new PinCodeTextWatcher() {
                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                    if(charSequence.length() > 0) {
                        pinCodeViewListener.onPinCodeChange(getPosition(), charSequence.charAt(0));
                    }
                }
            });

            etPinCode.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View view, int i, KeyEvent keyEvent) {
                    if(keyEvent.getAction() == 0 && etPinCode.length() == 1) {
                        etPinCode.getText().clear();
                    }
                    return false;
                }
            });

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            codeChangeListener.onPinCodeClick(getPosition());
        }
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
