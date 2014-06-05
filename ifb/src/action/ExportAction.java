package action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Model;

public class ExportAction implements Action {
	private String downloadPath;
	
	private boolean compressed = false;
	
	private boolean downloaded = false;

	public ExportAction(Model model) {
		downloadPath = model.getDownloadPath();
	}

	@Override
	public String perform(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
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

		File downLoadfile = new File(dirName, zipFileName);
		response.setContentType("application/octet-stream");
		response.setContentLength(new Long(downLoadfile.length()).intValue());
		response.addHeader("Content-Disposition", "attachment; filename="
				+ "privacy_form.zip");

		ServletOutputStream sos = response.getOutputStream();
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(
				downLoadfile));
		int readLen = -1;
		byte[] b = new byte[buffer_size];
		while ((readLen = bis.read(b)) != -1) {
			sos.write(b, 0, readLen);
		}
		sos.flush();
		sos.close();
		bis.close();

		for (File file : dir.listFiles()) {
			file.delete();
		}
		dir.delete();

		return "";
	}

	@Override
	public String getName() {
		return "export.do";
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

}
