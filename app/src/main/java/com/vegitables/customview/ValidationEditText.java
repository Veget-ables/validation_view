package com.vegitables.customview;

import android.content.Context;
import android.graphics.Rect;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;

public class ValidationEditText extends AppCompatEditText implements TextWatcher{
    private final static String ERROR_MESSAGE_REQUIRED = "必須です";
    private TextInputLayout tiLayout;

    public ValidationEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        addTextChangedListener(this);
    }

    public boolean isEmpty() {
        return getText().toString().isEmpty();
    }

    private void setupParentLayout() {
        tiLayout = (TextInputLayout) getParent().getParent();
    }

    private void resetValidation() { // validationチェックの前に前回の警告をリセット
        if (tiLayout == null) {
            setupParentLayout();
        }
        tiLayout.setErrorEnabled(false);
        tiLayout.setError(null);
    }

    public void checkValidation() {
        if (tiLayout == null) {
            setupParentLayout();
        }
        if (isEmpty()) {
            tiLayout.setErrorEnabled(true);
            tiLayout.setError(ERROR_MESSAGE_REQUIRED);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        resetValidation();
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    @Override
    public void afterTextChanged(Editable s) {
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);
        if(!focused) {
            checkValidation();
        }
    }
}
