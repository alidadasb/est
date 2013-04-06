package com.nw.rads.estimation

import com.nw.rads.estimation.phases.* 

class SubProject {
	Team team

	Project parrentProject
	String businessScope


	static hasOne = [
	preInitiatePhase:PreInitiatePhase,
	solutionScopingPhase:SolutionScopingPhase,
	designPhase:DesignPhase,
	estimatesVsActualsPhase:EstimatesVsActuals
	]

	
	static hasMany = [notes:Note,changeRequestLog:ChangeRequestLog,revisionLog: RevisionLog ]

	static constraints = {
		businessScope             widget: 'textarea', maxSize: 250
		parrentProject            nullable: false
		preInitiatePhase          nullable:true
		solutionScopingPhase      nullable:true
		designPhase               nullable:true
		estimatesVsActualsPhase   nullable:true
	}

	static namedQueries = {}

	Integer approavedChangeRequests(){
		this?.changeRequestLog?.changeRequestHours?.sum() ?:0
	}

	Status getStatus(){
		def ls = []
		ls << preInitiatePhase.status
		ls << solutionScopingPhase.status
		ls << designPhase.status
		ls << estimatesVsActualsPhase.status
		return Status.determineStatus(ls)
	}
}
