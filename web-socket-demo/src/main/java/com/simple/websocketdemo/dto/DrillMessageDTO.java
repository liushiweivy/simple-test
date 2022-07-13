package com.simple.websocketdemo.dto;


import lombok.Data;

/**
 * 演习推送消息
 * @author Justice
 */
@Data
public class DrillMessageDTO {

	private MessageType type;

	public enum MessageType {
		// 对话消息
		CHAT,
		// 完成步骤
		COMPLETE_STEP,
		// 完成幕
		COMPLETE_PLOT,
		// 定位信息
		GPS,
		//
	}
}
