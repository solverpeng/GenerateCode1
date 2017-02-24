/**
 *
 */
package com.solverpeng.modules.product.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.validator.constraints.Length;

import com.trekiz.admin.common.persistence.DataEntity;
import com.trekiz.admin.modules.sys.entity.User;

/**
 *  文件名: Product
 *  功能: 产品Entity
 *
 *  @author solverpeng
 */
@Entity
@Table(name = "product_product")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Product extends DataEntity {
	private static final long serialVersionUID = 1L;

	private Long id; 		// 编号

	public Product() {
		super();
	}

	public Product(Long id){
		this();
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_product_product")
	//@SequenceGenerator(name = "seq_product_product", sequenceName = "seq_product_product")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}