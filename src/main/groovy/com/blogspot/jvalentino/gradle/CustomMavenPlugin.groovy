package com.blogspot.jvalentino.gradle

import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.bundling.Jar

/**
 * <p>A basic gradle plugin.</p>
 * @author jvalentino2
 */
@SuppressWarnings(['Println', 'DuplicateStringLiteral', 'NestedBlockDepth'])
class CustomMavenPlugin implements Plugin<Project> {

    void apply(Project project) {
        Map props = project.properties

        project.configure(project) {
            apply plugin:'maven'

            afterEvaluate {
                project.task('sourcesJar', type:Jar, 
                    dependsOn:classes) {
                    classifier = 'sources'
                    from sourceSets.main.allSource
                }

                project.task('javadocJar', type:Jar, 
                    dependsOn:groovydoc) {
                    classifier = 'javadoc'
                    from groovydoc.destinationDir
                }

                artifacts {
                    archives sourcesJar
                    archives javadocJar
                }

                uploadArchives {
                    repositories {
                        mavenDeployer {
                            repository(url:props.url) {
                                authentication(
                                        userName:props.username,
                                        password:props.password)
                            }
                        }
                    }
                }
            }
        }
    }
}
