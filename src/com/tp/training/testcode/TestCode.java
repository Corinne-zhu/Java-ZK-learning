package com.tp.training.testcode;

import java.net.URL;

import com.tp.baselib.model.MapBeanResultList;
import com.tp.baselib.model.Schemas;
import com.tp.baselib.util.DbConnHelper.HikariConnProvider;
import com.tp.baselib.util.ResourceUtils;
import com.tp.training.dao.TrainingDAOFactory;

public class TestCode {
	public static void main(String[] args) throws Exception {
		URL url = ResourceUtils.getResource("/");
		String classPath = url.getPath();
		String prjPath = classPath.substring(0, classPath.indexOf("/build/"));
		String configPath = prjPath + "/WebContent/WEB-INF/JavaTraining.properties";
		HikariConnProvider.loadConfig(configPath);

		// 載入schemas 設定
		java.net.URL schemasUrl = ResourceUtils.getResource("/schemas.txt");
		Schemas.loadSchemas(schemasUrl);

		// 測試DAO
		MapBeanResultList data = TrainingDAOFactory.getBrandDao().queryByBrand("S", "");
		for (int i = 0; i < data.size(); i++) {
			System.out.println("Brand NO: " + data.get(i).get("BRAND_NO"));
		}
	}

}
