pipeline {
    agent any 
    stages {
        stage('code-pull'){
            steps {
                git branch: 'dev', url: 'https://github.com/shubham-chawhan/project-backend.git'
            }
        }
        stage('code-Build'){
            steps {
               sh 'mvn clean package'
            }
        }
         stage('Deploy-K8s'){
            steps {
               sh '''
                    docker build . -t shubham2025/project-backend-img:latest
                    docker push shubham2025/project-backend-img:latest
                    docker rmi shubham2025/project-backend-img:latest
                    kubectl apply -f ./deploy/

               '''
            }
        }
    }
}
