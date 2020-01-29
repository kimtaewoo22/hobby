package com.gethobby.service.openhobbyclass.impl;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.DiskFileUpload;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.gethobby.common.Search;
import com.gethobby.service.domain.HobbyClass;
import com.gethobby.service.domain.Lesson;
import com.gethobby.service.openhobbyclass.OpenHobbyClassDAO;
import com.gethobby.service.openhobbyclass.OpenHobbyClassService;
import com.gethobby.service.purchase.PurchaseDAO;
import com.gethobby.service.purchase.PurchaseService;

@Service("openHobbyClassServiceImpl")
public class OpenHobbyClassServiceImpl implements OpenHobbyClassService{
		
	//Field
	@Autowired
	@Qualifier("purchaseDAOImpl")
	private PurchaseDAO purchaseDAO;
	public void setPurchaseDAO(PurchaseDAO purchaseDAO) {
		this.purchaseDAO = purchaseDAO;
	}
	@Autowired
	@Qualifier("openHobbyClassDAOImpl")
	private OpenHobbyClassDAO openHobbyClassDAO;
	public void setOpenHobbyClassDAO(OpenHobbyClassDAO openHobbyClassDAO) {
		this.openHobbyClassDAO = openHobbyClassDAO;
	}
	@Autowired
	@Qualifier("purchaseServiceImpl")
	private PurchaseService purchaseService;
	public void setPurchaseService(PurchaseService purchaseService) {
		this.purchaseService = purchaseService;
	}
	private String temDir = "C:\\Users\\User\\git\\hobby\\GetHobby\\WebContent\\resources\\image\\openhobbyclass\\";		

	
	//Constructor
	public OpenHobbyClassServiceImpl() {
		System.out.println(this.getClass());
	}
		
	public List getHobbyClassList(Search search) throws Exception {
		return openHobbyClassDAO.getHobbyClassList(search);
	}

	public int addMyHobbyClass(String userId) throws Exception {
		return openHobbyClassDAO.addMyHobbyClass(userId);
	}

	public int deleteMyHobbyClass(int hobbyClassNo) throws Exception {
		return openHobbyClassDAO.deleteMyHobbyClass(hobbyClassNo);
	}

	public HobbyClass getHobbyClass(int hobbyClassNo, String what) throws Exception {
		Map<String, Object> hobbyClassIdAndWhat = new HashMap<String, Object>();
		hobbyClassIdAndWhat.put("hobbyClassNo", hobbyClassNo);
		hobbyClassIdAndWhat.put("what", what);
		HobbyClass hobbyClass = openHobbyClassDAO.getHobbyClass(hobbyClassIdAndWhat);
		
		if(what.equals("510")) {		
			hobbyClass.setHashtag(openHobbyClassDAO.getHashtag(hobbyClassNo));
		}else if(what.equals("520")) {
			hobbyClass.setLesson(openHobbyClassDAO.getLessonList(hobbyClassNo));	
			return hobbyClass;
		}
		return hobbyClass;
	}
	
	public int saveHobbyClassInfo(HobbyClass hobbyClass, HttpSession session) throws Exception {
		this.sessionDelete(session, hobbyClass.getTempFile());
		addHashtag(hobbyClass);
		return openHobbyClassDAO.saveHobbyClassInfo(hobbyClass);
	}

	public int saveKit(HobbyClass hobbyClass) throws Exception {
		return openHobbyClassDAO.saveKit(hobbyClass);
	}

	public int saveCheckHobbyClass(int hobbyClassNo) throws Exception {
		return openHobbyClassDAO.saveCheckHobbyClass(hobbyClassNo);
	}

	public HobbyClass getPreview(int hobbyClassNo, String what) throws Exception {
		Map<String, Object> hobbyClassNoAndWhat = new HashMap<String, Object>();
		hobbyClassNoAndWhat.put("hobbyClassNo", hobbyClassNo);
		hobbyClassNoAndWhat.put("what", what);
		HobbyClass hobbyClass = openHobbyClassDAO.getPreview(hobbyClassNoAndWhat);
		
		if(what.equals("510")) {		
			hobbyClass.setHashtag(openHobbyClassDAO.getHashtag(hobbyClassNo));
			//user�г���
		}else if(what.equals("520")) {
			hobbyClass.setLesson(openHobbyClassDAO.getLessonList(hobbyClassNo));	
			return hobbyClass;
		}		
		return hobbyClass;
	}

	public int saveLesson(Lesson lesson, HttpSession session) throws Exception {
		this.sessionDelete(session, lesson.getTempFile());
		return openHobbyClassDAO.saveLesson(lesson);
	}

	public int addLesson(int hobbyClassNo) throws Exception {
		return openHobbyClassDAO.addLesson(hobbyClassNo);
	}

	public int deleteLesson(int hobbyClassNo) throws Exception {
		return openHobbyClassDAO.deleteLesson(hobbyClassNo);
	}

	public int addHashtag(HobbyClass hobbyClass) throws Exception {
		Map<String, Object> hashtagMap = new HashMap<String, Object>();
		hashtagMap.put("hobbyClassNo", hobbyClass.getHobbyClassNo());
		hashtagMap.put("userId", hobbyClass.getUser().getUserId());
		int result = -1;
		for(int i=0; i<hobbyClass.getHashtag().size(); i++) {
			hashtagMap.put("hashtag", hobbyClass.getHashtag().get(i));
			openHobbyClassDAO.addHashtag(hashtagMap);
		}
		return result;
	}
	
	public List getHashtag(int hobbyClassNo) throws Exception {
		return openHobbyClassDAO.getHashtag(hobbyClassNo);
	}
	
	public int updateTotalMoney(int hobbyClassNo, int money, String what) throws Exception {
		Map<String, Object> hobbyClassNoAndTotalMoeny = new HashMap<String, Object>();
		hobbyClassNoAndTotalMoeny.put("hobbyClassNo", hobbyClassNo);
		if(what.equals("1")) {
			hobbyClassNoAndTotalMoeny.put("totalMoney", openHobbyClassDAO.getTotalMoney(hobbyClassNo)+money);
		}else if(what.equals("0")) {
			hobbyClassNoAndTotalMoeny.put("totalMoney", openHobbyClassDAO.getTotalMoney(hobbyClassNo)-money);
		}
		return openHobbyClassDAO.updateTotalMoney(hobbyClassNoAndTotalMoeny);
	}
	
	public int updateTotalStudent(int hobbyClassNo, int count, String what) throws Exception {
		Map<String, Object> hobbyClassNoAndTotalStudent = new HashMap<String, Object>();
		hobbyClassNoAndTotalStudent.put("hobbyClassNo", hobbyClassNo);
		if(what.equals("1")) {
			hobbyClassNoAndTotalStudent.put("totalStudent", openHobbyClassDAO.getTotalStudent(hobbyClassNo)+count);
		}else if(what.equals("0")) {
			hobbyClassNoAndTotalStudent.put("totalStudent", openHobbyClassDAO.getTotalStudent(hobbyClassNo)-count);

		}
		return openHobbyClassDAO.updateTotalStudent(hobbyClassNoAndTotalStudent);
	}
	
	public int updateTotalLesson(int hobbyClassId, int count, String what) throws Exception {
		Map<String, Object> hobbyClassIdAndTotalLesson = new HashMap<String, Object>();
		hobbyClassIdAndTotalLesson.put("hobbyClassNo", hobbyClassId);
		if(what.equals("1")) {
			hobbyClassIdAndTotalLesson.put("totalLesson", openHobbyClassDAO.getTotalLesson(hobbyClassId)+count);
		}else if(what.equals("0")) {
			hobbyClassIdAndTotalLesson.put("totalLesson", openHobbyClassDAO.getTotalLesson(hobbyClassId)-count);

		}
		return openHobbyClassDAO.updateTotalLesson(hobbyClassIdAndTotalLesson);
	}
	
	public List getFileName(HttpServletRequest request, HttpSession session) throws Exception {
		
		String fileName = "";	
		List tempFileList = new ArrayList();		
		if(FileUpload.isMultipartContent(request)) {		
			
			DiskFileUpload fileUpload = new DiskFileUpload();
			fileUpload.setRepositoryPath(temDir);
			// setSizeThreshold�� ũ�⸦ ����� �Ǹ� ������ ��ġ�� �ӽ÷� �����Ѵ�.
			fileUpload.setSizeMax(1024 * 1024 * 6000);
			// �ִ� 1�ް����� ���ε� ���� (1024 * 1024 * 100) <- 100MB
			fileUpload.setSizeThreshold(1024 * 100); // �ѹ��� 100k ������ �޸𸮿� ����
			
			if(request.getContentLength() < fileUpload.getSizeMax()) {

				List fileItemList = new ArrayList();
				int size = 0;
					
				// parseRequest()�� FileItem�� �����ϰ� �ִ� ListŸ���� �����Ѵ�.
				fileItemList = fileUpload.parseRequest(request);
				size = fileItemList.size(); // html page���� ���� ������ ������ ���Ѵ�.
				for(int i=0; i<size; i++) {
					
					FileItem fileItem = (FileItem)fileItemList.get(i);
					if(!fileItem.isFormField()) { // ���������̸�..
						if(fileItem.getSize() > 0) { // ������ �����ϴ� if
							//////////////////// tempFile ////////////////////	
							int idx = fileItem.getName().lastIndexOf("\\");
							// getName()�� ��θ� �� �������� ������ lastIndexOf�� �߶󳽴�
							if(idx == -1) {
								idx = fileItem.getName().lastIndexOf("/");
							}
							String extension = fileItem.getName().substring(idx + 1);
							fileName = this.getFileNo()+extension.substring(extension.length()-4, extension.length());
							
							try {
								File uploadedFile = new File(temDir, fileName);
								fileItem.write(uploadedFile);
								fileItem.delete();
								
								tempFileList.add(fileName);
								session.setAttribute(fileName, fileName);
								
							}catch(IOException e) {
								System.out.println(e);
							}
							//////////////////// tempFile ////////////////////		
						}else {
							System.out.println("���� �뷮�� 0 �Դϴ�..");
						}
					}else {
						System.out.println("���� ������ �ƴմϴ�..");
					}// If
				}// for
				
			}else { // ���ε��ϴ� ������ setSizeMax���� ū���
				int overSize = (request.getContentLength() / 10000000);
				System.out.println("<script>alert('������ ũ��� 1000MB���� �Դϴ�. �ø��� ���� �뷮��" + overSize + "MB�Դϴ�');");
				System.out.println("histroy.back();</scrpt>");
			}
		}else {
			System.out.println("���ڵ� Ÿ���� multipart/form-data�� �ƴմϴ�..");
		}
		
		return tempFileList;
	}
	
	public String getFileNo() throws Exception {
		Random random = new Random();
		String fileNo = (random.nextInt(90)+10) + "" + System.currentTimeMillis();
		return fileNo;
	}
	
	public void fileDelete(HttpSession session, List fileName) throws Exception {
		
		for(int fileListNo=0; fileListNo<fileName.size(); fileListNo++) {
			List tempFileList = (List)session.getAttribute(fileName.get(fileListNo).toString());
			for(int listNo=0; listNo<tempFileList.size(); listNo++) {
				File file = new File(temDir, (String)tempFileList.get(listNo));
				file.delete();
				
			}
			session.removeAttribute(fileName.get(fileListNo).toString());
		}
		
	}
	
	public void sessionDelete(HttpSession session, List fileName) throws Exception {
		
		for(int fileListNo=0; fileListNo<fileName.size(); fileListNo++) {
			session.removeAttribute(fileName.get(fileListNo).toString());
		}
	}

	public List getHobbyClassListAdmin(Map<String, Object> searchKeywordAndWhat) throws Exception {
		return openHobbyClassDAO.getHobbyClassListAdmin(searchKeywordAndWhat);
	}
	
	public int saveCheckHobbyClassAdmin(int hobbyClassNo) throws Exception {
		return openHobbyClassDAO.saveCheckHobbyClassAdmin(hobbyClassNo);
	}

	public int updateHobbyClassAdmin(HobbyClass hobbyClass, String what, HttpSession session) throws Exception {
		if(what.equals("510")) {
			return saveHobbyClassInfo(hobbyClass, session);
		}else if(what.equals("520")) {
			return saveLesson((Lesson)hobbyClass.getLesson().get(0), session);
		}
		return saveKit(hobbyClass);
	}

}//end of ServiceImpl
