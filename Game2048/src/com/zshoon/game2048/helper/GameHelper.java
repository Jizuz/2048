package com.zshoon.game2048.helper;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Point;

import com.zshoon.game2048.GameActivity;
import com.zshoon.game2048.model.Card;

/**
 * <descripton>
 * @author Jizuz
 * @creatDate 2017-9-24
 * @see com.zshoon.game2048.helper.GameHelper.java
 */
public class GameHelper {
	

	/**
	 * cardMap:存放表格信息
	 */
	public static Card[][] cardMap = new Card[4][4];
	
	/**
	 * emptyPointList:空格列表信息
	 */
	public static List<Point> emptyPointList = new ArrayList<Point>();
	
	/**
	 * <descripton> 开始游戏
	 * @author Jizuz
	 * @creatDate 2017-9-23
	 */
	public static void startGame() {
		
		GameActivity.getGameActivity().clearGame();
		
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				cardMap[x][y].setNum(0);
			}
		}
		
		// 开始游戏添加两格随机数
		addRandomNum();
		addRandomNum();
	}
	
	/**
	 * <descripton> 添加随机数
	 * @author Jizuz
	 * @creatDate 2017-9-23
	 */
	public static void addRandomNum() {
		
		emptyPointList.clear();
		
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				if (cardMap[x][y].getNum() <= 0)
					emptyPointList.add(new Point(x, y));
			}
		}
		
		Point p = emptyPointList.remove((int) (Math.random() * emptyPointList.size()));
		cardMap[p.x][p.y].setNum(Math.random() > 0.1 ? 2 : 4);
	}

	/**
	 * <descripton> 判断是否结束，满足非结束条件返回false
	 * @author Jizuz
	 * @creatDate 2017-9-24
	 * @return boolean
	 */
	public static boolean checkIsFinished() {
		boolean isfinish = true;
		ALL:
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				if (cardMap[x][y].getNum() == 0 ||
						(x > 0 && cardMap[x][y].equals(cardMap[x-1][y])) ||
						(x < 3 && cardMap[x][y].equals(cardMap[x+1][y])) ||
						(y > 0 && cardMap[x][y].equals(cardMap[x][y-1])) ||
						(y < 3 && cardMap[x][y].equals(cardMap[x][y+1]))) {
					
					isfinish = false;
					break ALL;
					
				}
			}
		}
		return isfinish;
	}
	
	/**
	 * <descripton> 校验是否向左产生移动
	 * @author Jizuz
	 * @creatDate 2017-9-24
	 * @return boolean
	 */
	public static boolean checkToLeft() {
		boolean flag = false;
		
		for (int y = 0; y < 4; y++) {
			for (int x = 0; x < 4; x++) {
				
				for (int k = x + 1; k < 4; k++) {
					if (cardMap[k][y].getNum() > 0) {
						if (cardMap[x][y].getNum() <= 0) {
							cardMap[x][y].setNum(cardMap[k][y].getNum());
							cardMap[k][y].setNum(0);
							
							x--;
							flag = true;
						}
						else if (cardMap[x][y].equals(cardMap[k][y])) {
							cardMap[x][y].setNum(cardMap[x][y].getNum() * 2);
							cardMap[k][y].setNum(0);
							
							GameActivity.getGameActivity().addScore(cardMap[x][y].getNum());
							flag = true;
						}
						break;
					}
				}
			}
		}
		return flag;
	}

	/**
	 * <descripton> 校验是否向右产生移动
	 * @author Jizuz
	 * @creatDate 2017-9-24
	 * @return boolean
	 */
	public static boolean checkToRight() {
		boolean flag = false;
		
		for (int y = 0; y < 4; y++) {
			for (int x = 3; x >= 0; x--) {
				
				for (int k = x - 1; k >= 0; k--) {
					if (cardMap[k][y].getNum() > 0) {
						if (cardMap[x][y].getNum() <= 0) {
							cardMap[x][y].setNum(cardMap[k][y].getNum());
							cardMap[k][y].setNum(0);
							
							x++;
							flag = true;
						}
						else if (cardMap[x][y].equals(cardMap[k][y])) {
							cardMap[x][y].setNum(cardMap[x][y].getNum() * 2);
							cardMap[k][y].setNum(0);
							
							GameActivity.getGameActivity().addScore(cardMap[x][y].getNum());
							flag = true;
						}
						break;
					}
				}
			}
		}
		return flag;
	}

	/**
	 * <descripton> 校验是否向上产生移动
	 * @author Jizuz
	 * @creatDate 2017-9-24
	 * @return boolean
	 */
	public static boolean checkToUp() {
		boolean flag = false;
		
		for (int x = 0; x < 4; x++) {
			for (int y = 0; y < 4; y++) {
				
				for (int k = y + 1; k < 4; k++) {
					if (cardMap[x][k].getNum() > 0) {
						if (cardMap[x][y].getNum() <= 0) {
							cardMap[x][y].setNum(cardMap[x][k].getNum());
							cardMap[x][k].setNum(0);
							
							y--;
							flag = true;
						}
						else if (cardMap[x][y].equals(cardMap[x][k])) {
							cardMap[x][y].setNum(cardMap[x][y].getNum() * 2);
							cardMap[x][k].setNum(0);
							
							GameActivity.getGameActivity().addScore(cardMap[x][y].getNum());
							flag = true;
						}
						break;
					}
				}
			}
		}
		return flag;
	}

	/**
	 * <descripton> 校验是否向下产生移动
	 * @author Jizuz
	 * @creatDate 2017-9-24
	 * @return boolean
	 */
	public static boolean checkToDown() {
		boolean flag = false;
		
		for (int x = 0; x < 4; x++) {
			for (int y = 3; y >= 0; y--) {
				
				for (int k = y - 1; k >= 0; k--) {
					if (cardMap[x][k].getNum() > 0) {
						if (cardMap[x][y].getNum() <= 0) {
							cardMap[x][y].setNum(cardMap[x][k].getNum());
							cardMap[x][k].setNum(0);
							
							y++;
							flag = true;
						}
						else if (cardMap[x][y].equals(cardMap[x][k])) {
							cardMap[x][y].setNum(cardMap[x][y].getNum() * 2);
							cardMap[x][k].setNum(0);
							
							GameActivity.getGameActivity().addScore(cardMap[x][y].getNum());
							flag = true;
						}
						break;
					}
				}
			}
		}
		return flag;
	}
}
