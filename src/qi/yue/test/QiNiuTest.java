package qi.yue.test;

import java.util.Map;

import org.junit.Test;

import com.google.gson.JsonObject;
import com.qiniu.util.Auth;
import com.yunpian.sdk.model.ResultDO;
import com.yunpian.sdk.model.SendSingleSmsInfo;
import com.yunpian.sdk.service.SmsOperator;
import com.yunpian.sdk.service.YunpianRestClient;

import net.sf.json.JSONObject;
import qi.yue.common.MessageCommon;
import qi.yue.utils.StringUtil;

public class QiNiuTest {
	@Test
	public void test() {
		Auth auth = Auth.create(MessageCommon.QI_NIU_ACCESS_KEY, MessageCommon.QI_NIU_SECRE_KEY);
		String upToken = auth.uploadToken(MessageCommon.QI_NIU_BUCKET, "filename");
		System.out.println(upToken);
	}

	@Test
	public void testSendSms() {
		String mobile = "18374911770";
		String randCode = StringUtil.getRandomNumber(MessageCommon.RAND_LENGTH);
		String text = StringUtil.getYunPianCode(randCode);
		YunpianRestClient client = new YunpianRestClient(MessageCommon.YUN_PIAN_APIKEY);// 用apikey生成client,可作为全局静态变量
		SmsOperator smsOperator = client.getSmsOperator();// 获取所需操作类
		ResultDO<SendSingleSmsInfo> result = smsOperator.singleSend(mobile, text);// 发送短信,ResultDO<?>.isSuccess()判断是否成功
		System.out.println(result);
		JSONObject json = JSONObject.fromObject(result);
		JSONObject data = (JSONObject) json.get("data");
		String code = data.get("code").toString();
		System.out.println(code);
	}

	// /**
	// * 基于HttpClient 4.3的通用POST方法
	// *
	// * @param url
	// * 提交的URL
	// * @param paramsMap
	// * 提交<参数，值>Map
	// * @return 提交响应
	// */
	//
	// public static String post(String url, Map<String, String> paramsMap) {
	// CloseableHttpClient client = HttpClients.createDefault();
	// String responseText = "";
	// CloseableHttpResponse response = null;
	// try {
	// HttpPost method = new HttpPost(url);
	// if (paramsMap != null) {
	// List<NameValuePair> paramList = new ArrayList<NameValuePair>();
	// for (Map.Entry<String, String> param : paramsMap.entrySet()) {
	// NameValuePair pair = new BasicNameValuePair(param.getKey(),
	// param.getValue());
	// paramList.add(pair);
	// }
	// method.setEntity(new UrlEncodedFormEntity(paramList, ENCODING));
	// }
	// response = client.execute(method);
	// HttpEntity entity = response.getEntity();
	// if (entity != null) {
	// responseText = EntityUtils.toString(entity, ENCODING);
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// } finally {
	// try {
	// response.close();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }
	// return responseText;
	// }
}
