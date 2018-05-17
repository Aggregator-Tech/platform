pipeline {
    agent any
      parameters {
        string(name: 'platform_url', defaultValue: 'https://aggregatortech-platform.herokuapp.com', description: 'Platform  url')
        string(name: 'proxy_url' , defaultValue: 'http://www-proxy.us.oracle.com:80', description: 'Proxy')
    }
        stages {
            
            stage('Build') {
                steps {
                       echo "${params.proxy_url} World!"
                        echo 'Building code.. '
                        sh './gradlew build'
                        echo 'running unit tests'
                        sh  './gradlew test'
                      }

                }
            
            stage('Deploy') {
                steps {
                    withEnv(["HTTPS_PROXY='${params.proxy_url}'"]) {
                            echo 'Deploy  to remote repo.  Artifactory or  docker hub. we will not publish it to artifactory/docker hub yet '
                            sh  './gradlew installDist'
                            sh 'rm -rf work-heroku'
                            sh 'mkdir work-heroku'
                            dir('work-heroku') {
                               sh 'sh ../gitclone.sh'
                            }
                            dir('work-heroku/heroku-platform') {
                                 sh 'sh ../../gitcommit.sh'
                            }
                        
                    }  
                      }  
               
                }
            stage ('verify') {
                 steps {
                     waitUntil {
                        sh "timeout 120 wget --retry-connrefused --tries=10 --waitretry=1 -q '${params.platform_url}'/webTemplate -O /dev/null"
}
                 }
                
            }
            stage('staging') {
                steps {
                    echo 'Commit to Heroku repo and that will trigger deploy on Heroku '
                    echo 'Run integ tests on staging env.'
                    sh  "./gradlew -b integ/build.gradle -DbaseUrl='${params.platform_url}'/webTemplate clean test"
                }
            }

            stage('Production') {
                steps {
                    echo 'Deploy the prod env'
                }
            }
        }
}
