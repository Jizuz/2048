package com.zshoon.game2048.model;

import android.content.Context;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.TextView;

/**
 * <descripton> 表格卡片类
 * @author Jizuz
 * @creatDate 2017-9-23
 * @see com.zshoon.game2048.model.Card.java
 */
public class Card extends FrameLayout {

	/**
	 * 构造：设置初始化表格字体大小背景色等属性
	 */
	public Card(Context context) {
		super(context);

		label = new TextView(getContext());
		label.setTextSize(32);
		label.setGravity(Gravity.CENTER);
		
		label.setBackgroundColor(0x33bacac6);
		
		LayoutParams pa = new LayoutParams(-1, -1);
		pa.setMargins(10, 10, 0, 0);
		addView(label, pa);
		
		setNum(0);
	}
	
	/**
	 * label
	 */
	private TextView label;

	/**
	 * num:表格数字
	 */
	private int num = 0;

	/**
	 * <descripton>
	 * @author Jizuz
	 * @creatDate 2017-9-23
	 * @return
	 */
	public int getNum() {
		return num;
	}

	/**
	 * <descripton> 设置表格卡面数字
	 * @author Jizuz
	 * @creatDate 2017-9-23
	 * @param num int
	 */
	public void setNum(int num) {
		this.num = num;
		
		if (num <= 0) {
			label.setText("");
			label.setBackgroundColor(0x33bacac6);
		}
		else {
			label.setText(num + "");
			
			if (num == 2)
				label.setBackgroundColor(0x66ffa631);
			else if (num == 4)
				label.setBackgroundColor(0x66eaff56);
			else if (num == 8)
				label.setBackgroundColor(0x99ff7500);
			else if (num == 16)
				label.setBackgroundColor(0x668cea00);
			else if (num == 32)
				label.setBackgroundColor(0x663de1ad);
			else if (num == 64)
				label.setBackgroundColor(0x66725e82);
			else if (num == 128)
				label.setBackgroundColor(0x66ff2d51);
			else if (num == 256)
				label.setBackgroundColor(0x99b766ad);
			else if (num == 512)
				label.setBackgroundColor(0x66cca4e3);
			else if (num == 1024)
				label.setBackgroundColor(0x66ff0097);
			else if (num == 2048)
				label.setBackgroundColor(0x66ff2121);
			else
				label.setBackgroundColor(0x33000000);
		}
		
	}
	
	/**
	 * <descripton>
	 * @author Jizuz
	 * @creatDate 2017-9-23
	 * @param c Card
	 * @return boolean
	 */
	public boolean equals(Card c) {
		return this.getNum() == c.getNum();
	}

}
