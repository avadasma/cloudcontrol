package com.cloud.cc.service;

import com.cloud.cc.vo.Group;

public interface GroupService {
	
	/**
	 * ���ӷ�������
	 * @param group
	 * @return
	 */
	int addGroupData(Group group);
	
	/**
	 * ɾ����������
	 * @param groupId
	 * @param flag	�Ƿ�ɾ�������µ��豸
	 * @param userId
	 * @return
	 */
	int delGroup(Integer groupId,boolean flag,Integer userId);
}
