/**
 *
 */
package com.solverpeng.modules.product.dao;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import com.trekiz.admin.common.persistence.BaseDao;
import com.trekiz.admin.common.persistence.BaseDaoImpl;
import com.solverpeng.modules.product.entity.Product;

/**
 * 产品DAO接口
 * @author solverpeng
 * @version 2017-02-23
 */
public interface ProductDao extends ProductDaoCustom, CrudRepository<Product, Long> {
	@Modifying
	@Query("update Product set delFlag='" + Product.DEL_FLAG_DELETE + "' where id = ?1")
	public int deleteById(Long id);
	
}

/**
 * DAO自定义接口
 * @author solverpeng
 */
interface ProductDaoCustom extends BaseDao<Product> {

}

/**
 * DAO自定义接口实现
 * @author solverpeng
 */
@Component
class ProductDaoImpl extends BaseDaoImpl<Product> implements ProductDaoCustom {

}