package com.project.jobbackenddeveloper.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Setter;

@Entity
@Table(name = "news")
@Setter
public class News  implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_news")
	public Long id;
	
	@Column(name = "dt_news")
	public LocalDate newsDate;
	
	@Column(name = "ds_news")
	public String description;
}
