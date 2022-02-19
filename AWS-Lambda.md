# AWS Lambda
Link -  https://docs.aws.amazon.com/lambda/latest/dg/welcome.html

## Lambda features
- Concurrency and scaling controls
- Functions defined as container images
- Code signing
- Lambda extensions : You can use Lambda extensions to augment your Lambda functions. For example, use extensions to more easily integrate Lambda with your favorite tools for monitoring, observability, security, and governance.
- Database access : A database proxy manages a pool of database connections and relays queries from a function.
- File systems access : You can configure a function to mount an Amazon Elastic File System (Amazon EFS) file system to a local directory. 
---
## The Lambda function is the foundational principle of Lambda. 
- Lambda runs instances of your function to process events. 
### Function : 
  - A function is a resource that you can invoke to run your code in Lambda. 
  - A function has code to process the events that you pass into the function or that other AWS services send to the function.
  
### Trigger : 
  - A trigger is a resource or configuration that invokes a Lambda function.  
  
### Event : 
- An event is a JSON-formatted document that contains data for a Lambda function to process. 
- When you invoke a function, you determine the structure and contents of the event. 
  
### Execution environment :   
- An execution environment provides a secure and isolated runtime environment for your Lambda function. 
-   
### Layer :
 - A Lambda layer is a .zip file archive that can contain additional code or other content.  
-  A layer can contain libraries, a custom runtime, data, or configuration files.
  
### Extension :
  - Lambda extensions enable you to augment your functions. 
  - For example, you can use extensions to integrate your functions with your preferred monitoring, observability, security, and governance tools. 
  
### Concurrency :
- Concurrency is the number of requests that your function is serving at any given time.
- When your function is invoked, Lambda provisions an instance of it to process the event. 
- Concurrency is subject to quotas at the AWS Region level.  
 
### Qualifier :
- When you invoke or view a function, you can include a qualifier to specify a version or alias. 
- A version is an immutable snapshot of a function's code and configuration that has a numerical qualifier.
- or example, my-function:1  and my-function:BLUE.
  
## VPC networking for Lambda
  - A Lambda function always runs inside a VPC owned by the Lambda service. 
  - Lambda applies network access and security rules to this VPC and Lambda maintains and monitors the VPC automatically. 
  - Lambda provides managed resources named Hyperplane ENIs, which your Lambda function uses to connect from the Lambda VPC to an ENI (Elastic network interface) in your account VPC.

## AWS Lambda execution environment
   
  
  
  
  
  
  
