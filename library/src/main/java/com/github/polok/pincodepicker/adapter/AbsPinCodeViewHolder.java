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

import android.support.annotation.IdRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.github.polok.pincodepicker.PinCodeViewListener;

public abstract class AbsPinCodeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    protected PinCodeViewListener codeChangeListener;
    protected EditText etPinCode;

    public AbsPinCodeViewHolder(View itemView, PinCodeViewListener pinCodeViewListener, @IdRes int editTextResId) {
        super(itemView);

        this.codeChangeListener = pinCodeViewListener;

        etPinCode = (EditText) itemView.findViewById(editTextResId);
    }

    @Override
    public void onClick(View view) {
        codeChangeListener.onPinCodeClick(getPosition());
    }
}
