package edu.just.reservation.manage.room.action;

import java.io.File;
import java.net.URLDecoder;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import edu.just.reservation.core.action.BaseAction;
import edu.just.reservation.core.util.QueryHelper;
import edu.just.reservation.manage.room.entity.Room;
import edu.just.reservation.manage.room.service.RoomService;

public class RoomAction extends BaseAction {
	@Resource
	private RoomService roomService;
	private Room room;
	private String strRNumber;

	private File headImg;
	private String headImgContentType;
	private String headImgFileName;

	private File roomFile;
	private String roomFileContentType;
	private String roomFileFileName;

	// 列表页面
	public String listUI() throws Exception {
		ActionContext.getContext().getContextMap()
				.put("roomTypeMap", Room.ROOM_TYPE_MAP);
		QueryHelper queryHelper = new QueryHelper(Room.class, "r");
		try {
			if (room != null) {
				if (room.getRNumber() != null && !"".equals(room.getRNumber())) {
					room.setRNumber(URLDecoder.decode(room.getRNumber(),
							"utf-8"));
					queryHelper.addCondition("r.RNumber like ?",
							"%" + room.getRNumber() + "%");
				}
			}
			pageResult = roomService.getPageResult(queryHelper, getPageNo(),
					getPageSize());
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new Exception(e.getMessage());
		}
		return "listUI";
	}

	// 跳转到新增页面
	public String addUI() {
		ActionContext.getContext().getContextMap()
				.put("roomTypeMap", Room.ROOM_TYPE_MAP);
		if (room != null && StringUtils.isNotBlank(room.getRNumber())) {
			strRNumber = room.getRNumber();
		}
		room = null;
		return "addUI";
	}

	// 保存新增
	public String add() {
		try {
			if (room != null) {
				if (headImg != null) {
					String filePath = ServletActionContext.getServletContext()
							.getRealPath("upload/room");
					String fileName = UUID.randomUUID().toString()
							.replace("-", "")
							+ headImgFileName.substring(headImgFileName
									.lastIndexOf("."));
					FileUtils.copyFile(headImg, new File(filePath, fileName));
					room.setRImgPath("room/" + fileName);
				}
				if (roomFile != null) {
					String filePath = ServletActionContext.getServletContext()
							.getRealPath("upload/room");
					String fileName = UUID.randomUUID().toString()
							.replace("-", "")
							+ roomFileFileName.substring(roomFileFileName
									.lastIndexOf("."));
					FileUtils.copyFile(roomFile, new File(filePath, fileName));
					room.setRFilePath("room/" + fileName);
				}
				roomService.add(room);
			}
			room.setRNumber(strRNumber);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}

	// 跳转到编辑页面
	public String editUI() {
		ActionContext.getContext().getContextMap()
				.put("roomTypeMap", Room.ROOM_TYPE_MAP);
		if (room != null && room.getRoomId() != null) {
			strRNumber = room.getRNumber();
			room = roomService.findById(room.getRoomId());
		}
		return "editUI";
	}

	// 保存编辑
	public String saveEdit() {
		try {
			if (room != null) {
				if (headImg != null) {
					String filePath = ServletActionContext.getServletContext()
							.getRealPath("upload/room");
					String fileName = UUID.randomUUID().toString()
							.replace("-", "")
							+ headImgFileName.substring(headImgFileName
									.lastIndexOf("."));
					FileUtils.copyFile(headImg, new File(filePath, fileName));
					room.setRImgPath("room/" + fileName);
				}
				if (roomFile != null) {
					String filePath = ServletActionContext.getServletContext()
							.getRealPath("upload/room");
					String fileName = UUID.randomUUID().toString()
							.replace("-", "")
							+ roomFileFileName.substring(roomFileFileName
									.lastIndexOf("."));
					FileUtils.copyFile(roomFile, new File(filePath, fileName));
					room.setRFilePath("room/" + fileName);
				}
				roomService.update(room);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "list";
	}

	// 删除
	public String delete() {
		if (room != null && room.getRoomId() != null) {
			strRNumber = room.getRNumber();
			roomService.delete(room.getRoomId());
		}
		return "list";
	}

	// 批量删除
	public String deleteSelected() {
		if (selectedRow != null) {
			strRNumber = room.getRNumber();
			for (String id : selectedRow) {
				roomService.delete(id);
			}
		}
		return "list";
	}

	// 更新状态
	public void publicRoom() {
		try {
			if (room != null) {
				Room temp = roomService.findById(room.getRoomId());
				temp.setRState(room.getRState());
				roomService.update(temp);

				HttpServletResponse response = ServletActionContext
						.getResponse();
				response.setContentType("text/html");
				ServletOutputStream outputStream = response.getOutputStream();
				outputStream.write("更新状态成功".getBytes("UTF-8"));
				outputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public String getStrRNumber() {
		return strRNumber;
	}

	public void setStrRNumber(String strRNumber) {
		this.strRNumber = strRNumber;
	}

	public File getHeadImg() {
		return headImg;
	}

	public void setHeadImg(File headImg) {
		this.headImg = headImg;
	}

	public String getHeadImgContentType() {
		return headImgContentType;
	}

	public void setHeadImgContentType(String headImgContentType) {
		this.headImgContentType = headImgContentType;
	}

	public String getHeadImgFileName() {
		return headImgFileName;
	}

	public void setHeadImgFileName(String headImgFileName) {
		this.headImgFileName = headImgFileName;
	}

	public File getRoomFile() {
		return roomFile;
	}

	public void setRoomFile(File roomFile) {
		this.roomFile = roomFile;
	}

	public String getRoomFileContentType() {
		return roomFileContentType;
	}

	public void setRoomFileContentType(String roomFileContentType) {
		this.roomFileContentType = roomFileContentType;
	}

	public String getRoomFileFileName() {
		return roomFileFileName;
	}

	public void setRoomFileFileName(String roomFileFileName) {
		this.roomFileFileName = roomFileFileName;
	}

}
