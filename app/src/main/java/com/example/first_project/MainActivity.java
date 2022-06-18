package com.example.first_project;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.ScriptableObject;

import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.text.ParsePosition;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final double ROUND_UNIT = 10000000000.0;
    private String[] btn_id = {
            "backspace",
            "function", "unit", "detail", "backspace"
    };

    private String now_stat = "";
    private EditText result_view;
    private Context rhino = Context.enter();
    private ScriptableObject scope;

    private FragmentTransaction transaction;
    private ControlFragment control;
    private HistoryFragment history;
    private ImageButton function_button;

    private boolean frame_state = true;

    private boolean resultVisible;

    private FileOutputStream fos;
    private PrintWriter writer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rhino.setOptimizationLevel(-1);
        scope = rhino.initStandardObjects();

        result_view = findViewById(R.id.result);
        function_button = findViewById(R.id.btn_function);
        result_view.setShowSoftInputOnFocus(false);
        result_view.requestFocus();

        transaction = getSupportFragmentManager().beginTransaction();

        control = new ControlFragment();
        history = new HistoryFragment();

        resultVisible = false;

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

    public void setState(String state) {
        now_stat = state;
        result_view.setText(now_stat);

        result_view.setSelection(now_stat.length());
    }

    public void onHistoryClick(String result) {
        resultVisible = true;
        setState(result);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.btn_clear: setState(""); break;
            case R.id.btn_backspace:
                resultVisible = false;
                backspace();
                break;
            case R.id.btn_unit: break;
            case R.id.btn_detail:
                if(getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                else setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                break;

            case R.id.btn_function:
                transaction = getSupportFragmentManager()
                        .beginTransaction()
                        .setCustomAnimations(
                                R.anim.fade_in,
                                R.anim.fade_out,
                                R.anim.fade_in,
                                R.anim.fade_out
                        ).setReorderingAllowed(true);

                if(frame_state) {
                    transaction
                            .add(R.id.frame_view, history)
                            .addToBackStack("control")
                            .commit();
                    function_button.setImageResource(R.drawable.calculator);
                } else {
                    getSupportFragmentManager().popBackStack();
                    function_button.setImageResource(R.drawable.outline_time);
                }

                frame_state = !frame_state;
                break;
            case R.id.btn_equal:
                try {
                    if(!isValidFormula(now_stat)) break;

                    fos = openFileOutput("history", android.content.Context.MODE_APPEND);
                    writer = new PrintWriter(fos);

                    String result = getCalculatedResult();

                    writer.println(String.format("%s=%s", now_stat, result));

                    setState(result);

                    resultVisible = true;

                    writer.close();
                    fos.close();
                } catch (Exception e) { Toast.makeText(this, "불가능한 식 입니다.", Toast.LENGTH_SHORT).show(); }
                break;
            default:
                String clicked = view.getTag().toString();

                if(resultVisible) {
                    if(isNumeric(clicked)) setState(clicked); // 연산자가 아니면
                    else input(clicked);
                    resultVisible = false;
                }
                else input(clicked);
                break;
        }
    }
    public static boolean isNumeric(String str) {
        ParsePosition pos = new ParsePosition(0);
        NumberFormat.getInstance().parse(str, pos);
        return str.length() == pos.getIndex();
    }
    private String getCalculatedResult() {
        double resultValue = Double.parseDouble(rhino.evaluateString(scope, now_stat, "JavaScript", 1, null).toString());
        resultValue = (long)(resultValue * ROUND_UNIT) / ROUND_UNIT;

        return (resultValue - (int)resultValue == 0) ? Long.toString((long)resultValue) : Double.toString(resultValue);
    }

    private Boolean isValidFormula(String formula) {
        return formula.length() > 0;
    }
}