package fast.dev.platform.android.bean;

import java.util.ArrayList;

public class MyConsultBean {

	private String title;
	private String time;
	private String level;
	private String level2;
	private String lawyer;
	private String status;
	private ArrayList<ReviewBean> reviews;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getLevel2() {
		return level2;
	}

	public void setLevel2(String level2) {
		this.level2 = level2;
	}

	public String getLawyer() {
		return lawyer;
	}

	public void setLawyer(String lawyer) {
		this.lawyer = lawyer;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ArrayList<ReviewBean> getReviews() {
		return reviews;
	}

	public void setReviews(ArrayList<ReviewBean> reviews) {
		this.reviews = reviews;
	}

}
