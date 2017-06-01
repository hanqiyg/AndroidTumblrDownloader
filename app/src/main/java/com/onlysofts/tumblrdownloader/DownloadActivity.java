package com.onlysofts.tumblrdownloader;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;
import java.util.Map;

/**
 * Created by i.c.e on 2017/6/1.
 */

public class DownloadActivity extends AppCompatActivity {
    public Button add = null;
    public ListView listView = null;
    private List<Map<String, Object>> listData;
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
        listData = getData();
        listView = (ListView) findViewById(R.id.listview);
      }

    private List<Map<String,Object>> getData() {
        return null;
    }

    public class MHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    }

    public class DownloadListAdapter extends BaseAdapter{
        private LayoutInflater mInflater;
        @Override
        public int getCount() {
            return listData.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            //http://www.cnblogs.com/allin/archive/2010/05/11/1732200.html
            return convertView;
        }
     }
}
