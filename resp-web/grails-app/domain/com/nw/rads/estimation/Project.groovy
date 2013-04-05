package com.nw.rads.estimation

class Project {
	String projectName	
	String clarityName	
	String description	

	LineOfBusiness lineOfBusiness

	Integer preInitiatePMTroikaHours
	Integer preInitiateOutsideTeamHours



	static hasMany = [subProjects: SubProject,notes:Note]

	static constraints = {
		description widget: 'textarea', maxSize: 250
	}

	static transients = ["pip_estimatedSizeOfEffort" ,"pip_preInitiateInsideTeamHours" ,"pip_totalEstimateHours" ,
	"pip_estimatedBuildToRunCost" ,"ssp_estimatedSizeOfEffort" ,"ssp_ssPhaseEndEstimatedHours" ,
	"ssp_totalEstimateHours" ,"dp_estimatedSizeOfEffort" ,"dp_teamsActualHoursToDate" ,
	"dp_designPhaseEndEstimatedHours" ,"dp_approvedChangeRequestHours" ,"dp_totalEstimateHours" ,
	"dp_reEstimatedBuildToRunCost" ,"eap_actualSizeOfEffort" ,"eap_ddiInsideTeamActualHours" ,
	"eap_varianceHours" ,"eap_actualsToDesignGateCommitment"]



	Integer getPip_estimatedSizeOfEffort(){
		this?.subProjects?.preInitiatePhase?.estimatedSizeOfEffort?.sum() ?:0
	}

	Integer getPip_preInitiateInsideTeamHours(){
		this?.subProjects?.preInitiatePhase?.totalEstimateHours?.sum() ?:0 		
	}

	Integer getPip_totalEstimateHours(){
		(preInitiatePMTroikaHours  + preInitiateOutsideTeamHours + pip_preInitiateInsideTeamHours)?:0
	}

	Integer getPip_estimatedBuildToRunCost(){
		this?.subProjects?.preInitiatePhase?.estimatedBuildToRunCost?.sum() ?:0 		
	}

	Integer getSsp_estimatedSizeOfEffort(){
		this?.subProjects?.solutionScopingPhase?.estimatedSizeOfEffort?.sum() ?:0 		
	}

	Integer getSsp_ssPhaseEndEstimatedHours(){
		this?.subProjects?.solutionScopingPhase?.ssPhaseEndEstimatedHours?.sum() ?:0 		
	}

	Integer getSsp_totalEstimateHours(){
		ssp_ssPhaseEndEstimatedHours
	}



	Integer getDp_estimatedSizeOfEffort(){
		this?.subProjects?.designPhase.estimatedSizeOfEffort?.sum() ?:0
	}

	Integer getDp_teamsActualHoursToDate(){
		this?.subProjects?.designPhase.teamsActualHoursToDate?.sum() ?:0
	}

	Integer getDp_designPhaseEndEstimatedHours(){
		this?.subProjects?.designPhase.designPhaseEndEstimatedHours?.sum() ?:0
	}

	Integer getDp_approvedChangeRequestHours(){
		this?.subProjects?.designPhase.approvedChangeRequestHours?.sum() ?:0
	}

	Integer getDp_totalEstimateHours(){
		(dp_designPhaseEndEstimatedHours + dp_approvedChangeRequestHours + dp_teamsActualHoursToDate)?:0
	}

	BigDecimal  getDp_reEstimatedBuildToRunCost(){
		this?.subProjects?.designPhase.reEstimatedBuildToRunCost?.sum() ?:0
	}


	Integer getEap_actualSizeOfEffort (){
		this?.subProjects?.estimatesVsActualsPhase?.actualSizeOfEffort?.sum() ?:0
	}
	Integer getEap_ddiInsideTeamActualHours (){
		this?.subProjects?.estimatesVsActualsPhase?.ddiInsideTeamActualHours?.sum() ?:0
	}
	Integer getEap_varianceHours (){
		this?.subProjects?.estimatesVsActualsPhase?.varianceHours?.sum() ?:0
	}

	BigDecimal getEap_actualsToDesignGateCommitment (){
		def teh= getDp_totalEstimateHours()
		def ddsi= getEap_ddiInsideTeamActualHours()
		BigDecimal  res = 0
		if (teh) res = ddsi/teh
		return new BigDecimal( res, 2 )
	}

}



