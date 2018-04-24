package edu.just.reservation.yyxx.action;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import edu.just.reservation.core.constant.Constant;
import edu.just.reservation.core.util.CreateStringUtil;
import edu.just.reservation.manage.clazz.entity.Clazz;
import edu.just.reservation.manage.course.entity.Course;
import edu.just.reservation.manage.order.entity.Orders;
import edu.just.reservation.manage.order.service.OrderService;
import edu.just.reservation.manage.room.entity.Room;
import edu.just.reservation.manage.room.service.RoomService;
import edu.just.reservation.manage.skgx.entity.TeachRelation;
import edu.just.reservation.manage.skgx.service.TeachRelatService;
import edu.just.reservation.manage.teacher.entity.Teacher;
import edu.just.reservation.manage.teacher.service.TeacherService;
import edu.just.reservation.manage.user.entity.User;
import edu.just.reservation.tjbb.service.DataStatService;

public class OrderAction extends ActionSupport {

	@Resource
	private RoomService roomService;
	@Resource
	private OrderService orderService;
	@Resource
	private TeacherService teacherService;
	@Resource
	private TeachRelatService teachRelatService;
	@Resource
	private DataStatService dataStatService;

	private Map<String, String> roomTypeMap;
	private List<Room> roomList;
	private String type;
	private Room room;
	private String selectedWeek;
	private Clazz clazz;
	private Course course;
	private String useDay;
	private String useTime;
	private TeachRelation r_tea;
	private Orders order;

	public String orderFrame() {
		if (room != null && StringUtils.isNotBlank(room.getRoomId())) {
			ActionContext.getContext().put("roomId", room.getRoomId());
		}
		if (r_tea != null && StringUtils.isNotBlank(r_tea.getId())) {
			ActionContext.getContext().put("rId", r_tea.getId());
		}
		return "orderFrame";
	}

	// ȥԤԼҳ��
	public String toOrderUI() {
		try {
			// ��ʼ��
			List<Orders> orderList = null;
			String o_firstDay = "";
			String o_lastDay = "";
			// ��ȡʵ���ҵ����
			roomTypeMap = Room.ROOM_TYPE_MAP;
			roomList = new LinkedList<Room>();
			// ��ȡ��ǰ��
			// ��ȡϵͳ�����õı�ѧ�ڿ�ʼʱ��

			Date date = getTermStartTime();
			String week = CreateStringUtil.getWeekStr(date);
			int weekNum = Integer.parseInt(week);
			ActionContext.getContext().put("weekNum", weekNum);

			// ��ȡ���ܵ�һ��
			String firstDay = CreateStringUtil.getFirstInThisWeek();
			ActionContext.getContext().put("firstDay", firstDay);
			// ��ȡ�������һ��
			String lastDay = CreateStringUtil.getLastInThisWeek();
			ActionContext.getContext().put("lastDay", lastDay);
			// �Ӹ����û�ѡ��ʵ������Ϣ��ת��������ʵ������Ϣ���õ��������
			if (room != null && StringUtils.isNotBlank(room.getRoomId())) {
				room = roomService.findById(room.getRoomId());
				ActionContext.getContext().put("room", room);
				ActionContext.getContext().put("map", roomTypeMap);
				roomList = roomService.findAllRoomsByType(room.getRType());
			}

			// �û�ָ����ԤԼ��
			if (StringUtils.isNotBlank(selectedWeek)) {
				// 1������ѡ���ܻ�ȡѡ���ܵĵ�һ�������
				int o_week = Integer.parseInt(selectedWeek);
				o_firstDay = CreateStringUtil.getFirstInOneWeek(o_week, date);
				ActionContext.getContext().put("o_firstDay", o_firstDay);
				// 2������ѡ���ܻ�ȡѡ���ܵ����һ�������
				o_lastDay = CreateStringUtil.getLastInOneWeek(o_week, date);
				ActionContext.getContext().put("o_lastDay", o_lastDay);
			} else {
				selectedWeek = week;
				o_firstDay = firstDay;
				o_lastDay = lastDay;
			}
			ActionContext.getContext().put("selectedWeek", selectedWeek);
			// ����ָ����ԤԼ�ܻ�ȡ��ǰ�ܵ�����ԤԼ��Ϣ
			// �����ݿ��л�ȡѡ���ܵ��������ڵ�ǰʵ���ҵ�ԤԼ��Ϣ
			orderList = orderService.findOrderByTimeAndRoom(o_firstDay,
					o_lastDay, room);
			// ��ȡ��ǰʱ�������ڼ�
			int weekday = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
			weekday = weekday == 1 ? 7 : (weekday - 1);
			ActionContext.getContext().put("weekday", weekday);
			// ԤԼ��Ϣ���ϴ���
			if (room != null && orderList != null && orderList.size() > 0) {// ���ݿ����з���������ԤԼ��Ϣ��¼
				Map<Integer, List<Orders>> ordersMap = new HashMap<Integer, List<Orders>>();// һ�ܵ�map��key��ĳ��,value��һ���е�Orders����
				for (int i = 0; i < orderList.size(); i++) {
					Orders o = orderList.get(i);
					Timestamp useDay = o.getUseDay();
					Calendar cfirst = Calendar.getInstance();// ѡ���ܵ�һ��
					String of = o_firstDay + " 00:00:00";
					cfirst.setTime(Timestamp.valueOf(of));
					Calendar cuse = Calendar.getInstance();
					cuse.setTime(useDay);
					long val = cuse.getTimeInMillis()
							- cfirst.getTimeInMillis();
					long day = val / (1000 * 60 * 60 * 24);
					List<Orders> oList = ordersMap.get((int) day);
					if (oList == null) {
						oList = new LinkedList<Orders>();
					}
					oList.add(o);
					ordersMap.put((int) day, oList);
				}
				ActionContext.getContext().put("ordersMap", ordersMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "orderUI";
	}

	// ��ȡϵͳ���õ�ѧ�ڿ�ʼʱ��
	private Date getTermStartTime() throws ParseException {
		String timeStr = (String) ActionContext.getContext().getApplication()
				.get(Constant.SYS_TIME);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		if (timeStr != null) {
			date = sdf.parse(timeStr);
		}
		return date;
	}

	// ��ȡ�ڿι�ϵ
	public void getTeachRelation() {
		try {
			// ��ȡ��ǰ��½�Ľ�ʦ��Ϣ
			Teacher teacher = (Teacher) ActionContext.getContext().getSession()
					.get(Constant.TEACHER);
			// �����ʦ�ǿ�
			if (teacher != null) {
				boolean isOk = true;
				// ���жϵ�ǰʱ��ý�ʦ�Ƿ��Ѿ���������ԤԼ��Ϣ����������˺����ͨ���ģ�
				if (StringUtils.isNotBlank(selectedWeek)
						&& StringUtils.isNotBlank(useDay)
						&& StringUtils.isNotBlank(useTime)) {
					Calendar cal = Calendar.getInstance();
					cal.setTime(getTermStartTime());
					int w = Integer.parseInt(selectedWeek);
					int d = Integer.parseInt(useDay);
					cal.add(Calendar.DATE, (w - 1) * 7 + d - 1);
					isOk = orderService.judgeIsOk(teacher.getId(),
							new Timestamp(cal.getTimeInMillis()),
							Integer.parseInt(useTime),Orders.ORDER_STATE_CHECKED);
				}
				if (isOk) {
					// ���ݽ�ʦ��Ϣ��ȡ�������е��ڿι�ϵ
					List<TeachRelation> list = teachRelatService
							.findAllByTeacher(teacher);
					// ���ڿι�ϵ�����еİ༶���Ϸ���
					Map<String, String> clazzMap = new HashMap<String, String>();
					for (int i = 0; i < list.size(); i++) {
						clazzMap.put(list.get(i).getClazz().getClazzId(), list
								.get(i).getClazz().getCnumber());
					}
					HttpServletResponse response = ServletActionContext
							.getResponse();
					response.setContentType("text/html;charset=UTF-8");
					response.getWriter().print(JSONArray.fromObject(clazzMap));
				}else{
					HttpServletResponse response = ServletActionContext
							.getResponse();
					response.setContentType("text/html");
					response.getWriter().print(false);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ����ʵ�������ͻ�ȡʵ����
	public void getRooms() {
		try {
			if (room != null && StringUtils.isNotBlank(room.getRType())
					&& !"0".equals(room.getRType())) {
				// ��ȡ��������Ϊѡ�����͵�ʵ������Ϣ
				roomList = roomService.findAllRoomsByType(room.getRType());
				HttpServletResponse response = ServletActionContext
						.getResponse();
				response.setContentType("text/html;charset=UTF-8");
				response.getWriter().print(JSONArray.fromObject(roomList));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// �����û�ѡ���ʵ���ң���ѯ���е�ǰʵ���ҵ�ԤԼ��Ϣ
	public String searchRoomInfo() throws IOException {
		if (room != null && StringUtils.isNotBlank(room.getRoomId())) {
			List<Orders> orderList = orderService
					.findByRoomId(room.getRoomId());
			ActionContext.getContext().put("orderList", orderList);
		}
		return "toOrderUI";
	}

	// ���ݽ�ʦ���༶���ڿι�ϵ�в��ҿγ�
	public void getCourse() {
		try {
			// ��ȡ��ǰ��½���û���Ϣ
			User user = (User) ActionContext.getContext().getSession()
					.get(Constant.USER);
			// ���ݵ�ǰ���û���Ϣ��ȡ�����Ӧ�Ľ�ʦ��Ϣ
			Teacher teacher = teacherService.findTeacherByUser(user);
			// �����ʦ�ǿ�
			if (teacher != null) {
				// ���ݽ�ʦ��Ϣ��ȡ�������е��ڿι�ϵ
				List<TeachRelation> list = teachRelatService
						.findAllByTeacher(teacher);
				Map<String, String> courseMap = new HashMap<String, String>();
				if (clazz != null && StringUtils.isNotBlank(clazz.getClazzId())
						&& !"0".equals(clazz.getClazzId())) {
					for (int i = 0; i < list.size(); i++) {
						if (list.get(i).getClazz().getClazzId()
								.equals(clazz.getClazzId())) {
							courseMap.put(
									list.get(i).getCourse().getCourseId(), list
											.get(i).getCourse().getCNumber());
						}
					}
					HttpServletResponse response = ServletActionContext
							.getResponse();
					response.setContentType("text/html;charset=UTF-8");
					response.getWriter().print(JSONArray.fromObject(courseMap));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// ����ԤԼ
	public String signOrder() {
		try {
			// ��ȡ��ǰ��½���û���Ϣ
			User user = (User) ActionContext.getContext().getSession()
					.get(Constant.USER);
			// ���ݵ�ǰ���û���Ϣ��ȡ�����Ӧ�Ľ�ʦ��Ϣ
			Teacher teacher = teacherService.findTeacherByUser(user);
			// �����ʦ�ǿ�
			if (teacher != null) {
				if (clazz != null && StringUtils.isNotBlank(clazz.getClazzId())
						&& course != null
						&& StringUtils.isNotBlank(course.getCourseId())
						&& room != null
						&& StringUtils.isNotBlank(room.getRoomId())
						&& StringUtils.isNotBlank(selectedWeek)
						&& StringUtils.isNotBlank(useDay)
						&& StringUtils.isNotBlank(useTime)) {
					Orders o = new Orders();
					o.setOrderTime(new Timestamp(Calendar.getInstance()
							.getTimeInMillis()));
					o.setOState(Orders.ORDER_STATE_APPLYED);
					o.setUseTime(Integer.parseInt(useTime));
					o.setRoom(roomService.findById(room.getRoomId()));
					TeachRelation relation = teachRelatService
							.findByTeaAndClzAndCour(teacher.getId(),
									clazz.getClazzId(), course.getCourseId());
					o.setR_tea(relation);
					Calendar cal = Calendar.getInstance();
					cal.setTime(getTermStartTime());
					int w = Integer.parseInt(selectedWeek);
					int d = Integer.parseInt(useDay);
					cal.add(Calendar.DATE, (w - 1) * 7 + d - 1);
					o.setUseDay(new Timestamp(cal.getTimeInMillis()));
					orderService.add(o);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "toOrderUI";
	}

	public String toOrderHistoryUI() {
		Teacher t = (Teacher) ActionContext.getContext().getSession()
				.get(Constant.TEACHER);
		if (t != null) {
			List<Orders> orderList = orderService.findOrdersByTeacherId(t
					.getId());
			ActionContext.getContext().put("orderList", orderList);
		}
		return "orderHistoryUI";
	}

	public String cancel() {
		if (order != null && StringUtils.isNotBlank(order.getOrderId())) {
			Orders o = orderService.findById(order.getOrderId());
			o.setOState(Orders.ORDER_STATE_INVALID);
			dataStatService.deleteByOrder(o);
			orderService.update(o);
		}
		if(selectedWeek!=null&&StringUtils.isNotBlank(selectedWeek)){
			return "toOrderUI";
		}
		return toOrderHistoryUI();
	}

	public String delete(){
		if(order!=null&&StringUtils.isNotBlank(order.getOrderId())){
			orderService.delete(order.getOrderId());
		}
		return toOrderHistoryUI();
	}
	
	public Map<String, String> getRoomTypeMap() {
		return roomTypeMap;
	}

	public void setRoomTypeMap(Map<String, String> roomTypeMap) {
		this.roomTypeMap = roomTypeMap;
	}

	public List<Room> getRoomList() {
		return roomList;
	}

	public void setRoomList(List<Room> roomList) {
		this.roomList = roomList;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSelectedWeek() {
		return selectedWeek;
	}

	public void setSelectedWeek(String selectedWeek) {
		this.selectedWeek = selectedWeek;
	}

	public Clazz getClazz() {
		return clazz;
	}

	public void setClazz(Clazz clazz) {
		this.clazz = clazz;
	}

	public String getUseDay() {
		return useDay;
	}

	public void setUseDay(String useDay) {
		this.useDay = useDay;
	}

	public String getUseTime() {
		return useTime;
	}

	public void setUseTime(String useTime) {
		this.useTime = useTime;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public TeachRelation getR_tea() {
		return r_tea;
	}

	public void setR_tea(TeachRelation r_tea) {
		this.r_tea = r_tea;
	}

	public Orders getOrder() {
		return order;
	}

	public void setOrder(Orders order) {
		this.order = order;
	}

}
