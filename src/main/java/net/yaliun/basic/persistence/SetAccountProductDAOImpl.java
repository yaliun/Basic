package net.yaliun.basic.persistence;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import net.yaliun.basic.domain.SetAccountProductReqVO;

@Repository
public class SetAccountProductDAOImpl implements SetAccountProductDAO {

	private static final Logger logger = LoggerFactory.getLogger(SetAccountProductDAOImpl.class);
	
	@Autowired
	private SqlSession session;
	
	private static String namespace = "net.yaliun.basic.mapper.SetAccountProductMapper";
	
	@Override
	public void create(SetAccountProductReqVO vo) {
		logger.info("SetAccountProductDAOImpl : create start");
		session.insert(namespace+".create", vo);
		logger.info("SetAccountProductDAOImpl : create end");
	}
}
