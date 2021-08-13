podTemplate(
    cloud: "kubernetes",
    namespace: "kube-ops",
    label: "jnlp-slave",
    // 配置容器信息
    containers: [
        containerTemplate(
            name: "jnlp",
            image: "jenkins/jenkins:lts",
            alwaysPullImage: true
        ),
    ],
    // 挂载，主要是为了使用宿主机的docker
    volumes: [
        hostPathVolume(mountPath: '/var/run/docker.sock', hostPath: '/var/run/docker.sock'),
        hostPathVolume(mountPath: '/usr/bin/docker', hostPath: '/usr/bin/docker'),
        hostPathVolume(mountPath: '/root/.m2', hostPath: '/root/.m2')
    ],
    imagePullSecrets: ['registry-pull-secret-2th'],
) {
    node("jnlp-slave") {
        // 拉取代码
        stage("clone") {
            // checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[credentialsId: 'jenkinsgitlab', url: 'ssh://git@192.168.0.102:13022/istiodemo/testserverone.git']]])
            echo "1.Clone Stage"
            sh "docker info"
        }
    }
}
