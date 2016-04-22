package in.eventmap.front.action.country;

import in.eventmap.common.dao.LanguageCountryDao;
import in.eventmap.front.action.AbstractAction;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

public class IndexAction extends AbstractAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Resource
	private LanguageCountryDao languageCountryDao;
	private Map<String, Object> uploadResultJson = new HashMap<String, Object>();

	@Action(value = "country/index-ajax", results = {
			@Result(name = "success", type = "json", params = { "statusCode",
					"200", "contentType", "application/json", "noCache",
					"true", "root", "uploadResultJson" }),
			@Result(name = "login", type = "json", params = { "statusCode",
					"401", "contentType", "application/json", "noCache",
					"true", "root", "uploadResultJson" }),
			@Result(name = "input", type = "json", params = { "statusCode",
					"200", "contentType", "application/json", "noCache",
					"true", "root", "uploadResultJson" }) })
	@Override
	public String execute() throws Exception {
		Map<String, String> ret = languageCountryDao
				.findSelectListByLanguageCode();
		uploadResultJson.put("countries", ret);
		return SUCCESS;
	}

	public Map<String, Object> getUploadResultJson() {
		return uploadResultJson;
	}
}
