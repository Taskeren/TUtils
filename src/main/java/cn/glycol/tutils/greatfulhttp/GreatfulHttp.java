package cn.glycol.tutils.greatfulhttp;

import java.io.IOException;

import com.sun.net.httpserver.*;

public abstract class GreatfulHttp implements HttpHandler {

	@Override
	public void handle(HttpExchange exchange) throws IOException {
		onHandle(new GreatfulHttpExchange(exchange));
	}

	/**
	 * Handling method.
	 * 
	 * @param exchange the exchange
	 * @throws IOException the io exception
	 */
	public abstract void onHandle(GreatfulHttpExchange exchange) throws IOException;

}
