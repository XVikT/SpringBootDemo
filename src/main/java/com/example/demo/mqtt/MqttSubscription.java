package com.example.demo.mqtt;

import java.util.UUID;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MqttSubscription {
	
	public static void connectAndListen() {
		String subscriberId = UUID.randomUUID().toString();
		try {
			IMqttClient subscriber = new MqttClient("tcp://localhost:1883",subscriberId);
			subscriber.connect();
			String topic = "testdata";
			subscriber.subscribe(topic, (_topic, _message) -> {
				System.out.println("Message: " + _message);
				 ObjectMapper mapper = new ObjectMapper();
				    JsonFactory factory = mapper.getFactory();
				    JsonParser parser = factory.createParser(_message.toString());
				    JsonNode actualObj = mapper.readTree(parser);
				    System.out.println("Id: " + actualObj.get("id"));
				    System.out.println("Name: " + actualObj.get("name"));
				
			});
			
		} catch (MqttException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
