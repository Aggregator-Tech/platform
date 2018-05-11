pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building..'
                node {
                    scm checkout
                }
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
