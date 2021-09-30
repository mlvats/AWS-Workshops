# What is CloudFormation

- Link :  https://docs.aws.amazon.com/AWSCloudFormation/latest/UserGuide/template-anatomy.html

     ![image](https://user-images.githubusercontent.com/32443900/135504534-614dbde4-6d78-4578-b798-cddfe66a82ee.png)

## intrinsic functions:
- A special action in a AWS CloudFormation template that assigns values to properties not available until runtime. 
![image](https://user-images.githubusercontent.com/32443900/135505647-d0d45817-a505-419d-874b-e246d6437da2.png)
![image](https://user-images.githubusercontent.com/32443900/135505794-04bf4b3e-4faf-4199-a8f6-86bf55f87c5f.png)

## Join 
- `  Value: !Join [ " ", [ EC2, Instance, with, Fn, Join ] ]` = EC2, Instance, with, Fn, Join 
- `  Value: !Join [ ":", [ a, b, c ] ]` = a:b:c 
- 

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
