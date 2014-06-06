package action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import json.JsonData;
import model.Model;

import com.google.gson.Gson;

public class GenerateAction implements Action {

	private String downloadPath;

	public GenerateAction(Model model) {
		this.downloadPath = model.getDownloadPath();
	}

	@Override
	public String perform(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		if (!"Send your data".equals(request.getParameter("submit_button"))) {
			return "";
		}

		File targetDir = new File(downloadPath + request.getSession().getId());
		if (targetDir.exists()) {
			for (File file : targetDir.listFiles()) {
				file.delete();
			}
			targetDir.delete();
			targetDir = new File(downloadPath + request.getSession().getId());
		}
		targetDir.mkdir();
		PrintWriter pw = new PrintWriter(new FileWriter(new File(targetDir,
				"privacy_notice.html")));

		pw.print("<!DOCTYPE html><html><meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" /><title>Privacy notice</title><link rel=\"stylesheet\" type=\"text/css\" href=\"css/pbi-web-ps-jawr.css\" media=\"all\" /><link rel=\"stylesheet\" type=\"text/css\" href=\"css/pbi-web-ps-jawr-print.css\" media=\"print\" /></head>");

		JsonData json = new JsonData();
		json.fname = request.getParameter("fname");
		json.rev_date = "Rev. June 2014";

		// File targetDir = new File(downloadPath +
		// request.getSession().getId());
		// if (targetDir.exists()) {
		// for (File file : targetDir.listFiles()) {
		// file.delete();
		// }
		// targetDir.delete();
		// targetDir = new File(downloadPath + request.getSession().getId());
		// }
		// targetDir.mkdir();
		// PrintWriter pw = new PrintWriter(new FileWriter(new File(targetDir,
		// "test.html")));
		// pw.println("hello");
		// pw.close();
		//
		// pw = new PrintWriter(new FileWriter(new File(targetDir,
		// "test.json")));
		// Gson json = new Gson();
		// pw.print(json.toJson(new JsonData()));
		// pw.close();

		pw.close();

		compress(request);

		return "";

	}

	private void compress(HttpServletRequest request) throws Exception {
		String dirName = downloadPath + request.getSession().getId();
		File dir = new File(dirName);
		File[] files = dir.listFiles();
		String zipFileName = "privacy_form.zip";
		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(
				new File(dir, zipFileName)));
		ZipEntry ze = null;
		int buffer_size = 4096;
		byte[] buf = new byte[buffer_size];
		for (int i = 0; i < files.length; i++) {
			File file = (File) files[i];
			ze = new ZipEntry(getAbsFileName(dirName, file));
			ze.setSize(file.length());
			ze.setTime(file.lastModified());
			zos.putNextEntry(ze);
			InputStream is = new BufferedInputStream(new FileInputStream(file));
			int readLen = -1;
			while ((readLen = is.read(buf, 0, buffer_size)) != -1) {
				zos.write(buf, 0, readLen);
			}
			is.close();
		}
		zos.close();
	}

	private String getAbsFileName(String baseDir, File realFileName) {
		File real = realFileName;
		File base = new File(baseDir);
		String ret = real.getName();
		while (true) {
			real = real.getParentFile();
			if (real == null)
				break;
			if (real.equals(base))
				break;
			else
				ret = real.getName() + "/" + ret;
		}
		return ret;
	}

	private void write(String text) {

	}

	@Override
	public String getName() {
		return "generate.do";
	}

}
