package com.tp.training.ctrler;

import java.util.List;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;

import com.tp.baselib.model.MapBean;
import com.tp.baselib.model.MapBeanResultList;
import com.tp.baselib.util.Msgbox;
import com.tp.baselib.util.Multilingual;
import com.tp.baselib.zul.Elistbox;
import com.tp.baselib.zul.InputsContainer.EditEvent;
import com.tp.baselib.zul.ListModelList;
import com.tp.baselib.zul.Listitem;
import com.tp.baselib.zul.Window;
import com.tp.training.dao.TrainingDAOFactory;
import com.tp.training.zul.TrainingBaseComposer;
import com.tp.training.zul.TrainingGeneralElistboxActionHandler;

public class BrandElistboxWithDetail extends TrainingBaseComposer {

	@Wire
	private Elistbox masterLbox, detailLbox;

	@Override
	public void doAfterCompose(Window comp) throws Exception {
		super.doAfterCompose(comp);
		this.masterLbox.setActionHandler(new MasterActionHandler());
		this.detailLbox.setActionHandler(new DetailActionHandler());
		this.masterLbox.setModel(new ListModelList<>());
		this.detailLbox.setModel(new ListModelList<>());
	}

	@Override
	protected boolean onQuery(MapBean bean) throws Exception {
		String brandNo = bean.get("BRAND_NO");
		String brandNm = bean.get("BRAND_NAME");
		MapBeanResultList data = TrainingDAOFactory.getBrandDao().queryByBrand(brandNo, brandNm);
		this.masterLbox.setModel(new ListModelList<>(data));
		return true;
	}

	@Listen("onItemFocus=#masterLbox")
	public void onItemMasterLbox() throws Exception {
		Listitem focusedItem = this.masterLbox.getFocusedItem();
		MapBean bean = focusedItem.getBean();

		String brandID = bean.get("BRAND_ID");
		MapBeanResultList detailData = TrainingDAOFactory.getBrandseasonDao().queryByBrandID(brandID);
		this.detailLbox.setModel(new ListModelList<>(detailData));
	}

	// 主檔
	private class MasterActionHandler extends TrainingGeneralElistboxActionHandler {
		public MasterActionHandler() {
			super(TrainingDAOFactory.getBrandDao(), false);
		}

		@Override
		protected String[][] getUkColNames() {
			return new String[][] { { "BRAND_NO" } };
		}

		@Override
		public void onBeforeDelete(EditEvent event) throws Exception {
			super.onBeforeDelete(event);
			super.onBeforeDelete(event);
			Elistbox lbox = this.getElistbox(); // 取得對應的Elistbox
			List<Listitem> items = lbox.getSortedSelectedItems(); // 取出有勾選的項目
			for (Listitem item : items) {
				MapBean bean = item.getBean();
				String brandID = bean.get("BRAND_ID");
				int detailCount = TrainingDAOFactory.getBrandseasonDao().queryBrandCount(brandID);
				if (detailCount > 0) {
					Msgbox.warn(Multilingual.getByUserLocale("traning.msg.BrandHasDetail", true, true));
					this.stop();
					return;
				}
			}
		}
	}

	// 明細檔
	private class DetailActionHandler extends TrainingGeneralElistboxActionHandler {
		public DetailActionHandler() {
			super(TrainingDAOFactory.getBrandseasonDao(), false);
		}

		@Override
		protected String[][] getUkColNames() {
			return new String[][] { { "#BRAND_ID", "SEASON_NO" } };
		}

		@Override
		protected void initNewBean(MapBean bean, EditEvent event) throws Exception {
			super.initNewBean(bean, event);
			MapBean masterBean = this.getMasterFocusedBean();
			String brandID = masterBean.get("BRAND_ID");
			bean.put("BRAND_ID", brandID);
		}
	}
}
