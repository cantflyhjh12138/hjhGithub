package com.neuedu.lvcity.service.impl;

import java.sql.Connection;

import com.neuedu.lvcity.common.DBpool;
import com.neuedu.lvcity.dao.impl.UserDao;
import com.neuedu.lvcity.dao.impl.UserDaoImpl;
import com.neuedu.lvcity.model.Users;

public class UsersServiceImpl implements UsersService{
	//����ģʽ
	 /**
	  * ���췽����˽�л�
	  */
	private  UsersServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	//�ڲ�����Ψһ��ʵ��
	private static final UsersService Instance = new UsersServiceImpl();
	
	/**
	 * �ṩһ���ⲿ���ʵĹ����ӿ�
	 */
	
	public static  UsersService getInstance(){
		return Instance;
	}
	
	public Users login(String name, String passwd) {
		// TODO Auto-generated method stub
		//��������ֵ�ı���
		Users user = null;
		//�������Ӷ���
		Connection conn = null;
		try{
			//��ȡ����
			conn = DBpool.getConnection();
			//����dao���ʵ����Ķ���
			UserDao userDao = new UserDaoImpl(conn);
			//����dao��ĵ�¼���������ص�¼��user����			
			user = userDao.login(name, passwd);			
		}catch(Exception e){
			DBpool.rollback(conn);
			System.out.println("��¼��ѯ�û��쳣��"+e.getMessage());
		}finally {
			DBpool.closeConnection(conn);
		}	
		return user;
	}

}
