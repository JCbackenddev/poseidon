package com.nnk.springboot.domain;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/***
 * 
 * @author j.c.
 * POJO class CurvePoint
 *
 */
@Entity
@Table(name = "curvepoint")
public class CurvePoint {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	
	@Column(name = "curveId")
	@NotNull(message = "Curve id is mandatory")
	@Min(1)
	Integer curveId;
	
	@Column(name = "asOfDate")
	Timestamp asOfDate;
	
	@Column(name = "term")
	@NotNull(message = "Term is mandatory")
	@Min(1)
	Double term;
	
	@Column(name = "value")
	@NotNull(message = "Value is mandatory")
	@Min(1)
	Double value;
	
	@Column(name = "creationDate")
	Timestamp creationDate;

	/***
	 * Constructor for test purposes
	 * @param curveId
	 * @param term
	 * @param value
	 */
	public CurvePoint(int curveId, double term, double value) {
		this.curveId = curveId;
		this.term = term;
		this.value = value;
	}
	
	public CurvePoint() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCurveId() {
		return curveId;
	}

	public void setCurveId(Integer curveId) {
		this.curveId = curveId;
	}

	public Timestamp getAsOfDate() {
		return asOfDate;
	}

	public void setAsOfDate(Timestamp asOfDate) {
		this.asOfDate = asOfDate;
	}

	public Double getTerm() {
		return term;
	}

	public void setTerm(Double term) {
		this.term = term;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	@Override
	public String toString() {
		return "CurvePoint [id=" + id + ", curveId=" + curveId + ", asOfDate=" + asOfDate + ", term=" + term
				+ ", value=" + value + ", creationDate=" + creationDate + "]";
	}
	
	
	
}
