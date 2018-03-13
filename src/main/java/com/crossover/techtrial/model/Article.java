/**
 * 
 */
package com.crossover.techtrial.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;


/**
 * @author crossover
 *
 */
@Entity
@Table(name = "article")
public class Article  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5124000706092599751L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter  Long id;
	
	@Column(name="email")
	@Getter @Setter String email;
	
	@Getter @Setter @Column(name="title")
	String title;
	
	@Getter @Setter @Column(name="content")
	String content;
	
	@Getter @Setter @Column(name="date")
	LocalDateTime date;
	
	@Getter @Setter @Column(name="published")
	Boolean published;
	
	@Getter @Setter @Column(name="created_at")
	LocalDateTime createdAt;
	
	@Getter @Setter @Column(name="updated_at")
	LocalDateTime updatedAt;
}
