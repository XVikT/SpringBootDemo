package com.example.demo;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
@RestController

public class DemoApplication {
	
	public static Environment EVN;
	
	@Autowired
	private Environment env;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@RequestMapping(value = "/")
	public String hello() {
		return "Hello World";
	}
	
	@RequestMapping(value = "/heartbeat")
	public ResponseEntity<Map<String, Object>> data() {
		Long time = Calendar.getInstance().getTimeInMillis();
		InetAddress inetAddress = null;
		try {
			inetAddress = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		Map<String, Object> envProps = new HashMap<String, Object>();
		envProps.put("mqtt-host", env.getProperty("mqtt.host"));
		envProps.put("mqtt-port", env.getProperty("mqtt.port"));
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("time", new Date(time).toString());
		data.put("unix-time", time.toString());
		data.put("host", inetAddress.getHostName());
		data.put("ip-address", inetAddress.getHostAddress());
		data.put("env", envProps);
		return new ResponseEntity<Map<String, Object>>(data, HttpStatus.OK);
	}
	
	public void loadEnv() {
		System.out.println("ENV: " + env);
		EVN = env;
	}
	
}

