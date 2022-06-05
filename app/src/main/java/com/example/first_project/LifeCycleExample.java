package com.example.first_project;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LifeCycleExample extends AppCompatActivity {
    TextView textView;
    // some transient state for the activity instance
    String gameState;

    final String GAME_STATE_KEY = "game_state_key";
    final String TEXT_VIEW_KEY = "text_view_key";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // 해당 함수가 호출된 후, 연쇄적으로 onStart -> onResume이 순차적으로 실행됨
        // call the super class onCreate to complete the creation of activity like
        // the view hierarchy
        super.onCreate(savedInstanceState);

        // recovering the instance state
        if (savedInstanceState != null) {
            gameState = savedInstanceState.getString(GAME_STATE_KEY);
        }

        // set the user interface layout for this activity
        // the layout file is defined in the project res/layout/main_activity.xml file
        setContentView(R.layout.lifecycle_activity);

        // initialize member TextView so we can manipulate it later
        textView = (TextView) findViewById(R.id.text_view);
    }

    // 해당 함수는 이전에 해당 함수를 사용하여 저장된 instance가 있는 경우에만 호출됨
    // onCreate()에서 일부 상태를 복원할 수 있지만 해당 함수에서는 선택적으로 복원 가능
    // onStart()가 완료된 후에 사용할 수 있는 상태들을 접근 가능
    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        textView.setText(savedInstanceState.getString(TEXT_VIEW_KEY));
    }

    // Activity가 일시적으로 Destroyed될 때, instance의 상태를 저장 가능
    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(GAME_STATE_KEY, gameState);
        outState.putString(TEXT_VIEW_KEY, textView.getText().toString());

        // call superclass to save any view hierarchy
        super.onSaveInstanceState(outState);
    }
}
