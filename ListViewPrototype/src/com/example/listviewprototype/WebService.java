package com.example.listviewprototype;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import android.net.Uri;
import android.util.Log;

public class WebService {

	public WebService() {
		
	}

	public String get(HashMap<String, String>map) {
		HttpHost host = new HttpHost("127.0.0.1", 9394);

		Uri.Builder uriBuilder = new Uri.Builder();
		uriBuilder.path("/login/login");
		
		//Mapから全てのキーと値のエントリをSet型のコレクションとして取得する(http://www.javadb.jp/Code.sd?id=222)
		Set<Map.Entry<String, String>> entrySet = map.entrySet();

		//キーと値のコレクションの反復子を取得する
		Iterator<Map.Entry<String, String>> it = entrySet.iterator();

		//次の要素がまだ存在する場合はtrueが返される
		while(it.hasNext())
		{
			//キーと値をセットを持つ、Map.Entry型のオブジェクトを取得する
			Map.Entry<String, String> entry = it.next();

			//Map.Entry型のオブジェクトからキーを取得する
			String key = entry.getKey();
			//Map.Entry型のオブジェクトから値を取得する
			String value = entry.getValue();

			Log.v("LogCat", key + "/" + value);
			uriBuilder.appendQueryParameter(key, value);
		}


		String uri = uriBuilder.toString();
	        
		HttpClient httpClient = new DefaultHttpClient();
		HttpParams params = httpClient.getParams();
		HttpConnectionParams.setConnectionTimeout(params, 1000);
		HttpConnectionParams.setSoTimeout(params, 1000);

		HttpUriRequest httpRequest = new HttpGet(uri);

		HttpResponse httpResponse = null;

		try {
			httpResponse = httpClient.execute(host, httpRequest);
			Log.v("LogCat", "got response.");
		}
		catch (ClientProtocolException e) {
			//例外処理
			Log.v("LogCat", e.getMessage());
		}
		catch (IOException e){
			//例外処理
			Log.v("LogCat", e.getMessage());
		}

		String json = null;
	        
		if (httpResponse != null && httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
			HttpEntity httpEntity = httpResponse.getEntity();
			try {
				json = EntityUtils.toString(httpEntity);
			}
			catch (ParseException e) {
				//例外処理
				Log.v("LogCat", e.getMessage());
			}
			catch (IOException e) {
				//例外処理
				Log.v("LogCat", e.getMessage());
			}
			finally {
				try {
					httpEntity.consumeContent();
				}
				catch (IOException e) {
					//例外処理
					Log.v("LogCat", e.getMessage());
				}
			}
		}
	        
		httpClient.getConnectionManager().shutdown();

		return json;
	}
}
