package com.gethobby.service.openhobbyclass;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.gethobby.common.Search;
import com.gethobby.service.domain.HobbyClass;
import com.gethobby.service.domain.Lesson;


public interface OpenHobbyClassService {
	
	public List getHobbyClassList(Search search) throws Exception; // Out : Ŭ����ID , Ŭ���� ���� , Ŭ���� Ŀ�� �̹��� , Ŭ���� ���� 
	
	public int addMyHobbyClass(String userId) throws Exception; 
	
	public int deleteMyHobbyClass(int hobbyClassNo) throws Exception;
	
	public HobbyClass getHobbyClass(int hobbyClassNo, String what) throws Exception; // Out : flag(1,2,3) ���� ���� save������ ��������
	
	public int saveHobbyClassInfo(HobbyClass hobbyClass, HttpSession session) throws Exception; // In : Ŭ����ID , Ŭ���� Ŀ�� �̹��� , Ŭ���� ����,Ŭ���� ī�װ�,Ŭ���� �ؽ��±�,Ŭ���� ����,Ŭ���� �Ұ���
	
	public int saveKit(HobbyClass hobbyClass) throws Exception; // In : Ŭ����ID, �غ� �̸�, �غ� �Ұ�, �غ� ����, �غ� �̹���
	
	public int saveCheckHobbyClass(int hobbyClassNo) throws Exception; 
	
	public HobbyClass getPreview(int hobbyClassNo, String what) throws Exception; // Out : flag���°��� ���� save���� �ҷ����� , �����ο���, ���ǰ���, Ŭ���� ���� �����Ⱓ
																	// ���Ǻκ��� Ŭ����ID, �����̹���, ��������, ���ǼҰ�

	public int saveLesson(Lesson lesson, HttpSession session) throws Exception; // In : Ŭ����ID, ����ID, �����̹���, ��������, ���ǼҰ�, ���ǿ���, ���ǰ���, ���ǳ���
	
	public int addLesson(int hobbyClassNo) throws Exception;
	
	public int deleteLesson(int hobbyClassNo) throws Exception;
	
	public int addHashtag(HobbyClass hobbyClass) throws Exception;
	
	public List getHashtag(int hobbyClassNo) throws Exception; // Out : hashtag
	
	public int updateTotalMoney(int hobbyClassNo, int money, String what) throws Exception;
	
	public int updateTotalStudent(int hobbyClassNo, int count, String what) throws Exception;
	
	public int updateTotalLesson(int hobbyClassNo, int count, String what) throws Exception;
	
	public List getFileName(HttpServletRequest request, HttpSession session) throws Exception;
	
	public String getFileNo() throws Exception;
	
	public void fileDelete(HttpSession session, List tempFile) throws Exception;
	
	public void sessionDelete(HttpSession session, List tempFile) throws Exception;
	
	// Admin
	public List getHobbyClassListAdmin(Map<String, Object> searchKeywordAndWhat) throws Exception; // Out : �̹���, ����, Ŭ�������̵�, ȸ�����̵�, ���ϱ��, �Ѽ���, ����, �����ο�, ��Ż����
	
	public int saveCheckHobbyClassAdmin(int hobbyClassNo) throws Exception; //
	
	public int updateHobbyClassAdmin(HobbyClass hobbyClass, String what, HttpSession session) throws Exception; // In : save ������ ����
	
}//end of interface HobbyClassService
