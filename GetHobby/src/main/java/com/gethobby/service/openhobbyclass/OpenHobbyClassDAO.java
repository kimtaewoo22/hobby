package com.gethobby.service.openhobbyclass;

import java.util.List;
import java.util.Map;

import com.gethobby.common.Search;
import com.gethobby.service.domain.HobbyClass;
import com.gethobby.service.domain.Lesson;

public interface OpenHobbyClassDAO {
	
	public List getHobbyClassList(Search search) throws Exception; // Out : Ŭ����ID , Ŭ���� ���� , Ŭ���� Ŀ�� �̹��� , Ŭ���� ���� 
	
	public int addMyHobbyClass(String userId) throws Exception; 
	
	public int deleteMyHobbyClass(int hobbyClassNo) throws Exception;
	
	public HobbyClass getHobbyClass(Map hobbyClassIdAndWhat) throws Exception; // Out : flag(1,2,3) ���� ���� save������ ��������
	
	public int saveHobbyClassInfo(HobbyClass hobbyClass) throws Exception; // In : Ŭ����ID , Ŭ���� Ŀ�� �̹��� , Ŭ���� ����,Ŭ���� ī�װ�,Ŭ���� �ؽ��±�,Ŭ���� ����,Ŭ���� �Ұ���
	
	public int saveKit(HobbyClass hobbyClass) throws Exception; // In : Ŭ����ID, �غ� �̸�, �غ� �Ұ�, �غ� ����, �غ� �̹���
	
	public int saveCheckHobbyClass(int hobbyClassNo) throws Exception; 
	
	public HobbyClass getPreview(Map hobbyClassNoAndWhat) throws Exception; // Out : flag���°��� ���� save���� �ҷ����� , �����ο���, ���ǰ���, Ŭ���� ���� �����Ⱓ
																	// ���Ǻκ��� Ŭ����ID, �����̹���, ��������, ���ǼҰ�
	
	public int saveLesson(Lesson lesson) throws Exception; // In : Ŭ����ID, ����ID, �����̹���, ��������, ���ǼҰ�, ���ǿ���, ���ǰ���, ���ǳ���
	
	public Lesson getLesson(int lessonNo) throws Exception;
	
	public List getLessonList(int hobbyClassNo) throws Exception;
	
	public int addLesson(int hobbyClassNo) throws Exception;
	
	public int deleteLesson(int hobbyClassNo) throws Exception;
	
	public int addHashtag(Map<String, Object> hashtagMap) throws Exception;
	
	public List getHashtag(int hobbyClassNo) throws Exception; // Out : hashtag
	
	public int updateTotalMoney(Map<String, Object> hobbyClassNoAndTotalMoeny) throws Exception;
	
	public int updateTotalStudent(Map<String, Object> hobbyClassNoAndTotalStudent) throws Exception;
	
	public int updateTotalLesson(Map<String, Object> hobbyClassNoAndTotalLesson) throws Exception;
	
	public int getTotalStudent(int hobbyClassNo) throws Exception;
	
	public int getTotalLesson(int hobbyClassNo) throws Exception;
	
	public int getTotalMoney(int hobbyClassNo) throws Exception;
	
	// Admin
	public List getHobbyClassListAdmin(Map<String, Object> searchKeywordAndWhat) throws Exception; // Out : �̹���, ����, Ŭ�������̵�, ȸ�����̵�, ���ϱ��, �Ѽ���, ����, �����ο�, ��Ż����
	
	public int saveCheckHobbyClassAdmin(int hobbyClassNo) throws Exception; //
	
	public int updateHobbyClassAdmin(HobbyClass hobbyClass) throws Exception; // In : save ������ ����
	
}//end of interface HobbyClassDAO
