package com.neuedu.lvcity.servlet;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.neuedu.lvcity.model.Users;
import com.neuedu.lvcity.service.impl.UsersService;
import com.neuedu.lvcity.service.impl.UsersServiceImpl;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/Admin/User")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println(action);
		if("login".equals(action));
			doLogin(request,response);
	}
	protected void doLogin(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		//获取客户端的参数
		String username = request.getParameter("name");
		String passwd = request.getParameter("passwd");
		System.out.println(username);
		System.out.println(passwd);
		
		//调用service的login方法
		UsersService usersService = UsersServiceImpl.getInstance();
		Users users = usersService.login(username, passwd);
		
		if(users == null) {
			request.getRequestDispatcher("/Login.jsp").forward(request, response);
		}
		else {
			System.out.println("hello");
			session.setAttribute("user", users);
			System.out.println(request.getContextPath()+"/Admin/index.jsp");
			response.sendRedirect(request.getContextPath()+"/Admin/index.jsp");
		}
	}
}
