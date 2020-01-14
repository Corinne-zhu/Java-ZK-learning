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

}
