pipeline {
    agent any
      
        stages {
            
            stage('Build') {
                steps {
                       echo "proxy configuration is $proxy_url"
                       echo "Platform configuration is $platform_url"
                       echo 'Building code.. '
                        sh 'chmod +x gradlew'
                        sh './gradlew build'
                       
                      }

                }
            
            stage('Deploy') {
                steps {
                    withEnv(["HTTPS_PROXY=$proxy_url"]) {
                            echo 'Deploy  to remote repo.  Artifactory or  docker hub. We will not publish it to artifactory/docker hub yet '
                         docker { image 'maven:3-alpine' }    
                          //sh  './gradlew distDocker'
                        
                            //if there is no heroku, we must deploy it on localmachine
                            //publish the docker image
                            //get the docker image
                            //start the docker
                            
                            
                            
                            
                            
                            
                            
                           
                    }  
                      }  
               
                }
            stage ('verifyDeployment') {
                 steps {
                    retry(3) {
                              script {
                                  try {
                                    def response = httpRequest "$platform_url/webTemplate/v1/about"
                                    println("Status: "+response.status)
                                    println("Content: "+response.content)
                                  }
                                  catch (e)
                                  {
                                      println "Sleeping for 30..."
                                      sleep 30
                                      throw e
                                }  
                        
                            }
                    }
                 }
            }
            stage('staging') {
                steps {
                    echo 'Commit to Heroku repo and that will trigger deploy on Heroku '
                    echo 'Run integ tests on staging env.'
                    sh  "./gradlew -b integ/build.gradle -DbaseUrl=$platform_url/webTemplate clean test"
                }
            }

            stage('Production') {
                steps {
                    echo 'Deploy the prod env'
                    input 'Do you want to deploy to the production env?'
                }
            }
        }
}
