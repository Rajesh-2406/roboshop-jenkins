def call () {
    pipeline {
        agent {
            node { label 'workstation' }
        }
        stages {
            stage('unit tests') {
                steps {
                    echo 'unit tests'
                }
            }
            stage('Code Analysis') {
                steps {
                    echo 'sonar'
                   // sh 'sudo sonar-scanner -Dsonar.host.url=http://172.31.68.239:9000 -Dsonar.login=admin -Dsonar.password=DevOps321 -Dsonar.projectKey=payment'
                }
            }

            stage('Security Scans') {
                steps {
                    echo 'Security Scans'
                }
            }

            stage('Publish a Artifact') {
                steps {
                    echo 'Publish a Artifact'
                }
            }

        }
    }
}