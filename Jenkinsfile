pipeline {
    stage('prepare') {
      echo "1.git tag Stage"
      script {
              build_tag = sh(returnStdout: true, script: 'git rev-parse --short HEAD').trim()
          }
    }

    stage('Build') {
        echo "2.Build Docker Image Stage"
        steps {
            container('gradle'){
                sh 'gradle clean build'
                sh "docker build -t harbor.jkservice.org/dpa/spring-cloud-admin:${build_tag} ."
            }
        }

    }

    stage('Push') {
        echo "3.Push Docker Image Stage"
        withCredentials([usernamePassword(credentialsId: 'jk-harbor', passwordVariable: 'jk-harborPassword', usernameVariable: 'jk-harborUser')]) {
            sh "docker push harbor.jkservice.org/dpa/spring-cloud-admin:${build_tag}"
        }
    }

    stage('Deploy') {
            echo "4. Deploy Stage"
            sh "kubectl apply -f cloud.yaml"
    }
}
