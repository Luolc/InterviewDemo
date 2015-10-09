package luolc.training.interviewdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Luo Liangchen on 2015/10/2.
 */
public class HoleListAdapter extends BaseAdapter {

    private static final int MOD = 8;

    private LayoutInflater layoutInflater;
    private Context context;
    private Integer size;

    public HoleListAdapter(Context context, int size) {
        this.layoutInflater = LayoutInflater.from(context);
        this.context = context;
        this.size = size;
    }

    @Override
    public int getCount() {
        if (size == null) {
            return 0;
        }
        return size < 0 ? 0 : size;
    }
//
    @Override
    public Object getItem(int position) {
        if (size == null || position >= size) {
            return null;
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.item_hole_list, parent, false);
            viewHolder = new ViewHolder();

            viewHolder.tvPid = (TextView) convertView.findViewById(R.id.tv_pid);
            viewHolder.tvText = (TextView) convertView.findViewById(R.id.tv_text);
            viewHolder.tvPostTime = (TextView) convertView.findViewById(R.id.tv_post_time);
            viewHolder.tvCommentCount = (TextView) convertView.findViewById(R.id.tv_comment_count);
            viewHolder.tvViewCount = (TextView) convertView.findViewById(R.id.tv_view_count);

            viewHolder.layoutImage = (RelativeLayout) convertView.findViewById(R.id.layout_image);
            viewHolder.imImage = (ImageView) convertView.findViewById(R.id.im_image);
            viewHolder.tvImageLong = (TextView) convertView.findViewById(R.id.tv_image_long);
            viewHolder.tvImageAlt = (TextView) convertView.findViewById(R.id.tv_image_alt);

            viewHolder.layoutAudio = (RelativeLayout) convertView.findViewById(R.id.layout_audio);
            viewHolder.imAudio = (ImageView) convertView.findViewById(R.id.im_audio);
            viewHolder.tvAudioDuration = (TextView) convertView.findViewById(R.id.tv_audio_duration);
            viewHolder.tvAudioAlt = (TextView) convertView.findViewById(R.id.tv_audio_alt);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvPid.setText("#" + (10000 + size - position - 1));
        viewHolder.tvPostTime.setText("2015-10-02 15:07:41");
        viewHolder.tvCommentCount.setText("(0)");
        viewHolder.tvViewCount.setText("(0)");

        switch (position % MOD) {
            case 0:
                viewHolder.tvText.setVisibility(View.VISIBLE);
                viewHolder.layoutImage.setVisibility(View.GONE);
                viewHolder.imImage.setVisibility(View.GONE);
                viewHolder.tvImageLong.setVisibility(View.GONE);
                viewHolder.tvImageAlt.setVisibility(View.GONE);
                viewHolder.layoutAudio.setVisibility(View.GONE);
                viewHolder.imAudio.setVisibility(View.GONE);
                viewHolder.tvAudioDuration.setVisibility(View.GONE);
                viewHolder.tvAudioAlt.setVisibility(View.GONE);

                viewHolder.tvText.setText("这是一条文字树洞\n【在以下各个item中我模拟出了自己黑箱测试出的或者设想到的各种典型情况，具体的说明请参见面试自述文档】");
                break;
            case 1:
                viewHolder.tvText.setVisibility(View.VISIBLE);
                viewHolder.layoutImage.setVisibility(View.VISIBLE);
                viewHolder.imImage.setVisibility(View.VISIBLE);
                viewHolder.tvImageLong.setVisibility(View.GONE);
                viewHolder.tvImageAlt.setVisibility(View.GONE);
                viewHolder.layoutAudio.setVisibility(View.GONE);
                viewHolder.imAudio.setVisibility(View.GONE);
                viewHolder.tvAudioDuration.setVisibility(View.GONE);
                viewHolder.tvAudioAlt.setVisibility(View.GONE);

                viewHolder.imImage.setImageResource(R.drawable.pkuhole_image_common);
                viewHolder.tvText.setText("这是一条带有文字的图片树洞");
                break;
            case 2:
                viewHolder.tvText.setVisibility(View.GONE);
                viewHolder.layoutImage.setVisibility(View.VISIBLE);
                viewHolder.imImage.setVisibility(View.VISIBLE);
                viewHolder.tvImageLong.setVisibility(View.GONE);
                viewHolder.tvImageAlt.setVisibility(View.GONE);
                viewHolder.layoutAudio.setVisibility(View.GONE);
                viewHolder.imAudio.setVisibility(View.GONE);
                viewHolder.tvAudioDuration.setVisibility(View.GONE);
                viewHolder.tvAudioAlt.setVisibility(View.GONE);

                viewHolder.imImage.setImageResource(R.drawable.pkuhole_image_without_text);
                break;
            case 3:
                viewHolder.tvText.setVisibility(View.GONE);
                viewHolder.layoutImage.setVisibility(View.VISIBLE);
                viewHolder.imImage.setVisibility(View.VISIBLE);
                viewHolder.tvImageLong.setVisibility(View.VISIBLE);
                viewHolder.tvImageAlt.setVisibility(View.GONE);
                viewHolder.layoutAudio.setVisibility(View.GONE);
                viewHolder.imAudio.setVisibility(View.GONE);
                viewHolder.tvAudioDuration.setVisibility(View.GONE);
                viewHolder.tvAudioAlt.setVisibility(View.GONE);

                viewHolder.imImage.setImageResource(R.drawable.pkuhole_image_long);
                break;
            case 4:
                viewHolder.tvText.setVisibility(View.VISIBLE);
                viewHolder.layoutImage.setVisibility(View.VISIBLE);
                viewHolder.imImage.setVisibility(View.GONE);
                viewHolder.tvImageLong.setVisibility(View.GONE);
                viewHolder.tvImageAlt.setVisibility(View.VISIBLE);
                viewHolder.layoutAudio.setVisibility(View.GONE);
                viewHolder.imAudio.setVisibility(View.GONE);
                viewHolder.tvAudioDuration.setVisibility(View.GONE);
                viewHolder.tvAudioAlt.setVisibility(View.GONE);

                viewHolder.tvText.setText("这是图片未正确加载时的情况");
                break;
            case 5:
                viewHolder.tvText.setVisibility(View.VISIBLE);
                viewHolder.layoutImage.setVisibility(View.GONE);
                viewHolder.imImage.setVisibility(View.GONE);
                viewHolder.tvImageLong.setVisibility(View.GONE);
                viewHolder.tvImageAlt.setVisibility(View.GONE);
                viewHolder.layoutAudio.setVisibility(View.VISIBLE);
                viewHolder.imAudio.setVisibility(View.VISIBLE);
                viewHolder.tvAudioDuration.setVisibility(View.VISIBLE);
                viewHolder.tvAudioAlt.setVisibility(View.GONE);

                viewHolder.imAudio.setImageResource(R.drawable.pkuhole_audio_start);
                viewHolder.tvAudioDuration.setText("3\'\'");
                viewHolder.tvText.setText("这是一条带有文字的语音树洞\n(下面的是一条没有文字的)");
                break;
            case 6:
                viewHolder.tvText.setVisibility(View.GONE);
                viewHolder.layoutImage.setVisibility(View.GONE);
                viewHolder.imImage.setVisibility(View.GONE);
                viewHolder.tvImageLong.setVisibility(View.GONE);
                viewHolder.tvImageAlt.setVisibility(View.GONE);
                viewHolder.layoutAudio.setVisibility(View.VISIBLE);
                viewHolder.imAudio.setVisibility(View.VISIBLE);
                viewHolder.tvAudioDuration.setVisibility(View.VISIBLE);
                viewHolder.tvAudioAlt.setVisibility(View.GONE);

                viewHolder.imAudio.setImageResource(R.drawable.pkuhole_audio_start);
                viewHolder.tvAudioDuration.setText("5\'\'");
                break;
            case 7:
                viewHolder.tvText.setVisibility(View.VISIBLE);
                viewHolder.layoutImage.setVisibility(View.GONE);
                viewHolder.imImage.setVisibility(View.GONE);
                viewHolder.tvImageLong.setVisibility(View.GONE);
                viewHolder.tvImageAlt.setVisibility(View.GONE);
                viewHolder.layoutAudio.setVisibility(View.VISIBLE);
                viewHolder.imAudio.setVisibility(View.GONE);
                viewHolder.tvAudioDuration.setVisibility(View.GONE);
                viewHolder.tvAudioAlt.setVisibility(View.VISIBLE);

                viewHolder.tvText.setText("这是语音未正确加载时的情况\n(完)");
                break;
            default:
                viewHolder.tvText.setVisibility(View.VISIBLE);
                viewHolder.layoutImage.setVisibility(View.GONE);
                viewHolder.imImage.setVisibility(View.GONE);
                viewHolder.tvImageLong.setVisibility(View.GONE);
                viewHolder.tvImageAlt.setVisibility(View.GONE);
                viewHolder.layoutAudio.setVisibility(View.GONE);
                viewHolder.imAudio.setVisibility(View.GONE);
                viewHolder.tvAudioDuration.setVisibility(View.GONE);
                viewHolder.tvAudioAlt.setVisibility(View.GONE);

                viewHolder.tvText.setText("Default");
                break;
        }

        return convertView;
    }

    static class ViewHolder {
        TextView tvPid;
        TextView tvText;
        TextView tvPostTime;
        TextView tvCommentCount;
        TextView tvViewCount;

        RelativeLayout layoutImage;
        ImageView imImage;
        TextView tvImageLong;
        TextView tvImageAlt;

        RelativeLayout layoutAudio;
        ImageView imAudio;
        TextView tvAudioDuration;
        TextView tvAudioAlt;
    }
}
