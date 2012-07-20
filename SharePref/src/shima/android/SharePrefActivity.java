package shima.android;

import java.util.HashMap;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;

public class SharePrefActivity extends Activity {
	private static final String TAG = SharePrefActivity.class.getSimpleName();
	Prefs prefs;
    @Override public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
		prefs = new Prefs(this);
		
		long start = System.currentTimeMillis();
		
		singular();
		
		long curr = System.currentTimeMillis();
		Log.d(TAG, "@@@ time=" + (curr - start));
		start = curr;
		
		prefs.begin();
		singular();
		prefs.end();
		
		curr = System.currentTimeMillis();
		Log.d(TAG, "@@@ time=" + (curr - start));


    }
    void singular() {
		// Getting test
		boolean flag	= prefs.getFlag();
		int level		= prefs.getLevel();
		float ratio		= prefs.getRatio();
		long millisec	= prefs.getMillisec();
		double factor	= prefs.getFactor();
		String title	= prefs.getTitle();
		Point point		= prefs.getPoint();
		HashMap<String, Integer> members = prefs.getMembers();
		
		Log.d(TAG, "flag=" + flag);
		Log.d(TAG, "level=" + level);
		Log.d(TAG, "ratio=" + ratio);
		Log.d(TAG, "millisec=" + millisec);
		Log.d(TAG, "factor=" + factor);
		Log.d(TAG, "title=" + title);
		Log.d(TAG, "point=" + point);
		Log.d(TAG, "members=" + members);
		
		// Setting test
		flag	= !flag;
		level	= 9999;
		ratio	= 9.9999f;
		millisec= 9999999999999999L;
		factor	= 9.999999999999999999999999999999;
		title	= "EDITED!";
		point	= new Point(9,99);
		members = new HashMap<String, Integer>();
		members.put("test1", 1);
		members.put("test2", 2);
		
		prefs.setFlag(flag);
		prefs.setLevel(level);
		prefs.setRatio(ratio);
		prefs.setMillisec(millisec);
		prefs.setFactor(factor);
		prefs.setTitle(title);
		prefs.setPoint(point);
		prefs.setMembers(members);
		
		flag	= prefs.getFlag();
		level	= prefs.getLevel();
		ratio	= prefs.getRatio();
		millisec= prefs.getMillisec();
		factor	= prefs.getFactor();
		title	= prefs.getTitle();
		point	= prefs.getPoint();
		members = prefs.getMembers();
		
		Log.d(TAG, "flag=" + flag);
		Log.d(TAG, "level=" + level);
		Log.d(TAG, "ratio=" + ratio);
		Log.d(TAG, "millisec=" + millisec);
		Log.d(TAG, "factor=" + factor);
		Log.d(TAG, "title=" + title);
		Log.d(TAG, "point=" + point);
		Log.d(TAG, "members=" + members);
    }
}