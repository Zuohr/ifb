package control;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Model;
import action.Action;
import action.ActionMap;
import action.ExportAction;
import action.GenerateAction;

/**
 * Servlet implementation class Controller
 */
@WebServlet(urlPatterns = { "*.do" }, initParams = { @WebInitParam(name = "downloadPath", value = "/Users/hidarikouzen/git/ifb/ifb/") })
public class Controller extends HttpServlet {
	private ActionMap actions;
	public static final String jspPath = "/WEB-INF/";

	private static final long serialVersionUID = 1L;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		Model model = new Model(config);

		actions = new ActionMap();
		actions.addAction(new ExportAction(model));
		actions.addAction(new GenerateAction(model));
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String nextStep;
		try {
			nextStep = processRequest(request, response);
			proceedToNext(nextStep, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String processRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String actionName = getActionName(request.getServletPath());
		Action a = actions.getAction(actionName);
		if (a == null) {
			return "404";
		} else {
			return a.perform(request, response);
		}
	}

	private void proceedToNext(String nextStep, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		if (nextStep.isEmpty()) {
			return;
		} else if (nextStep.endsWith(".jsp")) {
			request.getRequestDispatcher(jspPath + nextStep).forward(request,
					response);
		} else if ("404".equals(nextStep)) {
			response.sendError(404);
		} else {
			response.sendRedirect(nextStep);
		}
	}

	private String getActionName(String path) {
		return path.substring(path.lastIndexOf('/') + 1);
	}

}
