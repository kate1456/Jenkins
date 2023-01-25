
pipeline{
    agent { label 'windows' }

    stages{
        stage('Run Tests'){
            steps{
                sh "/var/jenkins_home/tools/hudson.tasks.Maven_MavenInstallation/3.6.3/bin/mvn clean test -Dtype.browser=${BROWSER}"
            }
        }
        stage('Allure Report Generation'){
            steps{
                allure includeProperties: false,
                        jdk: '',
                        results: [[path: 'target/reports/allure-results']]
            }
        }
    }
    post {
        always {
            cleanWs notFailBuild: true
        }
    }
}
