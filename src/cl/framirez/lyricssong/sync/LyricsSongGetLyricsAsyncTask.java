/**
Copyright 2014 Fabian Ramirez Barrios
This file is part of GeoComm.

GeoComm is free software: you can redistribute it and/or modify 
it under the terms of the GNU General Public License as published by 
the Free Software Foundation, either version 3 of the License, or 
(at your option) any later version.

GeoComm is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of 
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the 
GNU General Public License for more details.

You should have received a copy of the GNU General Public License 
along with GeoComm.  If not, see <http://www.gnu.org/licenses/>.

**/
package cl.framirez.lyricssong.sync;


import java.io.IOException;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import cl.framirez.lyricssong.R;
import cl.framirez.lyricssong.entity.LyricsSongLyric;
import cl.framirez.lyricssong.gui.LyricsSongListLyricsAdapter;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

/**
 * 
 * @author Fabian Ramirez
 *
 */
public class LyricsSongGetLyricsAsyncTask extends AsyncTask<String, Void, Boolean> {

	private static String TAGNAME = "LyricsSongGetLyricsAsyncTask";
	
	private ProgressDialog mProgressDialog;
	private Context context;	
	private List<LyricsSongLyric> list_lyrics;
	private ListView listview;
	private String hostname;
	private LyricsSongLyric ls;
	
	/**
	 * 
	 * @param cont
	 * @param url
	 * @param username
	 * @param password
	 */
	public LyricsSongGetLyricsAsyncTask(Context cont,String host,List<LyricsSongLyric> lyrics,ListView listview) 
	{
		this.context = cont;
		this.list_lyrics = lyrics;
		this.listview = listview;
		this.hostname = host;
		
		this.mProgressDialog = new ProgressDialog(context);
		this.mProgressDialog.setMessage(context.getResources().getString(R.string.mssg_loading_lyrics));
		this.mProgressDialog.setIndeterminate(false);
		this.mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		this.mProgressDialog.setCancelable(true);
		
		
	}
	
	@Override
	protected void onPreExecute() {
	    super.onPreExecute();
	    mProgressDialog.show();
	}
	
	@Override
	protected Boolean doInBackground(String... params) {
		// TODO Auto-generated method stub
		
		HttpClient httpclient = new DefaultHttpClient();
		HttpParams httpParams = httpclient.getParams();
		httpParams.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, 10000);
		httpParams.setParameter(CoreConnectionPNames.SO_TIMEOUT, 10000);
				
		HttpPost httppost = new HttpPost(this.hostname);
				
		Log.i(TAGNAME,"Try to connect to "+this.hostname);
				try {
					Log.i(TAGNAME,"Post Request");
					HttpResponse response = httpclient.execute(httppost);
					Log.i(TAGNAME,"CODE: "+1);
					int responseCode = response.getStatusLine().getStatusCode();
					Log.i(TAGNAME,"CODE: "+responseCode);
					switch(responseCode) {
						default:
							//Toast.makeText(this.context,R.string.error_not200code, Toast.LENGTH_SHORT).show();
							Log.i(TAGNAME, "Error");
							break;
						case 200:
							HttpEntity entity = response.getEntity();
							    if(entity != null) {
							        String responseBody = EntityUtils.toString(entity);
							        //Log.i(TAGNAME, responseBody);
							        JSONObject jObj,lyric;
									try {
										Log.i(TAGNAME, "Reading JSONResponse");
										//Log.i(TAGNAME, responseBody);
										jObj = new JSONObject(responseBody);
										
										for (int i=0;i<jObj.length();i++){
											lyric = new JSONObject(jObj.getString(jObj.names().getString(i)));
											ls = new LyricsSongLyric(lyric.getString("id"), lyric.getString("name"), lyric.getString("contein"),lyric.getString("author"));
											list_lyrics.add(ls);
											//list_lyrics.add(new LyricsSongLyric(lyric.getString("id"),lyric.getString("name"),lyric.getString("description")));
											//list_lyrics.add(new LyricsSongLyric("id1","Fa","afsdv<"));
											//Log.i(TAGNAME, "----------------------------------------");
											//Log.i(TAGNAME, ls.getName());
											//Log.i(TAGNAME, lyric.getString("name"));
											//Log.i(TAGNAME, lyric.getString("id"));
											//Log.i(TAGNAME, lyric.getString("author"));
											//Log.i(TAGNAME, lyric.getString("contein"));
										}
									} catch (JSONException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
							    }
							    break;
					}

				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					Log.e(TAGNAME, e.toString());
					return false;
				} catch (IOException e) {
					// TODO Auto-generated catch block
					Log.e(TAGNAME, e.toString());
					return false;
				}
				return true;
	}
	
	/**
	 * 
	 */
	@Override
	protected void onPostExecute(Boolean result) {
	    mProgressDialog.dismiss();
		if (result){
			Toast.makeText(this.context,R.string.mssg_taskcompleted, Toast.LENGTH_SHORT).show();
			LyricsSongListLyricsAdapter adapter = new LyricsSongListLyricsAdapter(context, list_lyrics);
	    	this.listview.setAdapter(adapter);
		}
		else {
			Toast.makeText(this.context,R.string.error_cantgetlyrics, Toast.LENGTH_SHORT).show();
		}
	    
	}

}
