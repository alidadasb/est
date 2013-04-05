package com.nw.rads.estimation

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification
import grails.buildtestdata.mixin.Build

import com.nw.rads.estimation.phases.*

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
@Build([Project,SubProject,DesignPhase])
class SubProjectSpec extends Specification {

	def setup() {
	}

	def cleanup() {
	}

 	void "Approaved Change Request query should return sum of all change requests"() {
 		setup:
 		when:
 			def project = Project.buildWithoutSave()
 			def subProject1 = SubProject.buildWithoutSave(id:10,parrentProject:project)
 			subProject1.addToChangeRequestLog(changeRequestDate: new Date(), approvedChangeRequest:"request 1" ,	changeRequestHours:100)
 			subProject1.addToChangeRequestLog(changeRequestDate: new Date(), approvedChangeRequest:"request 2" ,	changeRequestHours:200)
 			subProject1.addToChangeRequestLog(changeRequestDate: new Date(), approvedChangeRequest:"request 3" ,	changeRequestHours:300)
			subProject1.save(flush:true,failOnError:true)			
 		then:
 			SubProject.get(10).approavedChangeRequests() == 600 			
 	}


}