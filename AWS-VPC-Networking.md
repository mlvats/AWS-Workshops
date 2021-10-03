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
-  ** BGP ** : Border Gateway Protocol (BGP) refers to a gateway protocol that enables the internet to exchange routing information between autonomous systems (AS). As networks interact with each other, they need a way to communicate. 
-  This is accomplished through peering. 
-  BGP makes peering possible. Without it, networks would not be able to send and receive information with each other.

<img width="400" alt="image" src="https://user-images.githubusercontent.com/32443900/135767125-75c9ee15-2440-4db8-b69c-fa1544a2c2f7.png">




