package com.example.demo.pojo;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="DeviceData")
public class DeviceData {
	
	public static final String DEVICE_ID="deviceId";
	public static final String VALUE="value";
	public static final String TIMESTAMP="timeStamp";
	
	String deviceId;
	String value;
	Long timeStamp;
	
	public DeviceData() {
		// TODO Auto-generated constructor stub
	}
	
	public DeviceData(String deviceId, String value, Long timeStamp) {
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
	
	public String toString() {
		return this.deviceId + "::" + this.value + "::" + this.timeStamp;
	}
}
