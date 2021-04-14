/**
 * <p>Copyright: Copyright (c) 2017</p>
 * <p>Company: fade </p>
 */
package com.zn.biz.entity;

import java.io.Serializable;


/**
 * Description: <br/>
 *
 * @author zhounan
 * @version 1.0
 * @date: 2020-07-07 20:54:54
 * @since JDK 1.8
 */
public class AccountEntity  implements Serializable {

    private static final long serialVersionUID = 1L;

	/***/
	private Integer id;

	/***/
	private Double money;

	/***/
	private String name;


	/**{@linkplain #id}*/
	public AccountEntity setId(Integer id) {
		this.id = id;
		return this;
	}

	/**{@linkplain #id}*/
	public Integer getId() {
		return this.id;
	}

	/**{@linkplain #money}*/
	public AccountEntity setMoney(Double money) {
		this.money = money;
		return this;
	}

	/**{@linkplain #money}*/
	public Double getMoney() {
		return this.money;
	}

	/**{@linkplain #name}*/
	public AccountEntity setName(String name) {
		this.name = name;
		return this;
	}

	/**{@linkplain #name}*/
	public String getName() {
		return this.name;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("AccountEntity [");
  	    sb.append("id=");
  	    sb.append(id).append(",");
  	    sb.append(",money=");
  	    sb.append(money).append(",");
  	    sb.append(",name=");
  	    sb.append(name).append(",");
        sb.append(']');
        return sb.toString();
	}

}
