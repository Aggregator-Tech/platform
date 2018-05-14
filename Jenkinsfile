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
                        
                        sh 'echo $PWD'
                        withCredentials([usernamePassword(credentialsId: 'jenkins-git-cred', passwordVariable: 'GIT_PASSWORD', usernameVariable: 'GIT_USERNAME')]) {
                            sh ('echo ${GIT_USERNAME}')
                            sh ('echo ${GIT_PASSWORD}')
                            sh (' git config --global user.name "atul-aggregatortech"')
                            sh (' git config --global user.email "atul.aggregatortech@gmail.com"')
                            sh ('git clone https://github.com/Aggregator-Tech/heroku-platform')
                            sh ('touch build.gradle')
                            sh ('git add .')
                            sh ('git commit -m "Add change"')
                            sh('git push --force https://atul-aggregatortech:Westworld@1@heroku-platform.git HEAD:master')
                    }
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
