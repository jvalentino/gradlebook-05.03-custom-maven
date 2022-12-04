package com.blogspot.jvalentino.gradle

import org.gradle.api.Project
import org.gradle.api.internal.project.ProjectInternal
import org.gradle.testfixtures.ProjectBuilder

import spock.lang.Specification

class CustomMavenPluginTestSpec extends Specification {

    Project project
    CustomMavenPlugin plugin

    def setup() {
        project = Mock(ProjectInternal)
        plugin = new CustomMavenPlugin()
    }

    void "test plugin"() {
        when:
        plugin.apply(project)

        then:
        true
    }
}
