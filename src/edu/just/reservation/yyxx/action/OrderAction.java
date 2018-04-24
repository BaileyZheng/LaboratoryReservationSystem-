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

	// 去预约页面
	public String toOrderUI() {
		try {
			// 初始化
			List<Orders> orderList = null;
			String o_firstDay = "";
			String o_lastDay = "";
			// 获取实验室的类别
			roomTypeMap = Room.ROOM_TYPE_MAP;
			roomList = new LinkedList<Room>();
			// 获取当前周
			// 获取系统中设置的本学期开始时间

			Date date = getTermStartTime();
			String week = CreateStringUtil.getWeekStr(date);
			int weekNum = Integer.parseInt(week);
			ActionContext.getContext().put("weekNum", weekNum);

			// 获取本周第一天
			String firstDay = CreateStringUtil.getFirstInThisWeek();
			ActionContext.getContext().put("firstDay", firstDay);
			// 获取本周最后一天
			String lastDay = CreateStringUtil.getLastInThisWeek();
			ActionContext.getContext().put("lastDay", lastDay);
			// 从根据用户选择实验室信息跳转而来，将实验室信息设置到域对象中
			if (room != null && StringUtils.isNotBlank(room.getRoomId())) {
				room = roomService.findById(room.getRoomId());
				ActionContext.getContext().put("room", room);
				ActionContext.getContext().put("map", roomTypeMap);
				roomList = roomService.findAllRoomsByType(room.getRType());
			}

			// 用户指定了预约周
			if (StringUtils.isNotBlank(selectedWeek)) {
				// 1、根据选定周获取选定周的第一天的日期
				int o_week = Integer.parseInt(selectedWeek);
				o_firstDay = CreateStringUtil.getFirstInOneWeek(o_week, date);
				ActionContext.getContext().put("o_firstDay", o_firstDay);
				// 2、根据选定周获取选定周的最后一天的日期
				o_lastDay = CreateStringUtil.getLastInOneWeek(o_week, date);
				ActionContext.getContext().put("o_lastDay", o_lastDay);
			} else {
				selectedWeek = week;
				o_firstDay = firstDay;
				o_lastDay = lastDay;
			}
			ActionContext.getContext().put("selectedWeek", selectedWeek);
			// 根据指定的预约周获取当前周的所有预约信息
			// 从数据库中获取选定周的所有属于当前实验室的预约信息
			orderList = orderService.findOrderByTimeAndRoom(o_firstDay,
					o_lastDay, room);
			// 获取当前时间是星期几
			int weekday = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
			weekday = weekday == 1 ? 7 : (weekday - 1);
			ActionContext.getContext().put("weekday", weekday);
			// 预约信息集合处理
			if (room != null && orderList != null && orderList.size() > 0) {// 数据库中有符合条件的预约信息记录
				Map<Integer, List<Orders>> ordersMap = new HashMap<Integer, List<Orders>>();// 一周的map，key是某天,value是一天中的Orders集合
				for (int i = 0; i < orderList.size(); i++) {
					Orders o = orderList.get(i);
					Timestamp useDay = o.getUseDay();
					Calendar cfirst = Calendar.getInstance();// 选定周第一天
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

	// 获取系统设置的学期开始时间
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

	// 获取授课关系
	public void getTeachRelation() {
		try {
			// 获取当前登陆的教师信息
			Teacher teacher = (Teacher) ActionContext.getContext().getSession()
					.get(Constant.TEACHER);
			// 如果教师非空
			if (teacher != null) {
				boolean isOk = true;
				// 先判断当前时间该教师是否已经有了其他预约信息（包括待审核和审核通过的）
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
					// 根据教师信息获取到其所有的授课关系
					List<TeachRelation> list = teachRelatService
							.findAllByTeacher(teacher);
					// 将授课关系集合中的班级集合返回
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

	// 根据实验室类型获取实验室
	public void getRooms() {
		try {
			if (room != null && StringUtils.isNotBlank(room.getRType())
					&& !"0".equals(room.getRType())) {
				// 获取所有类型为选定类型的实验室信息
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

	// 根据用户选择的实验室，查询所有当前实验室的预约信息
	public String searchRoomInfo() throws IOException {
		if (room != null && StringUtils.isNotBlank(room.getRoomId())) {
			List<Orders> orderList = orderService
					.findByRoomId(room.getRoomId());
			ActionContext.getContext().put("orderList", orderList);
		}
		return "toOrderUI";
	}

	// 根据教师、班级到授课关系中查找课程
	public void getCourse() {
		try {
			// 获取当前登陆的用户信息
			User user = (User) ActionContext.getContext().getSession()
					.get(Constant.USER);
			// 根据当前的用户信息获取到其对应的教师信息
			Teacher teacher = teacherService.findTeacherByUser(user);
			// 如果教师非空
			if (teacher != null) {
				// 根据教师信息获取到其所有的授课关系
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

	// 保存预约
	public String signOrder() {
		try {
			// 获取当前登陆的用户信息
			User user = (User) ActionContext.getContext().getSession()
					.get(Constant.USER);
			// 根据当前的用户信息获取到其对应的教师信息
			Teacher teacher = teacherService.findTeacherByUser(user);
			// 如果教师非空
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
