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
package cl.framirez.lyricssong.gui;

import java.util.List;

import cl.framirez.lyricssong.R;
import cl.framirez.lyricssong.entity.LyricsSongLyric;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class LyricsSongListLyricsAdapter extends BaseAdapter {
	  private Context mcontext;
	  private List<LyricsSongLyric> values;
	  

	  //public LyricsSongListLyricsAdapter(Context context, List<LyricsSongLyric> lyrics) {
	  public LyricsSongListLyricsAdapter(Context context, List<LyricsSongLyric> lyrics) {
		  	super();
		    this.mcontext = context;
		    this.values = lyrics;
	  }
	  //@Override
	  public View getView(int position, View rowView, ViewGroup parent) {
		  //gat a information about the user for show a image		  
		  LayoutInflater inflater = (LayoutInflater) mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		  rowView = inflater.inflate(R.layout.item_lyric,null);
		  TextView textName = (TextView)rowView.findViewById(R.id.textViewName);
		  TextView textOwner = (TextView) rowView.findViewById(R.id.textView_author);
		 // TextView textCredentials = (TextView)rowView.findViewById(R.id.textView_points);
		  ImageView imageOwner = (ImageView)rowView.findViewById(R.id.image_owner);
		  
		  textName.setText(values.get(position).getName());
		  textOwner.setText(values.get(position).getAuthor());
		  
	    return rowView;
	  }
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return values.size();
	}
	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
}

