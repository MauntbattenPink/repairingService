package com.glomozda.machinerepair.domain.repair_type;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.glomozda.machinerepair.domain.client.Client;
import com.glomozda.machinerepair.domain.user.User;


@SuppressWarnings({"PMD.CommentRequired", "PMD.LawOfDemeter"})
@Entity
@Table(name = "repair_type")
public class Repair_Type {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "type_id")
	private Integer type_id;
	
	@Column(name = "name")
	@NotEmpty
	private String name;
	
	@Column(name = "price")
	@NotNull
	private Integer price;
	
	@Column(name = "duration")
	@NotNull
	private Integer duration;
	
	public Repair_Type() {
		
		// TODO Auto-generated constructor stub
	}
	public Repair_Type(String name, Integer price, Integer duration) {
		super();
		this.name = name;
		this.price = price;
		this.duration = duration;
	}
	public Integer getType_id() {
		return type_id;
	}
	public void setType_id(int type_id) {
		this.type_id = type_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	public Integer getDuration() {
		return duration;
	}
	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	public Repair_Type(Integer type_id, String name, Integer price, Integer duration) {
		super();
		this.type_id = type_id;
		this.name = name;
		this.price = price;
		this.duration = duration;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((duration == null) ? 0 : duration.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((type_id == null) ? 0 : type_id.hashCode());
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
		Repair_Type other = (Repair_Type) obj;
		if (duration == null) {
			if (other.duration != null)
				return false;
		} else if (!duration.equals(other.duration))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
			return false;
		if (type_id == null) {
			if (other.type_id != null)
				return false;
		} else if (!type_id.equals(other.type_id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return   name + " (" + price + "hrn," + duration + "hours)";
	}
	
	
	
}
