package com.glomozda.machinerepair;
import java.sql.Date;

import com.glomozda.machinerepair.domain.machine.Machine;
import com.glomozda.machinerepair.domain.order.Order;
import com.glomozda.machinerepair.domain.repair_type.Repair_Type;


public class OrderMachineRepair_Type {
	private int order_id;
	private Date start;
	private String status;
	private Integer clients_id;
	private Integer type_id;
	private Integer machine_id;
	
	private String name;
	private Integer price;
	private Integer duration;
	public OrderMachineRepair_Type(Order o,  Repair_Type r){
		 order_id=o.getOrder_id();
		 start=o.getStart();
		 status=o.getStatus();
		 clients_id=o.getClients_id();
		 type_id=o.getType_id();
		 machine_id=o.getMachine_id();
		
		  name=r.getName();
		  price=r.getPrice();
		  duration=r.getDuration();
		
	}
	public OrderMachineRepair_Type(int order_id, Date start, String status,
			Integer clients_id, Integer type_id, Integer machine_id,
			String name, Integer price, Integer duration) {
		super();
		this.order_id = order_id;
		this.start = start;
		this.status = status;
		this.clients_id = clients_id;
		this.type_id = type_id;
		this.machine_id = machine_id;
		this.name = name;
		this.price = price;
		this.duration = duration;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
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
		return clients_id;
	}
	public void setClients_id(Integer clients_id) {
		this.clients_id = clients_id;
	}
	public Integer getType_id() {
		return type_id;
	}
	public void setType_id(Integer type_id) {
		this.type_id = type_id;
	}
	public Integer getMachine_id() {
		return machine_id;
	}
	public void setMachine_id(Integer machine_id) {
		this.machine_id = machine_id;
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((clients_id == null) ? 0 : clients_id.hashCode());
		result = prime * result
				+ ((duration == null) ? 0 : duration.hashCode());
		result = prime * result
				+ ((machine_id == null) ? 0 : machine_id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + order_id;
		result = prime * result + ((price == null) ? 0 : price.hashCode());
		result = prime * result + ((start == null) ? 0 : start.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
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
		OrderMachineRepair_Type other = (OrderMachineRepair_Type) obj;
		if (clients_id == null) {
			if (other.clients_id != null)
				return false;
		} else if (!clients_id.equals(other.clients_id))
			return false;
		if (duration == null) {
			if (other.duration != null)
				return false;
		} else if (!duration.equals(other.duration))
			return false;
		if (machine_id == null) {
			if (other.machine_id != null)
				return false;
		} else if (!machine_id.equals(other.machine_id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (order_id != other.order_id)
			return false;
		if (price == null) {
			if (other.price != null)
				return false;
		} else if (!price.equals(other.price))
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
		if (type_id == null) {
			if (other.type_id != null)
				return false;
		} else if (!type_id.equals(other.type_id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "OrderMachineRepair_Type [order_id=" + order_id + ", start="
				+ start + ", status=" + status + ", clients_id=" + clients_id
				+ ", type_id=" + type_id + ", machine_id=" + machine_id
				+ ", name=" + name + ", price=" + price + ", duration="
				+ duration + "]";
	}
	
	
}
