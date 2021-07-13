node('node01') {

    stage('Clone') {
      echo "1.git tag Stage"
      script {
              build_tag = sh(returnStdout: true, script: 'git rev-parse --short HEAD').trim()
          }
    }

    stage('Build') {
            echo "2.Build Docker Image Stage"
            sh "docker build -t harbor.jkservice.org/dpa/spring-cloud-admin:${build_tag} ."
    }

    stage('Push') {
        echo "3.Push Docker Image Stage"
        withCredentials([usernamePassword(credentialsId: 'jk-harbor', passwordVariable: 'jk-harborPassword', usernameVariable: 'jk-harborUser')]) {
            sh "docker push harbor.jkservice.org/dpa/spring-cloud-admin:${build_tag}"
        }
    }

    stage('Deploy') {
            echo "4. Deploy Stage"
            if (env.BRANCH_NAME == 'master') {
                input "确认要部署线上环境吗？"
            }
            sh "sed -i 's/<BUILD_TAG>/${build_tag}/' k8s-admin.yaml"
            sh "sed -i 's/<BRANCH_NAME>/${env.BRANCH_NAME}/' k8s-admin.yaml"
            sh "kubectl create -f k8s-admin.yaml --record"
    }
}
