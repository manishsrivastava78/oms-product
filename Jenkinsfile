pipeline {
   		agent { 
            kubernetes{
                label 'jenkins-slave'
        }
     }
      
    environment{
        DOCKER_USERNAME = credentials('DOCKER_USERNAME')
        DOCKER_PASSWORD = credentials('DOCKER_PASSWORD')
    }
    stages {
			stage('Checkout the code') {
				steps{
					sh(script: """
					   git clone https://github.com/manishsrivastava78/oms-inventory.git
					""", returnStdout: true) 
				}
			}
		
		    stage('Build the code') {
				steps {
					sh script: '''
					#!/bin/bash 
					cd $WORKSPACE/oms-inventory/
				    echo $PATH
					mvn --version
					mvn install
					'''
				}
			}
			
			
			stage('docker build') {
				steps{
					sh script: '''
					#!/bin/bash
					cd $WORKSPACE/oms-inventory/
					docker build -t manishsrivastavaggn/oms-inventory:${BUILD_NUMBER} .
					'''
				}
			}
                    
			stage('docker login') {
				steps{
					sh(script: """
						docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD
					""", returnStdout: true) 
				}
			}
        
            stage('Push docker image') {
				steps{
					sh(script: """
						docker push manishsrivastavaggn/oms-inventory:${BUILD_NUMBER}
					""")
				}
			}
        
			stage('Deploy microservice') {
				steps{
					sh script: '''
						#!/bin/bash
						cd $WORKSPACE/oms-inventory/
					#get kubectl for this demo
					curl -LO https://storage.googleapis.com/kubernetes-release/release/$(curl -s https://storage.googleapis.com/kubernetes-release/release/stable.txt)/bin/linux/amd64/kubectl
					chmod +x ./kubectl
					./kubectl apply -f ./configmap.yaml
					./kubectl apply -f ./secret.yaml
					
					cat ./deployment.yaml | sed s/changeMePlease/${BUILD_NUMBER}/g | ./kubectl apply -f -
					 ./kubectl apply -f ./service.yaml
					 ./kubectl apply -f ./ingress.yaml
					'''
				}
			}
        
		
   	}
}
