package com.crossover.techtrial.model;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
public abstract class BaseEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Getter @Setter  Long id;
	
	@Getter  @Column(name="created_at")
	LocalDateTime createdAt;
	
	@Getter  @Column(name="updated_at")
	LocalDateTime updatedAt;
	
	@PrePersist
	public void setCreatedAt()
	{
		this.createdAt = LocalDateTime.now();
		this.updatedAt = LocalDateTime.now();
	}
	
	@PreUpdate
	public void setUpdatedAt()
	{
		this.updatedAt = LocalDateTime.now();
	}
}
