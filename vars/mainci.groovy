def call() {
    node('workstation') {

        stage('code checkout') {
            checkout scm: [$class: 'GitSCM' , userRemoteConfigs: [[url: 'https://github.com/Rajesh-2406/frontend']], branches: [[name: 'refs/tags/v1']]], poll: false
        }
        if (env.cibuild == "java") {

            stage('Build') {
                sh 'mvn package'
            }
        }
        stage('unit tests') {
            echo 'unit tests'

        }
        stage('Code Analysis') {
            // sh 'sudo sonar-scanner -Dsonar.host.url=http://172.31.68.239:9000 -Dsonar.login=admin -Dsonar.password=DevOps321 -Dsonar.projectKey=shipping'
        }

        stage('Security Scans') {
            echo 'Security Scans'
        }
        if (env.TAG_NAME ==~ ".*") {
            stage('Publish a Artifact') {
                if (env.cibuild == "nginx") {
                    sh 'zip -r $(component)-${TAG_NAME}.zip *'
                }
                sh 'curl -v -u admin:DevOps321 --upload-file ${component}-${TAG_NAME}.zip http://172.31.20.63:8081/repository/${component}/${component}-${TAG_NAME}.zip'
            }
        }
    }
}


