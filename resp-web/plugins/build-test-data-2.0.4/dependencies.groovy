grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
grails.project.target.level = 1.6

grails.project.dependency.resolution = {
    inherits("global") {
    }
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    repositories {
        grailsCentral()
        mavenCentral()
        grailsRepo "http://grails.org/plugins"
    }

    dependencies {
        compile "dk.brics.automaton:automaton:1.11-8"
    }

    plugins {
        build ":release:2.0.3", {
            export = false
        } 
    }
}
