package com.ddl.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.ddl.dao.FAQDao;
import com.ddl.model.FAQ;
import com.ddl.service.IFAQService;

@Service(value="faqService")
@Repository(value="faqService")
public class FAQService extends BaseService implements IFAQService{

	FAQDao faqDao;
	public FAQDao getResGroupDao() {
		return faqDao;
	}
	
	@Resource
	public void setResGroupDao(FAQDao faqDao) {
		this.faqDao = faqDao;
	}
	
	@Override
	public Object get(Object object) {
		return faqDao.get((FAQ)object);
	}

	@Override
	public List<?> getAll() {
		return faqDao.getAll();
	}

}