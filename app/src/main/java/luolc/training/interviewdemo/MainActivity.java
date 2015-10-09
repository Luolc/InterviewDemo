package luolc.training.interviewdemo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {

    private Context mContext;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_main);

        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar));
        actionBar.setTitle(R.string.main_title);
    }

    public void onClickHoleList(View view) {
        Intent intent = new Intent(this, HoleActivity.class);
        startActivity(intent);
    }

    public void onClickWeekCal(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle(R.string.main_week_cal)
                .setMessage("猜测设计getDeltaWeek函数的目的之一，是为了在课程表模块中计算当前周数。\n"
                + "故在演示Demo中，我设计了这个Dialog，点击\"计算\"会弹出Toast提示当前为2015秋季学期的第几周。\n"
                + "其内部实现调用getDeltaWeek函数，具体函数源码见面试自述文件。")
                .setPositiveButton("计算", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String toast = "当前为2015秋季学期第" + CalendarUtil.getCurrentWeek() + "周！";
                        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
                    }
                })
                .create().show();
    }

    public void onClickUrlToFile(View view) {
        Intent intent = new Intent(this, FileDownloadActivity.class);
        startActivity(intent);
    }

    public void onClickImageAsync(View view) {
        Intent intent = new Intent(this, ImageAsyncActivity.class);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

}
