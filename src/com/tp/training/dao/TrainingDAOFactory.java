package com.tp.training.dao;


/* 統一建立 DAO 的實體 */
public class TrainingDAOFactory {
	private static final BrandDAO brandDAO = new BrandDAO();
	private static final BrandSeasonDAO brandSeasonDAO = new BrandSeasonDAO();
	private static final StyleDAO styleDAO = new StyleDAO();


	public static BrandDAO getBrandDao() {
		return brandDAO;
	}
	public static BrandSeasonDAO getBrandseasonDao() {
		return brandSeasonDAO;
	}
	public static StyleDAO getStyleDao() {
		return styleDAO;
	}




}
