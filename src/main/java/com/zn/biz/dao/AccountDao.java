/**
 *
 */
package com.zn.biz.dao;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.zn.biz.mapper.AccountMapper;
import com.zn.biz.entity.AccountEntity;

import javax.annotation.Resource;

/**
 * Description:  逻辑服务类<br/>
 * 不做业务校验，参数校验统一放到前置的Service中
 * @author zhounan
 * @version 1.0
 * @date: 2020-07-07 20:54:54
 * @since JDK 1.8
 */
@Repository(value = "accountDao")
public class AccountDao  {

	@Resource
	private AccountMapper accountMapper;

	/**可选插入返回主键*/
	public Integer insertAccount(AccountEntity entity) {
		Integer id = null;

			accountMapper.insertSelective(entity);
		id = entity.getId();

		return id;
	}

	/**批量插入*/
	public void insertAccounts(List<AccountEntity> records) {

			accountMapper.insertRecords(records);

	}

	/**条件查询返回匹配的第一条记录*/
	public AccountEntity queryAccountLimitOne(AccountEntity entity) {

		return accountMapper.queryLimitOne(entity);
	}

	/**条件查询*/
	public List<AccountEntity> queryByCond(AccountEntity entity) {
		return accountMapper.queryByCond(entity);
	}


	/**主键查询 可能返回Null*/
	@Cacheable(value = "cache_account_name", key = "'id'+#a0",
			condition = "!T(org.springframework.transaction.support.TransactionSynchronizationManager).synchronizationActive")
	public AccountEntity queryAccountById (Integer id) {
		return accountMapper.queryById(id);
	}

	/**主键批量查询 可能返回空列表*/
	List<AccountEntity> queryAccountByIds (List<Integer> ids) {
		return accountMapper.queryByIds(ids);
	}

	/**主键更新*/
	public boolean updateAccountById (AccountEntity entity) {
		return accountMapper.updateAccountById(entity) > 0;
	}

	/**主键删除*/
	public boolean deleteAccountById (Integer id) {
		return accountMapper.deleteAccountById(id) > 0;
	}

}
