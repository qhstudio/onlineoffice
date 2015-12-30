package com.yyf.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.yyf.model.Doc;

/**
 * 文档Dao
 * 
 * @author yyf
 *
 */
public interface DocDao extends PagingAndSortingRepository<Doc, Long> {
	@Query("select d from Doc d where d.docAuthority < ?1 and d.docType.typeId = ?2 or d.docAuthority < ?3 and d.docType.parentType.typeId = ?4")
	public Page<Doc> findByDocAuthorityGreaterThanAndDocType(Integer docAuthority, Long typeId,Integer docAuthority2, Long typeId2, Pageable pageable);

	@Query("select d from Doc d where d.docAuthority < ?1")
	public Page<Doc> findByDocAuthorityGreaterThan(Integer docAuthority, Pageable pageable);

	@Query("select d from Doc d where d.docOwnUser.userId = ?1")
	public Page<Doc> findByOwnUserId(Long ownUserId, Pageable pageable);

	@Query("select d from Doc d where d.docOwnUser.userId = ?1")
	public Doc findByOwnUserId(Long ownUserId);
}
