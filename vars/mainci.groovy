def call() {
    node('workstation') {

        stage('code checkout') {
            sh 'env'
            sh 'find . | grep "./" | xargs rm -rf'
            git branch: env.BRANCH_NAME , url: 'https://github.com/Rajesh-2406/frontend'
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
                echo 'Publish a Artifact'
            }
        }
    }
}


