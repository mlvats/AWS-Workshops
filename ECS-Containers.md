- https://github.com/aws-containers 
- https://docs.aws.amazon.com/AmazonECS/latest/developerguide/Welcome.html
https://user-images.githubusercontent.com/32443900/135767631-831b2b0a-357f-4e81-adfe-6bd0e0fb165c.png">

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




