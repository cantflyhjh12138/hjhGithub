package com.neuedu.lvcity.dao.impl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.neuedu.lvcity.common.DBpool;
import com.neuedu.lvcity.model.Users;

public class UserDaoImpl implements UserDao{
	private Connection conn;
	
	public UserDaoImpl(Connection conn) {
		this.conn = conn;
	}
	
	public Users login(String username,String password) throws SQLException {
		PreparedStatement ps =null;
		ResultSet rs = null;
		Users users = null;	//要return 所以在这里定义
		String sql = "select * from users where name=? and passwd=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				users = new Users();
				users.setId(rs.getInt("id"));
				users.setName(rs.getString("name"));
				users.setPasswd(rs.getString("passwd"));
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			conn.close();
			DBpool.closeStatement(rs, ps);
		}
		
		return users;
	}
}
