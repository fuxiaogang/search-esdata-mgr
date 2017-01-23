/**
 * 
 */
package com.wlw.wlsearch.config;

/**
 * @Description TODO
 * @author fuxg
 * @date 2016年6月4日
 */
public enum CatEnum {

	ALL("所有", 1), STORE("店铺", 2), GOODS("商品", 3), VIDEO("视频", 4), NEWS("新闻", 5), QUESTION("问问", 6), IMAGE("相册", 7), GAME("游戏", 8), USER("人",
			9), GOODS_STORE("商品和店铺", 10);
	private String name;
	private int id;

	private CatEnum(String name, int id) {
		this.name = name;
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public int getId() {
		return id;
	}

}
