# VPC Intro Workshop
-  https://vpcintro.aworkshop.io
-  github - https://github.com/geseib/vpcintro
-  Another Workshop -  https://www.vpcendpointworkshop.com

## Default VPC
- In every region there is a default VPC. In the us-east-1 region it will have 6 subnets across 6 Availability Zones.
-  All of the subnets in the default VPC are public subnets!
- Take a look at the default VPC‘s route table. There is the local VPC route for 172.31.0.0/16 and a default route 0.0.0.0/0 targeting the Internet Gateway.
- Internet Gateways -  See that it is in a State attached. When we create an Internet Gateway, we want to make sure to attach it to our VPC.

### Creating one VPC Manually -

1. Create a Virtual Private Cloud(VPC), defining its IP CIDR block
2. Create four(4) subnets: 2 Public and 2 Private.
3. Create an Internet Gateway.
4. Create a Virtual Private Gateway
5. Create a NAT Gateway
6. Create a route table named public; with a default route to the Internet Gateway, and associate to public subnets.
7. Create a route table named private; with a default route to the NAT Gateway, and associate to private subnets.

#### Notes
- AT VPC Level, Enable DNS Hostnames, We want to be able to use Route53 Private Hosted Zones for DNS resolution, so lets go ahead and enable this, as it is a prerequisite.
- Internet Gateway : is required to provide internet access to and from your VPC. 
- Virtual Private Gateway: is used to provide private access to your data center and other locations using a VPN or AWS DIrect Connect (a dedicated connection).

  <img width="400" alt="image" src="https://user-images.githubusercontent.com/32443900/135766760-4d16a531-5599-415e-938c-b5f3f43e1c19.png">

- Autonomous System Numbers (ASN) : An Autonomous System Number (AS number or just ASN) is a special number assigned by IANA,
-  used primarilly with Border Gateway Protocol (BGP) which uniquely identifies an network under a single technical administration that has a unique routing policy, or is multi-homed to the public internet. 
-  This autonomous system number is required if you are to run BGP and peer with your internet service provider and between internet service providers at peering points and Internet Exchanges (IX).
-  **BGP** : Border Gateway Protocol (BGP) refers to a gateway protocol that enables the internet to exchange routing information between autonomous systems (AS). As networks interact with each other, they need a way to communicate. 
-  This is accomplished through peering. 
-  BGP makes peering possible. Without it, networks would not be able to send and receive information with each other.

<img width="400" alt="image" src="https://user-images.githubusercontent.com/32443900/135767125-75c9ee15-2440-4db8-b69c-fa1544a2c2f7.png">


### NAT Gateway
- A NAT Gateway provides outbound internet access to private resources in our VPC. 
- We will need this to connect to and be able to install packages on our EC2 instance later.

## Route Table
- Route Tables are assoicated with Subnets.
- We associate Public Route Table with public subnets and Private Route Table with private subnet.
- Public Route Table is assocaited 

<img width="400" alt="image" src="https://user-images.githubusercontent.com/32443900/135767631-831b2b0a-357f-4e81-adfe-6bd0e0fb165c.png">

- 0.0.0.0/0 is called the default route. 
- It means: use this route when there is no more specific destination in the list that matches. In this case send it out toward the internet.
- For Public Route Table, attached to public Subnet  - 0.0.0.0/0 is routed to IGW
- For Private Route Table, attached to private Subnet - 0.0.0.0/0 is routed to NAT Gateway
- 
## Create an EC2 Instance  and establish connectivity to the interne
- Create IAM Role
- Create EC2 Instance and check the connectivity.

<img width="400" alt="image" src="https://user-images.githubusercontent.com/32443900/135768114-18307471-ea65-44ec-b7b9-b989400bcc2f.png">

## Transit Gateway

<img width="500" alt="image" src="https://user-images.githubusercontent.com/32443900/135768752-a4c99fd3-37f5-4004-ae0a-c784812bace9.png">

#### Steps to Create a Transit Gateway
- Create a Transit Gateway :  Transit Gateway to route between the two VPCs.
- Create a Transit Gateway route table : 
- Attach both VPCs to the Transit Gateway
- Associate both VPCs to the Transit Gateway route table
- Propagate both VPCs to the Transit Gateway route table
- Edit the Private Subnet route tables for both VPCs
- Test connectivity between VPCs
---
-  VPN ECMP support : Equal cost multipath (ECMP) routing for VPN Connections that are attached to this transit gateway.
-  Equal-cost multipath (ECMP) is a network routing strategy that allows for traffic of the same session, or flow—that is, traffic with the same source and destination—to be transmitted across multiple paths of equal cost. 
-  It is a mechanism that allows you to load balance traffic and increase bandwidth by fully utilizing otherwise unused bandwidth on links to the same destination.
- **Multicast**: Enables the ability to create multicast domains in this transit gateway

- We can have multiple route tables in our Transit Gateway. 
- Route tables define routing behaviors not domains. 
- In other words, just becuase two VPC use the same route table, they will not nessasarily be able to route to each other
- An Transit Gateway VPC Attachment needs to place an ENI into each Availability Zone that you use in your VPC.
- Propagating A VPC CIDR to a Transit Gateway route table, populates the Transit Gateway route table with a route to the VPC. 
- You can propagate an Attachment to as many route tables as you need within the Transit Gateway.

<img width="600" alt="image" src="https://user-images.githubusercontent.com/32443900/135769751-2a54beed-8424-47d5-bc20-248617b3857c.png">




