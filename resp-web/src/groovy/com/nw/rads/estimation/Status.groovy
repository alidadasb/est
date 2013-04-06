package com.nw.rads.estimation

public enum Status {
	INPROGRESS ,	NOTSTARTED ,	COMPLETED

	static Status determineStatus(List list){
		if (list.findAll {it == Status.COMPLETED}.size()==list.size()){
			return Status.COMPLETED
		}
		if (list.findAll {it == Status.NOTSTARTED}.size()==list.size()){
			return Status.NOTSTARTED
		}
		return Status.INPROGRESS
	}
}
