podTemplate(
    cloud: "kubernetes",
    namespace: "kube-ops",
    label: "jnlp-slave",
    // 配置容器信息 image: "jenkins/jnlp-slave:4.6-1"
    containers: [
        containerTemplate(
            name: "jnlp",
            image: "cnych/jenkins:jnlp6"
        ),
    ],
    // 挂载，主要是为了使用宿主机的docker
    volumes: [
        hostPathVolume(mountPath: '/var/run/docker.sock', hostPath: '/var/run/docker.sock'),
        hostPathVolume(mountPath: '/usr/bin/docker', hostPath: '/usr/bin/docker'),
        hostPathVolume(mountPath: '/root/.m2', hostPath: '/root/.m2')
    ]
) {
    node("jnlp-slave") {
        // 拉取代码
        stage("clone") {
            // checkout([$class: 'GitSCM', branches: [[name: '*/master']], extensions: [], userRemoteConfigs: [[credentialsId: 'jenkinsgitlab', url: 'ssh://git@192.168.0.102:13022/istiodemo/testserverone.git']]])
            echo "1.Clone Stage"
            sh "docker info"
            sh "kubectl get pods -n kube-ops"
        }
    }
}
