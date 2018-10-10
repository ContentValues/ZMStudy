package xdroid.mwee.com.posdinnerprinter.queen;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.text.TextUtils;
import com.mwee.android.tools.LogUtil;

/**
 * Created by zhangmin on 2018/7/20.
 */

public class DinnerPrintWakeUpReceiver extends BroadcastReceiver {

    //public final static int WAKEUP_CYCLE = 60 * 4;
    public static final String ACTION_RE_PRINT = "com.mwee.android.pos.print.reprint";
    public static final String ACTION_LOOP = "com.mwee.android.pos.print.loop";

    public static boolean alive = false;

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        LogUtil.log("MB WakeUp onReceive---->intent.getAction()=" + action);
        if (TextUtils.equals(ACTION_RE_PRINT, action)) {
            startLoop(context);
        }
    }

    public static void registerAlarm(Context mContext) {
        if (!alive) {
            LogUtil.log("MB WakeUp registerAlarm");
            Intent intent = new Intent(mContext, DinnerPrintWakeUpReceiver.class);
            intent.setAction(ACTION_RE_PRINT);
            mContext.sendBroadcast(intent);
        }
    }

    private void startLoop(Context mContext) {
        LogUtil.log("MB WakeUp startLoop----->alive=" + alive);
        if (!alive) {
            long firsTime = SystemClock.elapsedRealtime();
            AlarmManager am = (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(mContext, DinnerPrintWakeUpReceiver.class);
            intent.setAction(ACTION_LOOP);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            if (am != null) {
                am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, firsTime, 1 * 1000, pendingIntent);
            }
            alive = true;
        }
    }


}
