package com.tp.training.ctrler;

import org.zkoss.zk.ui.select.annotation.Wire;

import com.tp.baselib.model.MapBean;
import com.tp.baselib.model.MapBeanResultList;
import com.tp.baselib.zul.Elistbox;
import com.tp.baselib.zul.ListModelList;
import com.tp.baselib.zul.Window;
import com.tp.training.dao.TrainingDAOFactory;
import com.tp.training.zul.TrainingBaseComposer;
import com.tp.training.zul.TrainingGeneralElistboxActionHandler;

public class BrandMainElist extends TrainingBaseComposer {
	@Wire
	private Elistbox masterLbox;

	@Override
	public void doAfterCompose(Window comp) throws Exception {
		super.doAfterCompose(comp);
		this.masterLbox.setActionHandler(new MasterActionHandler());

		// 默認畫面中呈現所有資料
//		MapBeanResultList data = TrainingDAOFactory.getBrandDao().brandAll();
//		this.masterLbox.setModel(new ListModelList<>(data));

		// 畫面打開不顯示任何資料
		this.masterLbox.setModel(new ListModelList<>());
	}

	// 查詢功能
	@Override
	protected boolean onQuery(MapBean bean) throws Exception {
		String brandNo = bean.get("BRAND_NO");
		String brandNm = bean.get("BRAND_NAME");
		MapBeanResultList data = TrainingDAOFactory.getBrandDao().queryByBrand(brandNo, brandNm);
		this.masterLbox.setModel(new ListModelList<>(data));
		return true;
	}

	//listbox 中的資料顯示在畫面中

	private class MasterActionHandler extends TrainingGeneralElistboxActionHandler {

		public MasterActionHandler() {
			super(TrainingDAOFactory.getBrandDao(), false);
		}

		//唯一性管控
		@Override
		protected String[][] getUkColNames() {
			return new String[][] {{"BRAND_NO"}};
		}

	}
}
