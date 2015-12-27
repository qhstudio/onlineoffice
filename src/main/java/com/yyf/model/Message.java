package com.yyf.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
/**
 * 消息实体
 * @author yyf
 *
 */
@Entity
@Table(name = "message_info")
public class Message {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long msgId;
	private String msgContent;
	private Date msgDate;
	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "ToUserId")
	private User msgTo;
	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "ToUserFrom")
	private User msgFrom;

	public Long getMsgId() {
		return msgId;
	}

	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}

	public String getMsgContent() {
		return msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	public Date getMsgDate() {
		return msgDate;
	}

	public void setMsgDate(Date msgDate) {
		this.msgDate = msgDate;
	}

	public User getMsgTo() {
		return msgTo;
	}

	public void setMsgTo(User msgTo) {
		this.msgTo = msgTo;
	}

	public User getMsgFrom() {
		return msgFrom;
	}

	public void setMsgFrom(User msgFrom) {
		this.msgFrom = msgFrom;
	}


}
