package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.DeviceRepository;
import com.example.demo.pojo.Devices;

@RestController
@RequestMapping("/devices")
public class DeviceController {

	private final DeviceRepository deviceRepository;
	
	public DeviceController(final DeviceRepository deviceRepository) {
		this.deviceRepository = deviceRepository;
	}
	
	@RequestMapping("/get")
	public ResponseEntity<List<Devices>> getDevices() {
		List<Devices> result = deviceRepository.findAll();
		return new ResponseEntity<List<Devices>>(result, HttpStatus.OK);
	}
	
	@RequestMapping("/get/{id}")
	public ResponseEntity<Devices> getDevices(@PathVariable String id) {
		Optional<Devices> result = deviceRepository.findById(id);
		return new ResponseEntity<Devices>(result.get(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/insert", method = RequestMethod.POST)
	public ResponseEntity<Devices> insertDevices(@RequestBody Devices dev) {
		Devices result = deviceRepository.insert(dev);
		return new ResponseEntity<Devices>(result, HttpStatus.OK);
	}
}
