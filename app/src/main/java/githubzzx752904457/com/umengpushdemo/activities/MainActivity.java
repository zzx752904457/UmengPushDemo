package githubzzx752904457.com.umengpushdemo.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.umeng.message.PushAgent;

import githubzzx752904457.com.umengpushdemo.R;

public class MainActivity extends AppCompatActivity {

    private PushAgent pushAgent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pushAgent = PushAgent.getInstance(this);

        //启动统计
        pushAgent.onAppStart();
        //启动推送
        pushAgent.enable();

        /**
         * 点击的推送才启动该activity所进行的操作
         */
        if ("clickMsg".equals(getIntent().getStringExtra("tag"))) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                        Intent intent = new Intent(MainActivity.this, MsgDetailActivity.class);
                        intent.putExtra("msg", getIntent().getStringExtra("msg") + "");
                        startActivity(intent);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

    }
}
