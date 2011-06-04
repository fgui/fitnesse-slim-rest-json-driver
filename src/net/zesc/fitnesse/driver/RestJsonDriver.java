package net.zesc.fitnesse.driver;

import java.io.IOException;
import java.util.Random;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.apache.http.client.ClientProtocolException;

public class RestJsonDriver {

	private static RestClient restClient = new RestClient();

	public RestJsonDriver() {
	}

	/**
	 * Convenient method to assign variables in FitNesse/SLIM. There may be a
	 * better way to do this in FitNesse/SLIM
	 * 
	 * @param str
	 *            string to be returned.
	 * @return param str.
	 */
	public String echo(String str) {
		return str;
	}

	/**
	 * Convert and id to string.
	 * Ignored decimal part.
	 * This is useful if you get an ID as a number like 15.00 which you need to use
	 * later on as a String 15.  
	 * @param id
	 * @return
	 */
	public String idToString(Number id) {
		return Long.toString(id.longValue());
	}

	/**
	 * Convenient method to generate random strings. Provide a String and the
	 * same String with a random integer suffix will be returned.
	 */
	public String concatRandomInt(String str) {
		return str + Math.abs(new Random().nextInt());
	}

	public String post(String path, String data)
			throws ClientProtocolException, IOException {
		return restClient.post(path, data).toString();
	}

	public String put(String path, String data) throws ClientProtocolException,
			IOException {
		return restClient.put(path, data).toString();
	}

	public String get(String path) throws ClientProtocolException, IOException {
		return restClient.get(path).toString();
	}

	public String delete(String path) throws ClientProtocolException, IOException {
		return restClient.delete(path).toString();
	}

	/**
	 * retrieve a javascript value from a json string.
	 */
	public Object retrieveJsJson(String js, String json) throws ScriptException {
		ScriptEngineManager factory = new ScriptEngineManager();
		ScriptEngine engine = factory.getEngineByName("JavaScript");
		return engine.eval("(" + json + ")." + js);
	}

	/**
	 * same as retrieveJsJson here because it may be a better method name.
	 * 
	 * @throws ScriptException
	 */
	public Object evalJsJson(String js, String json) throws ScriptException {
		return retrieveJsJson(js, json);
	}
}
