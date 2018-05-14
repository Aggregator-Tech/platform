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
                echo 'Deploy to remote repo. Artifactory or docker hub. we will not publish it to artifactory/docker hub yet '
                sh  './gradlew installDist'
                sh 'git clone https://github.com/Aggregator-Tech/heroku-platform'
                sh 'echo $PWD'
                sh 'git commit -m "Adding a change"'
                sh 'git push'
               
         
                
                
               
            }
        }
        stage('staging') {
            steps {
                echo 'Commit to Heroku repo and that will trigger deploy on Heroku '
                
                echo 'Run integ tests on staging env.'
                sh  './gradlew test'
                
                
            }
        }
  
        stage('Production') {
            steps {
                echo 'Deploy the prod env'
            }
        }
    }
}
