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

package pl.polok.pincodepicker.library;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.GridLayoutManager;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import pl.polok.pincodepicker.library.adapter.PinCodeAdapter;
import pl.polok.pincodepicker.library.view.RecyclerViewInsetDecoration;

public class PinCodeRecyclerView extends android.support.v7.widget.RecyclerView {

    private GridLayoutManager layoutManager;
    private PinCodeAdapter pinCodeAdapter;

    private int pinCodeLength;

    public PinCodeRecyclerView(Context context) {
        this(context, null);
    }

    public PinCodeRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PinCodeRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.PinCodeRecyclerViewWidget);
        pinCodeLength = typedArray.getInt(R.styleable.PinCodeRecyclerViewWidget_pin_code_length, 0);

        setHasFixedSize(true);

        layoutManager = new GridLayoutManager(context, 1);
        layoutManager.setSpanCount(pinCodeLength);

        setLayoutManager(layoutManager);

        pinCodeAdapter = new PinCodeAdapter(pinCodeLength);
        setAdapter(pinCodeAdapter);

        addItemDecoration(new RecyclerViewInsetDecoration(context, R.dimen.pin_code_view_inset_default));
    }

    @Override
    protected void onMeasure(int widthSpec, int heightSpec) {
        super.onMeasure(widthSpec, heightSpec);

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) getLayoutParams();
        params.height = getResources().getDimensionPixelSize(R.dimen.pin_code_height);
        setLayoutParams(params);
    }

    public void setPincodeListener(PinCodeListener pincodeListener) {
        pinCodeAdapter.setPinCodeListener(pincodeListener);
    }

}
