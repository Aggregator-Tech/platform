pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building code..'
                sh './gradlew build'
                echo 'running unit tests'
                sh  './gradlew test'
                
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploy to remote repo. Artifactory or docker hub '
                sh  './gradlew installDist'
                
                
                
               
            }
        }
        stage('staging') {
            steps {
                echo 'Run integ tests on staging env.Make a change in heroku repo so that heroku can pick up the change'
                
            }
        }
  
        stage('Production') {
            steps {
                echo 'Deploy the prod env'
            }
        }
    }
}
