package com.tp.training.ctrler;

import java.sql.SQLException;

import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;

import com.tp.baselib.model.MapBean;
import com.tp.baselib.model.MapBeanResultList;
import com.tp.baselib.zul.Egrid;
import com.tp.baselib.zul.Elistbox;
import com.tp.baselib.zul.InputsContainer.EditEvent;
import com.tp.baselib.zul.ListModelList;
import com.tp.baselib.zul.Listbox;
import com.tp.baselib.zul.Listitem;
import com.tp.baselib.zul.Window;
import com.tp.training.dao.TrainingDAOFactory;
import com.tp.training.zul.TrainingBaseComposer;
import com.tp.training.zul.TrainingGeneralElistboxActionHandler;
import com.tp.training.zul.TrainingGeneralListboxAndEgridActionHandler;

public class BrandEgridWithDetail extends TrainingBaseComposer {
	@Wire
	private Listbox indexLbox;

	@Wire
	private Egrid masterGrid;

	@Wire
	private Elistbox detailLbox;

	@Override
	public void doAfterCompose(Window comp) throws Exception {
		super.doAfterCompose(comp);
		this.masterGrid.setActionHandler(new MasterActionHandler());
		this.detailLbox.setActionHandler(new DetailActionHandler());

		// 畫面不帶出資料 & 按鈕可以使用
		this.indexLbox.setModel(new ListModelList<>());
	}

	@Override
	protected boolean onQuery(MapBean bean) throws Exception {
		String brandNo = bean.get("BRAND_NO");
		String brandNm = bean.get("BRAND_NAME");
		MapBeanResultList data = TrainingDAOFactory.getBrandDao().queryByWin(brandNo, brandNm);
		this.indexLbox.setModel(new ListModelList<>(data));
		return true;
	}

	@Listen("onItemFocus=#indexLbox")
	public void onIndexItemFocus() throws SQLException {
		Listitem item = this.indexLbox.getFocusedItem();
		MapBean bean = item.getValue();
		this.masterGrid.setBean(bean);
		// 帶出明細檔的資料
		String brandId = bean.get("BRAND_ID");
		MapBeanResultList detailData = TrainingDAOFactory.getBrandseasonDao().queryByBrandID(brandId);
		this.detailLbox.setModel(new ListModelList<>(detailData));
	}

	private class MasterActionHandler extends TrainingGeneralListboxAndEgridActionHandler {

		public MasterActionHandler() {
			super(TrainingDAOFactory.getBrandDao(), false);
		}

		// 刪除主檔需判斷明細檔是否有資料？
		@Override
		public void onBeforeDelete(EditEvent event) throws Exception {
			super.onBeforeDelete(event);
			// 若需要管控有明細時不可刪除，則可在此判斷
			// 這裡通常會用 MsgBox.warn() + stop 的方式處理
		}

		@Override
		protected String[][] getUkColNames() {
			return new String[][] { { "BRAND_NO" } };
		}
	}

	// 明細檔Detail
	private class DetailActionHandler extends TrainingGeneralElistboxActionHandler {
		public DetailActionHandler() {
			super(TrainingDAOFactory.getBrandseasonDao(), false);
		}

		@Override
		protected void initNewBean(MapBean bean, EditEvent event) throws Exception {
			// 這裡可用來對新增的 bean 給初始值
			MapBean masterBean = this.getMasterFocusedBean();
			String brandID = masterBean.get("BRAND_ID");
			bean.put("BRAND_ID", brandID);
		}

		@Override
		protected String[][] getUkColNames() {
			return new String[][] { { "#BRAND_ID", "SEASON_NO" } };
		}
	}
}
