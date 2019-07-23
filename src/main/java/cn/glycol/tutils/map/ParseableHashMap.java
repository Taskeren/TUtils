package cn.glycol.tutils.map;

import static cn.glycol.tutils.natives.TParser.*;

import java.util.HashMap;
import java.util.Map;

public class ParseableHashMap<K, V> extends HashMap<K, V> {

	private static final long serialVersionUID = -444796654696326279L;

	public ParseableHashMap() {
		super();
	}
	
	public ParseableHashMap(Map<? extends K, ? extends V> map) {
		super(map);
	}
	
	public ParseableHashMap(int initialCapacity) {
		super(initialCapacity);
	}
	
	public ParseableHashMap(int initialCapacity, float loadFactor) {
		super(initialCapacity, loadFactor);
	}
	
	public Byte getByte(Object key, byte _default) {
		return parseByte(String.valueOf(get(key)), _default);
	}
	
	public Short getShort(Object key, short _default) {
		return parseShort(String.valueOf(get(key)), _default);
	}
	
	public Integer getInteger(Object key, int _default) {
		return parseInteger(String.valueOf(get(key)), _default);
	}
	
	public Long getLong(Object key, long _default) {
		return parseLong(String.valueOf(get(key)), _default);
	}
	
	public Float getFloat(Object key, float _default) {
		return parseFloat(String.valueOf(get(key)), _default);
	}
	
	public Double getDouble(Object key, double _default) {
		return parseDouble(String.valueOf(get(key)), _default);
	}
	
	public Boolean getBoolean(Object key, boolean _default) {
		return parseBoolean(String.valueOf(get(key)), _default);
	}
	
}
