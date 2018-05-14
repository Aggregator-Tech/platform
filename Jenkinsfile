pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                echo 'Building code.. '
                sh './gradlew build'
                echo 'running unit tests'
                sh  './gradlew test'
                
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploy to remote repo.   Artifactory or  docker hub. we will not publish it to artifactory/docker hub yet '
                sh  './gradlew installDist'
                
                sshagent (credentials: ['20f5c48b-a570-4e38-84ed-66fdd4b85861']) {
                            
                            sh 'sh gitcommit.sh'
                           
                   
                }
                withEnv(['HTTPS_PROXY=http://www-proxy.us.oracle.com:80']) {
                        
                        sh 'echo $PWD'
                       
                        sshagent (credentials: ['20f5c48b-a570-4e38-84ed-66fdd4b85861']) {
                            
                            sh 'sh gitcommit.sh'
                           
                   
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
