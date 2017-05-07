package servlet.com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Check
 */
@WebServlet(description = "��֤", urlPatterns = { "/Check" })
public class Check extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Check() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String code = session.getAttribute("validateCode").toString();
		String checkcode = request.getParameter("checkcode");
		response.setContentType("text/html;charset=UTF-8");
		if(code.toLowerCase().equals(checkcode.toLowerCase())){
			out.println("<script>alert('Success');window.location.href='./login.jsp';</script>");
			out.flush();
			out.close();
		}else{
			out.println("<script>alert('Fail');window.location.href='./login.jsp';</script>");
			out.flush();
			out.close();
		}
	}

}
