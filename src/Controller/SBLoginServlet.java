package Controller;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Model.BuyerDAO;
import VO.BuyerVO;

/**
 * Servlet implementation class BuyerLoginServlet
 */
@WebServlet("/SBLoginServlet")
public class SBLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SBLoginServlet() {
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
		String username = request.getParameter("username"),
			   password = request.getParameter("password"),
			   usertype = request.getParameter("usertype"),
			   msg = "",
			   url = request.getContextPath()+"/Seller_Buyer/login.jsp",
			   loggedIn = "false";
		BuyerVO bvo = null;
		List<BuyerVO> buyerInfo = null;
		HttpSession session = request.getSession();
		
		if (username == null)
			username = "";
		if (password == null)
			password = "";
		int unlen = username.length();
		int pwlen = password.length();
		if (unlen == 0 && pwlen == 0)
			msg = "Please fill in your username and password";
		if (unlen == 0 || pwlen == 0) {
			if (unlen == 0)
				msg += "<br/>" + "Please input username";
			if (pwlen == 0)
				msg += "<br/>" + "Please input password";
		}
		else{	// When all fields are filled.
			if(usertype.equals("buyer")){
				buyerInfo = BuyerDAO.checkUsernamePass(username, password);
				if(buyerInfo != null){
					Iterator<BuyerVO> itr = buyerInfo.iterator();
					
					while(itr.hasNext())
						bvo = (BuyerVO) itr.next();
					
					if(bvo != null){
						if(bvo.getStatus() == 1){
							url = request.getContextPath()+"/Seller_Buyer/index.jsp";
							loggedIn = "true";
							session.setAttribute("buyerInfoObj", bvo);
						}
						else
							msg = "The user has not been authenticated yet.";
					}
					else
						msg = "Invalid username and/or password";
				}
				else
					msg = "Invalid username and/or password";
				
			}
			else if(usertype.equals("seller")){}
		}
		session.setAttribute("msg", msg);
		session.setAttribute("loggedIn", loggedIn);
		response.sendRedirect(url);
	}
};
