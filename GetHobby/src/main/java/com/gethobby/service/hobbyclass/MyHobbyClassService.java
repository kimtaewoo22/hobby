package com.gethobby.service.hobbyclass;

import java.util.List;
import java.util.Map;

import com.gethobby.service.domain.HobbyClass;
import com.gethobby.service.domain.User;

public interface MyHobbyClassService {
	public List<HobbyClass> getSteamHobbyClass(Map<String, Object> inputData) throws Exception;
	
	public List<HobbyClass> getRecentlyHobbyClass(Map<String, Object> inputData) throws Exception;
	
	public List<HobbyClass> getPurchaseHobbyClassSchedult(String userId) throws Exception; 
	
	public List<User> getHobbyClassBuyerStats(int hobbyClassNo) throws Exception;
	
	public void addSteamHobbyClass(Map<String, Object> inputData) throws Exception;
	
	public void updateHobbyClassSteam(Map<String, Object> inputData) throws Exception;
	
	public void deleteSteamHobbyClass(Map<String, Object> inputData) throws Exception;
}
