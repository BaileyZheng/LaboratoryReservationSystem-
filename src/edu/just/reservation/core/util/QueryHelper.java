package edu.just.reservation.core.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 条件查询工具类
 * 
 * @author 郑蓓蕾
 * 
 */
public class QueryHelper {

	private List<Object> parameters;
	private String fromClause = "";
	private String whereClause = "";
	private String orderByClause = "";

	public static String ORDER_BY_DESC = "DESC";// 降序
	public static String ORDER_BY_ASC = "ASC";// 升序

	/**
	 * 构造函数，生成from子句
	 * 
	 * @param clazz
	 *            查询类名
	 * @param alias
	 *            别名
	 */
	public QueryHelper(Class clazz, String alias) {
		fromClause = "FROM " + clazz.getSimpleName() + " " + alias;
	}

	/**
	 * 生成where子句
	 * 
	 * @param condition
	 *            条件
	 * @param params
	 *            ？对应的参数
	 */
	public void addCondition(String condition, Object... params) {
		if (whereClause.length() > 1) {
			whereClause += " AND " + condition;
		} else {
			whereClause = " WHERE " + condition;
		}
		if (parameters == null || parameters.size() == 0) {
			parameters = new ArrayList<Object>();
		}
		for (Object param : params) {
			parameters.add(param);
		}
	}

	/**
	 * 生成order by子句
	 * 
	 * @param property
	 *            排序属性
	 * @param type
	 *            升序或降序
	 */
	public void addOrderBy(String property, String type) {
		if (orderByClause.length() > 1) {
			orderByClause += "," + property + " " + type;
		} else {
			orderByClause = " ORDER BY " + property + " " + type;
		}
	}

	// 生成hql语句
	public String getHQL() {
		return fromClause + whereClause + orderByClause;
	}

	//生成查询总记录数的hql语句
	public String getCountHQL() {
		return "SELECT COUNT(*) " + fromClause + whereClause;
	}

	// 获取hql语句中？对应的参数集合
	public List<Object> getParameters() {
		return parameters;
	}
}
