package com.nw.rads.estimation

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification
import grails.test.mixin.TestFor
import grails.buildtestdata.mixin.Build

import com.nw.rads.estimation.*
import com.nw.rads.estimation.phases.*

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
 
 @TestMixin(GrailsUnitTestMixin)
 @Build([SolutionScopingPhase,ChangeRequestLog,Project,SubProject,DesignPhase,PreInitiatePhase,EstimatesVsActuals])
 
 class ProjectSpec extends Specification {

 	def setup() {
 		def project = Project.buildWithoutSave(
 			id:100, 
 			projectName:"My Test Project",
 			preInitiatePMTroikaHours: 100,
 			preInitiateOutsideTeamHours: 300)

//************************************************************************************************************
 		def subProject1 = SubProject.buildWithoutSave(parrentProject:project,team: new Team(name:'FCS'))

			subProject1.preInitiatePhase = PreInitiatePhase.buildWithoutSave(
				subProject:subProject1,
				estimatedSizeOfEffort:0,
				preInitiateEstimatedHours:0,
				estimatedBuildToRunCost:0
			).save(flush:true,failOnError:true)

			subProject1.solutionScopingPhase = SolutionScopingPhase.buildWithoutSave(
				subProject:subProject1,
				estimatedSizeOfEffort:0,
				ssPhaseEndEstimatedHours:0,
				reasonForVariance :""
			).save(flush:true,failOnError:true)


	 		subProject1.designPhase = DesignPhase.buildWithoutSave(
	 			subProject:subProject1,
	 			estimatedSizeOfEffort : 0,
	 			designPhaseEndEstimatedHours:0,
	 			teamsActualHoursToDate:0,
	 			reEstimatedBuildToRunCost :0
	 			).save(flush:true,failOnError:true)

	 		subProject1.estimatesVsActualsPhase = EstimatesVsActuals.buildWithoutSave(
	 			subProject:subProject1,
				actualSizeOfEffort:0,
	 			ddiInsideTeamActualHours:0
	 			).save(flush:true,failOnError:true)

			subProject1.addToChangeRequestLog(changeRequestHours:123)

		subProject1.save(flush:true,failOnError:true)
		project.addToSubProjects (subProject1)

//************************************************************************************************************

 		subProject1 = SubProject.buildWithoutSave(parrentProject:project,	team: new Team(name:'CPFR CEL'))


			subProject1.preInitiatePhase = PreInitiatePhase.buildWithoutSave(
				subProject:subProject1,
				estimatedSizeOfEffort:1000,	 
				preInitiateEstimatedHours:55,
				estimatedBuildToRunCost:6400
			).save(flush:true,failOnError:true)

			subProject1.solutionScopingPhase = SolutionScopingPhase.buildWithoutSave(
				subProject:subProject1,
				estimatedSizeOfEffort:2200,
				ssPhaseEndEstimatedHours:10,
				reasonForVariance :""
			).save(flush:true,failOnError:true)


	 		subProject1.designPhase = DesignPhase.buildWithoutSave(
	 			subProject:subProject1,
	 			estimatedSizeOfEffort : 3000,
	 			teamsActualHoursToDate:50,
	 			designPhaseEndEstimatedHours:200,
	 			reEstimatedBuildToRunCost :5000
	 			).save(flush:true,failOnError:true)

	 		subProject1.estimatesVsActualsPhase = EstimatesVsActuals.buildWithoutSave(
	 			subProject:subProject1,
				actualSizeOfEffort:5000,
	 			ddiInsideTeamActualHours:500
	 			).save(flush:true,failOnError:true)

			subProject1.addToChangeRequestLog(changeRequestHours:100)
			subProject1.addToChangeRequestLog(changeRequestHours:200)
		subProject1.save(flush:true,failOnError:true)
		project.addToSubProjects (subProject1)

//************************************************************************************************************

 		subProject1 = SubProject.buildWithoutSave(parrentProject:project,team: new Team(name:'CPFR Pres Sys'))
			subProject1.preInitiatePhase = PreInitiatePhase.buildWithoutSave(
				subProject:subProject1,
				estimatedSizeOfEffort:300,	 
				preInitiateEstimatedHours:20,
				estimatedBuildToRunCost:500
			).save(flush:true,failOnError:true)

			 subProject1.solutionScopingPhase = SolutionScopingPhase.buildWithoutSave(
				subProject:subProject1,
				estimatedSizeOfEffort:500,
				ssPhaseEndEstimatedHours:10,
				reasonForVariance :""
			).save(flush:true,failOnError:true)


	 		subProject1.designPhase = DesignPhase.buildWithoutSave(
	 			subProject:subProject1,
	 			estimatedSizeOfEffort : 560,
	 			designPhaseEndEstimatedHours:600,
	 			teamsActualHoursToDate:500,
	 			reEstimatedBuildToRunCost :700
	 			).save(flush:true,failOnError:true)

	 		subProject1.estimatesVsActualsPhase = EstimatesVsActuals.buildWithoutSave(
	 			subProject:subProject1,
				actualSizeOfEffort:1500,
	 			ddiInsideTeamActualHours:2000
	 			).save(flush:true,failOnError:true)


			subProject1.addToChangeRequestLog(changeRequestHours:100)
			subProject1.addToChangeRequestLog(changeRequestHours:200)
			subProject1.addToChangeRequestLog(changeRequestHours:300)
		subProject1.save(flush:true,failOnError:true)
 		project.addToSubProjects (subProject1)

 		project.save(flush:true,failOnError:true)		
 	}

 	def cleanup() {
 	}

 	def "Verify sample project before tests running "() {
 		given:
 		expect:
 		Project.count() == 1
 		SubProject.count() == 3
 		EstimatesVsActuals.count() == 3
 		PreInitiatePhase.count() == 3
 		ChangeRequestLog.count() == 6
 	}

 	def "When pip_estimatedSizeOfEffort  should return \$"(){
 		expect: 
 			Project.get(100).pip_estimatedSizeOfEffort == 1300
 	}
 	def "When pip_preInitiateInsideTeamHours  should return \$"(){
 		expect:  		
 			Project.get(100).pip_preInitiateInsideTeamHours == 75
 	}

 	def "When pip_totalEstimateHours  should return \$"(){
 		expect:  		
 			Project.get(100).pip_totalEstimateHours == 475
 	}

 	def "When pip_estimatedBuildToRunCost  should return \$"(){
 		expect:  		
 			Project.get(100).pip_estimatedBuildToRunCost == 6900
 	}

 	def "When ssp_estimatedSizeOfEffort  should return \$"(){
 		expect:  		 		
 		Project.get(100).ssp_estimatedSizeOfEffort == 2700
 	}

 	def "When ssp_ssPhaseEndEstimatedHours  should return \$"(){
 		expect:  		 		
 		Project.get(100).ssp_ssPhaseEndEstimatedHours == 20
 	}

 	def "When ssp_totalEstimateHours  should return \$"(){
 		expect:  		 		
 		Project.get(100).ssp_totalEstimateHours == 20 		
 	}

 	def "When dp_estimatedSizeOfEffort  should return \$"(){
 		expect:  
 		Project.get(100).dp_estimatedSizeOfEffort == 3560
 	}
 	def "When dp_teamsActualHoursToDate  should return \$"(){
 		expect:  	
 		Project.get(100).dp_teamsActualHoursToDate == 550
 	}

  	def "When dp_designPhaseEndEstimatedHours  should return \$"(){
 		expect:  	
 		Project.get(100).dp_designPhaseEndEstimatedHours == 800
 	}
 	def "When dp_approvedChangeRequestHours  should return \$"(){
 		expect:  
 		Project.get(100).dp_approvedChangeRequestHours == 1023
 	}
 	def "When dp_totalEstimateHours  should return \$"(){
 		expect: 
 		Project.get(100).dp_totalEstimateHours == 2373 
 	}
 	def "When dp_reEstimatedBuildToRunCost  should return \$"(){
 		expect: 
 		Project.get(100).dp_reEstimatedBuildToRunCost == 5700
 	}



 	def "When eap_actualSizeOfEffort  should return \$"(){
 		expect: 
 		Project.get(100).eap_actualSizeOfEffort == 6500 		
 	}
 	def "When eap_ddiInsideTeamActualHours  should return \$"(){
 		expect: 
 		Project.get(100).eap_ddiInsideTeamActualHours == 2500 		
 	}
 	def "When eap_varianceHours  should return \$"(){
 		expect: 
 		Project.get(100).eap_varianceHours == 127 		
 	}
 	def "When eap_actualsToDesignGateCommitment  should return \$"(){
 		expect: 
 		Project.get(100).eap_actualsToDesignGateCommitment == 105 		
 	}

 	def "The status should return the consolidated status of all sub projects"() {
 		expect: 
 		fail "not yet implemented"
 	} 	

 }