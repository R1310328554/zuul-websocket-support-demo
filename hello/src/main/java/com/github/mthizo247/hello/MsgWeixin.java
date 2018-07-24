package com.github.mthizo247.hello;

import java.util.Date;

public class MsgWeixin {

	private Date createtime;
	private String toUserId;
	private String fromUserId;
	private String appId;
	private String content;
	private String status;
	private String chatId;
	private String msgType;
	private boolean includeMe = true;

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public String getToUserId() {
		return toUserId;
	}

	public void setToUserId(String toUserId) {
		this.toUserId = toUserId;
	}

	public String getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(String fromUserId) {
		this.fromUserId = fromUserId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getChatId() {
		return chatId;
	}

	public void setChatId(String chatId) {
		this.chatId = chatId;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	@Override
	public String toString() {
		return "MsgWeixin{" +
				"createtime=" + createtime +
				", toUserId='" + toUserId + '\'' +
				", fromUserId='" + fromUserId + '\'' +
				", appId='" + appId + '\'' +
				", content='" + content + '\'' +
				", status='" + status + '\'' +
				", chatId='" + chatId + '\'' +
				", msgType='" + msgType + '\'' +
				'}';
	}

	public boolean isIncludeMe() {
		return includeMe;
	}

	public void setIncludeMe(boolean includeMe) {
		this.includeMe = includeMe;
	}
}