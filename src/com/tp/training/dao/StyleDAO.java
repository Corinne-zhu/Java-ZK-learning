package com.tp.training.dao;

import java.sql.SQLException;

import com.tp.baselib.model.MapBeanResultList;

/* Table 設置DAO*/
public class StyleDAO extends TrainingDAO {

	@Override
	public String getMainTableName() {
		return "WT_STYLE";
	}

	// 從 WT_STYLE 中抓取所有的資料
	public MapBeanResultList getStyleAll() throws SQLException {
		return super.queryMapBeanResultList(" SELECT * FROM " + this.getMainTableName() + " ORDER BY 1");

	}

}
