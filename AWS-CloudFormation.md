# What is CloudFormation

- Link :  https://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/template-anatomy.html
- https://github.com/awslabs/aws-cloudformation-templates
- https://github.com/ACloudGuru-Resources/course-mastering-aws-cloudformation
     ![image](https://user-images.githubusercontent.com/32443900/135504534-614dbde4-6d78-4578-b798-cddfe66a82ee.png)

## intrinsic functions:
- A special action in a AWS CloudFormation template that assigns values to properties not available until runtime. 
![image](https://user-images.githubusercontent.com/32443900/135505647-d0d45817-a505-419d-874b-e246d6437da2.png)
![image](https://user-images.githubusercontent.com/32443900/135505794-04bf4b3e-4faf-4199-a8f6-86bf55f87c5f.png)

#### Join 
- `  Value: !Join [ " ", [ EC2, Instance, with, Fn, Join ] ]` = EC2, Instance, with, Fn, Join 
- `  Value: !Join [ ":", [ a, b, c ] ]` = a:b:c 
- 
#### Fn::GetAtt 
- The Fn::GetAtt intrinsic function returns the value of an attribute from a resource in the template.
- Syntax for the full function name: `Fn::GetAtt: [ logicalNameOfResource, attributeName ]`
```
Outputs:
  ServerDns:
    Value: !GetAtt
      - Ec2Instance
      - PublicDnsName
```
#### Fn::Sub
- The intrinsic function Fn::Sub substitutes variables in an input string with values that you specify. 
- In your templates, you can use this function to construct commands or outputs that include values that aren't available until you create or update a stack.
```
YAML
Syntax for the full function name:

Fn::Sub:
  - String
  - Var1Name: Var1Value
    Var2Name: Var2Value
    
Syntax for the short form:

!Sub
  - String
  - Var1Name: Var1Value
    Var2Name: Var2Value
```

```
UserData:
   'Fn::Base64': 
     !Sub |
       #!/bin/bash -xe            
       # Ensure AWS CFN Bootstrap is the latest
       yum install -y aws-cfn-bootstrap
       # Install the files and packages from the metadata
       /opt/aws/bin/cfn-init -v --stack ${AWS::StackName} --resource EC2Instance  --region ${AWS::Region}

```

#### Condition functions
- You can use intrinsic functions, such as Fn::If, Fn::Equals, and Fn::Not, to conditionally create stack resources.
- https://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/intrinsic-function-reference-conditions.html


## Pseudo parameters

![image](https://user-images.githubusercontent.com/32443900/135512224-64186384-e66a-42ef-99dd-75e4d5e5f038.png)

- https://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/pseudo-parameter-reference.html
- Pseudo parameters are parameters that are predefined by AWS CloudFormation.
- Example : The following snippet assigns the value of the AWS::Region pseudo parameter to an output value.
- Json and yaml format
```
"Outputs" : {
   "MyStacksRegion" : { "Value" : { "Ref" : "AWS::Region" } }
}

Outputs:
  MyStacksRegion:
    Value: !Ref "AWS::Region"

```

- other examples - AWS::AccountId, AWS::NotificationARNs, AWS::NoValue, AWS::StackId, AWS::StackName etc

## Mappings
- The optional Mappings section matches a key to a corresponding set of named values. 
- https://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/mappings-section-structure.html
- 
![image](https://user-images.githubusercontent.com/32443900/135524741-eb5aff3d-337d-4606-bb01-b8a7f1e53c0c.png)

![image](https://user-images.githubusercontent.com/32443900/135524823-5d717613-dab4-40b3-888b-7614b3e71a32.png)

```
Resources:
  Ec2Instance:
    Type: 'AWS::EC2::Instance'
    Properties:
      InstanceType: t2.micro
      ImageId:
        Fn::FindInMap:
        - RegionMap
        - !Ref AWS::Region
        - AMI
```
## Input Parameters
- Helps to reuse same template for several accounts or environment.
- https://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/parameters-section-structure.html
![image](https://user-images.githubusercontent.com/32443900/135526212-ef4e2a04-eeed-471d-9958-79357e567e49.png)
- Parameter is refered with Intrinsic Function "Ref"
![image](https://user-images.githubusercontent.com/32443900/135526401-f2276acc-b11f-4e43-afda-256316bd6e3d.png)

- Parameter defined in the Template
```
Parameters:
  NameOfService:
    Description: "The name of the service this stack is to be used for."
    Type: String
  KeyName:
    Description: Name of an existing EC2 KeyPair to enable SSH access into the server
    Type: AWS::EC2::KeyPair::KeyName
```
-   Type: AWS::EC2::KeyPair::KeyName --> this needs to be defined @ EC2 service, 
- Parameters defined in the template are called in the blow tag 

```
Tags:
     - Key: "Name"
     Value: !Ref NameOfService
KeyName: !Ref KeyName
```

## Outputs
- The optional Outputs section declares output values that you can import into other stacks (to create cross-stack references)
- https://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/outputs-section-structure.html
![image](https://user-images.githubusercontent.com/32443900/135527339-5516908e-9241-475f-88c8-1cb50a9f6bf2.png)

![image](https://user-images.githubusercontent.com/32443900/135527418-7efbe96e-c6de-403f-83b8-c9fba1a188b3.png)

- !GetAtt  --> Get Attribute Intrinsic Function.
```
Outputs:
  ServerDns:
    Value: !GetAtt
      - Ec2Instance
      - PublicDnsName
```

## CloudFormation helper scripts
- CloudFormation helper scripts reference : https://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/cfn-helper-scripts-reference.html
- By default, helper scripts don't require credentials, so you don't need to use the --access-key, --secret-key, --role, or --credential-file options.

![image](https://user-images.githubusercontent.com/32443900/135691558-e2ecbd0d-2d32-4a71-98ad-41eaa259c378.png)

- 4 Cloud Formation Helpper Script by AWS

![image](https://user-images.githubusercontent.com/32443900/135691599-9f5deb01-cf43-41a9-9f1b-b37452d1e00c.png)



![image](https://user-images.githubusercontent.com/32443900/135691794-dbbeb877-6cad-441f-8ebb-2953d804094b.png)

- ConfigSets installs in order as specified.  It helps to execute the set up.
- 
![image](https://user-images.githubusercontent.com/32443900/135691873-54908bff-961e-456b-aa7d-539dc41b7145.png)

![image](https://user-images.githubusercontent.com/32443900/135691926-66116982-9546-4655-9941-b6fdb7d3a560.png)

![image](https://user-images.githubusercontent.com/32443900/135691950-577c548d-2dac-431b-9f4e-7e4765e0d3e2.png)

![image](https://user-images.githubusercontent.com/32443900/135691979-e2a0a2f8-d4d8-481d-9a88-7b25c8a32307.png)

```


Resources:
  EC2Instance:
    Type: AWS::EC2::Instance
    Metadata: 
      AWS::CloudFormation::Init:
        config: 
          packages: 
            yum:
              httpd: []
              php: []
          files: 
            /var/www/html/index.php:
              content: !Sub |
                <?php print "Hello world Abs was here!"; ?>
          services: 
            sysvinit:
              httpd:
                enabled: true
                ensureRunning: true
                
UserData:
   'Fn::Base64': 
     !Sub |
       #!/bin/bash -xe            
       # Ensure AWS CFN Bootstrap is the latest
       yum install -y aws-cfn-bootstrap
       # Install the files and packages from the metadata
       /opt/aws/bin/cfn-init -v --stack ${AWS::StackName} --resource EC2Instance  --region ${AWS::Region}
```

## AWS resource and property types reference
- https://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/aws-template-resource-type-ref.html
- `service-provider::service-name::data-type-name`
```
Type: AWS::DynamoDB::Table
Properties: 
  AttributeDefinitions: 
    - AttributeDefinition
  BillingMode: String
  ContributorInsightsSpecification: 
    ContributorInsightsSpecification
  GlobalSecondaryIndexes: 
    - GlobalSecondaryIndex
```

## Update behaviors of stack resources
- https://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/using-cfn-updating-stacks-update-behaviors.html
- Update with No Interruption  
     AWS CloudFormation updates the resource without disrupting operation of that resource and without changing the resource's physical ID. For example, if you update certain properties on an AWS::CloudTrail::Trail resource, AWS CloudFormation updates the trail without disruption.

- Updates with Some Interruption  
     AWS CloudFormation updates the resource with some interruption. For example, if you update certain properties on an AWS::EC2::Instance resource, the instance might have some interruption while AWS CloudFormation and Amazon EC2 reconfigure the instance.

-- To overcome limits, we can use nested Stacks
1. Resource Limit -  Seperate one Templates into many and use nested Stacks
2. Stacks Limit - Softlimit, reachout to AWS to increase it
3. Parameters - to overcome this limit, use mappings and list in the parameters
4. Ouputs - 

![image](https://user-images.githubusercontent.com/32443900/135697234-2d11636f-c5bd-40c0-a6e8-5cf12fc3a4e2.png)


----------------------------

# A Cloud Guru Course: Mastering CloudFormation
# acg-mastering-cloudformation

## Chapter 1 - Intro and Theory
*Everything you need to set you up for a successful course. We'll quickly cover some tips when working with the AWS CLI. We'll then cover a brief review of the CloudFormation template anatomy including metadata, intrinsic functions, as well as exporting and importing values between templates.*

```
[cloudshell-user@ip-10-1-70-131 ~]$ aws sts get-caller-identity
{
    "UserId": "AIDATEMSIOJDXGYAERN6C",
    "Account": "215591973447",
    "Arn": "arn:aws:iam::215591973447:user/cloud_user"
}

aws configure list
aws configure list-profiles
aws configure --profile moti

```

### Ch01_L01 – Introduction
*Take a high-level look at what this course has to offer.*

### Ch01_L02 – A Quick Refresher
*A quick refresher on AWS CLI, CloudFormation fundenmentals, and navigating the AWS CloudFormation Docs.*

1. DEMO: Working with the AWS CLI
2. How CloudFormation works
3. How to efficiently search and read the docs
   - Properties
   - Return Values (Ref, Fn::GetAtt)
4. DEMO: Finding Resource documentation

#### Links:
- [Installing AWS CLI](https://amzn.to/3gIOw0h)
- [VSCode Ext: google-search](https://bit.ly/2XQC71J) 


### Ch01_L03 – Template Anatomy
*A complete look at CloudFormation's template anatomy, with Parameters/Types, Mappings, Conditions, Metadata, Resources, Outputs and more.*

1. Parameters
   - [Constraints](http://bit.ly/2YyuvjE)
   - AWS-Specific Parameter Types
2. Mappings
3. Conditions
4. Metadata
5. Resources
6. Outputs

#### Links:
- [CloudFormation Template Anatomy](https://amzn.to/2ZXP1hb)
- [SSM Parameter Types](https://amzn.to/2YCgtg5)
- [AWS-Specific Parameter Types](https://amzn.to/2YCgsc1)
- [Using AWS-Specific Parameter Types](https://go.aws/3eH6DSM)

### Ch01_L04 – Template Operations
*A detailed look at intrinsic functions as well as a deep dive on Exporting and Importing values between templates.*

1. Intrinsic Functions
   - Ref, GetAtt
2. Export/Imports Outputs

#### Links:
- [Intrinsic Function Reference](https://amzn.to/3gJmnGz)
- [Pseudo Parameter Reference](https://amzn.to/39dPAVT)
- [Fn::ImportValue](https://amzn.to/2BpHPAj)
- [X-Reference CloudFormation Outputs](https://amzn.to/32CvJNG)

### Ch01_L05 – Tips & Watchouts
*A list of the most common CloudFormation pitfalls as well how to best setup your code editor, when working with CloudFormation.*

1. Trick-out your IDE
2. Lambda@Edge Deletion Times
3. CloudFront Propagation Times
4. Stack Creation Manual Steps
5. Renaming Things
6. Stateful Resources and Updates / Deletes
7. [Limits](https://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/cloudformation-limits.html)

#### Links:
- [VSCode Ext: vscode-yaml](https://bit.ly/2I6hU0v)
- [VSCode Ext: vscode-cfn-lint](https://bit.ly/2weofV0)
- [VSCode Ext: json2yaml](https://bit.ly/2IdykEd)
- [VSCode Ext: sort-lines](https://bit.ly/397Ln66)
- [VSCode Ext: cform](https://bit.ly/2Tc5vyq)
- [CloudFront Propagation Times](https://go.aws/2ZWD8bc)
- [Deleting Lambda@Edge Functions and Replicas](https://amzn.to/2YCF5Gs)
- [Verify Domains for SES using Custom Resources](https://bit.ly/38aD0p2)
- [Moving and Renaming Resources](https://bit.ly/3eAQYEc)
- [CloudFormation Limits](https://amzn.to/2YA8eSy)

## Chapter 2 - Custom Resources
*Learn to create, deploy and implement custom resources that can help extend CloudFormation way beyond just native resources.*

**Demo Description:** Create and use a custom resource that provisions unique subdomains and routing based on application version.
Eg. feat-blue--projectx.domain.com

### Ch02_L01 – Overview
*A birds-eye view of Custom Resources; what they are and various use cases.*

1. What they are
   - Lambda: Create, Update, Delete
   - Return & Fn::GetAtt
2. Features & Use Cases
3. Limits

#### Links:
- [Extend CloudFormation with Custom Resources](https://bit.ly/3gLtwpK)
- [cfn-response Module](https://amzn.to/2NT6hjn)
- [Custom Resource Limits](https://amzn.to/2YA8eSy)
- [Avoid Two Hour Exception Timeout](https://bit.ly/3gNM8Wq)
- [AWS::CloudFormation::CustomResource](https://amzn.to/2ZXqGIh)

### Ch02_L02 – What We Are Building 
*A detailed walk-through of the custom resource you will be building; as well as calling out a few custom resource helper libraries.*

1. Important Notes
   - Timeouts & Catching Errors
   - How CloudFormation identifies and replaces resources
   - Design functions for idempotency
2. Helper Libraries
3. Diagram: What We Are Building

#### Links:
- [custom-resource-helper](http://bit.ly/2NTFuTV)
- [cfn-lambda](http://bit.ly/2NQRipU)
- [cfn-wrapper-python](http://bit.ly/2NQS8my)
- [cfn-custom-resource](http://bit.ly/2NQmSEh)

### Ch02_L03 – Let's Make one
*A guided jounery in building your own custom resource, and how to deploy it.*

1. DEMO: Create/Deploy Custom Resource
2. DEMO: Review Custom Resource Function
3. DEMO: Review Exports in Console

### Ch02_L04 – Let's Use It
*A complete guide to implementing and using your new custom resource*

1. Using in another Template
2. DEMO: Create, Update and Delete
3. DEMO: Cleanup

#### Links:
- [X-Reference CloudFormation Outputs](https://amzn.to/32CvJNG)

## Chapter 3 - Macros & Transforms
*Elevate your template functionality with Macros and Transforms. Learn to create and use custom template functions.*

### Ch03_L01 – Overview
*A comprehensive look at Macros & Transforms; along with various use cases and limits.*

1. What they are
2. Snippet vs Template-Level
3. Features & Use Cases
4. Limits

#### Links:
- [Template-Level Macros](https://amzn.to/36TkqCI)
- [CommonTags](https://bit.ly/3ajCC9j)
- [Macro Examples](https://bit.ly/2KLRBke)
- [AWS SAM](https://bit.ly/36Skumz)

### Ch03_L02 – Macro: String Operations
*A hands-on lab where you will be building a deploying a Macro that is able to perform string manipulations in your templates.*

1. What We're Building
  - String Operations (Capitalize, Replace, Max Length)
2. DEMO: Create/Deploy String Operations Macro
  - Show CFN console view processed template http://bit.ly/32GrwIn
3. DEMO: Use Macro

### Ch03_L03 – Macro: Common Tags
*A hands-on lab where you will be building a deploying a Macro that cleanly provides a way to globally tag all the resources at once.*

1. What We're Building
2. DEMO: Create/Deploy CommonTags Macro
3. DEMO: Use Macro

### Ch03_L04 – Macro: Custom Resource Types
*A hands-on lab where you will build & deploy a Macro that abstracts away your custom resource, making it appear as though it's a native resource type.*

1. What We're Building
2. DEMO: Create/Deploy S3Objects Macro
3. DEMO: Use Macro

### Ch03_L05 – Unit Testing
*Learn to debug and test your Lambda's locally, or as part of a deployment step.*

1. Unit Testing
2. DEMO: Setting up Macro Unit Tests
  - validate-template

## Chapter 4 – Best Practices
*A focused look at the most impactful CloudFormation features, workflows and best practices for organizing, securing and managing your templates and stacks.*

### Ch04_L01-L02 - Nested Stacks
*An advanced exploration of Nested Stacks; how they work, use cases, features and a detailed hands-on demo.*

1. What are they?
3. Features & Benefits
2. Use Case
3. DEMO - Let's Build One
4. Passing Params to/from Parent and Child Stacks 
4. Recover a nested stacks hierarchy with ResourcesToSkip https://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/using-cfn-updating-stacks-continueupdaterollback.html#nested-stacks
5. Clean up

#### Links:
- [Nested Stacks](https://amzn.to/36PF9Y8)
- [ContinueUpdateRollback](https://amzn.to/2wSEmrx)
- [Recovering AWS CloudFormation stacks using ContinueUpdateRollback](https://go.aws/2x1Y3NN)
- [Using ResourcesToSkip to recover a nested stacks hierarchy](https://amzn.to/3cpiHX8)

### Ch04_L03 - Working with Secrets
*An extensive look at safe-guarding your secrets when working with CloudFormation without compromising on workflow or security.*

1. Overview
2. SSM vs Secrets Manager
2. Intro to KMS
4. Bundling Secrets
3. Encrypting in CLI
4. Decrypting in Lambda
5. Clean up

#### Links:
- [AWS Secrets Manager: Store, Distribute, and Rotate Credentials Securely](https://go.aws/2PD8jTg)
- [Rotating Your AWS Secrets Manager Secrets](https://amzn.to/2AwXEVa)
- [AWS Secrets Manager Pricing](https://go.aws/2MjfcXs)
- [Using dynamic references to specify template values](https://bit.ly/2yRjZfI)

### Ch04_L04 - Template Strategies
*A detailed examination of various techniques, workflows and tools for validating, cross-referencing, and orginizing your templates.*

1. Reuse & Stack Separation
3. Organize Stacks By Lifecycle and Ownership
4. Nested vs Exports vs AWS::Include
5. Validate Templates before deploying
  - https://github.com/aws-cloudformation/cfn-python-lint#basic-usage
  - https://github.com/ScottBrenner/cfn-lint-action

### Ch04_L05 - Template Storage and Revisions
*A practical look at a variety of approaches to automating the tasks of versioning, linting, packaging, storing and continuously deploying your templates.*

1. Versioning
2. Linting
3. Packaging
4. Storing
5. Automated CI/CD Pipeline

## Chapter 5 - Mastering Stacks
*A comprehensive review of some of the lesser known, but extremely powerful CloudFormation features.*

### Ch05_L01 - Service Roles
*A guided investigation of service roles; what they are and the granular control over stacks they provide.*

1. Why are they needed?
2. DEMO: User & Role Setup
3. DEMO: Stack Deploy & Update
4. Cleanup

### Ch05_L02 – Change Sets
*An exploration of Change Sets; what they are, some powerful use cases along with a hands-on demo on how to take full advantage of them when you deploy.*

1. What are they
2. Use Case
3. Stack Drift
4. DEMO - Let's use one
5. Cleanup

### Ch05_L03-L04 – StackSets
*An in-depth look at stack sets; some of their most important benefits and limitations as well as a hands-on demo of how they can help you master mult-region and account deploys.*

1. StackSet Concepts
2. Features & Benefits
3. Limitations
4. Granting permissions for Stack Set operations
5. Configuring a target account gate
6. DEMO - Deploying with StackSets
7. Cleanup

#### Links:
- [Prerequisites for Stack Set Operations](https://amzn.to/2VyDDWU)

### Ch05_L05 – Stack Policies
*Learn how to completely protect your stack resources, with ease using stack policies.*

1. What are they?
2. DEMO: Using Stack Policies
3. Cleanup

#### Links:
- [update-termination-protection](https://amzn.to/2TrEQwx)

## Chapter 6 - Working with EC2 Instances (eg. GhostCMS)
*Learn how to provision your EC2 instances complete with all required services, files, users, and groups all with native CloudFormation.*

**Demo Description:** Build and deploy a Ghost Blogging CMS hosted on EC2. Learn how to provision the instance for required packages with CloudFormation.

### Ch06_L01 – CloudFormationInit
*Learn about CloudFormationInit and how it can orgistrate your EC2 application provisioning.*

1. [UserData (Procedural)](https://acloud.guru/course/aws-advanced-cloudformation/learn/d8067ef9-7840-7c93-e19e-6e1d9a52d756/e72173eb-7bbe-7db7-0d7c-eb486ba6e6f6/watch?backUrl=~2Fcourses) vs CloudFormationInit
2. How it Works
3. Provisioning Workflow

### Ch06_L02 - ConfigSets
*Explore ConfigSets and how they offer declaritive control over the services, files, users, and groups that get installed on your EC2 instances*

1. Overview
2. packages
3. groups
4. users
5. sources
6. files
7. commands
8. services

### Ch06_L03 – Resource Policies
*Learn how to orgastrate the provisioning, updating and deleting of your instances with resource policies and cfn-signal.*

1. What are they?
2. [Creation Policy](https://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/aws-attribute-creationpolicy.html)
2. [Update Policy](https://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/aws-attribute-updatepolicy.html)
2. [Deletion Policy](https://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/aws-attribute-deletionpolicy.html)
3. cfn-signal

#### Links:
- [Signaling AWS CloudFormation WaitConditions using AWS PrivateLink](https://go.aws/3cglkf7)


### Ch06_L04 – cfn-hup
*Learn how to use cfn-up to keep you EC2 instances in sync with changes to your templates.*

1. How it works
2. Configuring
3. DEMO

## Chapter 7 - Working with Serverless
*Learn advanced techniques and workflows when working with CloudFormation and serverless; along with solutions to common challenges.*

### Ch07_L01-L03 – AWS Serverless Application Repository
*A detailed look at AWS Serverless Application Repository; what it is and how to use it as your team's extensive infrastructure rolodex.*

1. What is AWS SAR?
2. Searching for Apps
3. Publishing Apps
4. Using Apps

#### Links:
- [AWS Serverless Application Repository Resource-Based Policy Examples](https://amzn.to/2yYfjoy)


### Ch07_L03-L04 – JAMStack Deployment (Voting App)
*Learn how to automate the deployment of a serverless real-time voting application; as well as solutions to related CloudFormation challenges.*

1. What we’re going to build
2. Review Template & Code
3. Outputs injection
3. Deleting S3 assets as part of stack delete

#### Links:
- [JAMStack](https://bit.ly/3ck1vlH)
- [JAMStack Resources](https://bit.ly/2MlWG0I)

## Chapter 8 - Putting it all together (Self Service Portal)
*Learn how you can programmatically explore and control CloudFormation in a custom built Cloud Portal complete with Github Repository and Actions Integrations.*

### Ch08_L01 – Programmatic CloudFormation
*A discussion of common uses for programmic control of CloudFormation. A detailed look at the Cloud Portal application you'll be deploying as well as a step-by-step deployment walk-through.*

1. Use Cases
2. Cloud Portal Intro
3. Deployment

### Ch08_L02 – Portal Code Walk-Through
*A review of the Cloud Portal functionality followed by a comprehesive code walk-through to see what makes it tick.*

1. Cloud Portal Exploration
2. Code Walk-Through

### Ch08_L03 - Complete Course Clean-up
*A full and complete walk-through and tear down of all stacks, keys, configs, ssm params, roles etc that we're created for this course.*

1. Clean up

#### Links:
- [delete_all_awslogs.sh.md](https://bit.ly/2MkAh3Z)

## Chapter 9 - Other Tools
*Simplfiy your CloudFormation workflow with a  an industry CLI tool. We’ll briefly review and compare a number of industry tool options. Additionally, we'll also be taking a quick look at the CloudFormation Registry and CLI.*

### Ch9_L01 – Frameworks
*A high-level look at The Serverless Framework, AWS SAM, Troposphere and AWS CDK; compairing their feature sets and workflows.*

1. Troposphere
2. The Serverless Framework
3. AWS SAM
4. AWS CDK

#### Links:
- [Troposphere](https://bit.ly/2Miyr3p)
- [The Serverless Framework](https://bit.ly/2XlLJ5D)
- [Repo: The Serverless Framework](https://bit.ly/2XnSUu3)
- [AWS SAM: Serverless Application Model](https://go.aws/2vEzDd0)
- [Repo: AWS SAM](https://bit.ly/2AwHjzM)
- [AWS CDK: Cloud Development Kit](https://go.aws/2yUO1iT)
- [Repo: AWS CDK](https://bit.ly/2XlZtgS)
- [AWS CDK Examples](https://bit.ly/3dqnzN0)
- [AWS CDK Construct Library](https://amzn.to/2yU9bO5)

### Ch9_L02 – CloudFormation Registry and CLI
*Learn how, even though new to the scene at time of recording; the CloudFormation Registry and CLI promise to significantly standardize and open up CloudFormation to third-party resources.*

1. The Registry
2. The CLI
3. Using 3rd Party Providers
4. Creating Your Own Provider

#### Links:
- [Installing CloudFormation CLI](https://amzn.to/2IJwfjU)
- [IAM Policies](https://amzn.to/2Uusx35)
- [CloudFormation Resource Provider Pricing](https://go.aws/3dmQDVV)
- [Using a 3rd Party Provider](https://bit.ly/2U8la26)
- [Creating a Provider](https://amzn.to/33hqxPA)
- [Building Your Own Provider](https://bit.ly/2QDJKGe) 

### Ch9_L03 – Conclusion
*Congradulations, a sincere thanks and brief good-bye; until next time.*

## Links

- [Course: Lambda@Edge](https://acloud.guru/learn/lambda-edge)
- [Course: The Complete Serverless Course](https://acloud.guru/learn/the-complete-serverless-course)
- [AWS CloudFormation User Guide](https://github.com/awsdocs/aws-cloudformation-user-guide)
- [aws-cf-templates](https://github.com/widdix/aws-cf-templates)
- [aws-cloudformation-templates](https://github.com/awslabs/aws-cloudformation-templates)
- [Limits](https://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/cloudformation-limits.html)
- [IDE Tips](https://hodgkins.io/up-your-cloudformation-game-with-vscode)
- [New Parameter Types](https://aws.amazon.com/blogs/devops/using-the-new-cloudformation-parameter-types/)
- [Custom Resource Auto-approve cert](https://www.alexdebrie.com/posts/cloudformation-custom-resources/)
- [Macro Ideas](https://www.alexdebrie.com/posts/cloudformation-macros/)
- [Blue/Green Deploys](http://think-devops.com/blogs/blue-green-deployments.html)

## Missing Topics
- CFN Designer
- DependsOn
- Stack Notifications (https://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/cfn-console-add-tags.html)
- WaitCondition

---------------

![image](https://user-images.githubusercontent.com/32443900/135696567-bdd50bbb-1b7d-4389-8656-4da5f19a1616.png)
![image](https://user-images.githubusercontent.com/32443900/135696578-95953a95-9ef0-466c-a333-8327c4bbc06e.png)

![image](https://user-images.githubusercontent.com/32443900/135696668-254fe50a-f4a8-4483-bca9-ca699a791565.png)

![image](https://user-images.githubusercontent.com/32443900/135696694-1b47eab1-b1a2-449d-810c-81f89fa0bf74.png)

![image](https://user-images.githubusercontent.com/32443900/135696682-eaec562b-5c6d-48f3-9f54-69b3516362db.png)

![image](https://user-images.githubusercontent.com/32443900/135696714-3ffd89b1-d51e-40b5-a908-31baf4f32c52.png)
![image](https://user-images.githubusercontent.com/32443900/135696728-846aabd5-f9d4-41b6-9a61-f1d01d9da72a.png)

![image](https://user-images.githubusercontent.com/32443900/135696757-8d08210e-3982-4eea-9570-bc8660b24106.png)
![image](https://user-images.githubusercontent.com/32443900/135696768-8c122935-eaa7-459e-aef3-2ad580642cde.png)
![image](https://user-images.githubusercontent.com/32443900/135696782-2eac4e24-b52d-45f6-85e6-47b11c50bd8b.png)

![image](https://user-images.githubusercontent.com/32443900/135696794-a181a058-ef70-4ffc-9cb9-660af15228d7.png)
![image](https://user-images.githubusercontent.com/32443900/135696801-cde4f52e-2045-494c-9c64-17ccabbdb09b.png)
---
![image](https://user-images.githubusercontent.com/32443900/135696838-ad2bc42b-36df-4dcd-8ff6-dfd39330f477.png)

![image](https://user-images.githubusercontent.com/32443900/135696868-87e8c807-b756-4f4f-90bd-252e3d97f83e.png)
![image](https://user-images.githubusercontent.com/32443900/135696882-10dd6160-fc98-40d9-9afe-66edb182a96e.png)

---
![image](https://user-images.githubusercontent.com/32443900/135696907-85800e8f-9147-4bbd-8912-dd3fb4ad968b.png)
![image](https://user-images.githubusercontent.com/32443900/135696929-aea0aca4-9d68-45a8-a1eb-d832274a068c.png)

### AWS CloudFormation quotas
- https://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/cloudformation-limits.html

--
# Tips and Wachouts
## Stack Creation that requires Manual steps
![image](https://user-images.githubusercontent.com/32443900/135697189-21ddaa5b-22b3-4767-8094-41aea9d83814.png)

![image](https://user-images.githubusercontent.com/32443900/135697205-60381067-dd0f-4b3c-9c41-40727af2dadd.png)

![image](https://user-images.githubusercontent.com/32443900/135697218-8e0f5ffb-35d1-4e30-9e79-d819dddb3aa4.png)

### Using Custom Resources to Extend your CloudFormation
- https://www.alexdebrie.com/posts/cloudformation-custom-resources/
- AWS::CloudFormation::CustomResource - https://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/aws-resource-cfn-customresource.html
- CloudFormation custom resource used for
     - Provisioning AWS resources that are not supported by CloudFormation. examples IAM Role Tags, Daily backups for AWS ElastiCache    etc
     - Provisioning non-AWS resources with CloudFormation. for on-prem, third party resources 
     - Performing provisioning steps not related to infrastructure. The core example here is running relational database initialization or migration scripts
     - Any. Thing. You. Want.
  
![image](https://user-images.githubusercontent.com/32443900/135697789-72ed7074-7b09-415f-b5b6-6cb077ff7a34.png)
![image](https://user-images.githubusercontent.com/32443900/135697827-0f1fdb53-0513-41be-93c2-500b44c7cad0.png)
![image](https://user-images.githubusercontent.com/32443900/135697868-73e43593-d5c2-41fd-8be6-8fbce0db6303.png)
---
## Hands on for Custom Resources

![image](https://user-images.githubusercontent.com/32443900/135698009-62b74120-0ab9-47bc-bb3a-beff3e9a2cf5.png)

![image](https://user-images.githubusercontent.com/32443900/135703686-0b46340a-3b16-4951-b3e7-9a0cb0edc93f.png)

---

## AWS CloudFormation Macros
- Using AWS CloudFormation macros to perform custom processing on templates
- Macros enable you to perform custom processing on templates, from simple actions like find-and-replace operations to extensive transformations of entire templates.

![image](https://user-images.githubusercontent.com/32443900/135704256-ad256399-6a03-4f7c-a89a-1a9a8b0abbfa.png)



![image](https://user-images.githubusercontent.com/32443900/135704326-ec1d72d8-7e76-4136-91db-2280d6479663.png)

![image](https://user-images.githubusercontent.com/32443900/135704411-0bc10845-8fbc-4f79-b448-3a79009bf480.png)

![image](https://user-images.githubusercontent.com/32443900/135704415-8910e00d-c8be-4b73-b059-917ae3a729cb.png)

---
### Working with nested stacks

-  ![image](https://user-images.githubusercontent.com/32443900/135704629-572f9bc3-155d-47e6-95b1-f8092227e5d2.png)
![image](https://user-images.githubusercontent.com/32443900/135704644-90aaae85-ac46-4cdc-a70e-b7c82ef6d1fb.png)

-----------

## AWS Secrets Manager: 
Link 
- https://aws.amazon.com/blogs/aws/aws-secrets-manager-store-distribute-and-rotate-credentials-securely/
- https://docs.aws.amazon.com/secretsmanager/latest/userguide/rotating-secrets.html
- https://docs.amazonaws.cn/en_us/AWSCloudFormation/latest/UserGuide/dynamic-references.html
- https://aws.amazon.com/secrets-manager/pricing/
- SSM - now knwon as AWS Systems Manager Parameter Store

![image](https://user-images.githubusercontent.com/32443900/135734396-5d710e21-de3c-4a09-940d-65929d94b1ed.png)

![image](https://user-images.githubusercontent.com/32443900/135734437-1e00c2cf-fa63-4438-a5d7-18effe75907a.png)

### Create SSM Param with encrypted JSON file as value
```shell
STACKNAME=acg-secrets
REGION=us-east-1
PROFILE=moti
aws ssm put-parameter \
    --name /acg/master-cfn/secrets \
    --type SecureString \
    --value "$(cat secrets.json)" \
    --region $REGION \
    --profile $PROFILE
```

### Deploy Secrets Example Template
```shell
aws cloudformation deploy \
  --stack-name $STACKNAME \
  --template-file template.yaml \
  --capabilities CAPABILITY_NAMED_IAM \
  --region $REGION \
  --profile $PROFILE
```

---
# Service Roles

- Folks needs all sorts of roles to deply Stakcs.  For example, to deploy Lambda we need permission to deploy LAmbda service (admin)
- 
![image](https://user-images.githubusercontent.com/32443900/135735254-26601196-1043-482f-95ff-7a16a77103c9.png)

PErmission is given through Roles. 

![image](https://user-images.githubusercontent.com/32443900/135735315-6de93863-4403-4308-9b5d-a8b5f801b320.png)





















