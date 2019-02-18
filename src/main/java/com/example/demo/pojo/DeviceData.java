package com.example.demo.pojo;

public class DeviceData {
	String deviceId;
	String value;
	Long timeStamp;
	String host;
	
	public DeviceData() {
		// TODO Auto-generated constructor stub
	}
	
	public DeviceData(String host, String deviceId, String value, Long timeStamp) {
		this.host = host;
		this.deviceId = deviceId;
		this.value = value;
		this.timeStamp = timeStamp;
	}
	
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public Long getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
}
