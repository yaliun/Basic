package net.yaliun.basic.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.yaliun.basic.domain.SetAccountProductReqVO;
import net.yaliun.basic.persistence.SetAccountProductDAO;

@Service
public class SetAccountProductImpl implements SetAccountProductService {

	private static final Logger logger = LoggerFactory.getLogger(SetAccountProductImpl.class);
	
	@Autowired
	private SetAccountProductDAO accountDAO;
	
	@Override
	public void addProduct(SetAccountProductReqVO vo) {
		accountDAO.create(vo);
	}
	
	@Override
	public int sum() {
		
		return 1+2;
	}

}
