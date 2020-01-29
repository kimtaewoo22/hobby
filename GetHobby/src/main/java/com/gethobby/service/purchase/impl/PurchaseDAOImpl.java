package com.gethobby.service.purchase.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.gethobby.common.Search;
import com.gethobby.service.domain.HobbyClass;
import com.gethobby.service.domain.Purchase;
import com.gethobby.service.purchase.PurchaseDAO;

@Repository("purchaseDAOImpl")
public class PurchaseDAOImpl implements PurchaseDAO{
		
		//Field
		@Autowired
		@Qualifier("sqlSessionTemplate")
		private SqlSession sqlSession;
		public void setSqlSession(SqlSession sqlSession) {
			this.sqlSession = sqlSession;
		}
		
		//Constructor
		public PurchaseDAOImpl() {
			System.out.println(this.getClass());
		}

		//Method
		public HobbyClass getComponentOption(int hobbyClassNo) throws Exception {
			return sqlSession.selectOne("PurchaseMapper.getComponentOption", hobbyClassNo);
		}

		public Purchase getPayment(String userId ) throws Exception {
			return sqlSession.selectOne("PurchaseMapper.getPayment", userId);
		}

		public int addPayment(Purchase purchase) throws Exception {
			return sqlSession.insert("PurchaseMapper.addPayment", purchase);
		}

		public List getPaymentHistoryList(String userId) throws Exception {
			return sqlSession.selectList("PurchaseMapper.getPaymentHistoryList", userId);
		}

		public Purchase getDelivery(String userId) throws Exception {
			return sqlSession.selectOne("PurchaseMapper.getDelivery", userId);
		}

		public int requestRefund(String purchaseId) throws Exception {
			return sqlSession.update("PurchaseMapper.requestRefund", purchaseId);
		}

		public List getPaymentHistoryListAdmin(Search search) throws Exception {
			return sqlSession.selectList("PurchaseMapper.getPaymentHistoryList", search);
		}

		public int updateRefund(String purchaseId) throws Exception {
			return sqlSession.update("PurchaseMapper.updateRefund", purchaseId);
		}

		public int addDeliveryInfo(Purchase purchase) throws Exception {
			return sqlSession.update("PurchaseMapper.addDeliveryInfo", purchase);
		}

		public int getMoneyAnalysisSum(Map<String, Object> keyWordAndWhat) throws Exception {
			return sqlSession.selectOne("PurchaseMapper.getMoneyAnalysisSum", keyWordAndWhat);
		}

		public int getMoneyAnalysisAvg(Map<String, Object> keyWordAndWhat) throws Exception {
			return sqlSession.selectOne("PurchaseMapper.getMoneyAnalysisAvg", keyWordAndWhat);
		}

		public int getMoneyAnalysisCount(Map<String, Object> keyWordAndWhat) throws Exception {
			return sqlSession.selectOne("PurchaseMapper.getMoneyAnalysisCount", keyWordAndWhat);
		}

		public List getCreatorList() throws Exception {
			return sqlSession.selectList("PurchaseMapper.getCreatorList");
		}
		
}//end of DAOImpl
