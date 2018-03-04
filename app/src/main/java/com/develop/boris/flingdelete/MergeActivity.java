package com.develop.boris.flingdelete;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MergeActivity extends AppCompatActivity implements View.OnClickListener {

    private Button scrollBy;
    private LinearLayout rootLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merge);

        scrollBy = (Button) findViewById(R.id.btn_scroll_by2);
        rootLayout = (LinearLayout) findViewById(R.id.root);

        scrollBy.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_scroll_by2:
                rootLayout.scrollBy(50, 50);
                break;
            default:
                break;
        }

    }
}
