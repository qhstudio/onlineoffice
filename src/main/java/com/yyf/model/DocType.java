package com.yyf.model;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.apache.struts2.json.annotations.JSON;

/**
 * 文档类型
 * 
 * @author yyf
 *
 */

@Entity
@Table(name = "doc_type_info")
public class DocType {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long typeId;
	private String typeName;
	private String typeDesc;

	@ManyToOne(cascade = { CascadeType.REFRESH })
	@JoinColumn(name = "parentTypeId")
	private DocType parentType;

	@OrderBy("typeId desc")
	@OneToMany(cascade = { CascadeType.REFRESH, CascadeType.REMOVE }, fetch = FetchType.LAZY, mappedBy = "parentType")
	private Set<DocType> childrenDocType = new LinkedHashSet<DocType>();

	@OneToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, mappedBy = "docType")
	private Set<Doc> docs = new HashSet<Doc>();

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getTypeDesc() {
		return typeDesc;
	}

	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}

	public DocType getParentType() {
		return parentType;
	}

	public void setParentType(DocType parentType) {
		this.parentType = parentType;
	}

	@JSON(serialize = false)
	public Set<Doc> getDocs() {
		return docs;
	}

	public void setDocs(Set<Doc> docs) {
		this.docs = docs;
	}

	public Set<DocType> getChildrenDocType() {
		return childrenDocType;
	}

	public void setChildrenDocType(Set<DocType> childrenDocType) {
		this.childrenDocType = childrenDocType;
	}

	@Override
	public String toString() {
		return "DocType [typeId=" + typeId + ", typeName=" + typeName + "]";
	}
	

}
