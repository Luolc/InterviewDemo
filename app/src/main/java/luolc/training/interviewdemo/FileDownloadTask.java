package luolc.training.interviewdemo;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Luo Liangchen on 2015/10/5.
 */
public class FileDownloadTask extends AsyncTask<Void, Void, Boolean> {

    private static final int DOWNLOAD_BUFFER_SIZE = 2048;

    private Context mContext;
    private String downloadUrl;
    private File outFile;
    private ProgressBar progressBar;

    public FileDownloadTask(Context context, String url, File file, ProgressBar pb) {
        mContext = context;
        downloadUrl = "";
        outFile = new File(Environment.getExternalStorageDirectory(), "/file.bin");
        progressBar = pb;
        if(url != null) {
            downloadUrl = url;
        }
        if(file != null) {
            outFile = file;
        }
    }

    private boolean urlToFile(String url, File file) {
//        HttpURLConnection conn = null;
//        BufferedInputStream bis = null;
//        BufferedOutputStream bos = null;
//        FileOutputStream fos = null;
//
//        try {
//            Log.i("connect-test", "begin try");
//            conn = (HttpURLConnection)new URL(url).openConnection();
//            conn.setRequestMethod("HEAD");
//            conn.setConnectTimeout(1);
//            conn.setReadTimeout(1);
//            Log.i("connect-test", "begin connect");
//           // conn.connect();
//
//            Log.i("connect-test", "begin read");
//            // start download
//            bis = new BufferedInputStream(conn.getInputStream());
//            Log.i("connect-test", "already read");
//            fos = new FileOutputStream(file);
//            bos = new BufferedOutputStream(fos, DOWNLOAD_BUFFER_SIZE);
//            byte[] data = new byte[DOWNLOAD_BUFFER_SIZE];
//            int bytesRead;
//            while((bytesRead = bis.read(data, 0, data.length)) >= 0) {
//                bos.write(data, 0, bytesRead);
//            }
//
//            bos.close();
//            fos.close();
//            bis.close();
//            return true;
//        } catch(IOException e) {
//            e.printStackTrace();
//            Log.i("connect-test", e.getMessage());
//
//            return false;
//        } finally {
//            try {
//                if (bos != null)
//                    bos.close();
//                if (bis != null)
//                    bis.close();
//                if (fos != null)
//                    fos.close();
//            } catch (IOException ignored) {
//            }
//
//            if (conn != null)
//                conn.disconnect();
//        }
        HttpParams params;
        HttpClient httpClient;
        HttpGet httpGet;
        HttpResponse httpResponse;
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;

        try {
            params = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(params, 3000);
            HttpConnectionParams.setSoTimeout(params, 3000);
            httpClient = new DefaultHttpClient(params);
            httpGet = new HttpGet(url);
            httpResponse = httpClient.execute(httpGet);

            bis = new BufferedInputStream(httpResponse.getEntity().getContent());
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos, DOWNLOAD_BUFFER_SIZE);
            byte[] data = new byte[DOWNLOAD_BUFFER_SIZE];
            int bytesRead;
            while((bytesRead = bis.read(data, 0, data.length)) >= 0) {
                bos.write(data, 0, bytesRead);
            }

            bis.close();
            bos.close();
            fos.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            Log.i("connect-test", e.toString());
        } finally {
            try {
                if(bis != null) {
                    bis.close();
                }
                if(bos != null) {
                    bos.close();
                }
                if(fos != null) {
                    fos.close();
                }
            } catch (IOException ignore) {}
        }
        return false;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        return urlToFile(downloadUrl, outFile);
    }

    @Override
    protected void onPostExecute(Boolean state) {
        super.onPostExecute(state);
        progressBar.setVisibility(View.GONE);
        String toast;
        if(state) {
            toast = "下载成功！";
        } else {
            toast = "下载失败！";
        }
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
    }
}
