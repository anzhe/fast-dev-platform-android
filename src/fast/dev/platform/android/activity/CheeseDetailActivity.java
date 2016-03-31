package fast.dev.platform.android.activity;

import com.bumptech.glide.Glide;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageView;
import fast.dev.platform.android.R;
import fast.dev.platform.android.activity.base.BaseActivity;
import fast.dev.platform.android.model.Cheeses;

public class CheeseDetailActivity extends BaseActivity {

	public static final String EXTRA_NAME = "cheese_name";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_detail);

		Intent intent = getIntent();
		final String cheeseName = intent.getStringExtra(EXTRA_NAME);

		final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
		collapsingToolbar.setTitle(cheeseName);

		loadBackdrop();
	}

	private void loadBackdrop() {
		final ImageView imageView = (ImageView) findViewById(R.id.backdrop);
		Glide.with(this).load(Cheeses.getRandomCheeseDrawable()).centerCrop().into(imageView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.sample_actions, menu);
		return true;
	}

}
