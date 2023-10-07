def call() {
    node('workstation') {

        stage('code checkout') {
            sh 'find . | grep "^./" |xargs rm -rf'

            if(env.TAG_NAME ==~ ".*") {
                env.gitbrname = "refs/tags/${env.TAG_NAME}"
            }else {
                env.gitbrname = "${env.BRANCH_NAME}"
            }

            checkout scm: [$class: 'GitSCM', userRemoteConfigs: [[url: "https://github.com/Rajesh-2406/${env.component}"]], branches: [[name: gitbrname]]],poll: false
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
                if (env.cibuild == "java") {

                    stage('Build') {
                        sh 'mv target/${component}.jar ${component}.jar'
                        sh 'rm -rf pom.xml src target'
                    }
                }
                sh 'rm -f Jenkinsfile'
                sh 'echo ${TAG_NAME} >VERSION'
                sh 'zip -r ${component}-${TAG_NAME}.zip *'
                sh 'curl -v -u admin:DevOps321 --upload-file ${component}-${TAG_NAME}.zip http://172.31.20.63:8081/repository/${component}/${component}-${TAG_NAME}.zip'
            }
        }
    }
}


