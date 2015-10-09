package luolc.training.interviewdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

/**
 * Created by Luo Liangchen on 2015/10/3.
 */
public class HoleActivity extends ActionBarActivity {

    private Context mContext;
    private ActionBar actionBar;
    private ListView lvHole;
    private LinearLayout header;
    private HoleListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        setContentView(R.layout.activity_hole);

        actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.actionbar));
        actionBar.setTitle(R.string.hole_list_title);

        lvHole = (ListView) findViewById(R.id.lv_pkuhole);
        header = (LinearLayout) View.inflate(this, R.layout.header_hole_list, null);
        lvHole.addHeaderView(header);
        adapter = new HoleListAdapter(mContext, 20);
        lvHole.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
