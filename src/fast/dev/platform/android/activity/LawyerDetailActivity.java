package fast.dev.platform.android.activity;

import com.bumptech.glide.Glide;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;
import fast.dev.platform.android.R;
import fast.dev.platform.android.activity.base.BaseActivity;
import fast.dev.platform.android.bean.LawyerBean;

public class LawyerDetailActivity extends BaseActivity {

	public static final String EXTRA_NAME = "selected_lawyer";

	private TextView area;
	private TextView phone;
	private TextView email;
	private TextView profile;
	
	private LawyerBean selectedLawyer;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_lawyer_detail);

		area = (TextView) findViewById(R.id.area);
		phone = (TextView) findViewById(R.id.phone);
		email = (TextView) findViewById(R.id.email);
		profile = (TextView) findViewById(R.id.profile);
		
		Intent intent = getIntent();
		selectedLawyer = (LawyerBean) intent.getSerializableExtra(EXTRA_NAME);

		area.setText("地区：" + selectedLawyer.getPROVINCE() + "-" + selectedLawyer.getCITY());
		phone.setText("电话号码：" + selectedLawyer.getMY_PHONE());
		email.setText("电子邮箱：" + selectedLawyer.getEMAIL());
		profile.setText(selectedLawyer.getPROFILE());
		
		final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
		collapsingToolbar.setTitle(selectedLawyer.getREL_NAME());

		loadBackdrop();
	}

	private void loadBackdrop() {
		final ImageView imageView = (ImageView) findViewById(R.id.backdrop);
		Glide.with(this).load(selectedLawyer.getPHOTO()).centerCrop().into(imageView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.sample_actions, menu);
		return true;
	}

}
