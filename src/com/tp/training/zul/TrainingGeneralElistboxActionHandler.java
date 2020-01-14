package com.tp.training.zul;

import com.tp.baselib.model.MapBeanDAO;
import com.tp.webplugins.admin.zul.GeneralElistboxActionHandler;

public class TrainingGeneralElistboxActionHandler extends GeneralElistboxActionHandler {

	public TrainingGeneralElistboxActionHandler(MapBeanDAO dao) {
		super(dao);
	}

	public TrainingGeneralElistboxActionHandler(MapBeanDAO dao, boolean needDataLog) {
		super(dao, needDataLog);
	}
}
