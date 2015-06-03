package com.glomozda.machinerepair.domain.machines_serviceable;

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

import org.hibernate.validator.constraints.NotEmpty;

import com.glomozda.machinerepair.domain.client.Client;
import com.glomozda.machinerepair.domain.user.User;

@SuppressWarnings({"PMD.CommentRequired", "PMD.LawOfDemeter"})
@Entity
@Table(name = "machines_serviceable")
public class Machines_serviceable {

	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer ms_id;
	
	@Column
	private String name;
	
	@Column
	private String trademark;
	
	@Column
	private String country;
	
	@Column
	private Integer client_id;
	
	
	public Machines_serviceable(){};
	public Machines_serviceable(String name, String trademark,
			String country, int clientid) {
		super();
		this.name = name;
		this.trademark = trademark;
		this.country = country;
		this.client_id = clientid;
	}
	public Integer getMs_id() {
		return ms_id;
	}
	public void setMs_id(int ms_id) {
		this.ms_id = ms_id;
	}
	@Override
	public String toString() {
		return name +", " + trademark + "(" + country
				+ ") ";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + client_id;
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ms_id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((trademark == null) ? 0 : trademark.hashCode());
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
		Machines_serviceable other = (Machines_serviceable) obj;
		if (client_id != other.client_id)
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (ms_id != other.ms_id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (trademark == null) {
			if (other.trademark != null)
				return false;
		} else if (!trademark.equals(other.trademark))
			return false;
		return true;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTrademark() {
		return trademark;
	}
	public void setTrademark(String trademark) {
		this.trademark = trademark;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Integer getClientid() {
		return client_id;
	}
	public void setClientid(int clientid) {
		this.client_id = clientid;
	}
	
}
