#  AWS Serverless Observability Workshop
Link - https://serverless-observability.workshop.aws/en/010_introduction.html

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
