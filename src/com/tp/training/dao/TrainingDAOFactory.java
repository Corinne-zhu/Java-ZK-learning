package com.tp.training.dao;


/* 統一建立 DAO 的實體 */
public class TrainingDAOFactory {
	private static final BrandDAO brandDAO = new BrandDAO();
	private static final BrandSeasonDAO brandSeasonDAO = new BrandSeasonDAO();
	private static final StyleDAO styleDAO = new StyleDAO();


	public static BrandDAO getBranddao() {
		return brandDAO;
	}
	public static BrandSeasonDAO getBrandseasondao() {
		return brandSeasonDAO;
	}
	public static StyleDAO getStyledao() {
		return styleDAO;
	}




}
