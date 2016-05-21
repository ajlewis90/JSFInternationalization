package org.arthur.mvcdemo.model;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.CustomScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.arthur.mvcdemo.annotation.MySessionScoped;
import org.arthur.mvcdemo.service.LoginService;

@ManagedBean(name="user")
@SessionScoped
/*@CustomScoped("#{timeoutScope}")
@MySessionScoped(1)*/

public class User {
	private int user_oid;
	private String userName;
	private String password;
	private int lang_oid;
	private Locale locale;
	private ResourceBundle resourceBundle;

	public User(){

	}
	
	public int getUser_oid() {
		return user_oid;
	}

	public void setUser_oid(int user_oid) {
		this.user_oid = user_oid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getLang_oid() {
		return lang_oid;
	}

	public void setLang_oid(int lang_oid) {
		this.lang_oid = lang_oid;
	}

	//validate login
	public String validateUsernamePassword() {
		LoginService service = new LoginService();
		boolean valid = service.authenticate(userName, password);
		locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();

		if (valid) {
			HttpSession session = SessionBean.getSession();
			User user = service.getUserInformation(userName);
			String preferredLanguageCode = service.mapLanguageOIDToCode(user.getLang_oid());
			locale = Locale.forLanguageTag(preferredLanguageCode);
			resourceBundle = ResourceBundle.getBundle("org.arthur.mvcdemo.i18n.properties.ResourceBundle", locale);

			session.setAttribute("userName", userName);
			session.setAttribute("languageTag", resourceBundle);

			return "Success.jsp";
		} 

		else {
			resourceBundle = ResourceBundle.getBundle("org.arthur.mvcdemo.i18n.properties.ResourceBundle", locale);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_WARN,
							resourceBundle.getString("VALIDATION"),
							resourceBundle.getString("VALIDATION2")));
			return FacesContext.getCurrentInstance().getViewRoot().getViewId();
		}
	}

	
}
