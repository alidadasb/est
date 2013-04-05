package com.nw.rads.estimation.phases

import com.nw.rads.estimation.Note
import com.nw.rads.estimation.Status
import com.nw.rads.estimation.SubProject

class DesignPhase {
	String name = "Design"
	String description = "Develop to Implement"

	static hasMany = [notes:Note]

	static belongsTo = [subProject: SubProject]
	
	Integer estimatedSizeOfEffort =0
	Integer teamsActualHoursToDate  =0
	Integer designPhaseEndEstimatedHours  =0

	String reasonForVariance
	Date dateEstimateProvided
	Double reEstimatedBuildToRunCost = 0 

	static transients = ['totalEstimateHours','approvedChangeRequestHours']

	Integer getTotalEstimateHours() {
		teamsActualHoursToDate + designPhaseEndEstimatedHours + approvedChangeRequestHours
	}

	Integer getApprovedChangeRequestHours (){
		subProject.approavedChangeRequests()
	}

	Status status = Status.NOTSTARTED
	

	static constraints = {
		description widget: 'textarea' , maxSize: 250 
		reasonForVariance widget: 'textarea' , maxSize: 250
	}
}
