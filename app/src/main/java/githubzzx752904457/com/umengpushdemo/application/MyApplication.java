package githubzzx752904457.com.umengpushdemo.application;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.umeng.message.PushAgent;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;

import java.util.List;

import githubzzx752904457.com.umengpushdemo.activities.MsgDetailActivity;

/**
 * Created by admin on 2016/8/24.
 */
public class MyApplication extends Application {
    private PushAgent pushAgent;

    @Override
    public void onCreate() {
        super.onCreate();

        pushAgent = PushAgent.getInstance(this);

        //自定义处理结果
        UmengNotificationClickHandler umengNotificationClickHandler = new UmengNotificationClickHandler() {
            @Override
            public void launchApp(Context context, UMessage uMessage) {
                super.launchApp(context, uMessage);
            }

            @Override
            public void openUrl(Context context, UMessage uMessage) {
                super.openUrl(context, uMessage);
            }

            @Override
            public void openActivity(Context context, UMessage uMessage) {
                super.openActivity(context, uMessage);
            }

            @Override
            public void dealWithCustomAction(Context context, UMessage uMessage) {
                if (isAppInForeground(context)){
                    Intent i = new Intent(context, MsgDetailActivity.class);
                    i.putExtra("msg", uMessage.text+"");
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//必须添加
                    startActivity(i);
                }else {
                    Intent launchIntent = getPackageManager().getLaunchIntentForPackage(getPackageName());
                    launchIntent.putExtra("msg", uMessage.text+"");
                    launchIntent.putExtra("tag", "clickMsg");
                    launchIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//必须添加
                    startActivity(launchIntent);
                }
            }
        };

        pushAgent.setNotificationClickHandler(umengNotificationClickHandler);

    }

    //判断APP是否在运行
    public boolean isAppInForeground(Context context) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(context.getPackageName())) {
                return appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND;
            }
        }
        return false;
    }
}
