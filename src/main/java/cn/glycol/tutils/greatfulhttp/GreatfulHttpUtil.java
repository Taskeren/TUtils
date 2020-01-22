package cn.glycol.tutils.greatfulhttp;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.HashMap;

import com.google.common.collect.Maps;

public class GreatfulHttpUtil {

	/**
	 * 以 UTF8 解码 URL
	 * 
	 * @param raw 原内容
	 * @return 解码内容
	 * @throws IOException
	 */
	public static String decode(String raw) throws IOException {
		return decodeString(raw, "UTF-8");
	}

	/**
	 * 解码 URL
	 * 
	 * @param raw 原内容
	 * @param env 编码
	 * @return 解码内容
	 * @throws IOException
	 */
	public static String decodeString(String raw, String env) throws IOException {
		return URLDecoder.decode(raw, env);
	}

	/**
	 * 反序列化输入参数
	 * 
	 * @param str 输入参数
	 * @return 反序列结果
	 */
	public static HashMap<String, String> parseParameters(String str) {
		HashMap<String, String> table = Maps.newHashMap();
		if(str != null && !str.isEmpty()) {
			String[] parts = str.split("&"); // "a=1&b=2" => ["a=1", "b=2"]
			for(String part : parts) {
				String[] kvpair = part.split("="); // "a=1" => ["a", "1"]
				if(kvpair[0] != null) {
					table.put(kvpair[0], kvpair.length > 1 ? kvpair[1] : ""); // to put them into the map.
				}
			}
		}
		return table;
	}

}
