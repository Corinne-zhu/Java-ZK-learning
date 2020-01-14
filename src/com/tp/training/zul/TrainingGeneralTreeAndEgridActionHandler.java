package com.tp.training.zul;

import com.tp.baselib.model.MapBeanDAO;
import com.tp.webplugins.admin.zul.GeneralTreeAndEgridActionHandler;

public class TrainingGeneralTreeAndEgridActionHandler extends GeneralTreeAndEgridActionHandler {

	public TrainingGeneralTreeAndEgridActionHandler(MapBeanDAO dao) {
		super(dao);
	}

	public TrainingGeneralTreeAndEgridActionHandler(MapBeanDAO dao, boolean needDataLog) {
		super(dao, needDataLog);
	}
}
