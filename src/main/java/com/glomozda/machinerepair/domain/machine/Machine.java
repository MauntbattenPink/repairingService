package com.glomozda.machinerepair.domain.machine;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;
@SuppressWarnings({"PMD.CommentRequired", "PMD.LawOfDemeter"})
@Entity
@Table(name = "machine")

public class Machine {
	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	@Column
	private Integer ms_id;
	@Column
	private String serial_number;
	@Column
	private Integer year;
	@Column
	private Integer times_repaired;
	
	public Integer getTimes_repaired() {
		return times_repaired;
	}
	public void setTimes_repaired(Integer times_repaired) {
		this.times_repaired = times_repaired;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setMs_id(Integer ms_id) {
		this.ms_id = ms_id;
	}
	public void setYear(Integer year) {
		this.year = year;
	}
	public Machine(int ms_id, String serial_number, int year) {
		super();
		this.ms_id = ms_id;
		this.serial_number = serial_number;
		this.year = year;
		this.times_repaired=0;
	}
	public Machine() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Integer getMs_id() {
		return ms_id;
	}
	public void setMs_id(int ms_id) {
		this.ms_id = ms_id;
	}
	public String getSerial_number() {
		return serial_number;
	}
	public void setSerial_number(String serial_number) {
		this.serial_number = serial_number;
	}
	public Integer getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	
	public Machine(int id, int ms_id, String serial_number, int year,
			int timesRepaired) {
		super();
		this.id = id;
		this.ms_id = ms_id;
		this.serial_number = serial_number;
		this.year = year;
		this.times_repaired = timesRepaired;
	}
	public Machine(int ms_id, String serial_number, int year, int timesRepaired) {
		super();
		this.ms_id = ms_id;
		this.serial_number = serial_number;
		this.year = year;
		this.times_repaired = timesRepaired;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ms_id;
		result = prime * result
				+ ((serial_number == null) ? 0 : serial_number.hashCode());
		result = prime * result + times_repaired;
		result = prime * result + year;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Machine other = (Machine) obj;
		if (id != other.id)
			return false;
		if (ms_id != other.ms_id)
			return false;
		if (serial_number == null) {
			if (other.serial_number != null)
				return false;
		} else if (!serial_number.equals(other.serial_number))
			return false;
		if (times_repaired != other.times_repaired)
			return false;
		if (year != other.year)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return  ms_id+"-"+ serial_number+
				"(" +year + ") timesRepaired="
				+ times_repaired;
	}
	

}
