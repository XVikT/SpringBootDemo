package com.example.demo;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Calendar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.mqtt.MqttSubscription;
import com.example.demo.pojo.DeviceData;


@SpringBootApplication
@RestController

public class DemoApplication {

	public static void main(String[] args) {
		//Thread t = new Thread(()->{System.out.println("Starting Mqtt Listener..\n");MqttSubscription.connectAndListen();}) ;
		//t.start();
		SpringApplication.run(DemoApplication.class, args);
	}

	@RequestMapping(value = "/")
	   public String hello() {
	      return "Hello World";
	   }
	
	@RequestMapping(value = "/data")
	   public ResponseEntity<DeviceData> data() {
		
		  Long time = Calendar.getInstance().getTimeInMillis();
		  String value = String.valueOf(Math.random());
		  InetAddress inetAddress = null;
		  try {
				inetAddress = InetAddress.getLocalHost();
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  DeviceData d = new DeviceData(inetAddress.getHostName(),"Dev1", value, time);
	      return new ResponseEntity<DeviceData>(d, HttpStatus.OK);
	   }
	
}

