package com.tp.training.ctrler;

import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;

import com.tp.baselib.model.MapBeanResultList;
import com.tp.baselib.zul.Combobox;
import com.tp.baselib.zul.Elistbox;
import com.tp.baselib.zul.ListModelList;
import com.tp.baselib.zul.Window;
import com.tp.training.dao.TrainingDAOFactory;
import com.tp.training.zul.TrainingBaseComposer;
import com.tp.training.zul.TrainingGeneralElistboxActionHandler;

public class StyleElist extends TrainingBaseComposer {
	@Wire
	private Elistbox masterLbox;
	@Wire
	private Combobox brandCombo, yearCombo, seasonCombo;

	@Override
	public void doAfterCompose(Window comp) throws Exception {
		super.doAfterCompose(comp);
		this.masterLbox.setActionHandler(new MasterActionHandler());

		MapBeanResultList data = TrainingDAOFactory.getStyleDao().getStyleAll();
		this.masterLbox.setModel(new ListModelList<>(data));


		// 新增時帶出年度(當前年度 && 下一年度)
//		Calendar cal = Calendar.getInstance();
//		for (int i = 0; i < 3; i++) {
//			int year = cal.get(Calendar.YEAR) + i;
//			Comboitem item = new Comboitem();
//			item.setValue(String.valueOf(year));
//			item.setLabel(String.valueOf(year));
//			this.yearCombo.appendChild(item);
//		}
	}

	@Listen("onSelectBrand=#masterLbox")
	public void loadBrand(ForwardEvent event) throws Exception {
		SelectEvent originEvent = (SelectEvent) event.getOrigin();
		Combobox brandCombo = (Combobox) originEvent.getTarget();// 取得觸發事件的元件
		MapBeanResultList brandData = TrainingDAOFactory.getBrandDao().brandAll();
		brandCombo.setModel(new ListModelList<>(brandData));

	}

	private class MasterActionHandler extends TrainingGeneralElistboxActionHandler {
		public MasterActionHandler() {
			super(TrainingDAOFactory.getStyleDao(), false);
		}
	}
}
