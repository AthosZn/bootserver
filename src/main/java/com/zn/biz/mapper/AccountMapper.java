/**
 *
 */
package com.zn.biz.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;

import com.zn.biz.entity.AccountEntity;

/**
 * Description:  Dao<br/>
 *
 * @author zhounan
 * @version 1.0
 * @date: 2020-07-07 20:54:54
 * @since JDK 1.8
 */
@Mapper
public interface AccountMapper {

	/**可选插入返回主键*/
	Integer insertSelective(AccountEntity entity);

	/**批量插入返回影响记录数*/
	int insertRecords(@Param("records") List<AccountEntity> records);

	AccountEntity queryLimitOne(AccountEntity entity);

	/**批量主键查询*/
	List<AccountEntity> queryByIds(@Param("keys") List<Integer> ids);

	/**条件查询*/
	List<AccountEntity> queryByCond(AccountEntity entity);

	/**查询个数*/
	int countByCond(AccountEntity entity);

	/**主键查询*/
	AccountEntity queryById(@Param("id") Integer id);

	/**主键更新*/
	int updateAccountById(AccountEntity entity);

	/**主键删除*/
	int deleteAccountById(@Param("id") Integer id);

}
