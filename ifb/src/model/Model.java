package model;

import javax.servlet.ServletConfig;

public class Model {
	private String downloadPath;

	public Model(ServletConfig config) {
		this.downloadPath = config.getInitParameter("downloadPath");
	}

	public String getDownloadPath() {
		return downloadPath;
	}
}
