package com.example.demo.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.QueryByExampleExecutor;
import org.springframework.stereotype.Repository;

import com.example.demo.pojo.Devices;



@Repository
public interface DeviceRepository extends MongoRepository<Devices, String>, QueryByExampleExecutor<Devices> {
	
	@Query("{'deviceId':?0}")
	Devices findDeviceById(String deviceId);
	
	@Query("{'name':?0}")
	Devices findDeviceByName(String name);
	
}