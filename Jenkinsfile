pipeline {
    agent any

    stages {
        stage ('GetProject') {
            steps {
                git 'https://github.com/mbennett7UG/markspetition.git'
            }
        }
        stage ('build') {
            steps {
                sh 'mvn clean:clean'
                sh 'mvn dependency:copy-dependencies'
                sh 'mvn compiler:compile'
            }
        }

        stage ('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage ('Package') {
            steps {
                sh 'mvn package'
            }
        }
       stage ('Deploy')
        {
            steps {
                sh 'docker build -f Dockerfile -t myapp . '
                sh 'docker rm -f "myappcontainer" || true'
                sh 'docker run --name "myappcontainer" -p 9090:8080 --detach myapp:latest'
            }
        }


    }
    post {
        success {
            archiveArtifacts allowEmptyArchive: true,
                artifacts: '**/markspetition.war'
        }
    }
}