package com.yyf.actions;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.yyf.model.Comment;
import com.yyf.model.Doc;
import com.yyf.model.DocType;
import com.yyf.model.User;
import com.yyf.service.CommentService;
import com.yyf.service.DocTypeService;
import com.yyf.service.UserService;

/**
 * struts2 中 json的使用
 * 
 * @author yyf
 *
 */
@ParentPackage("json-default")
@Namespace("/json")
@Results({ @Result(name = "json", type = "json", params = { "root", "dataMap" }) })
@Controller
public class JsonAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	private DocTypeService docTypeService;
	@Autowired
	private UserService userService;
	@Resource
	private CommentService commentService;
	
	private Map<String, Object> dataMap = new HashMap<String, Object>();

	private Integer page;
	private Long docId;
	private Long parentId;
	private String commentContext;
	
	
	
	
	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getCommentContext() {
		return commentContext;
	}

	public void setCommentContext(String commentContext) {
		this.commentContext = commentContext;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Long getDocId() {
		return docId;
	}

	public void setDocId(Long docId) {
		this.docId = docId;
	}

	public Map<String, Object> getDataMap() {
		return dataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		this.dataMap = dataMap;
	}

	@Action(value = "json-users")
	public String json() throws Exception {
		dataMap.put("users", userService.getUsers());
		return "json";
	}

	/**
	 * 得到分类列表
	 * 
	 * @return
	 * @throws Exception
	 */
	@Action(value = "json-doc-types")
	public String getDocTypeList() throws Exception {
		List<DocType> list = docTypeService.getParentsType();
		dataMap.put("doctypes", list);
		return "json";
	}
	
	/**
	 * 得到doc评论
	 * @return
	 * @throws Exception
	 */
	@Action(value = "json-doc-comments")
	public String getDocCommentList() throws Exception {
		if(page == null){
			page = 0;
		}
		Page<Comment> cPage = commentService.getCommentPageByDocId(docId, page, 10);
		dataMap.put("cPage", cPage);
		return "json";
	}
	
	@Action(value = "json-doc-add-comments")
	public String addDocComment() throws Exception {
		User user = (User) ActionContext.getContext().getSession().get("user");
		Comment c = new Comment();
		c.setCommentTime(new Date());
		c.setCommentUser(user);
		c.setCommentContext(commentContext);
		Doc doc = new Doc();
		doc.setDocId(docId);
		c.setCommentDoc(doc);
		if(parentId!=null){
			Comment nc = new Comment();
			nc.setCommentId(parentId);
			c.setParentComment(nc);
		}
		Comment comment = commentService.addComment(c);
		dataMap.put("comment", comment);
		return "json";
	}
	
	
}
