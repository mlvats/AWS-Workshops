Link - https://aws.amazon.com/blogs/mobile/appsync-direct-lambda/
- https://gerard-sans.medium.com/aws-appsync-velocity-templates-guide-55b9d2bff053
- 
- AWS AppSync is a managed serverless GraphQL. flexible API to securely access, manipulate, and combine data from one or more data sources with a single network call.
- API  data sources, Amazon DynamoDB NoSQL tables, Amazon Aurora Serverless relational databases, Amazon OpenSearch Service (successor to Amazon Elasticsearch Service) clusters, HTTP APIs, and serverless functions powered by AWS Lambda.
- GraphQL has both an API component to expose and access data as well as a compute or runtime component where developers can customize their own business logic directly at the API layer.

- There are three main elements in an AppSync GraphQL API:

    - GraphQL schema – this is where the API or application data is defined and modeled in a GraphQL schema definition language (SDL). The data modeled in the schema tells API consumers what data can be exposed to authorized clients, with automatically generated API documentation.
    - Data sources – this is the component that points AppSync to where the data is stored (DynamoDB, Aurora, Amazon OpenSearch Service, Lambda, HTTP/REST APIs or other AWS services).
    - Resolvers – provide business logic linking or “resolving” types/fields defined in the GraphQL schema with the data in the data sources.

- A resolver is a function or method that is responsible for populating the data for a field or operation defined in the GraphQL schema. Resolvers provide the runtime to fulfill GraphQL queries, mutations, or subscriptions with the right data. 
- 
