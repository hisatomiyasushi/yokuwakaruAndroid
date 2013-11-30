
package jp.android.booksample.firstapp;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;


/**
 * アプリケーションメインアクティビティ
 */

public class MainActivity extends Activity implements Runnable, OnClickListener {
    
    /**
     * タイマーによりタイマースレッドで呼び出されるタイマー用のタスクです。
     */

    public final class MyTimerTask extends TimerTask {
        
        /**
         * タイマー用タスクのメソッドです。このメソッドが定期的に呼び出されます。
         */

        @Override
        public void run() {
            // メインスレッドにて処理を実行させる
            MainActivity.this.runOnUiThread(MainActivity.this);
        }

    }
    
    /**
     * アクティビティ生成時に呼び出されます。
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMoving = true;
        CircleView circleView1 = (CircleView) findViewById(R.id.circleView1);
        // イベントハンドラの設定
        circleView1.setOnClickListener(this);
    }
    
    
    /**
     * タイマーの呼び出し間隔を示します。
     */
    private static final long TIMER_PERIOD = 50;
    
    /**
     * タイマーオブジェクトです。
     */
    private Timer mTimer;
    
    /**
     * 円がアニメーションを行うかどうかを格納する変数です。
     */
    private boolean mMoving;
    
    /**
     * アクティビティーの操作が可能になったタイミングで呼び出されてます。
     */
    @Override
    protected void onResume() {
        super.onResume();
        // タイマーを開始
        mTimer = new Timer(true);
        mTimer.schedule(new MyTimerTask(), TIMER_PERIOD,  TIMER_PERIOD);
    }
    
    /**
     * アクティビティーが操作不能になったタイミングで呼び出されます。
     */
    @Override
    protected void onPause() {
        super.onPause();
        // タイマーを停止
        mTimer.cancel();        
    }
    
    /**
     * タイマーにより「メインスレッドで」呼び出されます。
     */
    @Override
    public void run() {
        CircleView circleView1 = (CircleView) findViewById(R.id.circleView1);
        // 実際に円を移動させる
        if (mMoving) {
            circleView1.setX(circleView1.getX() +2);
            circleView1.setY(circleView1.getY() +2);
        }
    }
    
    /**
     * ビューがクリック（タップ）されたときに呼び出されます
     */
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.circleView1:
                // 円がタップされたときの処理
                // 円の動作を止める
                mMoving = false;
                // ダイアログの表示
                TouchDialog dialog = new TouchDialog();
                dialog.show(getFragmentManager(), "");
                break;
            default:
                break;                
        }
    }
    
    
    
}





