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
Git : https://github.com/arshad849/nagp-assignment
Cloud Build : Builds the code and push the image to google cloud registry.
Kubernetes : service is deployed on kubernetes.


## Setup

###  Set Up Google Cloud

1. **Authenticate with Google Cloud:**

   ```bash
   gcloud auth login
   gcloud config set project <your-project-id>

2. **Create a GKE Cluster**

   ```bash
   gcloud container clusters create nagp-assignment-kubernetes
   gcloud container clusters get-credentials nagp-assignment-kubernetes

3. **Create resources for MySQL**

   ```bash
   kubectl apply -f mysql-config-secret.yaml
   kubectl apply -f mysql-service.yaml
   kubectl apply -f mysql-statefulset.yaml
   
4. **Create resources for spring boot service deployment**
   create a cloud build which will pull the code from github and creates image.
   Image will be pushed to io.gcr
   It will use the cloudbuild.yaml

   ```bash
   kubectl apply -f myapp-config-secret.yaml
   kubectl apply -f myapp-service.yaml
   kubectl apply -f myapp-statefulset.yaml
   
5. **Create HPA and job for the load**

   ```bash
   kubectl apply -f myapp-hpa.yaml
   kubectl apply -f load-job.yaml 
   
Assumption and changes from Assignment
Reduced the replica to 1 instead of 3 due to exceeded quota in free tier gcp account.
If load exceeds the threshold 2 additional pods will be created.
Used dynamic provisioning of pv. Dynamic provisioning with statefulset is standard approach.


   


