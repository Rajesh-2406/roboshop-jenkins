
def call () {
    pipeline {
        agent {
            node { label 'workstation' }
        }
        stages {

            stage('Build') {
                steps {
                    echo 'npm install'
                }
            }
            stage('unit tests') {
                steps {
                    echo 'unit tests'
                }
            }
            stage('Code Analysis') {
                steps {
                    echo 'sonar'

                }
            }

            stage('Security Scans') {
                steps {
                    echo 'Security Scans'
                }
            }

            stage('Publish a Artifact') {
                when {
                    expression {
                        env.TAG_NAME ==~ ".*"
                    }
                }
                steps {
                    echo 'Publish a Artifact'
                    sh 'env'
                }
            }

        }
    }
}
sh 'find . | grep "^./" | xargs rm -rf'
if(env.TAG_NAME ==~ ".*") {
    env.gitbname = "refs/tags/${env.TAG_NAME}"
}
else {
    env.gitbrname = "${env.BRANCH_NAME}"
}
checkout scm: [$class: 'GitSCM' , userRemoteConfigs: [[url: 'https://github.com/Rajesh-2406/frontend']], branches: [[name: 'refs/tags/v1']]], poll: false