pipeline {
    agent any

    environment {
        DOCKER_CREDENTIALS_ID = 'dockerhub-creds' // El ID de las credenciales que agregaste
    }

    stages {
        stage('Build') {
            steps {
                echo 'Building project with Maven'
                sh 'mvn clean package'
            }
        }

        stage('Docker Build and Push') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', DOCKER_CREDENTIALS_ID) {
                        sh 'docker build -t elturcofarid/transaccion_elvale:v1 .'
                        sh 'docker push elturcofarid/transaccion_elvale:v1'
                    }
                }
            }
        }
    }

    post {
        always {
            echo 'Pipeline operation finished.'
        }
        success {
            echo 'Pipeline succeeded!'
        }
        failure {
            echo 'Pipeline failed!'
        }
    }
}
