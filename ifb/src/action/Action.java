package action;

import javax.servlet.http.HttpServletRequest;

public interface Action {
	public String perform(HttpServletRequest request);

	public String getName();
}
