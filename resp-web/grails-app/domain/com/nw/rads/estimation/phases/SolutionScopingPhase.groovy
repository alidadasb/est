package com.nw.rads.estimation.phases

import com.nw.rads.estimation.Note
import com.nw.rads.estimation.Status
import com.nw.rads.estimation.SubProject


class SolutionScopingPhase {
	String  name = "Solution Scoping"
	String  description = "Design to Implement"
	
	static hasMany = [notes:Note]
	static belongsTo = [subProject: SubProject]

	Integer estimatedSizeOfEffort =0
	Integer ssPhaseEndEstimatedHours  =0
	String  reasonForVariance
	Date    dateEstimateProvided

	Status status = Status.NOTSTARTED

	Integer getTotalEstimateHours() {
		ssPhaseEndEstimatedHours
	}

	static transients  = ['totalEstimateHours']

	

	static constraints = {
		description widget: 'textarea'    	, maxSize: 250
		reasonForVariance widget: 'textarea'    , maxSize: 250	
	}
}
