#  AWS Serverless Observability Workshop
Link - https://catalog.us-east-1.prod.workshops.aws/workshops/31676d37-bbe9-4992-9cd1-ceae13c5116c/en-US/  
    -  https://serverless-observability.workshop.aws/en/010_introduction.html  
    
 ![image](https://user-images.githubusercontent.com/32443900/154785135-6f6af5d5-9a78-4818-ba07-4eb6934efe7b.png)

    
## AWS observability functionalities on 
- Amazon CloudWatch : provides native monitoring, logging, alarming, and dashboards
- AWS X-Ray : tracing through 
-  Amazon CloudWatch  and tracing through AWS X-Ray . When deployed together, they provide the 3 pillars (Metric, Logs & Traces) of an observability solution.
- Amazon Managed Service for Prometheus : Highly available, secure, and managed monitoring for your containers
- Amazon Managed Grafana : Scalable, secure, and highly available data visualization for your operational metrics, logs, and traces 
- AWS Distro for OpenTelemetry (ADOT) : a secure, production-ready, AWS-supported distribution of the OpenTelemetry project.

## Three main pillars of Observability
###  Tracing :  
- Understanding the path of data as it propagates through the components of our application.
- Distributed tracing, especially those built using a microservices architecture. 
- A Trace connects all these checkpoints into a complete route that explains how that request was handled across all services from start to end.
- AWS X-Ray, https://docs.aws.amazon.com/xray/latest/devguide/aws-xray.html
### Logging : 
- Centralized logging allows one to store all logs in a single location and in a standardized format, simplifying log analysis and correlation tasks.
- CloudWatch Logs Insights enables you to interactively search and analyze your log data.
- CloudWatch Logs Insights automatically discovers fields in logs from AWS services such as Amazon Route 53, AWS Lambda, AWS CloudTrail, and Amazon VPC, and any application or custom log that emits log events as JSON. 
- Link : https://docs.aws.amazon.com/AmazonCloudWatch/latest/logs/AnalyzingLogData.html

### Metrics : 
- Metrics are data about the performance of your systems.
- By default, several services provide free metrics for resources (such as Amazon EC2 instances, Amazon EBS volumes, and Amazon RDS DB instances).
- You can also enable detailed monitoring for some resources, such as your Amazon EC2 instances, or publish your own application metrics. 
- Amazon CloudWatch can load all the metrics in your account (both AWS resource metrics and application metrics that you provide) for search, graphing, and alarms.
- Metric data is kept for 15 months, enabling you to view both up-to-the-minute data and historical data.
- Amazon CloudWatch metrics Link - https://docs.aws.amazon.com/AmazonCloudWatch/latest/monitoring/working_with_metrics.html 

## CLOUDWATCH METRICS, ALARMS, AND DASHBOARDS
- Application can push custom business and operational metrics to CloudWatch
- Create alarms based on failure metrics to notify our SRE/SysOps engineers 
- consolidate all these metrics in a single dashboard to ease monitoring tasks.

## What are MTTR and MTTI
- Mean Time to Resolve (MTTR) is the average time between the start and resolution of an incident. B
- That’s why Mean Time to Identify (MTTI) is also an important key performance indicator (KPI).
- Reducing MTTR and MTTI is more crucial than ever.
- By monitoring deployments in real time, you can drastically improve MTTI from days to minutes. 
- Using Machine Learning for Better MTTR

---
![image](https://user-images.githubusercontent.com/32443900/154784095-055a0613-b551-4979-9561-d8895f9d8b32.png)
---
![image](https://user-images.githubusercontent.com/32443900/154784134-8bf83d55-71fc-447e-aaf1-fa007ac6d5ff.png)
---
![image](https://user-images.githubusercontent.com/32443900/154784144-80d222e7-d11e-46a4-883a-86361de46ef9.png)
---
![image](https://user-images.githubusercontent.com/32443900/154784219-7d7b51a3-2eb7-4aef-a01d-88a2c5ebf06e.png)
---
![image](https://user-images.githubusercontent.com/32443900/154784799-3b45a958-75b0-4f19-bd7b-48ad403dc5f4.png)
---
![image](https://user-images.githubusercontent.com/32443900/154784827-1927dab2-6fb5-44a8-8c74-590abacbde49.png)
---

