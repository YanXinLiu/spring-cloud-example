pipeline {
    agent any
    parameters {
        choices (
            description: '模块名称', name: 'NAME', choices: ['spring-cloud-admin','spring-cloud-platform']
        )
    }
    stages {
        stage('prepare') {
            steps{
                echo "1.git tag Stage"
                script{
                    build_tag = sh(returnStdout: true, script: 'git rev-parse --short HEAD').trim()
                }
            }
        }
        stage('Build') {
            agent {
                docker {
                    image 'gradle:6.5.1'
                    args '-v $HOME/.gradle/:/root/.gradle/ -v $HOME/.m2/:/root/.m2/'
                }
            }
            steps {
                echo "2.Build Docker Image Stage"
                sh 'cd ${params.NAME} && gradle clean build'
                sh 'cd ${params.Name} && docker build -t harbor.jkservice.org/dpa/spring-cloud-admin:${build_tag} .'
            }
        }
        /* stage('Push') {
            steps {
                sh 'echo "3.Push Docker Image Stage"'
                withCredentials([usernamePassword(credentialsId: 'jk-harbor', passwordVariable: 'jk-harborPassword', usernameVariable: 'jk-harborUser')]) {
                    sh "docker push harbor.jkservice.org/dpa/spring-cloud-admin:${build_tag}"
                }
            }
        }
        stage('Deploy') {
            steps {
                sh 'echo "4. Deploy Stage"'
                sh 'kubectl apply -f cloud.yaml'
            }
        } */
    }
}
