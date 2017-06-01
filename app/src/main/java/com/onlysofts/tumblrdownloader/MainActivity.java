package com.onlysofts.tumblrdownloader;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    DownloadTask downloadTask = null;
    private static final String FILENAME = "platform-24_r02.zip";
    private static final String URL = "https://dl.google.com/android/repository/platform-24_r02.zip";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.download_info);
        Button download = (Button) findViewById(R.id.start);
        Button stop = (Button) findViewById(R.id.stop);
        Button delete = (Button) findViewById(R.id.delete);
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar2);

        download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadTask = new DownloadTask(
                        getApplicationContext(),
                        URL,
                        FILENAME,
                        progressBar
                );
                downloadTask.execute();
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadTask.cancel(true);
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downloadTask = null;
                String filename = "Download/" + FILENAME + ".tmp";
                System.out.println("delete [" + filename + "].");
                if(StorageUtils.isFileExist(
                        getApplicationContext(),
                        filename,
                        true)){
                    System.out.println("Exist [" + filename +"].");
                    StorageUtils.remove(
                            getApplicationContext(),
                            filename,
                            true
                    );
                }
            }
        });
    }
}
