/** Soin Software, 2018 */
package com.soinsoftware.petcity.bll;

import java.io.IOException;
import java.math.BigInteger;

import com.soinsoftware.petcity.dao.UserDao;
import com.soinsoftware.petcity.model.User;

/**
 * @author Carlos Rodriguez
 * @since 14/08/2018
 */
public class UserBll extends AbstractBll<User, BigInteger> {
	
	public UserBll() throws IOException {
		super();
		dao = new UserDao();
	}
	
	public User select(final String document) {
		return ((UserDao) dao).select(document);
	}
	
	public User select(final String email, final String password) {
		return ((UserDao) dao).select(email, password);
	}
}