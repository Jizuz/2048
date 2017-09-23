package com.zshoon.game2048.view;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.GridLayout;

import com.zshoon.game2048.helper.GameHelper;
import com.zshoon.game2048.model.Card;

/**
 * <descripton>
 * @author Jizuz
 * @creatDate 2017-9-23
 * @see com.zshoon.game2048.view.GameView.java
 */
public class GameView extends GridLayout {

	/**
	 * @param context
	 */
	public GameView(Context context) {
		super(context);
		initGameView();
	}
	
	/**
	 * @param context
	 * @param attrs
	 */
	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initGameView();
	}
	
	/**
	 * @param context
	 * @param attrs
	 * @param defStyle
	 */
	public GameView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		initGameView();
	}
	
	/**
	 * <descripton> 初始化游戏界面
	 * @author Jizuz
	 * @creatDate 2017-9-23
	 */
	@SuppressLint("ClickableViewAccessibility")
	public void initGameView() {
		// 设置列数
		setColumnCount(4);
		// 设置界面背景色
		setBackgroundColor(0xfff0f0f4);
		
		// 监听触控操作
		setOnTouchListener(new View.OnTouchListener() {
			
			private float startX, startY, offsetX, offsetY;
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				switch (event.getAction()) {
					case MotionEvent.ACTION_DOWN:  // 按下：0
						startX = event.getX();
						startY = event.getY();
						break;
	
					case MotionEvent.ACTION_UP:  // 离开：1
						offsetX = event.getX() - startX;
						offsetY = event.getY() - startY;
						// 绝对值比较X轴和Y轴大小，判断横向或者纵向移动
						if (Math.abs(offsetX) > Math.abs(offsetY)) {
							if (offsetX < -5)
								toLeft();
							else if (offsetX > 5)
								toRight();
						}
						else {
							if (offsetY < -5)
								toUp();
							else if (offsetY > 5)
								toDown();
						}
						
						break;
					default:
						break;
					}
				return true;
			}

		});
	}
	
	/**
	 * <descripton> 设置屏幕适应，计算表格宽度，开始游戏
	 * @author Jizuz
	 * @creatDate 2017-9-23
	 * @param w int
	 * @param h int
	 * @param oldw int
	 * @param oldh int
	 * @see android.view.View#onSizeChanged(int, int, int, int)
	 */
	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		
		int cardWidth = (Math.min(w, h) - 10) / 4;
		addCards(cardWidth, cardWidth);
		
		GameHelper.startGame();
	}
	
	/**
	 * <descripton> 添加表格卡片
	 * @author Jizuz
	 * @creatDate 2017-9-23
	 * @param width int
	 * @param height int
	 */
	private void addCards(int width, int height) {
		Card c;
		
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				c = new Card(getContext());
				c.setNum(2);
				addView(c, width, height);
				
				GameHelper.cardMap[x][y] = c;
			}
		}
	}

	/**
	 * <descripton> 判断是否结束游戏
	 * @author Jizuz
	 * @creatDate 2017-9-23
	 */
	public void isFinished() {
		
		boolean isfinish = true;
		
		isfinish = GameHelper.checkIsFinished();
		
		if (isfinish) {
			new AlertDialog.Builder(getContext()).setTitle("sorry !").setMessage("The game has been over !").setPositiveButton("ReStart", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					GameHelper.startGame();
				}
			}).show();
		}
	}

	/**
	 * <descripton> 向左移动
	 * @author Jizuz
	 * @creatDate 2017-9-23
	 */
	private void toLeft() {
		
		boolean flag = GameHelper.checkToLeft();
		
		if (flag) {
			GameHelper.addRandomNum();
			isFinished();
		}
	}

	/**
	 * <descripton> 向右移动
	 * @author Jizuz
	 * @creatDate 2017-9-23
	 */
	private void toRight() {
		
		boolean flag = GameHelper.checkToRight();
		
		if (flag) {
			GameHelper.addRandomNum();
			isFinished();
		}
	}



	/**
	 * <descripton> 向上移动
	 * @author Jizuz
	 * @creatDate 2017-9-23
	 */
	private void toUp() {

		boolean flag = GameHelper.checkToUp();
		
		if (flag) {
			GameHelper.addRandomNum();
			isFinished();
		}
	}



	/**
	 * <descripton> 向下移动
	 * @author Jizuz
	 * @creatDate 2017-9-23
	 */
	private void toDown() {
		
		boolean flag = GameHelper.checkToDown();
		
		if (flag) {
			GameHelper.addRandomNum();
			isFinished();
		}
	}


}
