package luolc.training.interviewdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Luo Liangchen on 2015/10/6.
 */
public class ImageAsyncActivity extends ActionBarActivity {

    private static final String DOWNLOAD_URL =
            "http://witty-wordpress.stor.sinaapp.com/uploads/2015/10/imageAsyncSource.jpg";

    private Context mContext;
    private ActionBar actionBar;
    private TextView tvInstruction;
    private ImageView imImage;
    private ProgressBar pbImageDownload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_image_async);

        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar));
        actionBar.setTitle(R.string.image_async_title);

        tvInstruction = (TextView) findViewById(R.id.tv_image_async_instruction);
        imImage = (ImageView) findViewById(R.id.im_image);
        pbImageDownload = (ProgressBar) findViewById(R.id.pb_image_download);

        tvInstruction.setText("这组演示模块逻辑比较简单，即在点击\"下载并设置图片\"后，创建ImageDownload类"
                + "并调用其execute方法，从预先指定的url(我的GitHub头像地址)下载图片并配置在ImageView中。"
                + "在该类中我们实现了一个监听器接口，演示中ProgressBar的出现消失即使用该接口实现，"
                + "详细说明参见面试自述文档。");
    }

    public void onClickImageSet(View view) {
        ImageDownload.Builder builder = new ImageDownload.Builder(mContext);
        builder.setUrl(DOWNLOAD_URL).setImageView(imImage)
                .setOnDownloadListener(new ImageDownload.ImageDownloadListener() {
                    @Override
                    public void onDownloadStart() {
                        pbImageDownload.setVisibility(View.VISIBLE);
                        imImage.setImageResource(R.color.bk_image_undownloaded);
                    }

                    @Override
                    public void onDownComplete() {
                        pbImageDownload.setVisibility(View.GONE);
                    }

                    @Override
                    public void onError() {
                        Toast.makeText(mContext, "配置失败！", Toast.LENGTH_SHORT).show();
                        pbImageDownload.setVisibility(View.GONE);
                    }
                }).create().execute();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
