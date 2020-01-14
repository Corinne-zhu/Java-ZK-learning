package com.tp.training.zul;

import com.tp.baselib.model.MapBeanDAO;
import com.tp.webplugins.admin.zul.GeneralListboxAndEgridActionHandler;

public class TrainingGeneralListboxAndEgridActionHandler extends GeneralListboxAndEgridActionHandler {

	public TrainingGeneralListboxAndEgridActionHandler(MapBeanDAO dao) {
		super(dao);
	}

	public TrainingGeneralListboxAndEgridActionHandler(MapBeanDAO dao, boolean needDataLog) {
		super(dao, needDataLog);
	}

}
