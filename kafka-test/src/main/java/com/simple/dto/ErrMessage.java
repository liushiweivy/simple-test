package com.simple.dto;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.jetbrains.annotations.Nullable;
import com.alibaba.fastjson.JSONObject;


/**
 * @author simple
 * @date 2021/7/30 19:48
 */
@Data
@AllArgsConstructor
public class ErrMessage {
	String code;
	String msg;
//
//	public static void main(String[] args) throws ApiException {
//
////		dingtalk.corp-id=dinga4f5b86d99a07962
////		dingtalk.agent-id=868127283
////		dingtalk.app-mode=custom
////		dingtalk.key=suite1qkk4sy9o12crn4k
////		dingtalk.secret=GVGlN35O22uaZKAlaVcrL5TGFPYuq4SvIFVvQzOyZzDUSUOkiRqZGuSG3ul-2_Pe
////		dingtalk.token=123456
////		dingtalk.aes-key=1234567890123456789012345678901234567890123
////		dingtalk.security.jwt.secret-key=mC9jQ8xHh41RWNqZanQBSsFA46jNrFY1GFIF3Pj2ZK1gTVQTKNYRkDqB4vMdSjAK
//		String accessToken = getToken();
//
//		List<Long> strings = getDeptIds(1L);
//		Deque<Long> longs = new LinkedList<>();
//
//		for (Long id : longs) {
//
//		}
//
//		DingTalkClient client1 = new DefaultDingTalkClient("https://oapi.dingtalk.com/user/get");
//		OapiUserGetRequest req = new OapiUserGetRequest();
//		req.setUserid("userid1");
//		req.setHttpMethod("GET");
//		OapiUserGetResponse rsp = client1.execute(req, accessToken);
//		OapiV2UserListRequest req1 = new OapiV2UserListRequest();
//		req.setDeptId(10L);
//		req.setCursor(0L);
//		req.setSize(10L);
//		req.setOrderField("modify_desc");
//		req.setContainAccessLimit(false);
//		req.setLanguage("zh_CN");
//		OapiV2UserListResponse rsp = client.execute(req, access_token);
//	}
//
//	@Nullable
//	private static List<Long> getDeptIds(Long id) throws ApiException {
//		String token = getToken();
//		DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/topapi/v2/department/listsubid");
//		OapiV2DepartmentListsubidRequest req = new OapiV2DepartmentListsubidRequest();
//		req.setDeptId(id);
//		OapiV2DepartmentListsubidResponse rsp = client.execute(req, token);
//		System.out.println(rsp.getBody());
//		String body = rsp.getBody();
//		List<Long> strings = JSONObject.parseArray(JSONObject.parseObject(JSONObject.parseObject(body).getString(
//			"result"))
//			.getString("dept_id_list"), Long.class);
//		return strings;
//	}
//
//	private static String getToken() throws ApiException {
//		DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/gettoken");
//		OapiGettokenRequest request = new OapiGettokenRequest();
//		request.setAppkey("suite1qkk4sy9o12crn4k");
//		request.setAppsecret("GVGlN35O22uaZKAlaVcrL5TGFPYuq4SvIFVvQzOyZzDUSUOkiRqZGuSG3ul-2_Pe");
//		request.setHttpMethod("GET");
//		OapiGettokenResponse response = client.execute(request);
//		String accessToken = response.getBody();
//		return accessToken;
//	}
}
