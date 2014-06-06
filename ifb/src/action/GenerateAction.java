package action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import json.JsonData;
import model.Model;

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

		JsonData json = new JsonData();

		json.fname = request.getParameter("fname");
		request.setAttribute("fname", json.fname);

		json.rev_date = "Rev. June 2014";
		request.setAttribute("rev_date", json.rev_date);

		json.what_coll = request.getParameterValues("what_coll");
		request.setAttribute("what_coll", json.what_coll);

		boolean offer_opt_out = false;
		json.share_eday = request.getParameter("share_eday");
		if ("y_w_opt".equals(json.share_eday)) {
			offer_opt_out = true;
			request.setAttribute("share_eday", "Yes");
			request.setAttribute("limit_eday", "Yes");
		} else if ("y_wo_opt".equals(json.share_eday)) {
			request.setAttribute("share_eday", "Yes");
			request.setAttribute("limit_eday", "No");
		} else {
			request.setAttribute("share_eday", "No");
			request.setAttribute("limit_eday", "No");
		}

		json.share_mar = request.getParameter("share_mar");
		if ("y_w_opt".equals(json.share_mar)) {
			offer_opt_out = true;
			request.setAttribute("share_mar", "Yes");
			request.setAttribute("limit_mar", "Yes");
		} else if ("y_wo_opt".equals(json.share_mar)) {
			request.setAttribute("share_mar", "Yes");
			request.setAttribute("limit_mar", "No");
		} else {
			request.setAttribute("share_mar", "No");
			request.setAttribute("limit_mar", "No");
		}

		json.share_joint = request.getParameter("share_joint");
		if ("y_w_opt".equals(json.share_joint)) {
			offer_opt_out = true;
			request.setAttribute("share_joint", "Yes");
			request.setAttribute("limit_joint", "Yes");
		} else if ("y_wo_opt".equals(json.share_joint)) {
			request.setAttribute("share_joint", "Yes");
			request.setAttribute("limit_joint", "No");
		} else {
			request.setAttribute("share_joint", "No");
			request.setAttribute("limit_joint", "No");
		}

		json.share_aff = request.getParameter("share_aff");
		if ("y_w_opt".equals(json.share_aff)) {
			offer_opt_out = true;
			request.setAttribute("share_aff", "Yes");
			request.setAttribute("limit_aff", "Yes");
		} else if ("y_wo_opt".equals(json.share_aff)) {
			request.setAttribute("share_aff", "Yes");
			request.setAttribute("limit_aff", "No");
		} else {
			request.setAttribute("share_aff", "No");
			request.setAttribute("limit_aff", "No");
		}

		json.share_credit = request.getParameter("share_credit");
		if ("y_w_opt".equals(json.share_credit)) {
			offer_opt_out = true;
			request.setAttribute("share_credit", "Yes");
			request.setAttribute("limit_credit", "Yes");
		} else if ("y_wo_opt".equals(json.share_credit)) {
			request.setAttribute("share_credit", "Yes");
			request.setAttribute("limit_credit", "No");
		} else {
			request.setAttribute("share_credit", "No");
			request.setAttribute("limit_credit", "No");
		}

		json.share_aff_mar = request.getParameter("share_aff_mar");
		if ("y_w_opt".equals(json.share_aff_mar)) {
			offer_opt_out = true;
			request.setAttribute("share_aff_mar", "Yes");
			request.setAttribute("limit_aff_mar", "Yes");
		} else if ("y_wo_opt".equals(json.share_aff_mar)) {
			request.setAttribute("share_aff_mar", "Yes");
			request.setAttribute("limit_aff_mar", "No");
		} else if ("omit".equals(json.share_aff_mar)) {
			request.setAttribute("omit_share_aff_mar", true);
		} else {
			request.setAttribute("share_aff_mar", "No");
			request.setAttribute("limit_aff_mar", "No");
		}

		json.share_non_aff_mar = request.getParameter("share_non_aff_mar");
		if ("y_w_opt".equals(json.share_non_aff_mar)) {
			offer_opt_out = true;
			request.setAttribute("share_non_aff_mar", "Yes");
			request.setAttribute("limit_non_aff_mar", "Yes");
		} else if ("y_wo_opt".equals(json.share_non_aff_mar)) {
			request.setAttribute("share_non_aff_mar", "Yes");
			request.setAttribute("limit_non_aff_mar", "No");
		} else {
			request.setAttribute("share_non_aff_mar", "No");
			request.setAttribute("limit_non_aff_mar", "No");
		}

		request.setAttribute("offer_opt_out", offer_opt_out);

		json.opt_phone = request.getParameter("opt_phone");
		request.setAttribute("opt_phone", json.opt_phone);

		json.opt_web = request.getParameter("opt_web");
		request.setAttribute("opt_web", json.opt_web);

		boolean mail_opt_out = false;
		if (offer_opt_out) {
			json.opt_num_days = request.getParameter("opt_num_days");
			request.setAttribute("opt_num_days", json.opt_num_days);

			json.opt_scheme = request.getParameterValues("opt_scheme");
			if (json.opt_scheme != null) {
				for (String opt : json.opt_scheme) {
					if ("mail".equals(opt)) {
						mail_opt_out = true;
						request.setAttribute("mail_opt", true);
					} else if ("phone".equals(opt)) {
						request.setAttribute("phone_opt", true);
					} else if ("online".equals(opt)) {
						request.setAttribute("online_opt", true);
					} else if ("email".equals(opt)) {
						request.setAttribute("email_opt", true);
					} else if ("DNT".equals(opt)) {
						request.setAttribute("DNT_opt", true);
					} else if ("cookie".equals(opt)) {
						request.setAttribute("cookie_opt", true);
					}
				}
			}
		}
		request.setAttribute("mail_opt_out", mail_opt_out);
		
		if (mail_opt_out) {
			json.joint_acct = request.getParameter("joint_acct");
			request.setAttribute("joint_acct", json.joint_acct);
			
			json.opt_addr1 = request.getParameter("opt_addr1");
			request.setAttribute("opt_addr1", json.opt_addr1);
			
			json.opt_addr2 = request.getParameter("opt_addr2");
			request.setAttribute("opt_addr2", json.opt_addr2);
			
			json.opt_city = request.getParameter("opt_city");
			request.setAttribute("opt_city", json.opt_city);
			
			json.opt_state = request.getParameter("opt_state");
			request.setAttribute("opt_state", json.opt_state);
			
			json.opt_zip = request.getParameter("opt_zip");
			request.setAttribute("opt_zip", json.opt_zip);
		}
		
		json.who_we_are = request.getParameter("who_we_are");
		request.setAttribute("who_we_are", json.who_we_are);
		
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

	@Override
	public String getName() {
		return "generate.do";
	}

}
