package net.yaliun.basic;

import java.io.IOError;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

class Header{
	private String requestId;
	private String callId;
	
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getCallId() {
		return callId;
	}
	public void setCallId(String callId) {
		this.callId = callId;
	}
	
	@Override
	public String toString() {
		return "Header [requestId=" + requestId + ", callId=" + callId + "]";
	}	
	
}

class DownloadOrder{
	private Header header;
	private String iccid;
	private String eid;
	private String message;
	
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public String getIccid() {
		return iccid;
	}
	public void setIccid(String iccid) {
		this.iccid = iccid;
	}
	public String getEid() {
		return eid;
	}
	public void setEid(String eid) {
		this.eid = eid;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return "DownloadOrder [header=" + header + ", iccid=" + iccid + ", eid=" + eid + ", message=" + message + "]";
	}	
}

public class JacksonTest {

	public static void main(String[] args) throws JsonProcessingException, IOException{
		
		ObjectMapper mapper = new ObjectMapper();
		
		DownloadOrder download = new DownloadOrder();
		Header header = new Header();
		header.setRequestId("req-1");
		header.setCallId("cal-135");
		
		download.setHeader(header);
		download.setIccid("iccid-123");
		download.setEid("eid-456");
		download.setMessage("OK");
		
		String jsonStr = mapper.writeValueAsString(download);
		
		System.out.println(jsonStr);
		
		DownloadOrder order = mapper.readValue(jsonStr, DownloadOrder.class);
		
		System.out.println("req-id : " + order.getHeader().getRequestId());
		System.out.println("cal-id : " + order.getHeader().getCallId());
		System.out.println("iccid : " + order.getIccid());
		System.out.println("eid : " + order.getEid());
		System.out.println("msg : " + order.getMessage());
		
		
		JsonNode rootNode = mapper.readTree(jsonStr);
		JsonNode headNode = rootNode.path("header");
		
		String reqId = headNode.path("requestId").textValue();
		String callId = headNode.path("callId").textValue();
		String iccid = rootNode.path("iccid").textValue();
		String eid = rootNode.path("eid").textValue();
		String msg = rootNode.path("message").textValue();
		
		System.out.println("tree - reqId : " + reqId);
		System.out.println("tree - callId : " + callId);
		System.out.println("tree - iccid : " + iccid);
		System.out.println("tree - eid : " + eid);
		System.out.println("tree - msg : " + msg);
		
		System.out.println(mapper.writeValueAsString(rootNode));

	}

}
