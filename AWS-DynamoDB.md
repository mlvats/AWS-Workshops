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

