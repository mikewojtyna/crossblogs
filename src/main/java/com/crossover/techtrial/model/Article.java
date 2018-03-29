/**
 * 
 */
package com.crossover.techtrial.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;


/**
 * @author crossover
 *
 */
@Entity
@Table(name = "article")
@EqualsAndHashCode(callSuper=true)
public class Article  extends BaseEntity  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5124000706092599751L;

	
	@Email
	@NotNull
	@Column(name="email")
	@Getter @Setter String email;
	
	@Getter @Setter @Column(name="title")
	String title;
	
	@Size(min=0,max=32768)
	@Getter @Setter @Column(name="content")
	String content;
	
	@Getter @Setter @Column(name="date")
	LocalDateTime date;
	
	@Getter @Setter @Column(name="published")
	Boolean published;
	
	
}
