package com.example.demo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.DeviceDataRepository;
import com.example.demo.dao.DeviceRepository;
import com.example.demo.pojo.DeviceData;
import com.example.demo.pojo.Devices;
import com.example.demo.pojo.ErrorCode;
import com.example.demo.pojo.ErrorMessage;

@RestController
@RequestMapping ("/data")
public class DeviceDataController {
	private final DeviceDataRepository deviceDataRepository;
	private final DeviceRepository deviceRepository;
	
	public DeviceDataController(final DeviceDataRepository deviceDataRepository, final DeviceRepository deviceRepository) {
		this.deviceDataRepository = deviceDataRepository;
		this.deviceRepository = deviceRepository;
	}
	
	@SuppressWarnings ({ "rawtypes", "unchecked" })
	@RequestMapping ("/get/{deviceId}")
	public ResponseEntity getDevices(@PathVariable String deviceId) {
		Devices dev = deviceRepository.findDeviceById(deviceId);
		if(dev == null) {
			ErrorMessage error = new ErrorMessage("Device not present", ErrorCode.DEVICE_NOT_FOUND);
			return new ResponseEntity(error, HttpStatus.NOT_ACCEPTABLE);
		}
		List<DeviceData> result = deviceDataRepository.findByDevice(deviceId);
		return new ResponseEntity<List<DeviceData>>(result, HttpStatus.OK);
	}
}
