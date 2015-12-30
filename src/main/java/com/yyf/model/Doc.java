package com.yyf.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.struts2.json.annotations.JSON;

/**
 * 文档实体
 * 
 * @author yyf
 *
 */
@Entity
@Table(name = "doc_info")
public class Doc {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long docId;
	private String docName;
	private Float docSize;
	private Date docDate;
	private String docDesc;
	private String docPath;
	private String docFoot;
	private Integer docAuthority;
	private String docContentType;
	

	public String getDocContentType() {
		return docContentType;
	}

	public void setDocContentType(String docContentType) {
		this.docContentType = docContentType;
	}

	

	public Integer getDocAuthority() {
		return docAuthority;
	}

	public void setDocAuthority(Integer docAuthority) {
		this.docAuthority = docAuthority;
	}



	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "docTypeId")
	private DocType docType;

	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "ownUserId")
	private User docOwnUser;

	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "commentDoc")
	private Set<Comment> docComments = new HashSet<Comment>();

	@ManyToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
	@JoinTable(name = "doc_user_fork", joinColumns = { @JoinColumn(name = "docId") }, inverseJoinColumns = {
			@JoinColumn(name = "userID") })
	private Set<User> docForkers = new HashSet<User>();

	public Long getDocId() {
		return docId;
	}

	public void setDocId(Long docId) {
		this.docId = docId;
	}
	

	public String getDocFoot() {
		return docFoot;
	}

	public void setDocFoot(String docFoot) {
		this.docFoot = docFoot;
	}

	public String getDocName() {
		return docName;
	}

	public void setDocName(String docName) {
		this.docName = docName;
	}

	public Float getDocSize() {
		return docSize;
	}

	public void setDocSize(Float docSize) {
		this.docSize = docSize;
	}

	@JSON(format="yyyy年MM月dd日 HH:mm:ss")
	public Date getDocDate() {
		return docDate;
	}

	public void setDocDate(Date docDate) {
		this.docDate = docDate;
	}

	public String getDocDesc() {
		return docDesc;
	}

	public void setDocDesc(String docDesc) {
		this.docDesc = docDesc;
	}

	public String getDocPath() {
		return docPath;
	}

	public void setDocPath(String docPath) {
		this.docPath = docPath;
	}

	@JSON(serialize=false)
	public DocType getDocType() {
		return docType;
	}

	public void setDocType(DocType docType) {
		this.docType = docType;
	}

	public User getDocOwnUser() {
		return docOwnUser;
	}

	public void setDocOwnUser(User docOwnUser) {
		this.docOwnUser = docOwnUser;
	}

	@JSON(serialize=false)
	public Set<Comment> getDocComments() {
		return docComments;
	}

	public void setDocComments(Set<Comment> docComments) {
		this.docComments = docComments;
	}

	@JSON(serialize=false)
	public Set<User> getDocForkers() {
		return docForkers;
	}

	public void setDocForkers(Set<User> docForkers) {
		this.docForkers = docForkers;
	}

	@Override
	public String toString() {
		return "Doc [docId=" + docId + ", docName=" + docName + ", docSize=" + docSize + ", docDate=" + docDate
				+ ", docDesc=" + docDesc + ", docPath=" + docPath + ", docFoot=" + docFoot + ", docAuthority="
				+ docAuthority + ", docContentType=" + docContentType + ", docType=" + docType + ", docOwnUser="
				+ docOwnUser + "]";
	}
	
	
}
