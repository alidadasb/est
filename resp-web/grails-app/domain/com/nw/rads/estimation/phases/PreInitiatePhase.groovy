package com.nw.rads.estimation.phases

import com.nw.rads.estimation.Note
import com.nw.rads.estimation.Status
import com.nw.rads.estimation.SubProject

class PreInitiatePhase {
	String name  = "Pre-Initiate"
	String description = "Initiate to Implement"

	static hasMany = [notes:Note]
	static belongsTo = [subProject: SubProject]

	Integer estimatedSizeOfEffort =0
	Integer preInitiateEstimatedHours =0
	Integer totalEstimateHours =0
	Double estimatedBuildToRunCost =0
	Date dateEstimateProvided

	Integer getTotalEstimateHours() {
		preInitiateEstimatedHours
	}

	static transients  = ['totalEstimateHours']

	
	Status status = Status.NOTSTARTED

	static constraints = {
		description widget: 'textarea'  , maxSize: 250  	
	}
}
