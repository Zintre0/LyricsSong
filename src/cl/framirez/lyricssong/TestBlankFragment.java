package cl.framirez.lyricssong;

import java.util.ArrayList;
import java.util.List;

import cl.framirez.lyricssong.entity.LyricsSongLyric;
import cl.framirez.lyricssong.gui.LyricsSongListLyricsAdapter;
import cl.framirez.lyricssong.sync.LyricsSongGetLyricsAsyncTask;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment
 * must implement the {@link TestBlankFragment.OnFragmentInteractionListener}
 * interface to handle interaction events. Use the
 * {@link TestBlankFragment#newInstance} factory method to create an instance of
 * this fragment.
 * 
 */
public class TestBlankFragment extends Fragment implements OnItemClickListener{
	// TODO: Rename parameter arguments, choose names that match
	// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
	private String TAGNAME = "TestBlankFragment";

	// TODO: Rename and change types of parameters
	private int mParam1;
	//private String mParam1;

	private OnFragmentInteractionListener mListener;

	private ListView list_lyrics;
	private ArrayAdapter<String> adapter;
	private List<String> values;
	
	private List<LyricsSongLyric> lyrics;
	private LyricsSongListLyricsAdapter adapter2;
	/**
	 * Use this factory method to create a new instance of this fragment using
	 * the provided parameters.
	 * 
	 * @param param1
	 *            Parameter 1.
	 * @param param2
	 *            Parameter 2.
	 * @return A new instance of fragment TestBlankFragment.
	 */
	// TODO: Rename and change types and number of parameters
	public static TestBlankFragment newInstance(int sectionNumber) {
		TestBlankFragment fragment = new TestBlankFragment();
		Bundle args = new Bundle();
		args.putInt("section_number", sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public TestBlankFragment() {
		// Required empty public constructor
	}
	

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.i("COSA", "onResume!!");
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		Log.i("COSA", "onStart!!");
	}
		

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		Log.i("COSA", "PAUSE!!");
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (getArguments() != null) {
			mParam1 = getArguments().getInt("section_number");
			//mParam2 = getArguments().getString(ARG_PARAM2);
		}
	}
	
	public void getLyrics() {
		lyrics = new ArrayList<LyricsSongLyric>();
		LyricsSongGetLyricsAsyncTask gl = new LyricsSongGetLyricsAsyncTask(getActivity(),"http://192.168.2.5/~zintre/testLyrics.php",lyrics,this.list_lyrics);
		gl.execute();
	}
	

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment		
		 View view = inflater.inflate(R.layout.fragment_test_blank,container, false);
		 this.list_lyrics = (ListView) view.findViewById(R.id.list_lyrics);
		 list_lyrics.setClickable(true);
	     list_lyrics.setOnItemClickListener(this);
		Button button = (Button) view.findViewById(R.id.btn_get_lyrics);
	    button.setOnClickListener(new View.OnClickListener() {
	      @Override
	      public void onClick(View v) {
	    	  getLyrics();
	      }
	    });
	    return view;
	}

	// TODO: Rename method, update argument and hook method into UI event
	public void onButtonPressed(Uri uri) {
		if (mListener != null) {
			mListener.onFragmentInteraction(uri);
		}
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mListener = (OnFragmentInteractionListener) activity;
			//getLyrics();
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString()
					+ " must implement OnFragmentInteractionListener");
		}
	}

	@Override
	public void onDetach() {
		super.onDetach();
		mListener = null;
	}

	/**
	 * This interface must be implemented by activities that contain this
	 * fragment to allow an interaction in this fragment to be communicated to
	 * the activity and potentially other fragments contained in that activity.
	 * <p>
	 * See the Android Training lesson <a href=
	 * "http://developer.android.com/training/basics/fragments/communicating.html"
	 * >Communicating with Other Fragments</a> for more information.
	 */
	public interface OnFragmentInteractionListener {
		// TODO: Update argument type and name
		public void onFragmentInteraction(Uri uri);
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
		//final String path=files[position].getPath();
		//Log.i(TAGNAME, ""+arg2);
		//Log.i(TAGNAME, ""+arg1.getId());
		//Log.i(TAGNAME, ""+this.lyrics.get(arg2).getName());
		final String author = this.lyrics.get(arg2).getAuthor();
		final String lyric = this.lyrics.get(arg2).getContein();
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setIcon(R.drawable.musical);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {            	
            }
        });
        //builder.setMessage("FILE: ");
        builder.setMessage(lyric);
        //builder.setTitle(getResources().getString(R.string.deletePoints_ask_delete)+this.points.get(position).getName());
        builder.setTitle(author+" - "+this.lyrics.get(arg2).getName());
        AlertDialog dialog = builder.create();
        dialog.show();
		
	}

}
