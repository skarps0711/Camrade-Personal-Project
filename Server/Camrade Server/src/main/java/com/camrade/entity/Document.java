package com.camrade.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "document")
public class Document {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "document_id")
	private Long documentId;

	@Column(name = "owner_id")
	private Long ownerId;

	@Column(name = "document_path")
	private String documentPath;

	@Column(name = "permitted_persons_id")
	private String permittedPersonsId;
	
	@Column(name = "added_date")
	private Date addedDate;

	public Long getDocumentId() {
		return documentId;
	}

	public void setDocumentId(Long documentId) {
		this.documentId = documentId;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public String getDocumentPath() {
		return documentPath;
	}

	public void setDocumentPath(String documentPath) {
		this.documentPath = documentPath;
	}

	public String getPermittedPersonsId() {
		return permittedPersonsId;
	}

	public void setPermittedPersonsId(String permittedPersonsId) {
		this.permittedPersonsId = permittedPersonsId;
	}
	
	public Date getAddedDate() {
		return addedDate;
	}

	public void setAddedDate(Date addedDate) {
		this.addedDate = addedDate;
	}

	public Document(){}
}
