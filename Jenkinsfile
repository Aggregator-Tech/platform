pipeline {
    agent any
      
        stages {
            
            stage('Build') {
               steps {
                    withEnv(["HTTPS_PROXY=$proxy_url"]) {
                   //will be build
                     echo "proxy configuration is $proxy_url"
                       echo "Platform configuration is $platform_url"
                       echo 'Building code.. '
                       sh 'chmod +x gradlew'
                       sh './gradlew  -Dhttp.proxyHost=www-proxy.us.oracle.com -Dhttp.proxyPort=80 -Dhttps.proxyHost=www-proxy.us.oracle.com -Dhttps.proxyPort=80  build'
                    }
               }
                }
            
            stage('Deploy') {
                steps {
                    
                        sh 'chmod +x gradlew'
                        sh './gradlew  -Dhttp.proxyHost=www-proxy.us.oracle.com -Dhttp.proxyPort=80 -Dhttps.proxyHost=www-proxy.us.oracle.com -Dhttps.proxyPort=80  distDocker'
                        sh './gradlew  -Dhttp.proxyHost=www-proxy.us.oracle.com -Dhttp.proxyPort=80 -Dhttps.proxyHost=www-proxy.us.oracle.com -Dhttps.proxyPort=80  startDocker'
               }
               
                }
             stage('integrationTests') {
                steps {
                    //will b e builidfingh
                      echo "proxy configuration is $proxy_url"
                       echo "Platform configuration is $platform_url"
                       echo 'Building code.. '
                        sh 'chmod +x gradlew'
                        sh './gradlew  -Dhttp.proxyHost=www-proxy.us.oracle.com -Dhttp.proxyPort=80 -Dhttps.proxyHost=www-proxy.us.oracle.com -Dhttps.proxyPort=80  integrationTest -DbaseUrl=http://localhost:9501/'
                        
               }
               
                }
             stage('publish') {
                steps {
                    //will b e builidfingh
                      echo "proxy configuration is $proxy_url"
                       echo "Platform configuration is $platform_url"
                       echo 'Building code.. '
                        sh 'chmod +x gradlew'
                        sh 'docker login'
                        sh 'docker push aggregatortech/webtemplate'
                        
               }
             }
            
}
}
