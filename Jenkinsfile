node {
     stage('Build') {
        shell "echo 'Building..'"
        checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/Aggregator-Tech/platform.git']]])
        //get the source code, compile, run unit tests and buld artifacts
     }
     stage('Test') {
        shell "echo 'Testing..'"
     }
     stage('Deploy') {
        shell  "echo 'Deploying....'"
     }
}


