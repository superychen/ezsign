package com.ezcsign.service.PayService.tenpay.util;

public class WaybillRoute {

	private String id;//路由节点信息编号，每一个id代表一条不同的路由节点信息。
	private String mailno;//顺丰运单号
	private String orderid;//客户订单号
	private String acceptTime;//路由节点产生的时间，格式：YYYY-MM-DD HH24:MM:SS，示例：2012-7-30 09:30:00。
	private String acceptAddress;//路由节点发生的城市
	private String remark;//路由节点具体描述
	private String opCode;//路由节点操作码
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMailno() {
		return mailno;
	}
	public void setMailno(String mailno) {
		this.mailno = mailno;
	}
	public String getOrderid() {
		return orderid;
	}
	public void setOrderid(String orderid) {
		this.orderid = orderid;
	}
	public String getAcceptTime() {
		return acceptTime;
	}
	public void setAcceptTime(String acceptTime) {
		this.acceptTime = acceptTime;
	}
	public String getAcceptAddress() {
		return acceptAddress;
	}
	public void setAcceptAddress(String acceptAddress) {
		this.acceptAddress = acceptAddress;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getOpCode() {
		return opCode;
	}
	public void setOpCode(String opCode) {
		this.opCode = opCode;
	}
}
