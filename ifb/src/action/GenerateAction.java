package action;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import json.JsonData;

import com.google.gson.Gson;

import model.Model;

public class GenerateAction implements Action {
	
	private String downloadPath;
	
	public GenerateAction(Model model) {
		this.downloadPath = model.getDownloadPath();
	}

	@Override
	public String perform(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		File targetDir = new File(downloadPath + request.getSession().getId());
		targetDir.mkdir();
		PrintWriter pw = new PrintWriter(new FileWriter(new File(targetDir, "test.html")));
		pw.println("hello");
		pw.close();
		
		pw = new PrintWriter(new FileWriter(new File(targetDir, "test.json")));
		Gson json = new Gson();
		pw.print(json.toJson(new JsonData()));
		pw.close();
		return "";
		
	}

	@Override
	public String getName() {
		return "generate.do";
	}

}
