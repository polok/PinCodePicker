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

package com.github.polok.pincodepicker.view;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.DimenRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;


public class RecyclerViewInsetDecoration extends RecyclerView.ItemDecoration {

    private int insetLeft;
    private int insetRight;
    private int insetTop;
    private int insetBottom;

    public RecyclerViewInsetDecoration(Context context, @DimenRes int inset) {
        this.insetLeft = context.getResources().getDimensionPixelSize(inset);
        this.insetRight = context.getResources().getDimensionPixelSize(inset);
        this.insetTop = context.getResources().getDimensionPixelSize(inset);
        this.insetBottom = context.getResources().getDimensionPixelSize(inset);
    }

    public RecyclerViewInsetDecoration(Context context, @DimenRes int insetLeft, @DimenRes int insetTop, @DimenRes int insetRight, @DimenRes int insetBottom) {
        this.insetLeft = context.getResources().getDimensionPixelSize(insetLeft);
        this.insetRight = context.getResources().getDimensionPixelSize(insetRight);
        this.insetTop = context.getResources().getDimensionPixelSize(insetTop);
        this.insetBottom = context.getResources().getDimensionPixelSize(insetBottom);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(insetLeft, insetTop, insetRight, insetBottom);
    }
}