/**
 *
 */
package ${packageName}.${moduleName}.entity${subModuleName};

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
 *  文件名: ${ClassName}
 *  功能: ${functionName}Entity
 *
 *  @author ${classAuthor}
 */
@Entity
@Table(name = "${tableName}")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class ${ClassName} extends DataEntity {
	private static final long serialVersionUID = 1L;

	private Long id; 		// 编号

	public ${ClassName}() {
		super();
	}

	public ${ClassName}(Long id){
		this();
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	//@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_${tableName}")
	//@SequenceGenerator(name = "seq_${tableName}", sequenceName = "seq_${tableName}")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}