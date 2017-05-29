package user.service;

import static user.utils.CloseableUtil.*;
import static user.utils.DBUtil.*;

import java.sql.Connection;

import user.beans.User;
import user.dao.UserDao;
import user.utils.CipherUtil;

public class LoginService {

	public User login(String login_id, String password) { //loginはgetloginとかに変えたほうがいい

		Connection connection = null;//webサーバーとDBのやり取りを繋いでいる
		try {
			connection = getConnection();

			UserDao userDao = new UserDao();
			String encPassword = CipherUtil.encrypt(password);
			User user = userDao
					.getUser(connection, login_id, encPassword);

			commit(connection);

			return user;

		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

}
