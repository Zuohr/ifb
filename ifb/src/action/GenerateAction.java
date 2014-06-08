package action;

import io.CharArrayWriterResponse;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import control.Controller;
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

		ArrayList<String> what_coll = new ArrayList<String>();
		Map<String, String> what_coll_map = new HashMap<String, String>();
		what_coll_map.put("ssn", "SSN");
		what_coll_map.put("income", "income");
		what_coll_map.put("balance", "account balances");
		what_coll_map.put("pay_his", "payment history");
		what_coll_map.put("tr_his", "transaction history");
		what_coll_map.put("lo_his", "transaction or loss history");
		what_coll_map.put("cr_his", "credit history");
		what_coll_map.put("cr_sco", "credit scores");
		what_coll_map.put("assets", "assets");
		what_coll_map.put("in_exp", "investment experience");
		what_coll_map.put("cr_ins", "credit-based insurance scores");
		what_coll_map.put("ins_cl", "insurance claim history");
		what_coll_map.put("med_inf", "medical information");
		what_coll_map.put("ove_his", "overdraft history");
		what_coll_map.put("pur_his", "purchase history");
		what_coll_map.put("acc_tra", "account transactions");
		what_coll_map.put("ris_tol", "risk tolerance");
		what_coll_map.put("med_rel", "medical-related debts");
		what_coll_map.put("cr_car", "credit card or other debt");
		what_coll_map.put("mor_rat", "mortgage rates and payments");
		what_coll_map.put("ret_ass", "retirement assets");
		what_coll_map.put("che_acc", "checking account information");
		what_coll_map.put("emp_info", "employment information");
		what_coll_map.put("wire_tran", "wire transfer instructions");
		json.what_coll = request.getParameterValues("what_coll");
		if (json.what_coll != null) {
			for (String what : json.what_coll) {
				what_coll.add(what_coll_map.get(what));
			}
		}
		request.setAttribute("what_coll", what_coll);

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
					} else if ("e-mail".equals(opt)) {
						request.setAttribute("email_opt", true);
					} else if ("DNT".equals(opt)) {
						request.setAttribute("DNT_opt", true);
					} else if ("cookies".equals(opt)) {
						request.setAttribute("cookie_opt", true);
					}
				}
			}
		}
		request.setAttribute("mail_opt_out", mail_opt_out);

		if (mail_opt_out) {
			json.joint_acct = request.getParameter("joint_acct");
			if ("yes".equals(json.joint_acct)) {
				request.setAttribute("joint_acct", true);
			}

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

			ArrayList<String> messages = new ArrayList<String>();

			json.mail_opt_out_1 = request.getParameter("mail_opt_out_1");
			request.setAttribute("mail_opt_out_1", json.mail_opt_out_1);
			if ("yes".equals(json.mail_opt_out_1)) {
				messages.add("Do not share information about my creditworthiness with your affiliates for their everyday business purposes.");
			}

			json.mail_opt_out_2 = request.getParameter("mail_opt_out_2");
			request.setAttribute("mail_opt_out_2", json.mail_opt_out_2);
			if ("yes".equals(json.mail_opt_out_2)) {
				messages.add("Do not allow your affiliates to use my personal information to market to me.");
			}
			json.mail_opt_out_3 = request.getParameter("mail_opt_out_3");
			request.setAttribute("mail_opt_out_3", json.mail_opt_out_3);
			if ("yes".equals(json.mail_opt_out_3)) {
				messages.add("Do not share my personal information with nonaffiliates to market their products and services to me.");
			}

			json.mail_opt_out_4 = request.getParameter("mail_opt_out_4");
			request.setAttribute("mail_opt_out_4", json.mail_opt_out_4);
			if ("yes".equals(json.mail_opt_out_4)) {
				messages.add("Do not share my personal information to market to me.");
			}

			json.mail_opt_out_5 = request.getParameter("mail_opt_out_5");
			request.setAttribute("mail_opt_out_5", json.mail_opt_out_5);
			if ("yes".equals(json.mail_opt_out_5)) {
				messages.add("Do not share my personal information with other financial institutions to jointly market to me.");
			}

			request.setAttribute("mail_opt_options", messages);
		}

		json.who_we_are = request.getParameter("who_we_are");
		request.setAttribute("who_we_are", json.who_we_are);

		json.protect = request.getParameter("protect");
		request.setAttribute("protect", json.protect);

		ArrayList<String> how_coll = new ArrayList<String>();
		Map<String, String> how_coll_map = new HashMap<String, String>();
		how_coll_map.put("open", "open an account");
		how_coll_map.put("dep", "deposit money");
		how_coll_map.put("pay bill", "pay your bills");
		how_coll_map.put("app_loan", "apply for a loan");
		how_coll_map.put("use_card", "use your credit or debit card");
		how_coll_map.put("seek_tax", "seek financial or tax advice");
		how_coll_map.put("app_ins", "apply for insurance");
		how_coll_map.put("pay_ins", "pay insurance premiums");
		how_coll_map.put("file_ins", "file an insurance claim");
		how_coll_map.put("seek_adv", "seek advice about your");
		how_coll_map.put("buy_sec", "buy securities from us");
		how_coll_map.put("sell_sec", "sell securities to us");
		how_coll_map.put("dir_buy", "direct us to buy securities");
		how_coll_map.put("dir_sell", "direct us to sell your securities");
		how_coll_map.put("make_dep",
				"make deposits or withdrawals from your account");
		how_coll_map.put("ent_inv",
				"enter into an investment advisory contract");
		how_coll_map.put("inc_inf", "give us your income information");
		how_coll_map.put("pro_emp", "provide employment information");
		how_coll_map.put("give_emp", "give us your employment history");
		how_coll_map.put("tell_por",
				"tell us about your investment or retirement portfolio");
		how_coll_map.put("tell_ear",
				"tell us about your investment or retirement earnings");
		how_coll_map.put("app_fin", "apply for financing");
		how_coll_map.put("app_lea", "apply for a lease");
		how_coll_map.put("pro_acc", "provide account information");
		how_coll_map.put("giv_con", "give us your contact information");
		how_coll_map.put("pay_che", "pay us by check");
		how_coll_map.put("giv_wag", "give us your wage statements");
		how_coll_map.put("pro_mor", "provide your mortgage information");
		how_coll_map.put("mak_wir", "make a wire transfer");
		how_coll_map.put("who_rec", "tell us who receives the money");
		how_coll_map.put("where_send", "tell us where to send the money");
		how_coll_map.put("show_gov", "show your government-issued ID");
		how_coll_map.put("show_lic", "show your driver's license");
		how_coll_map.put("ord_comm",
				"order a commodity futures or option trade");
		json.how_coll = request.getParameterValues("how_coll");
		if (json.how_coll != null) {
			for (String how : json.how_coll) {
				how_coll.add(how_coll_map.get(how));
			}
		}
		request.setAttribute("how_coll", how_coll);

		Map<String, String> other_src_map = new HashMap<String, String>();
		json.other_src = request.getParameter("other_src");
		other_src_map
				.put("af_bu",
						"We also collect your personal information from others, such as credit bureaus, affiliates, or other companies.");
		other_src_map
				.put("oth_cmp",
						"We also collect your personal information from other companies.");
		other_src_map.put("none", "");
		request.setAttribute("other_src_info",
				other_src_map.get(json.other_src));

		json.state_law = request.getParameter("state_law");
		if ("yes".equals(json.state_law)) {
			request.setAttribute("see_below",
					"See below for more on your rights under state law.");
		}

		json.aff = request.getParameter("aff");
		request.setAttribute("aff", json.aff);

		json.naff = request.getParameter("naff");
		request.setAttribute("naff", json.naff);

		json.jmar = request.getParameter("jmar");
		request.setAttribute("jmar", json.jmar);

		json.other_info = request.getParameter("other_info");
		request.setAttribute("other_info", json.other_info);

		request.setAttribute("dsp_download", false);

		generateFiles(request, response, json);

		compress(request);

		request.setAttribute("dsp_download", true);
		
		return "output.jsp";

	}

	private void generateFiles(HttpServletRequest request,
			HttpServletResponse response, JsonData json) throws Exception {
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
				"config.json")));
		pw.write(new Gson().toJson(json));
		pw.close();

		CharArrayWriterResponse customResponse = new CharArrayWriterResponse(
				response);
		request.getRequestDispatcher(Controller.jspPath + "output.jsp")
				.forward(request, customResponse);
		pw = new PrintWriter(new FileWriter(new File(targetDir,
				"privacy_notice.html")));
		pw.write(customResponse.getOutput());
		pw.close();
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
