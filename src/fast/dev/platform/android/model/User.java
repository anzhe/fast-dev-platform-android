package fast.dev.platform.android.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "Users")
public class User extends Model {

	@Column(name = "Account")
	public String account;
	
	@Column(name = "Password")
	public String password;
	
	@Column(name = "Role")
	public int role;// 0为用户, 1为律师
	
	public User() {
		super();
	}
	
	public User(String account, String password, int role) {
		super();
		
		this.account = account;
		this.password = password;
		this.role = role;
	}
	
}
