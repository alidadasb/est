package com.nw.rads.estimation.phases

import com.nw.rads.estimation.Note
import com.nw.rads.estimation.Status
import com.nw.rads.estimation.SubProject

class EstimatesVsActuals {
	String        name = "Estimates vs Actuals"
	String        description  =""
	
	static        hasMany = [notes:Note]
	static 		  belongsTo = [subProject: SubProject]
	Status status = Status.NOTSTARTED
	
	Integer       actualSizeOfEffort =0
	Integer       ddiInsideTeamActualHours  =0

	String        reasonForVariance
	
	
	Integer getVarianceHours(){
		 def dObj  = this.subProject.designPhase
		 return ddiInsideTeamActualHours - dObj.totalEstimateHours
	}

	static transients  = ['varianceHours']

	static constraints = {
		description widget: 'textarea'  , maxSize: 250
		reasonForVariance widget: 'textarea'    , maxSize: 250			  	
	}
}
