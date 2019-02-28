package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.DeviceRepository;
import com.example.demo.pojo.Devices;
import com.example.demo.pojo.ErrorCode;
import com.example.demo.pojo.ErrorMessage;

@RestController
@RequestMapping("/devices")
public class DeviceController {

	private final DeviceRepository deviceRepository;
	
	public DeviceController(final DeviceRepository deviceRepository) {
		this.deviceRepository = deviceRepository;
	}
	
	@RequestMapping("/get")
	public ResponseEntity<Iterable<Devices>> getDevices() {
		Iterable<Devices> result = deviceRepository.findAll(); //findAll();
		return new ResponseEntity<Iterable<Devices>>(result, HttpStatus.OK);
	}
	
	@RequestMapping("/get/{id}")
	public ResponseEntity<Devices> getDevices(@PathVariable String id) {
		Optional<Devices> result = deviceRepository.findById(id);
		return new ResponseEntity<Devices>(result.get(), HttpStatus.OK);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value="/insert", method = RequestMethod.POST)
	public ResponseEntity insertDevices(@RequestBody Devices dev) {
		List<ErrorMessage> errors = new ArrayList<ErrorMessage>();
		boolean hasError = false;
		
		if(StringUtils.isEmpty(dev.getDeviceId())) {
			errors.add(new ErrorMessage("Device Id is missing", ErrorCode.MISSING_INFO));
			hasError = true;
		}
		
		if(StringUtils.isEmpty(dev.getName())) {
			errors.add(new ErrorMessage("Device Name is missing", ErrorCode.MISSING_INFO));
			hasError = true;
		}
		
		if(StringUtils.isEmpty(dev.getHost())) {
			errors.add(new ErrorMessage("Device Host is missing", ErrorCode.MISSING_INFO));
			hasError = true;
		}
		
		Devices devId = deviceRepository.findDeviceById(dev.getDeviceId());
		
		if(devId != null) {
			errors.add(new ErrorMessage("Device ID already exists", ErrorCode.DUPLICATE_ENTRY));
			hasError = true;
		}
		
		Devices devName = deviceRepository.findDeviceByName(dev.getName());
		
		if(devName != null) {
			errors.add(new ErrorMessage("Device Name already exists", ErrorCode.DUPLICATE_ENTRY));
			hasError = true;
		}
		
		if(hasError) {
			return new ResponseEntity (errors, HttpStatus.NOT_ACCEPTABLE);
		}
		
		Devices result = deviceRepository.save(dev);
		return new ResponseEntity<Devices>(result, HttpStatus.OK);
	}
}
