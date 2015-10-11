package luolc.training.interviewdemo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.InputStream;

/**
 * Created by Luo Liangchen on 2015/10/6.
 */
public class ImageDownload {

    private static final int MESSAGE_DOWNLOAD_START = 10000;
    private static final int MESSAGE_DOWNLOAD_COMPLETE = 10001;
    private static final int MESSAGE_ENCOUNTERED_ERROR = 10002;

    private Context context;
    private Handler handler;
    private Activity parentActivity;
    private String downloadUrl;
    private ImageView image;
    private Bitmap bitmap;
    private ImageDownloadListener listener;

    public interface ImageDownloadListener {
        void onDownloadStart();
        void onDownloadComplete();
        void onError();
    }

    public ImageDownload(Activity activity, String url, ImageView imageView) {
        parentActivity = activity;
        downloadUrl = url;
        image = imageView;
        listener = new ImageDownloadListener() {
            @Override
            public void onDownloadStart() {}

            @Override
            public void onDownloadComplete() {}

            @Override
            public void onError() {}
        };
        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what) {
                    case MESSAGE_DOWNLOAD_START:
                        listener.onDownloadStart();
                        break;
                    case MESSAGE_DOWNLOAD_COMPLETE:
                        listener.onDownloadComplete();
                        if (bitmap == null) {
                            break;
                        }
                        image.setImageBitmap(bitmap);
                        break;
                    case MESSAGE_ENCOUNTERED_ERROR:
                        listener.onError();
                        break;
                    default:
                        break;
                }
                return false;
            }});
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public void setOnDownloadListener(ImageDownloadListener listener) {
        this.listener = listener;
    }

    public static class Builder {
        private Context mContext;
        private Activity mParentActivity;
        private String mDownloadUrl;
        private ImageView mImage;
        private ImageDownloadListener mListener;

        public Builder(Context context) {
            mContext = context;
        }

        public Builder setActivity(Activity activity) {
            mParentActivity = activity;
            return this;
        }

        public Builder setUrl(String url) {
            mDownloadUrl = url;
            return this;
        }

        public Builder setImageView(ImageView imageView) {
            mImage = imageView;
            return this;
        }

        public Builder setOnDownloadListener(ImageDownloadListener listener) {
            mListener = listener;
            return this;
        }

        public ImageDownload create() {
            final ImageDownload imageDownload = new ImageDownload(mParentActivity,
                    mDownloadUrl, mImage);
            imageDownload.setContext(mContext);
            if(mListener != null) {
                imageDownload.setOnDownloadListener(mListener);
            }
            return imageDownload;
        }
    }

    public void execute() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpParams params;
                HttpClient httpClient;
                HttpGet httpGet;
                HttpResponse httpResponse;
                InputStream is = null;
                Message msg;

                try {
                    params = new BasicHttpParams();
                    HttpConnectionParams.setConnectionTimeout(params, 3000);
                    HttpConnectionParams.setSoTimeout(params, 3000);
                    httpClient = new DefaultHttpClient(params);
                    httpGet = new HttpGet(downloadUrl);

                    msg = Message.obtain(handler, MESSAGE_DOWNLOAD_START);
                    handler.sendMessage(msg);
                    httpResponse = httpClient.execute(httpGet);

                    is = httpResponse.getEntity().getContent();
                    bitmap = BitmapFactory.decodeStream(is);

                    msg = Message.obtain(handler, MESSAGE_DOWNLOAD_COMPLETE);
                    handler.sendMessage(msg);
                } catch (Exception e) {
                    e.printStackTrace();
                    msg = Message.obtain(handler, MESSAGE_ENCOUNTERED_ERROR);
                    handler.sendMessage(msg);
                } finally {
                    try {
                        if(is != null) {
                            is.close();
                        }
                    } catch (Exception ignore) {}
                }
            }
        }).start();
    }
}
