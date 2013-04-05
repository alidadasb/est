package com.nw.rads.estimation.phases

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

import grails.buildtestdata.mixin.Build
import com.nw.rads.estimation.phases.*
import com.nw.rads.estimation.*

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
 @TestMixin(GrailsUnitTestMixin)
 @Build([Project,SubProject,DesignPhase,EstimatesVsActuals])
 class EstimatesVsActualsSpec extends Specification {

 	def setup() {
 	}

 	def cleanup() {
 	}

 	void "When getVarianceHours is called it should return the value ddiInsideTeamActualHours minues design TotalEstimateHours"() {
 		setup:
 		when:
 		def project = Project.buildWithoutSave(projectName:"My Test Project")
 		def subProject1 = SubProject.buildWithoutSave(parrentProject:project)

 		def designPhase = DesignPhase.buildWithoutSave(subProject:subProject1,id:123,teamsActualHoursToDate:100,designPhaseEndEstimatedHours:200) 		
 		designPhase.save(flush:true,failOnError:true)

 		def estimatesVsActuals= EstimatesVsActuals.buildWithoutSave(subProject:subProject1,id:51,ddiInsideTeamActualHours:2000) 		
 		estimatesVsActuals.save(flush:true,failOnError:true)

		subProject1.designPhase        = designPhase
		subProject1.estimatesVsActualsPhase = estimatesVsActuals

		subProject1.addToChangeRequestLog(changeRequestDate: new Date(), approvedChangeRequest:"request 1" ,	changeRequestHours:100)
		subProject1.addToChangeRequestLog(changeRequestDate: new Date(), approvedChangeRequest:"request 2" ,	changeRequestHours:200)
		subProject1.addToChangeRequestLog(changeRequestDate: new Date(), approvedChangeRequest:"request 3" ,	changeRequestHours:300)

		subProject1.save(flush:true,failOnError:true)

 		project.addToSubProjects (subProject1)
 		project.save(flush:true,failOnError:true)

 		then:
 		project != null
 		DesignPhase.count() == 1
 		EstimatesVsActuals.count() == 1
 		SubProject.count() == 1
 		Project.count() == 1

 		EstimatesVsActuals.get(51).varianceHours == 1100//2000- (100+200+(100+200+300))
 	}

 }