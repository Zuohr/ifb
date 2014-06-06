package control;

import io.CharArrayWriterResponse;

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
	private final String jspPath = "/WEB-INF/";

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

		// request.getRequestDispatcher(jspPath + "output.jsp").forward(request,
		// response);

		// String[] vals = request.getParameterValues("select");
		// JsonBean jb = new JsonBean();
		// if (vals != null) {
		// jb.vals = vals;
		// Gson json = new Gson();
		// String json_str = json.toJson(jb);
		// request.setAttribute("json", json_str);
		//
		// File file = new File("/Users/hidarikouzen/git/ifb/ifb/json");
		// PrintWriter pw = new PrintWriter(new FileWriter(file));
		// pw.print(json_str);
		// pw.close();
		//
		// file = new File("/Users/hidarikouzen/git/ifb/ifb/json");
		// response.setContentType("application/octet-stream");
		// response.setContentLength(new Long(file.length()).intValue());
		// response.addHeader("Content-Disposition", "attachment; filename="
		// + "json");
		//
		// ServletOutputStream servletOutputStream = response
		// .getOutputStream();
		// FileInputStream fileInputStream = new FileInputStream(file);
		// BufferedInputStream bufferedInputStream = new BufferedInputStream(
		// fileInputStream);
		// int size = -1;
		// byte[] b = new byte[4096];
		// while ((size = bufferedInputStream.read(b)) != -1) {
		// servletOutputStream.write(b, 0, size);
		// }
		// servletOutputStream.flush();
		// servletOutputStream.close();
		// bufferedInputStream.close();
		//
		// }

		// for (String str : vals) {
		// System.out.println(str);
		// }
		// request.getRequestDispatcher(jspPath + "test.jsp").forward(request,
		// response);
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
			CharArrayWriterResponse customResponse = new CharArrayWriterResponse(
					response);
			request.getRequestDispatcher(jspPath + nextStep).forward(request,
					customResponse);
			System.out.println(customResponse.getOutput());
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
