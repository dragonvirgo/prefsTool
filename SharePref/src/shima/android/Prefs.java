package shima.android;

import java.util.HashMap;

import shima.android.prefs.PreferencesKey;
import shima.android.prefs.PreferencesWrapper;

import android.content.Context;
import android.graphics.Point;

public class Prefs extends PreferencesWrapper {
	private enum Key implements PreferencesKey {
		FLAG, LEVEL, RATIO, MILLISEC, FACTOR, TITLE, POINT, MEMBERS;
	}
	private static final Point defaultPoint = new Point(1, 2);
	
	public Prefs(Context c, String n, int m)	{ super(c, n, m); }
	public Prefs(Context c, String n)			{ super(c, n); }
	public Prefs(Context c, int m)				{ super(c, m); }
	public Prefs(Context c)						{ super(c); }
	// getters
	public boolean getFlag()	{ return get(Key.FLAG, false); }
	public int getLevel()		{ return get(Key.LEVEL, 5); }
	public float getRatio()		{ return get(Key.RATIO, 0.7f); }
	public long getMillisec()	{ return get(Key.MILLISEC, 9876543217L); }
	public double getFactor()	{ return get(Key.FACTOR, 20.7788); }
	public String getTitle()	{ return get(Key.TITLE, "default title"); }
	public Point getPoint()		{ return get(Key.POINT, Point.class, defaultPoint); }
	public HashMap<String, Integer> getMembers() { return get(Key.MEMBERS, HashMap.class, null); }
	// setters
	public void setFlag(boolean v)	{ put(Key.FLAG, v); }
	public void setLevel(int v)		{ put(Key.LEVEL, v); }
	public void setRatio(float v)	{ put(Key.RATIO, v); }
	public void setMillisec(long v)	{ put(Key.MILLISEC, v); }
	public void setFactor(double v)	{ put(Key.FACTOR, v); }
	public void setTitle(String v)	{ put(Key.TITLE, v); }
	public void setPoint(Point v)	{ put(Key.POINT, v); }
	public void setMembers(HashMap<String, Integer> v) { put(Key.MEMBERS, v); }
}
