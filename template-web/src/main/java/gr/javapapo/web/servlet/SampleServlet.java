/**
 * Disclaimer: this code is only for demo no production use
 */
package gr.javapapo.web.servlet;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;

/**
 * @author papo
 *
 */
@WebServlet(value="/template", name="hello-template")
public class SampleServlet extends GenericServlet{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5381773888206571844L;

	@Override
	public void service(ServletRequest req, ServletResponse res)
			throws ServletException, IOException {
	    res.getWriter().println("Hello template!");		
	}

}
