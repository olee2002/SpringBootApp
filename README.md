# DeployADockerContainerToK8s
describe step by step process of deploying a local docker container to kubenetes. Note: the order of each step is important!

Ingress(DNS)/Service(IP address)/Deploy/Pod/Container relationships.
https://www.figma.com/file/1ACJR3D3jvOFs205NTjIK6/K8s-Deployment-relationship?node-id=0%3A1

Summary: Once a locally created container per a docker images is deployed to k8s, k8s created a pod as a deployment.
A service is created with an ip address(load-balancer) by exposing the deployment.
Eventually DNS can be assigned to the service. As a new deployment is being created due to a version update by running another dockerbuild image with a tag (:tag-ex. :v1), the deployment and service will need to be re-created. 

## GCloud Init 
Create an google cloud account with a $300 credit. 
- create a project (Project-ID will be important and will be mentioned below.)
- create a cluster ( run connect script in the console)
- create a spanner instance

setup gcloud

```
gcloud init
```
log in to the google cloud console
```
gcloud auth login
```
if for some reason the above, try below,
```
gcloud auth application-default login
```
list the gcloud information
```
gcloud components list
gcloud config list
```
set project id(this is your APPLICATION ID)
```
gcloud config set project PROJECT_ID
```
connect your cluster to CLI
```
gcloud container clusters get-credentials "react-spring-app --zone us-central1-a --project app-name"
```
" " information above should be your own. Go to the console and open cluster and click connect to get code above!
now gcloud CLI is set up!

## Docker
install docker CLI
```
gradle build
```
build file is ready (app.jar)

Create docker file to the root directory (examples below)
```
FROM openjdk:8-jdk // this will pull image from docker hub (takes some time to download)
COPY build/libs/demo-0.0.1-app.jar /app.jar //(copy local app.jar file to docker container)
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "/app.jar"] //tell docker which file to exec.
```
```
FROM openjdk:8
ENV PORT=8080
EXPOSE 8080
COPY ./build/libs/*.jar /app.jar
CMD exec java ${JAVA_USER_OPTS} -jar /app.jar
```


see running containers in CLI (all containers - docker ps -a)
```
docker ps
```
delete docker containers
```
docker rm containerId
```
start or stop docker containers
```
docker start/stop containerId
```
see docker images
```
docker images
```
delete docker images
```
docker rmi imageId
```

Incase you need to get rid of docker images and containers (but use this carefully!)
```
docker system prune -a
```

## Connect Local Docker to Cloud Repository

"docker build" will grab the local docker file & "-t" will tag and create an image in the cloud repository with the name provided.

Adding tag after the image will differenciate each deployment! GCloud doesn't recognize the update unless the tag is changed.

```
docker build -t gcr.io/Project-ID/ImageName:Tag .
example : docker build -t gcr.io/react-spring-app-252019/my-spring-app:v2 .
```
push the gcr tagged image to cloud with below
(note: make sure push is done before creating deployment, otherwise it will throw an ImagePullOff err!) 
```
docker push gcr.io/Project-ID/ImageName:Tag
```

## GCloud Deployment
Create a deployment by running (name is "react-spring-app" and image is "gcr.io/react-spring-app-252019/my-spring-app")
images don't need to be directly from gcloud. it can be hosted at dockerhub as well.
### Manually creating deployment & service
below will create a deployment(pod) 
```
kubectl run react-spring-app --image=gcr.io/react-spring-app-252019/my-spring-app --port=8080 (creating deployment)
```
service (service is created by exposing a deployment and settig default and target-port, type LoadBalancer automatically assign externalIP to a service)
```
kubectl expose deployment react-spring-app --type=LoadBalancer --port=80 --target-port=8080
```
### creating deployment from deployment. yml
```
kubectl apply -f ./deployment.yml
```
now port-forward the running pod to the port
```
kubectl port-forward react-spring-two-86d56bff9d-prfpp 8080
```
in case, you need to reset kubectl
```
killall kubectl
```

Check the external IP address in GCloud console and should be able to access to the app at this point.

reference:
http://35.226.14.8/
