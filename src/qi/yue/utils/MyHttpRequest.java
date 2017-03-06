package qi.yue.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.httpclient.HttpClient;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import net.sf.json.JSONObject;

public class MyHttpRequest {
	public String post(String json, String urlStr) {
		// json = json.replace("'", "\"");
		// HttpPost post = new HttpPost(urlStr);
		try {
			// 定义需要获取的内容来源地址
			System.out.println(urlStr);
			URL url = new URL(urlStr);
			HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();

			// 加入数据
			httpConn.setRequestProperty("Accept-Charset", "utf-8");
			httpConn.setRequestProperty("Content-Type", "application/json");
			httpConn.setRequestMethod("GET");
			System.out.println(httpConn.getResponseCode());
			if (httpConn.getResponseCode() == 200) {
				BufferedReader br = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));
				String line;
				String result = "";
				while ((line = br.readLine()) != null) {
					result += line;
				}
				br.close();
				return result;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	public static Map sendHttpPost(String httpUrl, Object object) {
		Map strResult = null;
		// try {
		// // 设置参数
		// JSONObject jsonObject = JSONObject.fromObject(object);
		// String params = jsonObject.toString();
		// HttpClient client = HttpClients.createDefault();
		// StringEntity entity = new StringEntity(params.toString(),
		// Charset.forName("utf-8"));
		// entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE,
		// "application/x-www-form-urlencoded"));// "application/octet-stream"
		// entity.setContentEncoding(new BasicHeader(HTTP.CONTENT_ENCODING,
		// "UTF-8"));
		// /* httpUrl= URLEncoder.encode(httpUrl, "UTF-8"); */
		// HttpPost post = new HttpPost(httpUrl);
		// post.addHeader("Accept", "*/*");
		// post.addHeader("Content-Type", "application/json");
		// post.setEntity(entity);
		// HttpResponse response = client.execute(post);
		// if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
		// strResult =
		// JsonUtil.stringToObj(EntityUtils.toString(response.getEntity(),
		// "UTF-8"), Map.class);
		// } else {
		// post.abort();
		// Map map = new HashMap();
		// map.put("msg", "Error Response code " +
		// response.getStatusLine().getStatusCode() + " :"
		// + response.getStatusLine().toString());
		// return map;
		// }
		// } catch (ClientProtocolException e) {
		// e.printStackTrace();
		// } catch (UnsupportedEncodingException e) {
		// e.printStackTrace();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		return strResult;
	}
}
