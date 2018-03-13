package com.crossover.techtrial.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="comment")
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter  long id;
	
	@Column(name="email")
	@Getter @Setter  String email;
	
	@Column(name="article_id")
	@Getter @Setter Long articleId;
	
	@Column(name="message")
	@Getter @Setter  String content;
	
	@Column(name="created_at")
	@Getter @Setter  LocalDateTime createdAt;
	
	@Column(name="updated_at")
	@Getter @Setter  LocalDateTime updatedAt;

}
