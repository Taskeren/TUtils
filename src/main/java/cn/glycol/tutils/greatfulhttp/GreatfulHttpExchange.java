package cn.glycol.tutils.greatfulhttp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URI;
import java.util.HashMap;
import java.util.Scanner;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpPrincipal;

/**
 * 对 {@link HttpExchange} 的封装类。
 * 
 * @author Taskeren
 */
public class GreatfulHttpExchange extends HttpExchange {

	protected final HttpExchange exchange;

	public GreatfulHttpExchange(HttpExchange exchange) {
		this.exchange = exchange;
	}

	/*
	 * ******************************************
	 * 
	 * GreatfulHttp Methods
	 * 
	 ******************************************/

	protected String requestBody;

	/**
	 * 以 UTF8 读取请求内容
	 * 
	 * @return 请求内容
	 * @throws IOException
	 */
	public String readRequestBody() throws IOException {
		return readRequestBody("UTF-8");
	}

	/**
	 * 读取请求内容
	 * 
	 * @param charset 编码
	 * @return 请求内容
	 * @throws IOException
	 */
	public String readRequestBody(String charset) throws IOException {
		if (requestBody == null) {
			Scanner scan = new Scanner(getRequestBody(), charset);
			scan.useDelimiter("\\A");
			String str = scan.hasNext() ? scan.next() : "";
			scan.close();
			requestBody = GreatfulHttpUtil.decode(str);
		}
		return requestBody;
	}

	/**
	 * 返回请求并关闭连接
	 * 
	 * @param code 状态码
	 * @throws IOException
	 */
	public void respondAndClose(int code) throws IOException {
		respondAndClose(code, "");
	}

	/**
	 * 返回请求并关闭连接
	 * 
	 * @param code    状态码
	 * @param message 消息
	 * @throws IOException
	 */
	public void respondAndClose(int code, Object message) throws IOException {
		byte[] bytes = message.toString().getBytes();
		exchange.sendResponseHeaders(code, bytes.length == 0 ? -1 : bytes.length);
		OutputStream out = exchange.getResponseBody();
		out.write(bytes);
		out.close();
	}

	/**
	 * 获取 POST 数据
	 * 
	 * @return POST 数据
	 * @throws IOException
	 */
	public HashMap<String, String> getPostParameters() throws IOException {
		return getPostParameters("UTF-8");
	}

	/**
	 * 获取 POST 数据
	 * 
	 * @param enc 编码
	 * @return POST 数据
	 * @throws IOException
	 */
	public HashMap<String, String> getPostParameters(String enc) throws IOException {
		if (exchange.getRequestMethod().equalsIgnoreCase("post")) {
			return GreatfulHttpUtil.parseParameters(readRequestBody(enc));
		} else {
			throw new IllegalStateException("request should be POST method");
		}
	}

	/**
	 * 是否是 Post 请求
	 * 
	 * @return 是否是 Post 请求
	 */
	public boolean isPostRequest() {
		return getRequestMethod().equalsIgnoreCase("post");
	}

	/**
	 * 是否是 Get 请求
	 * 
	 * @return 是否是 Get 请求
	 */
	public boolean isGetRequest() {
		return getRequestMethod().equalsIgnoreCase("get");
	}

	/**
	 * 获取 GET 数据
	 * 
	 * @return GET 数据
	 */
	public HashMap<String, String> getGetParameters() {
		if (exchange.getRequestMethod().equalsIgnoreCase("get")) {
			return GreatfulHttpUtil.parseParameters(getRequestURI().getQuery());
		} else {
			throw new IllegalStateException("request should be GET method");
		}
	}

	/**
	 * 获取数据，自动判断 GET 或 POST
	 * 
	 * @return 数据
	 */
	public HashMap<String, String> getParameters() throws IOException {
		return isGetRequest() ? getGetParameters() : getPostParameters();
	}

	/*
	 * ******************************************
	 * 
	 * Override Methods
	 * 
	 ******************************************/

	public Headers getRequestHeaders() {
		return exchange.getRequestHeaders();
	}

	public Headers getResponseHeaders() {
		return exchange.getResponseHeaders();
	}

	public URI getRequestURI() {
		return exchange.getRequestURI();
	}

	public String getRequestMethod() {
		return exchange.getRequestMethod();
	}

	public HttpContext getHttpContext() {
		return exchange.getHttpContext();
	}

	public void close() {
		exchange.close();
	}

	public InputStream getRequestBody() {
		return exchange.getRequestBody();
	}

	public OutputStream getResponseBody() {
		return exchange.getResponseBody();
	}

	public void sendResponseHeaders(int var1, long var2) throws IOException {
		exchange.sendResponseHeaders(var1, var2);
	}

	public InetSocketAddress getRemoteAddress() {
		return exchange.getRemoteAddress();
	}

	public int getResponseCode() {
		return exchange.getResponseCode();
	}

	public InetSocketAddress getLocalAddress() {
		return exchange.getLocalAddress();
	}

	public String getProtocol() {
		return exchange.getProtocol();
	}

	public Object getAttribute(String var1) {
		return exchange.getAttribute(var1);
	}

	public void setAttribute(String key, Object val) {
		exchange.setAttribute(key, val);
	}

	public void setStreams(InputStream var1, OutputStream var2) {
		exchange.setStreams(var1, var2);
	}

	public HttpPrincipal getPrincipal() {
		return exchange.getPrincipal();
	}

}
