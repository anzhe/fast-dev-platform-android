package fast.dev.platform.android.fragment.my;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import fast.dev.platform.android.R;
import fast.dev.platform.android.activity.account.ProfileActivity;
import fast.dev.platform.android.fragment.base.BaseFragment;
import fast.dev.platform.android.util.CommonUtils;

public class MyFragment extends BaseFragment {

	private LinearLayout my_consult;
	private LinearLayout my_set;
	private TextView my_consult_tv;
	private TextView my_thing_tv;
	private TextView my_name;
	private TextView my_focus;
	private TextView my_fans;
	private LinearLayout my_thing;
	private LinearLayout my_message;
	private LinearLayout my_account;
	private ImageView my_pic;
	private View msg_container;
	private TextView msg_count;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_my, container, false);
		
		initData();
		initViews(rootView);
		initListeners();
		
		return rootView;
	}

	@Override
	public void onResume() {
		super.onResume();
		
		if (!TextUtils.isEmpty(user_sp.getString("photo", ""))) {
			mImageLoader.displayImageForAvatar(user_sp.getString("photo", ""), my_pic);
		}
		my_name.setText(user_sp.getString("relName", ""));
		
		my_focus.setText("我的关注(" + user_sp.getString("focus_count", "0") + ")");
		my_fans.setText("我的粉丝(" + user_sp.getString("fans_count", "0") + ")");
		
		msg_container.postDelayed(get_msg_timer, 0);
	}

	Runnable get_msg_timer = new Runnable() {
		
		@Override
		public void run() {
			if (user_sp.getInt("msg_count", 0) != 0) {
				msg_container.setVisibility(View.VISIBLE);
				if (user_sp.getInt("msg_count", 0) > 99) {
					msg_count.setText("99+");
				} else {
					msg_count.setText(user_sp.getInt("msg_count", 0) + "");
				}
			} else {
				msg_container.setVisibility(View.GONE);
			}
			
			msg_container.postDelayed(get_msg_timer, 2000);
		}
		
	};
	
	private void initData() {
		
	}

	private void initViews(View rootView) {
		msg_container = rootView.findViewById(R.id.msg_container);
		msg_count = (TextView) rootView.findViewById(R.id.msg_count);
		my_consult = (LinearLayout) rootView.findViewById(R.id.my_consult);
		my_set = (LinearLayout) rootView.findViewById(R.id.my_set);
		my_thing = (LinearLayout) rootView.findViewById(R.id.my_thing);
		my_message = (LinearLayout) rootView.findViewById(R.id.my_message);
		my_account = (LinearLayout) rootView.findViewById(R.id.my_account);
		my_consult_tv = (TextView) rootView.findViewById(R.id.my_consult_tv);
		my_thing_tv = (TextView) rootView.findViewById(R.id.my_thing_tv);
		my_name = (TextView) rootView.findViewById(R.id.my_name);
		
		my_focus = (TextView) rootView.findViewById(R.id.my_focus);
		my_fans = (TextView) rootView.findViewById(R.id.my_fans);
		
		my_pic = (ImageView) rootView.findViewById(R.id.my_pic);
		if (CommonUtils.isLawyer(user_sp)) {
			my_consult_tv.setText("我的回复");
			my_thing_tv.setText("我的受理");
		}
	}

	private void initListeners() {
		my_consult.setOnClickListener(this);
		my_set.setOnClickListener(this);
		my_thing.setOnClickListener(this);
		my_message.setOnClickListener(this);
		my_account.setOnClickListener(this);
		my_focus.setOnClickListener(this);
		my_fans.setOnClickListener(this);
		
		my_pic.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent(getContext(), ProfileActivity.class));
			}
			
		});
	}

	@Override
	public void onClick(View v) {/*
		Intent intent = null;
		switch (v.getId()) {
		case R.id.my_consult:
			intent = new Intent(getContext(), MyConsultActivity.class);
			startActivity(intent);
			break;
		case R.id.my_set:
			intent = new Intent(getContext(), SettingsActivity.class);
			startActivity(intent);
			break;
		case R.id.my_message:
			intent = new Intent(getContext(), MessageActivity.class);
			startActivity(intent);
			break;
		case R.id.my_account:
			if (CommonUtils.isLawyer(user_sp)) {
				intent = new Intent(getContext(), LawyerAccountActivity.class);
			} else {
				intent = new Intent(getContext(), UserAccountActivity.class);
			}
			startActivity(intent);
			break;
		case R.id.my_thing:
			if (CommonUtils.isLawyer(user_sp)) {
				intent = new Intent(getContext(), MyCaseEntrustActivty.class);
			} else if (CommonUtils.isUser(user_sp)) {
				intent = new Intent(getContext(), MyBusinessActivity.class);
			}
			startActivity(intent);
			break;
		case R.id.my_focus:
			Intent intent1 = new Intent(getContext(), MyFocusActivity.class);
			if (CommonUtils.isLawyer(user_sp)) {
				intent1.putExtra("lawyerid", Long.parseLong(user_sp.getString("lawyerid", "-1")));
			} else {
				intent1.putExtra("userid", Long.parseLong(user_sp.getString("userid", "-1")));
			}
			startActivity(intent1);
			break;
		case R.id.my_fans:
			Intent intent2 = new Intent(getContext(), MyFansActivity.class);
			if (CommonUtils.isLawyer(user_sp)) {
				intent2.putExtra("lawyerid", Long.parseLong(user_sp.getString("lawyerid", "-1")));
			} else {
				intent2.putExtra("userid", Long.parseLong(user_sp.getString("userid", "-1")));
			}
			startActivity(intent2);
			break;
		}
	*/}

}
