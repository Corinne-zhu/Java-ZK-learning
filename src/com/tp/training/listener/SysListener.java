package com.tp.training.listener;

import org.zkoss.zk.ui.WebApp;
import org.zkoss.zk.ui.util.WebAppCleanup;
import org.zkoss.zk.ui.util.WebAppInit;

import com.tp.baselib.util.DbConnHelper;
import com.tp.baselib.util.DbConnHelper.HikariConnProvider;
import com.tp.webplugins.admin.util.AdminZkListener;

public class SysListener implements WebAppInit, WebAppCleanup {

	@Override
	public void cleanup(WebApp wapp) throws Exception {
		AdminZkListener.cleanup(wapp);
		DbConnHelper.closeThreadLocal();
	}

	@Override
	public void init(WebApp wapp) throws Exception {
		// 載入DB 連線 (若有多個， 依次載入)
		String configPath = wapp.getRealPath("WEB-INF/JavaTraining.properties");
		HikariConnProvider.loadConfig("JavaTraining", configPath);

//		載入 schemas 設定
//		URL schemasUrl = ResourceUtils.getResource("/schemas_training.txt");
//		Schemas.loadSchemas(schemasUrl);

		try {
			AdminZkListener.init(wapp);
		} catch (Exception e) {
			throw e;
		} finally {
			DbConnHelper.closeThreadLocal();
		}

	}

}
