package com.example.first_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.ScriptableObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private String[] btn_id = {
            "backspace",
            "history", "unit", "detail", "backspace"
    };

    private String now_stat = "";
    private EditText result_view;
    private Context rhino = Context.enter();
    private ScriptableObject scope;

    private FragmentTransaction transaction;
    private ControlFragment control;
    private HistoryFragment history;

    private boolean frame_state = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rhino.setOptimizationLevel(-1);
        scope = rhino.initStandardObjects();

        result_view = findViewById(R.id.result);
        result_view.setShowSoftInputOnFocus(false);
        result_view.requestFocus();

        transaction = getSupportFragmentManager().beginTransaction();

        control = new ControlFragment();
        history = new HistoryFragment();

        control.setButtonClickListener(this);
        transaction
                .replace(R.id.frame_view, control)
                .commit();

        for(String id: btn_id)
            findViewById(getResources().getIdentifier("btn_" + id, "id", getPackageName()))
                    .setOnClickListener(this);
    }

    private void backspace() {
        int position = result_view.getSelectionStart();

        if(position == 0) return;
        setState(now_stat.substring(0, position - 1) + now_stat.substring(position, now_stat.length()));
        result_view.setSelection(position - 1);
    }

    private void input(String ch) {
        int position = result_view.getSelectionStart();

        setState(now_stat.substring(0, position) + ch + now_stat.substring(position, now_stat.length()));
        result_view.setSelection(position + 1);
    }

    private void setState(String state) {
        now_stat = state;
        result_view.setText(now_stat);

        result_view.setSelection(now_stat.length());
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.btn_clear: setState(""); break;
            case R.id.btn_backspace: backspace(); break;
            case R.id.btn_history:
                transaction = getSupportFragmentManager().beginTransaction();

                if(frame_state) {
                    transaction
                            .add(R.id.frame_view, history)
                            .setReorderingAllowed(true)
                            .addToBackStack("control")
                            .commit();
                } else {
                    getSupportFragmentManager().popBackStack();
                }

                frame_state = !frame_state;
                break;
            case R.id.btn_equal:
                try {
                    if(now_stat.length() > 0)
                        setState(rhino.evaluateString(scope, now_stat, "JavaScript", 1, null).toString());

                    double value = Double.parseDouble(now_stat);

                    if (value - (int)value == 0) setState((int)value + "");
                } catch (Exception e) { Toast.makeText(this, "불가능한 식 입니다.", Toast.LENGTH_SHORT).show(); }
                break;

            default:
                input(view.getTag().toString());
                break;
        }
    }
}