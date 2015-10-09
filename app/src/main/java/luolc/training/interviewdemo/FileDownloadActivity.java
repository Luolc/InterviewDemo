package luolc.training.interviewdemo;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import java.io.File;

/**
 * Created by Luo Liangchen on 2015/10/5.
 */
public class FileDownloadActivity extends ActionBarActivity {

    private static final String DOWNLOAD_URL_CORRECT = "https://www.baidu.com/img/bdlogo.png";
    private static final String DOWNLOAD_URL_FAULT = "http://test.com/test.xyz";
    private static final String DIR_APP = "/A-PKUHelperInterview/";
    private Context mContext;
    private ActionBar actionBar;
    private TextView tvInstruction;
    private TextView tvUrlCorrect;
    private TextView tvUrlFault;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_file_download);

        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar));
        actionBar.setTitle(R.string.file_download_title);

        tvInstruction = (TextView) findViewById(R.id.tv_file_download_instruction);
        tvInstruction.setText("在这部分演示中，我编写了一个异步任务，当点击\"下载文件\"时被触发。\n"
                + "在doInBackground中调用urlToFile函数，并将其返回值返回至onPostExecute，"
                + "根据返回值会弹出不同的toast提示下载是否成功。\n"
                + "下载成功的文件会存储至SD卡的\"A-PKUHelperInterview\"目录下，下载文件的文件名根据url的后缀自动生成。\n"
                + "如下是两个测试url：百度logo链接和一个随意写的url。当点击不存在url的下载时，"
                + "你可能需要稍稍等待几秒，待其超过链接超时上限时会返回失败提示。\n"
                + "下载的文件可以在稍后使用为这次面试编写的文件管理app查看。");
        tvUrlCorrect = (TextView) findViewById(R.id.tv_url_correct);
        tvUrlFault = (TextView) findViewById(R.id.tv_url_fault);
        tvUrlCorrect.setText(DOWNLOAD_URL_CORRECT);
        tvUrlFault.setText(DOWNLOAD_URL_FAULT);
    }

    public void onClickFileDownload_0(View view) {
        String url;
        String fileName;
        int lastSlash;
        File file;

        url = DOWNLOAD_URL_CORRECT;

        lastSlash = url.lastIndexOf('/');
        fileName = "file.bin";
        if(lastSlash >= 0) {
            fileName = url.substring(lastSlash + 1);
        }
        if("".equals(fileName)) {
            fileName = "file.bin";
        }

        mkdirs();
        file = new File(Environment.getExternalStorageDirectory() + DIR_APP + fileName);

        new FileDownloadTask(mContext, url, file).execute();
    }

    public void onClickFileDownload_1(View view) {
        String url;
        String fileName;
        int lastSlash;
        File file;

        url = DOWNLOAD_URL_FAULT;

        lastSlash = url.lastIndexOf('/');
        fileName = "file.bin";
        if(lastSlash >= 0) {
            fileName = url.substring(lastSlash + 1);
        }
        if("".equals(fileName)) {
            fileName = "file.bin";
        }

        mkdirs();
        file = new File(Environment.getExternalStorageDirectory() + DIR_APP + fileName);

        new FileDownloadTask(mContext, url, file).execute();
    }

    private void mkdirs() {
        try {
            File file = new File(Environment.getExternalStorageDirectory() + DIR_APP);
            if (!file.exists()) {
                file.mkdirs();
            } else {
                if (!file.isDirectory()) {
                    file.delete();
                    file.mkdirs();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
