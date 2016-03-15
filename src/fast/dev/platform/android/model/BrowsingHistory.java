package fast.dev.platform.android.model;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "BrowsingHistory")
public class BrowsingHistory extends Model {

	/**
	 * 浏览律师Id
	 */
	@Column(name = "userId")
	public String userId;

	/**
	 * 浏览项Id
	 */
	@Column(name = "itemId")
	public String itemId;

	/**
	 * 浏览类型，0为个人消息列表，1为咨询列表，2为案件委托列表
	 */
	@Column(name = "browsingType")
	public int browsingType;

}
