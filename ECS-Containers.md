- https://github.com/aws-containers 
- https://docs.aws.amazon.com/AmazonECS/latest/developerguide/Welcome.html
https://user-images.githubusercontent.com/32443900/135767631-831b2b0a-357f-4e81-adfe-6bd0e0fb165c.png">

## Workshop Links 
-  https://catalog.us-east-1.prod.workshops.aws/v2/workshops/8c9036a7-7564-434c-b558-3588754e21f5/en-US/  (best one)
-  AWS Containers Immersion Day :  https://catalog.us-east-1.prod.workshops.aws/v2/workshops/ed1a8610-c721-43be-b8e7-0f300f74684e/en-US/  
-  ECS_Fargate_Workshop :  https://catalog.us-east-1.prod.workshops.aws/v2/workshops/1dab7316-6c7c-4a6c-bf76-d6aeeb5ad379/en-US/dev/overview
-  -  https://ecsworkshop.com/introduction/
-  https://workshops.aws/?tag=ECS
-
---

![image ](https://user-images.githubusercontent.com/32443900/148304204-48903916-4949-4f6f-a144-9f3f24a3a958.png)
![image ](https://user-images.githubusercontent.com/32443900/148304236-cebfc499-8343-41e6-9f35-76a64a99c25b.png)

----


## Features of Amazon ECS
- Amazon ECS is a regional service 
- We can create Amazon ECS clusters within a new or existing VPC. 
- After a cluster is up and running, you can create task definitions
- Task Definitions:  define which container images run across your clusters. 
- Task definitions are used to run tasks or create services. 
- Container images are stored in and pulled from container registries.

![image](https://user-images.githubusercontent.com/32443900/148304736-e833abfc-c2e0-4430-9efd-1fd392a83350.png)

## Task definitions
- To prepare your application to run on Amazon ECS, you create a task definition. 
- The task definition is a text file, in JSON format, that describes one or more containers, up to a maximum of ten, that form your application.
- You can define multiple containers in a task definition. 
- The parameters that you use depend on the launch type you choose for the task
- Link - https://docs.aws.amazon.com/AmazonECS/latest/developerguide/task_definitions.html
- https://docs.aws.amazon.com/AmazonECS/latest/developerguide/Welcome.html  
- Sample example of a task definition that specifies the use of Fargate to launch a single container that runs an NGINX web server.
``
{
    "family": "webserver",
    "containerDefinitions": [
        {
            "name": "web",
            "image": "nginx",
            "memory": "100",
            "cpu": "99"
        },
    ],
    "requiresCompatibilities": [
        "FARGATE"
    ],
    "networkMode": "awsvpc",
    "memory": "512",
    "cpu": "256",
}
``
![image](https://user-images.githubusercontent.com/32443900/148308155-24d370ed-36f9-4fef-8d15-1472ac40e885.png)
![image](https://user-images.githubusercontent.com/32443900/148308236-d68b6ce1-0ed3-43e0-bb51-c8a6ac131c8f.png)
![image](https://user-images.githubusercontent.com/32443900/148308302-fa4a7025-8953-49bb-b110-f669164dd793.png)
![image](https://user-images.githubusercontent.com/32443900/148308401-1bdd2a98-1709-4381-be56-1ad21e8e48fe.png)


## Task 
- Optimal for Batch Jobs
- A task is the instantiation of a task definition within a cluster.
- After you have created a task definition for your application within Amazon ECS, you can specify the number of tasks that will run on your cluster.
- Each task that uses the Fargate launch type has its own isolation boundary and does not share the underlying kernel, CPU resources, memory resources, or elastic network interface with another task.

## Service
- https://docs.aws.amazon.com/AmazonECS/latest/developerguide/ecs_services.html
- Tasks may run standalone  or part of a service
- A Service is an abstraction on top of a task
- An Amazon ECS service allows you to run and maintain a specified number of instances of a task definition simultaneously in an Amazon ECS cluster. 
- A Service runs a specified number of tasks and can include a load balancer to distribute traffice across the tasks that are associated with the service
- If any of your tasks should fail or stop for any reason, the Amazon ECS service scheduler launches another instance of your task definition to replace it in order to maintain the desired number of tasks in the service.
- In addition to maintaining the desired number of tasks in your service, you can optionally run your service behind a load balancer. The load balancer distributes traffic across the tasks that are associated with the service.
- You can optionally specify a deployment configuration for your service
- You can optionally configure your service to use Amazon ECS service discovery. Service discovery uses Amazon Route 53 auto naming APIs to manage DNS entries for your service's tasks, making them discoverable within your VPC. 
- 

## Service scheduler concepts
- The service scheduler is ideally suited for long running stateless services and applications. 
- Task placement strategies and constraints can be used to customize how the scheduler places and terminates tasks.
- There are two service scheduler strategies available: REPLICA and DAEMON
- REPLICA : The replica scheduling strategy places and maintains the desired number of tasks in your cluster.
- DAEMON : The daemon scheduling strategy deploys exactly one task on each active container instance 

![image](https://user-images.githubusercontent.com/32443900/148308744-43c74183-f18d-4b38-bb14-aa4288a5028a.png)

## ECS Integration with Other AWS Services

![image](https://user-images.githubusercontent.com/32443900/148308954-bfab04ab-b8f5-4b87-abfd-ea1ffceb930a.png)


## ECS Integration with Other AWS Services : Microservices Architecture for Integration
![image](https://user-images.githubusercontent.com/32443900/148309114-12a72403-2919-461c-8597-8b67f35e3420.png)

## ECS Integration with Other AWS Services :  With ECS Service Discovery

![image](https://user-images.githubusercontent.com/32443900/148309215-41bfbd7a-c85a-41fa-b462-3bb693ab2f8c.png)


## ECS Integration with Other AWS Services :  CD

![image](https://user-images.githubusercontent.com/32443900/148309342-fed70f65-2186-404b-9565-43c3df77e845.png)

## ECS Integration with Other AWS Services :  Blue/Green Deployment

![image](https://user-images.githubusercontent.com/32443900/148309401-cb5cc4e5-40e7-4be8-a253-550e16c29113.png)
----

![image](https://user-images.githubusercontent.com/32443900/148309451-590e8c65-fd66-4456-b8e3-6adad0f25f91.png)

## Access Management : IAM Roles
- Each Task can have its own IAM Role, Providing Granular permission for service access
![image](https://user-images.githubusercontent.com/32443900/148309781-31409cec-0b0d-446f-9c0d-b13a400f5dad.png)

## Managing Secrets
- Attach IAM Policy to a Role 
- and then Role to a Task

![image](https://user-images.githubusercontent.com/32443900/148310066-0cb2c906-6596-4f5c-8515-49473d6e8db5.png)











