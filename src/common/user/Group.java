package common.user;

/**
 * 用户组
 * @author liangyx
 * 2014-09-18
 */
public class Group {
	private int id;
	private String name;
	private String authCode;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAuthCode() {
		return authCode;
	}
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}
	
}
