package fast.dev.platform.android.bean.business;

import fast.dev.platform.android.bean.base.BaseBusinessBean;

public class UpdateUserInfoBusinessBean extends BaseBusinessBean {

	private Data data;

	public Data getData() {
		return data;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public class Data {

		private String photo;

		public String getPhoto() {
			return photo;
		}

		public void setPhoto(String photo) {
			this.photo = photo;
		}

	}

}
