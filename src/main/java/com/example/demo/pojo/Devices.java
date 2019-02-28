package com.example.demo.pojo;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="Devices")
public class Devices {

	String deviceId;
	String name;
	String host;
	
	public String getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHost() {
		return host;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String toString() {
		return deviceId + "::" + name + "::" + host;
	}
}
