package com.example.demo.dao;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import com.example.demo.pojo.DeviceData;

public interface DeviceDataRepository extends MongoRepository<DeviceData, String>, QueryByExampleExecutor<DeviceData> {
	@Query("{'deviceId':?0}")
	List<DeviceData> findByDevice(String deviceId);
}
