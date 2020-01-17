package com.tp.training.ctrler;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;

import com.tp.baselib.model.MapBean;
import com.tp.baselib.model.MapBeanResultList;
import com.tp.baselib.zul.Egrid;
import com.tp.baselib.zul.ListModelList;
import com.tp.baselib.zul.Listbox;
import com.tp.baselib.zul.Listitem;
import com.tp.baselib.zul.Window;
import com.tp.training.dao.TrainingDAOFactory;
import com.tp.training.zul.TrainingBaseComposer;
import com.tp.training.zul.TrainingGeneralListboxAndEgridActionHandler;

public class BrandMainEgrid extends TrainingBaseComposer {
	@Wire
	private Listbox indexLbox;

	@Wire
	private Egrid masterGrid;

	@Override
	public void doAfterCompose(Window comp) throws Exception {
		super.doAfterCompose(comp);

		this.masterGrid.setActionHandler(new MasterActionHandler());
		// 預設不帶出資料 & 畫面按鈕可用
		this.indexLbox.setModel(new ListModelList<>());
	}

	@Override
	protected boolean onQuery(MapBean bean) throws Exception {
		String brandNo = bean.get("BRAND_NO");
		String brandNm = bean.get("BRAND_NAME");
		MapBeanResultList data = TrainingDAOFactory.getBrandDao().queryByBrand(brandNo, brandNm);
		this.indexLbox.setModel(new ListModelList<>(data));
		return true;
	}

	// 將資料顯示在畫面中
	@Listen("onItemFocus=#indexLbox")
	public void onIndexItemFocused() {
		Listitem focusedItem = this.indexLbox.getFocusedItem();
		MapBean bean = focusedItem.getValue();
		this.masterGrid.setBean(bean);
	}

	// 主檔的ActionHandler
	private class MasterActionHandler extends TrainingGeneralListboxAndEgridActionHandler {
		public MasterActionHandler() {
			super(TrainingDAOFactory.getBrandDao(), false);
		}

		// 唯一性管控
		@Override
		protected String[][] getUkColNames() {
			return new String[][] { { "BRAND_NO" } };
		}
	}

}
