package com.cloud.cc.service;

import com.cloud.cc.vo.Devices;

public interface DevicesService {

	/**
	 *����豸
	 * @param devices
	 * @return
	 */
	int addDevice(Devices devices);
	
	/**
	 * ɾ������/�����豸����
	 * @param devicesId
	 * @param userId
	 * @return
	 */
	int delDevice(Integer[] devicesId,Integer userId);
}
