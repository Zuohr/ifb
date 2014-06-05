package control;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import test.JsonBean;
import action.Action;
import action.ActionMap;

import com.google.gson.Gson;

/**
 * Servlet implementation class Controller
 */
@WebServlet("*.do")
public class Controller extends HttpServlet {
	private ActionMap actions;
	private final String jspPath = "/WEB-INF/";

	private volatile long currFileID = 1l;

	private static final long serialVersionUID = 1L;

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		actions = new ActionMap();
		// add actions
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
		// String nextStep;
		// nextStep = processRequest(request);
		// proceedToNext(nextStep, request, response);

		// request.getRequestDispatcher(jspPath + "output.jsp").forward(request,
		// response);

		String[] vals = request.getParameterValues("select");
		JsonBean jb = new JsonBean();
		if (vals != null) {
			jb.vals = vals;
			Gson json = new Gson();
			String json_str = json.toJson(jb);
			request.setAttribute("json", json_str);

			File file = new File("/Users/hidarikouzen/git/ifb/ifb/json");
			PrintWriter pw = new PrintWriter(new FileWriter(file));
			pw.print(json_str);
			pw.close();

			file = new File("/Users/hidarikouzen/git/ifb/ifb/json");
			response.setContentType("application/octet-stream");
			response.setContentLength(new Long(file.length()).intValue());
			response.addHeader("Content-Disposition", "attachment; filename="
					+ "json");

			ServletOutputStream servletOutputStream = response
					.getOutputStream();
			FileInputStream fileInputStream = new FileInputStream(file);
			BufferedInputStream bufferedInputStream = new BufferedInputStream(
					fileInputStream);
			int size = -1;
			byte[] b = new byte[4096];
			while ((size = bufferedInputStream.read(b)) != -1) {
				servletOutputStream.write(b, 0, size);
			}
			servletOutputStream.flush();
			servletOutputStream.close();
			bufferedInputStream.close();
			
		}

		// for (String str : vals) {
		// System.out.println(str);
		// }
		// request.getRequestDispatcher(jspPath + "test.jsp").forward(request,
		// response);
	}

	private String processRequest(HttpServletRequest request) {
		String actionName = getActionName(request.getServletPath());
		Action a = actions.getAction(actionName);
		if (a == null) {
			return "404";
		} else {
			return a.perform(request);
		}
	}

	private void proceedToNext(String nextStep, HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		if (nextStep.endsWith(".do")) {
			response.sendRedirect(nextStep);
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
