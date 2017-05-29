package user.service;

import static user.utils.CloseableUtil.*;
import static user.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import user.beans.User;
import user.dao.UserDao;
import user.utils.CipherUtil;

public class UserService {

	//DBに登録する
	public void register(User users) { //DAOのinsertメソッドを呼び出してusersテーブルにつなぐ(registerとinsertは今別の名称だけど同じほうが分かりやすい)

		Connection connection = null;
		try {
			connection = getConnection();

			//暗号化
			String encPassword = CipherUtil.encrypt(users.getPassword());
			users.setPassword(encPassword);

			UserDao userDao = new UserDao();
			userDao.insert(connection, users);

			commit(connection);
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

	//ユーザー一覧表示
	public List<User> getUsers() {
		Connection connection = null;//webサーバーとDBのやり取りを繋いでいる
		try {
			connection = getConnection();

			UserDao userDao = new UserDao();
			List<User> ret = userDao.getUsers(connection);

			commit(connection);

			return ret;

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