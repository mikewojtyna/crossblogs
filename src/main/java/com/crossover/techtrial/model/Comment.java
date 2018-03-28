package com.crossover.techtrial.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="comment")
public class Comment extends BaseEntity implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -481073315751589931L;
	
	@Email
	@NotNull
	@Column(name="email")
	@Getter @Setter  String email;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "article_id",referencedColumnName="id")
	@Getter @Setter Article article;
	
	@Size(max=32768)
	@Column(name="message", length=32)
	@Getter @Setter  String message;
}
