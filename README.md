
# Property Rental Management System (PRMS)

## Instructions

4 Files are provided to run the PRMS on your local PC

* .tar files contain .java files
* .jar files contain .class files

**Files Submitted:**

* PRMS_Server.jar
* PRSM_Server.tar
* PRMS_Client.jar
* PRMS_Client.tar

**How to run the PRMS:**

1. Run the server on a machine using the following command: 

* Note: the PC must contain the database alongside the server

```
java -jar PRMS_Server.jar
```

2. Run the client on the same machine or a seperate machine using the following command:

* Note: the IP address should be changed to the server's external IP in the ClientCommunicationController.java to connect the client to the server

Same machine: (IP Address: 'localhost')

External Machine: (IP Address: 'Server external IP Address')

```
java -jar PRMS_Client.jar
```

**Instructions for the Client**

1. Login

* the client can enter the system as a regular renter, registered renter, landlord, or a manager using thier username and password
* the client can also register a new account as registered renter or landlord (managers are predefined by server)

	* Predefined Manager: 
	
		* Username: testManager
		* Password: 123

* Be sure to enter your email address when creating an account to receive email notificaitons

2. Post Login

* After logging in, the client will be prompted with the main menu view where they can navigate the system

## Tasks

* A complete list of systems actors: people, other systems, devices (if applies)
* For each actor:  A  brief description. couple of sentences
* For each actor: A list of major events that triggers a process on the system. 
* You high level use case diagram, using StarUML. Please be aware of the fact that use case is not a flowchart. Its only purpose is to illustrate your term project's scope (what is within and what is outside your target system). Please indication of major high level User/Devices/Other systems interfaces. Examples include, required high-level GUIs, interfaces with the other systems such as database connectivity requirements, etc.

## Actors

### Landlord

* The **owner** of the property being rented that **registers** the property to the online management system and **pays** a fee to have the property ad posted which can be viewed by renters. The fee is charged for the amount of the time the owner wants the ad to remain on the system. The landlord can also change the state of the listing.
* List of major events:

	* registration of property to system
	* payment of fees
	* change the state of a listing (active, expired, cancelled, rented)

### Manager

* Manager of the online rental system that has to **login** with credentials given and is allowed to **change amount and period of fees**, has **full access to renters, landlords, and properties information via the database**. The manager is allowed permission to **change the state of the listing**. Additionally, managers can ask for **periodical summary report** of the system.
* Periodical Summary Report Criteria:

	* Total number of houses **listed** in the period
	* Status of listings (active, expired, cancelled, rented)
	* Number of houses **rented** in the period
	* Total number of active listings
	* **List of houses rented** in the period (landlord's name, house's id, address)

* List of major events:

	* Login to system
	* Change in amount of fees
	* Change in period of fees
	* Accessing other entity's information from database
	* Fetch periodical summary report 
	* Change the state of a listing (active, expired, cancelled, rented)


### Renters
* **Regular Renters**

	* These renters are like **observers** of the system that **don't need to login** and are allowed to **search** the system for listings. Regular renters can also send email to the landord without seeing their info.
	* List of Major Events:

		* Search query for listings
		* Send email to landlord

* **Registered Renters**

	* These rents **must login** to the system and can **search** for listings with the same critera listed below. However, registered renters will be **notified** when new listings are posted that match their critera. Registered renters can also send email to the landord without seeing their info.
	* List of Major Events:

		* Login to the system
		* Search Query for listings
		* Storing of search queries to database

			* Required to comapre against new listings

		* Notification of new listing
		* Send email to landlord

	* Search Criteria for Renters

		* Apartment, attached/detached house, townhouse, etc.
		* Number of bedrooms,
		* Number of bathrooms
		* Furnished/unfurnished
		* City quadrant: SW, NW, NE, SE

### Database Engine

* Stores the data of the system and can be used by managers to fetch information on other entities as well as retrieves periodical reports of the system.
* Major Events:

	* Store of query under users
	* Storing new listing
	* Changing status of listing 
	* Computing periodical report


<!--stackedit_data:
eyJoaXN0b3J5IjpbLTE4NzY2NjQyNzRdfQ==
-->
