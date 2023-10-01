def call() {
            node { label 'workstation'}
            if (env.cibuild == "java") {

                stage('Build') {
                        sh 'mvn package'
                }
                stage('unit tests') {
                        echo 'unit tests'

                }
                stage('Code Analysis') {
                        sh 'sudo sonar-scanner -Dsonar.host.url=http://172.31.68.239:9000 -Dsonar.login=admin -Dsonar.password=DevOps321 -Dsonar.projectKey=shipping'
                }

                stage('Security Scans') {
                        echo 'Security Scans'
                }

                stage('Publish a Artifact') {
                        echo 'Publish a Artifact'
                }
            }
        }

