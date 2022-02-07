# ECS Pre-Prod Requirements
## Deployment Strategies
![image](https://user-images.githubusercontent.com/32443900/152814619-4daa8b5f-6283-4b0b-bb4d-f31486e6ed29.png)

- Post Certification : 
### Blue - Green Deployments
- Active - Active Deployments
- Multi Region Web App
- Queue Processor
- Protocol based reduduancy 

### Upgrade in Place Deployments
- Its a feature in Clouformation
- Some deployments cannot be deployed in Blue-Green Fashion, for example Subnet 
- Most upgrde in place deployments are zero-downtime deployments
- Resources are either updated or replaced with mini-green blue deployments
- In the event of deployment failure a rollback can be performed.
- for example - Lambda Function 

### Rebuild Deployments
- Code of a batch application is staged into a repository
- Control-M jobs or Java Batch application will run application
- Rollback is that you redeploy the old version

