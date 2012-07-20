package shima.android.prefs;

import com.google.gson.Gson;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class PreferencesWrapper {
	private Context context;
	private String name = this.getClass().getSimpleName();
	protected int mode = Context.MODE_PRIVATE;
	
	private SharedPreferences preferences;
	private Editor editor;
	private Gson gson = new Gson();
	
	protected PreferencesWrapper(Context c, String n, int m)	{ context = c; name = n; mode = m; }
	protected PreferencesWrapper(Context c, String n)			{ context = c; name = n; }
	protected PreferencesWrapper(Context c, int m)				{ context = c; mode = m; }
	protected PreferencesWrapper(Context c)						{ context = c; }
	@SuppressWarnings("unchecked") protected static <T> T autoCast(Object src) { return (T)src; }
	public void begin() {
		preferences = fromContext();
		editor = null;
	}
	public void end() {
		if (editor != null) {
			editor.commit();
			editor = null;
		}
		preferences = null;
	}
	
	private SharedPreferences fromContext() { return context.getSharedPreferences(name, mode); }
	private boolean singular() { return preferences == null; }
	private SharedPreferences preferences() { return singular()? fromContext() : preferences; }
	private Editor editor() {
		if (singular()) {
			return fromContext().edit();
		} else {
			if (editor == null)
				editor = preferences.edit();
			return editor;
		}
	}
	// getters
	protected boolean	get(PreferencesKey key, boolean def){ return preferences().getBoolean(key.name(), def); }
	protected int		get(PreferencesKey key, int def)	{ return preferences().getInt(key.name(), def); }
	protected float		get(PreferencesKey key, float def)	{ return preferences().getFloat(key.name(), def); }
	protected long		get(PreferencesKey key, long def)	{ return preferences().getLong(key.name(), def); }
	protected String	get(PreferencesKey key, String def)	{ return preferences().getString(key.name(), def); }
	protected double	get(PreferencesKey key, double def) {
		SharedPreferences p = preferences();
		return (p.contains(key.name()))? Double.longBitsToDouble(p.getLong(key.name(), 0L)) : def;
	}	
	protected <T> T get(PreferencesKey key, Class<T> cls, T def) {
		SharedPreferences p = preferences();
		return (p.contains(key.name()))? gson.fromJson(p.getString(key.name(), null), cls) : def;
	}	
	// setters
	protected void put(PreferencesKey key, boolean value) {
		Editor e = editor().putBoolean(key.name(), value);
		if (singular()) e.commit();
	}
	protected void put(PreferencesKey key, int value) {
		Editor e = editor().putInt(key.name(), value);
		if (singular()) e.commit();
	}
	protected void put(PreferencesKey key, float value) {
		Editor e = editor().putFloat(key.name(), value);
		if (singular()) e.commit();
	}
	protected void put(PreferencesKey key, long value) {
		Editor e = editor().putLong(key.name(), value);
		if (singular()) e.commit();
	}
	protected void put(PreferencesKey key, String value) {
		Editor e = editor().putString(key.name(), value);
		if (singular()) e.commit();
	}
	protected void put(PreferencesKey key, double value) {
		Editor e = editor().putLong(key.name(), Double.doubleToLongBits(value));
		if (singular()) e.commit();
	}
	protected <T> void put(PreferencesKey key, T value) {
		Editor e = editor().putString(key.name(), gson.toJson(value));
		if (singular()) e.commit();
	}
}
