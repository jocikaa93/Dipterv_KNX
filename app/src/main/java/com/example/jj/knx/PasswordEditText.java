package com.example.jj.knx;

import android.content.Context;
import android.os.IBinder;
import android.text.Editable;
import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * Created by Juhász József on 2018. 03. 03..
 */

public class PasswordEditText extends RelativeLayout {

    protected EditText passwordEditText;
    protected ImageView eyeImageView;

    public PasswordEditText(Context context) {
        super(context);
        init(context);
    }

    public PasswordEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PasswordEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_password_edittext, this, true);

        passwordEditText = findViewById(R.id.passwordET);
        eyeImageView = findViewById(R.id.passwordIV);

        eyeImageView.setOnTouchListener(new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    setTransformationMethod(null);
                    return true;
                } else if (event.getAction() == MotionEvent.ACTION_UP || event.getAction() == MotionEvent.ACTION_CANCEL) {
                    setTransformationMethod(PasswordTransformationMethod.getInstance());
                    return true;
                }
                return false;
            }
        });


        setTransformationMethod(PasswordTransformationMethod.getInstance());
    }

    private void setTransformationMethod(TransformationMethod method) {
        int ss = passwordEditText.getSelectionStart();
        int se = passwordEditText.getSelectionEnd();
        passwordEditText.setTransformationMethod(method);
        passwordEditText.setSelection(ss, se);
    }

    public Editable getText() {
        if(passwordEditText != null)
            return passwordEditText.getText();
        else
            return null;
    }

    public void setError(CharSequence str) {
        passwordEditText.setError(str);
    }

    public void setText(CharSequence text) {
        passwordEditText.setText(text);
    }

    public IBinder getWindowToken() {
        if (passwordEditText != null) {
            return passwordEditText.getWindowToken();
        }
        return null;
    }

}