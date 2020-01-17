package com.tp.training.dao;

import java.sql.SQLException;

import com.tp.baselib.model.MapBeanResultList;

/* Table 設置DAO*/
public class BrandSeasonDAO extends TrainingDAO {
	@Override
	public String getMainTableName() {
		return "WT_BRAND_SEASON";
	}

	// 從WT_BRAND_SEASON 中抓取出BRAND_ID = ? 的資料
	public MapBeanResultList queryByBrandID(String brandID) throws SQLException {
		return super.queryMapBeanResultList(
				" SELECT * FROM " + this.getMainTableName() + " WHERE  BRAND_ID =  ?  ORDER BY  SEASON_NO", brandID);
	}

}
