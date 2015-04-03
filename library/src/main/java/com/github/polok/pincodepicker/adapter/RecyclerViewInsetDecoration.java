/**
 * Copyright 2015 Marcin Polak
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.polok.pincodepicker.adapter;

import android.content.res.Resources;
import android.graphics.Rect;
import android.support.annotation.DimenRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class RecyclerViewInsetDecoration extends RecyclerView.ItemDecoration {

    private int insetLeft;
    private int insetRight;
    private int insetTop;
    private int insetBottom;

    public RecyclerViewInsetDecoration(Resources resources, @DimenRes int inset) {
        this.insetLeft = resources.getDimensionPixelSize(inset);
        this.insetRight = resources.getDimensionPixelSize(inset);
        this.insetTop = resources.getDimensionPixelSize(inset);
        this.insetBottom = resources.getDimensionPixelSize(inset);
    }

    public RecyclerViewInsetDecoration(Resources resources, @DimenRes int insetLeft,
                                       @DimenRes int insetTop, @DimenRes int insetRight,
                                       @DimenRes int insetBottom) {
        this.insetLeft = resources.getDimensionPixelSize(insetLeft);
        this.insetRight = resources.getDimensionPixelSize(insetRight);
        this.insetTop = resources.getDimensionPixelSize(insetTop);
        this.insetBottom = resources.getDimensionPixelSize(insetBottom);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.set(insetLeft, insetTop, insetRight, insetBottom);
    }
}