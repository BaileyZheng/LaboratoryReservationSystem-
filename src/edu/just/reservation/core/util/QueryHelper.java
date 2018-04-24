package edu.just.reservation.core.util;

import java.util.ArrayList;
import java.util.List;

/**
 * ������ѯ������
 * 
 * @author ֣����
 * 
 */
public class QueryHelper {

	private List<Object> parameters;
	private String fromClause = "";
	private String whereClause = "";
	private String orderByClause = "";

	public static String ORDER_BY_DESC = "DESC";// ����
	public static String ORDER_BY_ASC = "ASC";// ����

	/**
	 * ���캯��������from�Ӿ�
	 * 
	 * @param clazz
	 *            ��ѯ����
	 * @param alias
	 *            ����
	 */
	public QueryHelper(Class clazz, String alias) {
		fromClause = "FROM " + clazz.getSimpleName() + " " + alias;
	}

	/**
	 * ����where�Ӿ�
	 * 
	 * @param condition
	 *            ����
	 * @param params
	 *            ����Ӧ�Ĳ���
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
	 * ����order by�Ӿ�
	 * 
	 * @param property
	 *            ��������
	 * @param type
	 *            �������
	 */
	public void addOrderBy(String property, String type) {
		if (orderByClause.length() > 1) {
			orderByClause += "," + property + " " + type;
		} else {
			orderByClause = " ORDER BY " + property + " " + type;
		}
	}

	// ����hql���
	public String getHQL() {
		return fromClause + whereClause + orderByClause;
	}

	//���ɲ�ѯ�ܼ�¼����hql���
	public String getCountHQL() {
		return "SELECT COUNT(*) " + fromClause + whereClause;
	}

	// ��ȡhql����У���Ӧ�Ĳ�������
	public List<Object> getParameters() {
		return parameters;
	}
}
