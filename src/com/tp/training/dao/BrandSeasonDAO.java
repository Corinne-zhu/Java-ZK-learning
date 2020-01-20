package com.tp.training.dao;

import java.math.BigDecimal;
import java.sql.SQLException;

import com.tp.baselib.model.MapBeanResultList;
import com.tp.baselib.model.MapBeanSQLRunner;
import com.tp.baselib.util.DbConnHelper;

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

	// 檢索出BRAND_ID =? 的資料筆數 (一般寫法)
	public int queryBrandCount(String brandID) throws SQLException {
		Object rtn = MapBeanSQLRunner.querySingleValue(DbConnHelper.get(this.getMainDBName()),
				"SELECT COUNT(*) FROM " + this.getMainTableName() + " WHERE BRAND_ID=?", brandID);
		if (rtn instanceof BigDecimal) {
			BigDecimal bd = (BigDecimal) rtn;
			return bd.intValue();
		}
		return -1;

	}

}
