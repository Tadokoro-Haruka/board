package user.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import user.beans.User;


@WebFilter({"/newmessage.jsp","/setting.jsp","/signup.jsp","/top.jsp"})
public class LoginFilter implements Filter{

	public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain)
			throws IOException, ServletException{

		HttpSession session = ((HttpServletRequest)request).getSession();
		session.getAttribute("loginUser");
		User user = (User) session.getAttribute("loginUser");

		if((user) == null){
			((HttpServletResponse)response).sendRedirect("login");
			return;
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// TODO 自動生成されたメソッド・スタブ

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO 自動生成されたメソッド・スタブ

	}
}
