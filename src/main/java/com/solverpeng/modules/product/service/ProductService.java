/**
 *
 */
package com.solverpeng.modules.product.service;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.trekiz.admin.common.persistence.Page;
import com.trekiz.admin.common.service.BaseService;
import com.solverpeng.modules.product.entity.Product;
import com.solverpeng.modules.product.dao.ProductDao;

/**
 * 产品Service
 * @author solverpeng
 * @version 2017-02-24
 */
@Component
@Transactional(readOnly = true)
public class ProductService extends BaseService {
	private static Logger logger = LoggerFactory.getLogger(ProductService.class);
	
	@Autowired
	private ProductDao productDao;
	
	public Product get(Long id) {
		return productDao.findOne(id);
	}
	
	public Page<Product> find(Page<Product> page, Product product) {
		DetachedCriteria dc = productDao.createDetachedCriteria();
		if (StringUtils.isNotEmpty(product.getName())){
			dc.add(Restrictions.like("name", "%"+product.getName()+"%"));
		}
		dc.add(Restrictions.eq(Product.DEL_FLAG, Product.DEL_FLAG_NORMAL));
		dc.addOrder(Order.desc("id"));
		return productDao.find(page, dc);
	}
	
	@Transactional(readOnly = false)
	public void save(Product product) {
		productDao.save(product);
	}
	
	@Transactional(readOnly = false)
	public void delete(Long id) {
		productDao.deleteById(id);
	}
	
}
