
![image](https://user-images.githubusercontent.com/32443900/233867322-b99cacc2-45cf-4cda-afaa-1dd6ef662953.png)
--
![image](https://user-images.githubusercontent.com/32443900/233867351-fe538145-5540-4449-9f61-d6a3850a1ed8.png)
---
![image](https://user-images.githubusercontent.com/32443900/233867439-143e5181-c11f-4af7-ba60-ac7a93b2bf20.png)
---
![image](https://user-images.githubusercontent.com/32443900/233867463-54061371-847e-45a2-af73-f3e48a81df43.png)
---

![image](https://user-images.githubusercontent.com/32443900/233867483-99110ffd-5b23-4bc9-9212-32109bfb94f7.png)
---
![image](https://user-images.githubusercontent.com/32443900/233867516-1b18f67e-0d21-4229-8e74-b84d60c5b302.png)
---
![image](https://user-images.githubusercontent.com/32443900/233867537-db6f7a1e-ebf2-49c6-834a-d520e22c161d.png)
---
![image](https://user-images.githubusercontent.com/32443900/233867562-f5073689-8e86-4e80-9426-40819037d5e8.png)
---
![image](https://user-images.githubusercontent.com/32443900/233867600-abda6fa9-2fec-4d24-a539-c246f9f602e2.png)
---
![image](https://user-images.githubusercontent.com/32443900/233867629-696c522b-212e-4405-a18f-3520a9081c62.png)
---
![image](https://user-images.githubusercontent.com/32443900/233867668-3e692dd9-c1ad-4f72-8bad-64eb983dfc46.png)
---
![image](https://user-images.githubusercontent.com/32443900/233867702-4725629c-8ee5-4d6c-9545-54043e47a95d.png)
----
![image](https://user-images.githubusercontent.com/32443900/233867893-ba7b7e22-8952-4b11-befb-1eeedde4e2f5.png)
---
![image](https://user-images.githubusercontent.com/32443900/233867793-fe9ed43d-3edc-43c0-85e3-65e66a4dfeb9.png)
---














----
## Python Sample Code and link
### Links  
- https://dynobase.dev/dynamodb-python-with-boto3/#get-item
- https://highlandsolutions.com/blog/hands-on-examples-for-working-with-dynamodb-boto3-and-python

Running code below 

```
print("1 Hello World")

import boto3
from boto3.dynamodb.conditions import Key
from boto3.dynamodb.conditions import Attr


dynamodb = boto3.resource('dynamodb')
table = dynamodb.Table('ProductCatalog')

table2 = dynamodb.Table('Thread')

#response = table.scan()

#response = table.scan(FilterExpression=Attr('BicycleType').eq('Hybrid'))


#response = table.query(KeyConditionExpression=Key('Id').eq(102))


#response = table2.query(KeyConditionExpression=Key('ForumName').eq("Amazon DynamoDB"))

# response = table2.query(KeyConditionExpression=Key('ForumName').eq("Amazon DynamoDB"),
#                        FilterExpression=Attr('Message').eq('DynamoDB thread 1 message') )


#response = table2.query(KeyConditionExpression=Key('ForumName').eq("Amazon DynamoDB"),
#                        FilterExpression=Attr('LastPostedBy').not_exists())


response = table2.query(KeyConditionExpression=Key('ForumName').eq("Amazon DynamoDB"),
                        FilterExpression=Attr('LastPostedBy').eq("") | Attr('LastPostedBy').not_exists())
                        
print(response)

print("2 Hello World")


data = response['Items']


while 'LastEvaluatedKey' in response:
    response = table.scan(ExclusiveStartKey=response['LastEvaluatedKey'])
    data.extend(response['Items'])

print(response['Items'])

```

