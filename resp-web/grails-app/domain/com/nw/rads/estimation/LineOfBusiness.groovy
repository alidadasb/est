package com.nw.rads.estimation

class LineOfBusiness {
	String name
	String description

    static constraints = {
    	description widget: 'textarea'	, maxSize: 250
    }
}
