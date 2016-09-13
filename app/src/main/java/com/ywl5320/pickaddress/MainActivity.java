package com.ywl5320.pickaddress;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import android.widget.Toast;

import com.ywl5320.pickaddress.wheel.widget.dialog.ChangeAddressDialog;
import com.ywl5320.pickaddress.wheel.widget.dialog.ChangeAddressDialog.OnAddressCListener;
import com.ywl5320.pickaddress.wheel.widget.dialog.ChangeBirthDialog;
import com.ywl5320.pickaddress.wheel.widget.dialog.ChangeBirthDialog.OnBirthListener;

import java.util.Calendar;

/**
 * 使用方法，在布局中直接引用自定义组件WheelView，然后自定义显示适配器，这个适配器继承AbstractWheelTextAdapter，就跟继承BaseAdapter差不多。
 * 然后自定义dialog传送数据给activity的回调接口，跟fragment传数据给activity的回调接口一样。
 * 
 * WheelView的作用基本就是得到适配器选中的索引，然后玩家根据索引和适配器的数据，自定义返回数据是什么，这就达到了数据回调交互。
 * 
 * @author admin
 *
 */
public class MainActivity extends Activity {

	private TextView mBirth;
	private TextView mAddress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mBirth = (TextView) findViewById(R.id.tv_birth);
		mAddress = (TextView) findViewById(R.id.tv_address);

		mBirth.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ChangeBirthDialog mChangeBirthDialog = new ChangeBirthDialog(
						MainActivity.this);
				final Calendar calendar=Calendar.getInstance();
				mChangeBirthDialog.setDate(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH)+1, calendar.get(Calendar.DATE));
				mChangeBirthDialog.show();
				mChangeBirthDialog.setBirthdayListener(new OnBirthListener() {

					@Override
					public void onClick(String year, String month, String day) {
						// TODO Auto-generated method stub
						Toast.makeText(MainActivity.this,
								year + "-" + month + "-" + day,
								Toast.LENGTH_LONG).show();
					}
				});
			}
		});

		mAddress.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ChangeAddressDialog mChangeAddressDialog = new ChangeAddressDialog(
						MainActivity.this);
				mChangeAddressDialog.setAddress("四川", "成都");
				mChangeAddressDialog.show();
				mChangeAddressDialog
						.setAddresskListener(new OnAddressCListener() {

							@Override
							public void onClick(String province, String city) {
								// TODO Auto-generated method stub
								Toast.makeText(MainActivity.this,
										province + "-" + city,
										Toast.LENGTH_LONG).show();
							}
						});
			}
		});
	}
}
