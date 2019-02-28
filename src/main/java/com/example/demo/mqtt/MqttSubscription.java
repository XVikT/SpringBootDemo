package com.example.demo.mqtt;

import java.util.UUID;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.example.demo.DemoApplication;
import com.example.demo.dao.DeviceDataRepository;
import com.example.demo.dao.DeviceRepository;
import com.example.demo.pojo.DeviceData;
import com.example.demo.pojo.Devices;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MqttSubscription {

	
	private final DeviceDataRepository deviceDataRepository;
	private final DeviceRepository deviceRepository;
	
	private final DemoApplication da;
	
	public MqttSubscription(DeviceDataRepository deviceDataRepository, DeviceRepository deviceRepository, DemoApplication da) {
		System.out.println("MQTT SUBSCRIPTION!!!");
		this.deviceDataRepository = deviceDataRepository;
		this.deviceRepository = deviceRepository;
		this.da = da;
		da.loadEnv();
		this.connectAndListen();
	}

	public void connectAndListen() {
		String subscriberId = UUID.randomUUID().toString();
		
		System.out.println("ENV: " + DemoApplication.EVN);
		String host = DemoApplication.EVN.getProperty("mqtt.host");
		String port = DemoApplication.EVN.getProperty("mqtt.port");
		
		try {
			IMqttClient subscriber = new MqttClient("tcp://"+host+":"+port, subscriberId);
			subscriber.connect();
			String topic = "testdata";
			subscriber.subscribe(topic, (_topic, _message) -> {
				System.out.println("Message: " + _message);
				ObjectMapper mapper = new ObjectMapper();
				JsonFactory factory = mapper.getFactory();
				JsonParser parser = factory.createParser(_message.toString());
				JsonNode actualObj = mapper.readTree(parser);
				validateAndConvert(actualObj);
			});

		} catch (MqttException e) {
			e.printStackTrace();
		}
	}

	private DeviceData validateAndConvert(JsonNode actualObj) {
		try {
			String deviceId = actualObj.get(DeviceData.DEVICE_ID).toString();
			
			if (!StringUtils.isEmpty(deviceId)) {
				System.out.println("deviceId:: " + deviceId);
				Devices dev = deviceRepository.findDeviceById(deviceId);

				if (dev != null) {
					System.out.println("Inserting record..");
					DeviceData ddata = new DeviceData();
					ddata.setDeviceId(deviceId);
					ddata.setTimeStamp(actualObj.get(DeviceData.TIMESTAMP).asLong());
					ddata.setValue(actualObj.get(DeviceData.VALUE).toString());
					deviceDataRepository.insert(ddata);
				}
			}
		} catch (Exception ex) {
			System.err.println("Exception: " + ex);
		}

		return null;
	}

}
