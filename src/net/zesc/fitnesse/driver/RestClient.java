package net.zesc.fitnesse.driver;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

class RestClient {

	private static final String APPLICATION_JSON = "application/json";

	private HttpClient httpClient = new DefaultHttpClient();

	// default constructor ok

	public ResponseJsonWrapper get(String path) throws ClientProtocolException,
			IOException {
		HttpGet request = new HttpGet(path);
		setupRequestJSON(request);
		return new ResponseJsonWrapper(httpClient.execute(request));
	}

	public ResponseJsonWrapper post(String path, String data)
			throws ClientProtocolException, IOException {
		HttpPost request = null;
		request = new HttpPost(path);
		setupRequestJsonAddBody(request, data);
		return new ResponseJsonWrapper(httpClient.execute(request));
	}

	public ResponseJsonWrapper put(String path, String data)
			throws ClientProtocolException, IOException {
		HttpPut request = null;
		request = new HttpPut(path);
		setupRequestJsonAddBody(request, data);
		return new ResponseJsonWrapper(httpClient.execute(request));
	}

	public ResponseJsonWrapper delete(String path)
			throws ClientProtocolException, IOException {
		HttpDelete request = null;
		request = new HttpDelete(path);
		setupRequestJSON(request);
		return new ResponseJsonWrapper(httpClient.execute(request));
	}

	private void setupRequestJsonAddBody(
			HttpEntityEnclosingRequestBase request, String data)
			throws UnsupportedEncodingException {
		StringEntity requestBody = new StringEntity(data, "UTF-8");
		request.setEntity(requestBody);
		setupRequestJSON(request);
	}

	private void setupRequestJSON(HttpRequestBase request) {
		request.addHeader("Accept", APPLICATION_JSON);
		request.addHeader("Content-Type", APPLICATION_JSON);
	}

}
