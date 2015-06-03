package com.glomozda.machinerepair;

import com.glomozda.machinerepair.domain.machine.Machine;
import com.glomozda.machinerepair.domain.machines_serviceable.Machines_serviceable;

public class MachineMachineServiceable {
	private int mid;
	private String mserial_number;
	private int myear;
	private int mtimes_repaired;
	
	
	private int ms_id;
	private String msname;
	private String mstrademark;
	private String mscountry;
	private int msclient_id;
	
	public MachineMachineServiceable(Machine m, Machines_serviceable ms){
		this.mid=m.getId();
		this.mtimes_repaired=m.getTimes_repaired();
		this.myear=m.getYear();
		this.mserial_number=m.getSerial_number();
		this.ms_id=ms.getMs_id();
		this.msname=ms.getName();
		this.mstrademark=ms.getTrademark();
		this.mscountry=ms.getCountry();
		this.msclient_id=ms.getClientid();
	}

	public int getMid() {
		return mid;
	}

	public void setMid(int mid) {
		this.mid = mid;
	}

	public String getMserial_number() {
		return mserial_number;
	}

	public void setMserial_number(String mserial_number) {
		this.mserial_number = mserial_number;
	}

	public int getMyear() {
		return myear;
	}

	public void setMyear(int myear) {
		this.myear = myear;
	}

	public int getMtimes_repaired() {
		return mtimes_repaired;
	}

	public void setMtimes_repaired(int mtimes_repaired) {
		this.mtimes_repaired = mtimes_repaired;
	}

	public int getMs_id() {
		return ms_id;
	}

	public void setMs_id(int ms_id) {
		this.ms_id = ms_id;
	}

	public String getMsname() {
		return msname;
	}

	public void setMsname(String msname) {
		this.msname = msname;
	}

	public String getMstrademark() {
		return mstrademark;
	}

	public void setMstrademark(String mstrademark) {
		this.mstrademark = mstrademark;
	}

	public String getMscountry() {
		return mscountry;
	}

	public void setMscountry(String mscountry) {
		this.mscountry = mscountry;
	}

	public int getMsclient_id() {
		return msclient_id;
	}

	public void setMsclient_id(int msclient_id) {
		this.msclient_id = msclient_id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + mid;
		result = prime * result + ms_id;
		result = prime * result + msclient_id;
		result = prime * result
				+ ((mscountry == null) ? 0 : mscountry.hashCode());
		result = prime * result
				+ ((mserial_number == null) ? 0 : mserial_number.hashCode());
		result = prime * result + ((msname == null) ? 0 : msname.hashCode());
		result = prime * result
				+ ((mstrademark == null) ? 0 : mstrademark.hashCode());
		result = prime * result + mtimes_repaired;
		result = prime * result + myear;
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
		MachineMachineServiceable other = (MachineMachineServiceable) obj;
		if (mid != other.mid)
			return false;
		if (ms_id != other.ms_id)
			return false;
		if (msclient_id != other.msclient_id)
			return false;
		if (mscountry == null) {
			if (other.mscountry != null)
				return false;
		} else if (!mscountry.equals(other.mscountry))
			return false;
		if (mserial_number == null) {
			if (other.mserial_number != null)
				return false;
		} else if (!mserial_number.equals(other.mserial_number))
			return false;
		if (msname == null) {
			if (other.msname != null)
				return false;
		} else if (!msname.equals(other.msname))
			return false;
		if (mstrademark == null) {
			if (other.mstrademark != null)
				return false;
		} else if (!mstrademark.equals(other.mstrademark))
			return false;
		if (mtimes_repaired != other.mtimes_repaired)
			return false;
		if (myear != other.myear)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ms"+ mserial_number + " (" + myear + ")"+", " + msname
				+ "(" + mstrademark + "," + mscountry
				+ ")";
	}
	
}
