# What is CloudFormation

- Link :  https://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/template-anatomy.html

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
     
     

