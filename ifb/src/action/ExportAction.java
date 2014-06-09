package action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Model;

public class ExportAction implements Action {
	private String downloadPath;
	
	public ExportAction(Model model) {
		downloadPath = model.getDownloadPath();
	}

	@Override
	public String perform(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		File dir = new File(downloadPath + request.getSession().getId());
		if (!dir.exists()) {
			return "";
		}
		
		String zipFileName = "privacy_form.zip";

		File downLoadfile = new File(dir, zipFileName);
		response.setContentType("application/octet-stream");
		response.setContentLength(new Long(downLoadfile.length()).intValue());
		response.addHeader("Content-Disposition", "attachment; filename="
				+ "privacy_form.zip");

		ServletOutputStream sos = response.getOutputStream();
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(
				downLoadfile));
		int buffer_size = 4096;
		int readLen = -1;
		byte[] b = new byte[buffer_size];
		while ((readLen = bis.read(b)) != -1) {
			sos.write(b, 0, readLen);
		}
		sos.flush();
		sos.close();
		bis.close();
		
		File[] files = dir.listFiles();
		for (File file : files) {
			file.delete();
		}
		dir.delete();

		return "";
	}

	@Override
	public String getName() {
		return "export.do";
	}

}
