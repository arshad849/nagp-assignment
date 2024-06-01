# Recording Details and URL.

https://nagarro-my.sharepoint.com/personal/arshad_patel_nagarro_com/_layouts/15/onedrive.aspx?id=%2Fpersonal%2Farshad_patel_nagarro_com%2FDocuments%2FArshadPatel_3210467_Kubernetes%20and%20DevOps%20Advanced&view=0

Note:
1. Skip the video from 33:50 to 36:00. During this period, I was waiting for the quota limit to stabilize.
2. Please consider the quota issue with the free tier account, which requires me to follow a few extra steps.
3. This has made the demo longer.

# Spring Boot Application Deployment with Kubernetes and Google Cloud

## Overview

This project demonstrates deploying a Spring Boot application with a MySQL database on a Kubernetes cluster using Google Cloud. The setup includes a StatefulSet for the MySQL database, a Deployment for the Spring Boot application, and a Horizontal Pod Autoscaler (HPA) for scaling.

## Prerequisites

- Google Cloud Platform (GCP) account
- Google Kubernetes Engine (GKE) cluster
- kubectl command-line tool
- Maven
- Docker
- gcloud command-line tool

## Infrastructure
* Git : https://github.com/arshad849/nagp-assignment
* Cloud Build : Builds the code and push the image to google cloud registry.
* Kubernetes : service is deployed on kubernetes.

## Assumption and changes from Assignment
1. Reduced the replica to 1 instead of 3 due to exceeded quota in free tier gcp account.
2. If load exceeds the threshold 2 additional pods will be created.
3. Used dynamic provisioning of pv. Dynamic provisioning with statefulset is standard approach.
4. A rolling update is demonstrated at the end, after deleting the HPA and deployment to stabilize the quota limits.


## Setup

###  Set Up Google Cloud

1. ## Authenticate with Google Cloud:

   ```bash
   gcloud auth login
   gcloud config set project <your-project-id>

2. ## Create a GKE Cluster

   ```bash
   gcloud container clusters create nagp-assignment-kubernetes
   gcloud container clusters get-credentials nagp-assignment-kubernetes

3. ## Create resources for MySQL

   ```bash
   kubectl apply -f mysql-config-secret.yaml
   kubectl apply -f mysql-service.yaml
   kubectl apply -f mysql-statefulset.yaml
   
4. ## Create resources for spring boot service deployment
   create a cloud build which will pull the code from github and creates image.
   Image will be pushed to io.gcr
   It will use the cloudbuild.yaml

   ```bash
   kubectl apply -f myapp-config-secret.yaml
   kubectl apply -f myapp-service.yaml
   kubectl apply -f myapp-statefulset.yaml
   
5. ## Create HPA and job for the load

   ```bash
   kubectl apply -f myapp-hpa.yaml
   kubectl apply -f load-job.yaml
   
6. ## Delete the job once the load is increased and HPA is tested.

  ```bash
  kubectl delete job load-generator

   


