package com.crossover.techtrial.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="comment")
public class Comment extends BaseEntity implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -481073315751589931L;
	
	@Column(name="email")
	@Getter @Setter  String email;
	
	@Column(name="article_id")
	@Getter @Setter Long articleId;
	
	@Column(name="message")
	@Getter @Setter  String content;
}
