pipeline {
    agent any
      
        stages {
            
            stage('Build') {
               steps {
                   //will be builidfingh
                     echo "proxy configuration is $proxy_url"
                       echo "Platform configuration is $platform_url"
                       echo 'Building code.. '
                       sh './gradlew build'
               }
                }
            
            stage('Deploy') {
                steps {
                    //will be builidfingh
                      echo "proxy configuration is $proxy_url"
                       echo "Platform configuration is $platform_url"
                       echo 'Building code.. '
               }
               
                }
            
}
}
