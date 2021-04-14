/**
 *
 */
package com.zn.biz.service;

import java.util.List;

import com.zn.biz.dao.AccountDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service(value = "accountLogicService")
public class AccountLogicService  {

	@Resource
	private AccountDao accountDao;

	/**可选插入返回主键*/
	public Integer insertAccount(AccountEntity entity) {

		return	accountDao.insertAccount(entity);
	}

	/**批量插入*/
	public void insertAccounts(List<AccountEntity> records) {

	   	accountDao.insertAccounts(records);

	}

	/**条件查询*/
	public List<AccountEntity> queryByCond(AccountEntity entity) {
		return accountDao.queryByCond(entity);
	}


	/**主键查询 可能返回Null*/
	public AccountEntity queryAccountById (Integer id) {
		return accountDao.queryAccountById(id);
	}


	/**主键删除*/
	public boolean deleteAccountById (Integer id) {
		return accountDao.deleteAccountById(id);
	}

}
