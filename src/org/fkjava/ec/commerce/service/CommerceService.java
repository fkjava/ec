package org.fkjava.ec.commerce.service;

import org.fkjava.ec.commerce.vo.IndexPage;

public interface CommerceService {

	IndexPage getIndexPage(String typeCode, String keyword, IndexPage page);

}
