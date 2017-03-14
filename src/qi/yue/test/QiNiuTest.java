package qi.yue.test;

import org.junit.Test;

import com.qiniu.util.Auth;

import qi.yue.common.MessageCommon;

public class QiNiuTest {
	@Test
	public void test() {
		Auth auth = Auth.create(MessageCommon.QI_NIU_ACCESS_KEY, MessageCommon.QI_NIU_SECRE_KEY);
		String upToken = auth.uploadToken(MessageCommon.QI_NIU_BUCKET, "filename");
		System.out.println(upToken);
	}
}
