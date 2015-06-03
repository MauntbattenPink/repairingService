package com.glomozda.machinerepair.domain.order;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;


@SuppressWarnings({"PMD.CommentRequired", "PMD.LawOfDemeter"})
@Entity
@Table(name = "orders", schema="machinerepair")
public class Order {

	@Id
	@GeneratedValue
	@Column(name = "orderid")
	private Integer orderid;
	
	@Column(name = "start")
	@NotNull
	private Date start;

	@Column(name = "status")
	@NotEmpty
	private String status;

	@Column(name = "clientsid")
	@NotNull
	private Integer clientsid;

	@Column(name = "typeid")
	@NotNull
	private Integer typeid;

	@Column(name = "machineid")
	@NotNull
	private Integer machineid;

	public Integer getOrder_id() {
		return orderid;
	}

	public void setOrder_id(Integer order_id) {
		this.orderid = order_id;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getClients_id() {
		return clientsid;
	}

	public void setClients_id(Integer clients_id) {
		this.clientsid = clients_id;
	}

	public Integer getType_id() {
		return typeid;
	}

	public void setType_id(Integer type_id) {
		this.typeid = type_id;
	}

	public Integer getMachine_id() {
		return machineid;
	}

	public void setMachine_id(Integer machine_id) {
		this.machineid = machine_id;
	}

	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Order(Integer order_id, Date start, String status,
			Integer clients_id, Integer type_id, Integer machine_id) {
		super();
		this.orderid = order_id;
		this.start = start;
		this.status = status;
		this.clientsid = clients_id;
		this.typeid = type_id;
		this.machineid = machine_id;
	}

	public Order(Date start, String status, Integer clients_id,
			Integer type_id, Integer machine_id) {
		this.start = start;
		this.status = status;
		this.clientsid = clients_id;
		this.typeid = type_id;
		this.machineid = machine_id;
	}

	public Order(String status,
			Integer clients_id, Integer type_id, Integer machine_id) {
	
		this.status = status;
		this.clientsid = clients_id;
		this.typeid = type_id;
		this.machineid = machine_id;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((clientsid == null) ? 0 : clientsid.hashCode());
		result = prime * result
				+ ((machineid == null) ? 0 : machineid.hashCode());
		result = prime * result
				+ ((orderid == null) ? 0 : orderid.hashCode());
		result = prime * result + ((start == null) ? 0 : start.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((typeid == null) ? 0 : typeid.hashCode());
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
		Order other = (Order) obj;
		if (clientsid == null) {
			if (other.clientsid != null)
				return false;
		} else if (!clientsid.equals(other.clientsid))
			return false;
		if (machineid == null) {
			if (other.machineid != null)
				return false;
		} else if (!machineid.equals(other.machineid))
			return false;
		if (orderid == null) {
			if (other.orderid != null)
				return false;
		} else if (!orderid.equals(other.orderid))
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (typeid == null) {
			if (other.typeid != null)
				return false;
		} else if (!typeid.equals(other.typeid))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Order [order_id=" + orderid + ", start=" + start + ", status="
				+ status + ", clients_id=" + clientsid + ", type_id="
				+ typeid + ", machine_id=" + machineid + "]";
	}

	
}
