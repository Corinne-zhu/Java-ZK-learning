package com.tp.training.dao;

import java.sql.SQLException;

import com.tp.baselib.model.MapBeanResultList;

/* Table 設置DAO*/
public class BrandDAO extends TrainingDAO {

	@Override
	public String getMainTableName() {
		return "WT_BRAND";
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
}
