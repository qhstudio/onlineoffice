package com.yyf.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "run_pic")
public class RunPic {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long runPicId;
	private String runPicTitle;
	private String runPicContent;
	private Date runPicDate;
	private String runPicPath;
	private Boolean isShow;

	public String getRunPicPath() {
		return runPicPath;
	}

	public void setRunPicPath(String runPicPath) {
		this.runPicPath = runPicPath;
	}

	public Long getRunPicId() {
		return runPicId;
	}

	public void setRunPicId(Long runPicId) {
		this.runPicId = runPicId;
	}

	public String getRunPicTitle() {
		return runPicTitle;
	}

	public void setRunPicTitle(String runPicTitle) {
		this.runPicTitle = runPicTitle;
	}

	public String getRunPicContent() {
		return runPicContent;
	}

	public void setRunPicContent(String runPicContent) {
		this.runPicContent = runPicContent;
	}

	public Date getRunPicDate() {
		return runPicDate;
	}

	public void setRunPicDate(Date runPicDate) {
		this.runPicDate = runPicDate;
	}

	public Boolean getIsShow() {
		return isShow;
	}

	public void setIsShow(Boolean isShow) {
		this.isShow = isShow;
	}

}
