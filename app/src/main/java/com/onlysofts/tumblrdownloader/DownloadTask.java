package com.onlysofts.tumblrdownloader;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.widget.ProgressBar;

public class DownloadTask extends AsyncTask<String, Integer, String> {
	private Context context;
	private String url;
	private String filename;
	private long total;
	private long current;
	private ProgressBar progress;
	private static final String DIR = "Download";
	private static final String EXT = ".tmp";
	boolean run = true;
	public DownloadTask(Context context,String url,String filename,ProgressBar progress){
		this.context = context;
		this.url = url;
		this.filename = filename;
		this.progress = progress;
	}

	@Override
	protected String doInBackground(String... arg0) {
		HttpURLConnection urlConnection = null;
		InputStream in = null;
		BufferedOutputStream outStream = null;
		File file = null;
		 try {
			 System.out.println(url);
			 URL urlcon = new URL(url);
			 urlConnection = (HttpURLConnection) urlcon.openConnection();
			 urlConnection.setConnectTimeout(10000);
			 urlConnection.setReadTimeout(10000);
			 System.out.println(urlConnection);
			 total = urlConnection.getContentLength();
			 System.out.println("Total: " + total);
		     in = new BufferedInputStream(urlConnection.getInputStream());
		     byte[] buffer = new byte[1024];
		     int size = 0;
			 File path = StorageUtils.getExtPath(context,true);
			 file = new File(path,DIR + "/" + filename + EXT);
			 StorageUtils.createDirs(file);
			 if(file != null){
				 if(!file.exists()){
					 file.createNewFile();
					 System.out.println(file.getAbsolutePath());
					 outStream = new BufferedOutputStream(new FileOutputStream(file));
				 }else {
					 System.out.println("File [" + file.getAbsolutePath() + "] already exist.");
					 return null;
				 }
			 }else{
				 System.out.println("Can not find dir to write." + file.getAbsolutePath());
				 return null;
			 }

		     while(run && (size = in.read(buffer)) != -1){
				 outStream.write(buffer);
				 current += size;
				 float percent = 0;
				 if(total == 0){
					 percent = 0;
				 }else{
					 percent = current * 100 / total;
				 }
				 publishProgress((int)percent);
			 }
		     if(current == total){
				 boolean rename = file.renameTo(new File(filename));
			 }
		 } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		 } finally {
			 if(in != null)
			 {
				 try {
						in.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			 }
			 if(urlConnection != null)
			 {
				    urlConnection.disconnect(); 
			 }
			 if(outStream != null)
			 {
				 try {
					 outStream.flush();
					 outStream.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
		 }
		return null;
	}
	
	@Override
	protected void onCancelled() {
		super.onCancelled();
		run = false;
	}

	@Override
	protected void onCancelled(String result) {
		// TODO Auto-generated method stub
		super.onCancelled(result);
	}

	@Override
	protected void onPostExecute(String result) {
		System.out.println("Success");
		super.onPostExecute(result);
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}

	@Override
	protected void onProgressUpdate(Integer... values) {
		int p = values[0];
		progress.setProgress(p);
	}
}
