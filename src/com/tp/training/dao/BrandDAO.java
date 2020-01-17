package com.tp.training.dao;

import java.sql.SQLException;

import com.tp.baselib.model.MapBean;
import com.tp.baselib.model.MapBeanResultList;

/* Table 設置DAO*/
public class BrandDAO extends TrainingDAO {

	@Override
	public String getMainTableName() {
		return "WT_BRAND";
	}

	@Override
	public int delete(MapBean bean) throws SQLException {
		String brandId = bean.get("BRAND_ID");
		super.execute(" DELETE FROM  WT_BRAND_SEASON WHERE BRAND_ID= ?", brandId);
		return super.delete(bean);// 呼叫 super 刪主
	}

	// 檢索出所有的資料
	public MapBeanResultList brandAll() throws SQLException {
		return super.queryMapBeanResultList("SELECT * FROM " + this.getMainTableName() + " ORDER BY BRAND_NO ");
	}

	// 彈出窗口的查詢條件，查找資料
	public MapBeanResultList queryByBrand(String brandNo, String brandNm) throws SQLException {
		return super.queryMapBeanResultList(" SELECT * FROM  " + this.getMainTableName()
				+ " WHERE BRAND_NO LIKE '%' ||?||'%' AND  (  ? IS NULL OR  BRAND_NAME LIKE '%'||?||'%' )  ORDER BY BRAND_NO ",
				brandNo, brandNm, brandNm);
	}

	// 按照彈出窗口的查詢條件，查找資料
	public MapBeanResultList queryByWin(String brandNo, String brandNm) throws SQLException {
		return super.queryMapBeanResultList(" SELECT * FROM  " + this.getMainTableName()
				+ " WHERE BRAND_NO LIKE '%' ||?||'%' AND  (  ? IS NULL OR  BRAND_NAME LIKE '%'||?||'%' )  ORDER BY BRAND_NO ",
				brandNo, brandNm, brandNm);
	}
}
