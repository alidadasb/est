package com.nw.rads.estimation

class RevisionLog {
	Date revisionDate
	String updatedBy
	String descriptionOfChange


    static constraints = {
    	descriptionOfChange  widget: 'textarea'  , maxSize: 250	
    }
}
