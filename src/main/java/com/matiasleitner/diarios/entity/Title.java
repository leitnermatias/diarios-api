package com.matiasleitner.diarios.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@NoArgsConstructor
public class Title extends BaseEntity {
	
	@NonNull
	@Column(unique = true)
	private String header;

	@NonNull
	private String titleUrl;
	
	@NonNull
	private String imgUrl;
	
	@NonNull
	@OneToOne
	private Newspaper newspaper;
}
