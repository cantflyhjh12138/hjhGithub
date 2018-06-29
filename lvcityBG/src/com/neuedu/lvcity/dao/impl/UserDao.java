package com.neuedu.lvcity.dao.impl;
import java.sql.SQLException;

import com.neuedu.lvcity.model.Users;

public interface UserDao {
	Users login(String name, String passwd) throws SQLException;
}
