package githubzzx752904457.com.umengpushdemo.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import githubzzx752904457.com.umengpushdemo.R;

public class MsgDetailActivity extends AppCompatActivity {
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_detail);

        tv = (TextView) findViewById(R.id.tv);

        tv.setText(getIntent().getStringExtra("msg")+"");
    }
}
