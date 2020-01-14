package com.tp.training.generator;

import java.net.URL;

import com.tp.baselib.model.Schemas;
import com.tp.baselib.util.DbConnHelper.HikariConnProvider;
import com.tp.baselib.util.ResourceUtils;

public class SchemasGenerator {
	public static void main(String[] args) throws Exception {
		URL url = ResourceUtils.getResource("/");
		String classPath = url.getPath();
		String prjPath = classPath.substring(0, classPath.indexOf("/build/"));
		String configPath = prjPath + "/WebContent/WEB-INF/JavaTraining.properties";
		HikariConnProvider.loadConfig(configPath);

		Schemas.generateSchemaInfo(prjPath + "/src/schemas.txt" // 輸出位置,
				, false// 是否要附加在原內容後面，false 則取代
				, "JavaTraining" // 連線名稱
				, null // owner 名，如果 proxool.xml 中的帳號即是 owner，則這裡可 null
				, "%" // tablePattern，格式應該是同 like 語法，預設 "%"
				, new String[] { "^(?=ADMIN_)\\w+$" });

	}

}
