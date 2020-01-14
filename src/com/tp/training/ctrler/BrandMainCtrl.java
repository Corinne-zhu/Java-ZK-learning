package com.tp.training.ctrler;

import org.zkoss.zk.ui.select.annotation.Wire;

import com.tp.baselib.model.MapBeanResultList;
import com.tp.baselib.zul.Elistbox;
import com.tp.baselib.zul.ListModelList;
import com.tp.baselib.zul.Window;
import com.tp.training.dao.TrainingDAOFactory;
import com.tp.training.zul.TrainingBaseComposer;
import com.tp.training.zul.TrainingGeneralElistboxActionHandler;

public class BrandMainCtrl extends TrainingBaseComposer {
	@Wire
	private Elistbox masterLbox;

	@Override
	public void doAfterCompose(Window comp) throws Exception {
		super.doAfterCompose(comp);
		this.masterLbox.setActionHandler(new MasterActionHandler());

		// 默認畫面中呈現所有資料
		MapBeanResultList data = TrainingDAOFactory.getBrandDao().brandAll();
		this.masterLbox.setModel(new ListModelList<>(data));

	}

	private class MasterActionHandler extends TrainingGeneralElistboxActionHandler {

		public MasterActionHandler() {
			super(TrainingDAOFactory.getBrandDao(), false);
		}
	}
}
