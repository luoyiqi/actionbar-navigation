package com.cobyplain.actionbarnavigation;

/*
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 * 
 * @author Coby Plain coby.plain@gmail.com
 */

import android.app.ActionBar;
import android.app.Activity;
import android.app.ActionBar.OnNavigationListener;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class BaseActivity extends Activity {

	protected String[] navMenuItems;
	protected Context mContext;
	protected ActionBar mActionBar;
	protected int mSelectedCount = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		setUpActionBar();

	}

	protected void setUpActionBar() {

		navMenuItems = new String[] { "Main page", "Page 1", "Page 2", "Page 3", "Page 4" };

		ArrayAdapter<String> mSpinnerAdapter = new ArrayAdapter<String>(this, R.layout.custom_spinner_item, navMenuItems);
		mSpinnerAdapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_item);

		mActionBar = getActionBar();
		mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
		this.setTitle("");

		OnNavigationListener mOnNavigationListener = new OnNavigationListener() {

			@Override
			public boolean onNavigationItemSelected(int position, long itemId) {

				switch (mSelectedCount) {
				case 0:
					mSelectedCount = 1;
					break;

				default:
					Intent intent = null;
					boolean intentHit = true;
					switch (position) {

					case 0:
						intent = new Intent(mContext, MainActivity.class);
						break;
					case 1:
						intent = new Intent(mContext, OneActivity.class);
						break;
					case 2:
						intent = new Intent(mContext, TwoActivity.class);
						break;
					case 3:
						intent = new Intent(mContext, ThreeActivity.class);
						break;
					case 4:
						intent = new Intent(mContext, FourActivity.class);
						break;
					default:
						intentHit = false;
						break;

					}

					if (intentHit) {
						intent.putExtra("position", position);
						startActivity(intent);
						overridePendingTransition(0, 0); //this is only to give the illusion activities are not being launched
						finish();
					}
					break;
				}
				return true;
			}
		};

		mActionBar.setListNavigationCallbacks(mSpinnerAdapter, mOnNavigationListener);
		
		Bundle bundle = getIntent().getExtras();
		
		if (bundle != null)
			mActionBar.setSelectedNavigationItem(bundle.getInt("position", 0));

	}

}
