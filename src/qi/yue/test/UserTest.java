package qi.yue.test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

import net.sf.json.JSONObject;
import qi.yue.utils.MyHttpRequest;

public class UserTest {
	MyHttpRequest http = new MyHttpRequest();

	/**
	 * 用户登录
	 */
	@Test
	public void userLogin() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", "username");
		map.put("password", "password");
		map.put("sign", String.valueOf(new Date().getTime()));
		JSONObject jsonObject = JSONObject.fromObject(map);
		String json = "{\"username\"=\"" + "username1" + "\"," + "\"password\"=\"" + "password" + "\"," + "\"sign\"=\""
				+ 123456789 + "\"}";
		String result = http.post(json, "http://localhost:8080/rideread/users/register?nickname=nickname");
		System.out.println(result);
	}

}
