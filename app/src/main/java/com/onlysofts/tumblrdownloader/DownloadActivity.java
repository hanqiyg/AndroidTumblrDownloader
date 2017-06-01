package com.onlysofts.tumblrdownloader;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

/**
 * Created by i.c.e on 2017/6/1.
 */

public class DownloadActivity extends AppCompatActivity {
    public Button add = null;
    public ListView listView = null;
    public String[] urls =
    {

    };
    public int index = 0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.download);
        MHandler handler = new MHandler();
        add = (Button) findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (index < urls.length){
                    DownloadWorker worker = new DownloadWorker(urls[index]);
                    index++;
                }
            }
        });
        listView = (ListView) findViewById(R.id.listview);
        ArrayAdapter adapter = new ArrayAdapter(this, R.layout.download_info, urls);
        listView.setAdapter(adapter);
    }
    public class MHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    }
}
