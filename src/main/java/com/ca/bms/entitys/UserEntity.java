package com.ca.bms.entitys;

import java.io.Serializable;
import java.util.List;

/**
 * 用户类
 * 
 * @author：刘志龙
 * @since：2014年11月28日 下午1:51:10
 * @version:1.0
 */
public class UserEntity implements Serializable {
	private static final long serialVersionUID = -751832958410532799L;
	private Long id;
	private String createTime;
	private String modifyTime;
	private int version;
	private String username;
	private String password;
	private String nickname;
	private String phoneNum;
	private int authNum;
	private List<SensorEntity> sensorList;

	public List<SensorEntity> getSensorList() {
		return sensorList;
	}

	public void setSensorList(List<SensorEntity> sensorList) {
		this.sensorList = sensorList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public int getAuthNum() {
		return authNum;
	}

	public void setAuthNum(int authNum) {
		this.authNum = authNum;
	}

	@Override
	public String toString() {
		return "UserEntity [id=" + id + ", username=" + username
				+ ", nickname=" + nickname + ", phoneNum=" + phoneNum
				+ ", authNum=" + "]";
	}
}
