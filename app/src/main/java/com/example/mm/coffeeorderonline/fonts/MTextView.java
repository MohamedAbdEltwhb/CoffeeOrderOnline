package com.example.mm.coffeeorderonline.fonts;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

public class MTextView extends TextView {

    public MTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        this.setTypeface(Typeface.createFromAsset(context.getAssets(), "lemonada-bold.ttf"));
    }
}
