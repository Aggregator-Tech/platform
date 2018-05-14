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
                sh  'rm -rf heroku-platform'
                withEnv(['HTTPS_PROXY=http://www-proxy.us.oracle.com:80']) {
                    sh 'git clone https://github.com/Aggregator-Tech/heroku-platform'
                }
                sh 'echo $PWD'
                sh "git commit -m 'Jenkins change'"
                withCredentials([usernamePassword(credentialsId: 'jenkins-git-cred', passwordVariable: 'GIT_PASSWORD', usernameVariable: 'GIT_USERNAME')]) {
                   
                    sh('git push https://${GIT_USERNAME}:${GIT_PASSWORD}@<REPO> --tags')
                }
                      
                
                
               
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
