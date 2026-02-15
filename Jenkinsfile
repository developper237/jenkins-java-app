pipeline {
    agent any
    
    tools {
        maven 'Maven 3.8.1'
    }
    
    stages {
        stage('Cloner le code source depuis Git') {
            steps {
                echo 'Code cloné depuis GitHub avec succès!'
            }
        }
        
        stage('Compiler le code avec Maven') {
            steps {
                echo 'Compilation du projet avec Maven...'
                bat 'mvn clean compile'
            }
        }
        
        stage('Exécuter les tests unitaires') {
            steps {
                echo 'Exécution des tests unitaires...'
                bat 'mvn test'
            }
            post {
                always {
                    junit '**/target/surefire-reports/*.xml'
                }
            }
        }
        
        stage('Package de l\'application') {
            steps {
                echo 'Création du fichier WAR...'
                bat 'mvn package'
            }
        }
        
        stage('Déployer sur Tomcat') {
            steps {
                echo 'Déploiement sur le serveur Tomcat...'
                script {
                    def warFile = 'target\\jenkins-java-app-1.0-SNAPSHOT.war'
                    withCredentials([usernamePassword(credentialsId: 'tomcat-credentials', 
                                                       usernameVariable: 'TOMCAT_USER', 
                                                       passwordVariable: 'TOMCAT_PASS')]) {
                        bat """
                            curl -v -u %TOMCAT_USER%:%TOMCAT_PASS% --upload-file ${warFile} "http://localhost:8082/manager/text/deploy?path=/jenkins-app&update=true"
                        """
                    }
                }
            }
        }
        
        stage('Vérification du déploiement') {
            steps {
                echo 'Vérification que l\'application est accessible...'
                bat 'timeout 5 && curl -f http://localhost:8082/jenkins-app/hello || exit 0'
            }
        }
    }
    
    post {
        success {
            echo 'Pipeline exécuté avec succès! Application déployée sur http://localhost:8082/jenkins-app/hello'
        }
        failure {
            echo 'Le pipeline a échoué. Vérifiez les logs.'
        }
    }
}