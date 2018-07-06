pipeline {
    agent any
      
        stages {
            
            stage('Build') {
               steps {
                    withEnv(["HTTPS_PROXY=$proxy_url"]) {
                   //will be build
                       echo 'Building code.. '
                       sh 'chmod +x gradlew'
                       sh './gradlew  -Dhttp.proxyHost=www-proxy.us.oracle.com -Dhttp.proxyPort=80 -Dhttps.proxyHost=www-proxy.us.oracle.com -Dhttps.proxyPort=80  build'
                    }
               }
                }
            
            stage('Deploy') {
                steps {
                        echo 'Creating docker and deploying docker images locally '
                        sh 'chmod +x gradlew'
                        sh './gradlew  -Dhttp.proxyHost=www-proxy.us.oracle.com -Dhttp.proxyPort=80 -Dhttps.proxyHost=www-proxy.us.oracle.com -Dhttps.proxyPort=80  distDocker'
                        sh './gradlew  -Dhttp.proxyHost=www-proxy.us.oracle.com -Dhttp.proxyPort=80 -Dhttps.proxyHost=www-proxy.us.oracle.com -Dhttps.proxyPort=80  startDocker'
               }
               
                }
             stage('integrationTests') {
                steps {
                    //will b e builidfingh
                    
                       echo 'running integ tests.. '
                        sh 'chmod +x gradlew'
                        sh './gradlew  -Dhttp.proxyHost=www-proxy.us.oracle.com -Dhttp.proxyPort=80 -Dhttps.proxyHost=www-proxy.us.oracle.com -Dhttps.proxyPort=80  integrationTest -DbaseUrl=http://localhost:9501/'
                        
               }
               
                }
             stage('publish') {
                steps {
                    //will b e builidfingh
                     
                       echo 'Publish to repository'
                        sh 'chmod +x gradlew'
                        sh './gradlew  publish'
                       
                        
               }
             }
            
}
}
