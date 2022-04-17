package com.example.first_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.ScriptableObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private String[] btn_id = {
            "1", "2", "3", "4",
            "5", "6", "7", "8",
            "9", "0", "plus", "minus",
            "dot", "equal", "clear",
            "bracket", "mod", "div", "multi"
    };

    private String now_stat = "";
    private EditText result_view;
    private Context rhino = Context.enter();
    private ScriptableObject scope;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rhino.setOptimizationLevel(-1);
        scope = rhino.initStandardObjects();

        result_view = findViewById(R.id.result);
        result_view.setShowSoftInputOnFocus(false);
        result_view.requestFocus();

        for(String id: btn_id)
            findViewById(getResources().getIdentifier("btn_" + id, "id", getPackageName()))
                    .setOnClickListener(this);
    }

    private void setState(String state) {
        now_stat = state;
        result_view.setText(now_stat);

        result_view.setSelection(now_stat.length());
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.btn_clear:
                setState("");
                break;
            case R.id.btn_equal:
                try {
                    if(now_stat.trim().length() > 0)
                        setState(rhino.evaluateString(
                                scope,
                                now_stat,
                                "JavaScript",
                                1,
                                null
                        ).toString());
                } catch (Exception e) {
                    Toast.makeText(this, "불가능한 식 입니다.", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                String clicked = view.getTag().toString();

                if(clicked.matches("[.]?\\d*(\\.\\d+)?"))
                    setState(now_stat + clicked);
                else setState(String.format(" %s %s ", now_stat, clicked));
                break;
        }
    }
}