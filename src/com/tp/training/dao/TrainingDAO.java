package com.tp.training.dao;

import com.tp.baselib.model.MapBeanDAO;

/*DB 設置DAO */

public abstract class TrainingDAO extends MapBeanDAO {
	@Override
	public String getMainDBName() {
		return "JavaTraining";
	}
}
