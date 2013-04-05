package com.nw.rads.estimation.phases

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

import grails.buildtestdata.mixin.Build
import com.nw.rads.estimation.*

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */

@Build([Project,SubProject,DesignPhase,EstimatesVsActuals])
@TestMixin(GrailsUnitTestMixin)
class PreInitiatePhaseSpec extends Specification {

	def setup() {
	}

	def cleanup() {
	}

	void "test something"() {
	}
}