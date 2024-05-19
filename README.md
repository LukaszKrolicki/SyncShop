# :convenience_store: Topic of the project:

An interactive mobile application supporting group shopping

------------------------------------------------------------------------------------------------

### Project goal:
The aim of the project is to develop a mobile application called "SyncShop" that facilitates group shopping by allowing users to create, share, and update shopping lists collaboratively. The application aims to streamline the process of organizing group purchases, making it easier for users to plan their shopping trips, coordinate with others, and ensure that all necessary items are obtained efficiently. 

------------------------------------------------------------------------------------------------

### Product functionalities:
The scope of the created product includes the creation of a mobile android application, nodejs server and a database dedicated to it. Many users will be able to use the system at the same time. It will be a simple, intuitive system.

------------------------------------------------------------------------------------------------

### For all users:

•	User Registration: Users can sign up for the system by providing necessary details.

• Password Recovery: Users have the option to recover their passwords in case they forget them.

• Password Change: Users can change their passwords for security reasons or personal preferences.

• Viewing Friend Lists: Users can browse through their list of added friends.

• Removing Friends: Users can remove friends from their list if desired.

• Creating Shopping Lists: Users can create shopping lists to organize their purchases.

• Adding Friends to Shopping Lists: Users can invite their friends to collaborate on shared shopping lists.

• Adding Products to Lists: Users can add products along with their details to the shopping lists.

• Declaring Intent to Purchase: Users can indicate their intention to purchase a specific product from the list.

• Marking Unavailable Products: Users can mark products as unavailable if they are unable to purchase them.

• Marking Products as Purchased: Users can mark products as purchased once they have bought them.

------------------------------------------------------------------------------------------------

### For administrative staff:

• Browsing User List: Users can browse through a list of registered users in the application.

• Changing User Data: Users have the ability to modify their personal information such as name, email, etc.

• Deleting User Account: Users can delete their own accounts if they wish to do so

• Viewing Error Reports: Users can access and review error reports submitted by users regarding any issues or bugs encountered in the application.

------------------------------------------------------------------------------------------------


### Tools we used:

• Kotlin

• Java

• Nodejs

• MySqlWorkbench

------------------------------------------------------------------------------------------------

### ERD database diagram:

![image](https://github.com/LukaszKrolicki/SyncShop/assets/54467678/26169095-aff1-44e4-b8b6-e4c62e8b361d)

------------------------------------------------------------------------------------------------

### Architectural pattern:

MVC - model view controller

------------------------------------------------------------------------------------------------

### Crucial models:

Model (Singleton):
This class serves as the central model in the MVC architecture. It manages various data structures and is responsible for coordinating interactions between different components of the application. Being implemented as a singleton ensures that there is only one instance of the model throughout the application's lifecycle, promoting data consistency and centralized management.

![image](https://github.com/LukaszKrolicki/SyncShop/assets/54467678/faf68236-d9be-453d-ae65-385addfe1385)


Api.java defines the interface for web services that the client application can invoke to interact with the server. It's part of the REST client, which uses the Retrofit library to communicate with the backend. Retrofit is an HTTP client library for Android and Java that simplifies creating HTTP connections to RESTful services.

![image](https://github.com/LukaszKrolicki/SyncShop/assets/54467678/ed826a1d-0c5f-418d-9ba6-2e694b74de03)


The ApiDatabaseDriver class is used for communication with the server using a REST-based API, utilizing the Retrofit library to execute HTTP requests and process responses. This class contains various methods for login, registration, fetching protected data, creating users, shopping lists, and managing them, as well as other functions related to the application.

![image](https://github.com/LukaszKrolicki/SyncShop/assets/54467678/98393f4d-1125-463c-9e9a-76beff7414ce)

------------------------------------------------------------------------------------------------

# User documentation:

![image](https://github.com/LukaszKrolicki/SyncShop/assets/54467678/f6f670d4-8451-4604-8c07-d63dc3707351)

This is the page that appears immediately after launching the application. The user can log in, create an account, or recover a password.

------------------------------------------------------------------------------------------------

## Registration

![image](https://github.com/LukaszKrolicki/SyncShop/assets/54467678/961ad3c4-e47f-4a18-b692-730423b9a836)

The user enters their data and clicks "create account".

![image](https://github.com/LukaszKrolicki/SyncShop/assets/54467678/d739df7e-0149-4c09-9bb2-ca5bf5e9a8ad)

Next, a window appears for entering the verification code from the email.

![image](https://github.com/LukaszKrolicki/SyncShop/assets/54467678/eb2bb2c4-befc-4fac-956f-e565362fd72a)

User checks mail inbox

![image](https://github.com/LukaszKrolicki/SyncShop/assets/54467678/16c7c7f0-e6a9-4a91-8008-a07442b11dc2)

and clicks confirm

![image](https://github.com/LukaszKrolicki/SyncShop/assets/54467678/155a5701-b65d-4374-b1af-a9e476ef49e6)

Registration completed

## Password retrieve

![image](https://github.com/LukaszKrolicki/SyncShop/assets/54467678/72fc5904-8cb3-49ce-a9d6-3c32a425da9e)

The user enters the email to which the confirmation code will be sent.

![image](https://github.com/LukaszKrolicki/SyncShop/assets/54467678/8bb914c4-bbd7-4f79-8f0c-ae167bec15c6)

The user checks their mailbox and enters the code.

![image](https://github.com/LukaszKrolicki/SyncShop/assets/54467678/9f28a621-3be1-49cc-97f7-ff0ba595eab4)


![image](https://github.com/LukaszKrolicki/SyncShop/assets/54467678/bef28f89-1947-428b-a84e-f8970f7695ba)

Then they enter a new password.

![image](https://github.com/LukaszKrolicki/SyncShop/assets/54467678/cbde6039-f439-4b72-b499-54ac5e9a3b49)

The password change process has been successfully completed.

## User menu

![image](https://github.com/LukaszKrolicki/SyncShop/assets/54467678/48def34a-e74b-4d26-ba71-e812a620e57e)

In the main menu, there are options that the user can choose.

## User Notifications

![image](https://github.com/LukaszKrolicki/SyncShop/assets/54467678/f2fdd56e-0519-43c1-8432-345356d5d548)


The notifications page is displayed immediately after logging in. The user can accept/delete invitations and learn about new lists they've been added to.

## Create shopping list

![image](https://github.com/LukaszKrolicki/SyncShop/assets/54467678/989b34d5-82bc-4f94-8a52-f2e1866f0f17)

![image](https://github.com/LukaszKrolicki/SyncShop/assets/54467678/3aeec921-1e25-49b4-8aa8-93477dc0a198)

This view allows creating a new shopping list, adding a date, and selecting friends to share the list with.

## Friend Search

![image](https://github.com/LukaszKrolicki/SyncShop/assets/54467678/3d7b7b83-b554-4f22-82a1-e933287aab38)

This view allows searching for people within the application by their "username" and establishing connections.

## Friend List

![image](https://github.com/LukaszKrolicki/SyncShop/assets/54467678/4d58dd3f-fa50-46dc-9bb5-d4caf32a4792)

This view allows browsing the list of friends and the option to remove a friend.

## Shopping lists search

![image](https://github.com/LukaszKrolicki/SyncShop/assets/54467678/c9cabe15-29e9-4cbe-a0a4-4e8d8e9cf278)

This view allows browsing shopping lists, with the types of lists divided into those created by us or by our friends.

## Shopping list exit

![image](https://github.com/LukaszKrolicki/SyncShop/assets/54467678/81320177-3429-4095-bbf9-b0eb488a67c6)

When we want to exit a particular shopping list, a message will appear.

![image](https://github.com/LukaszKrolicki/SyncShop/assets/54467678/fb087169-93e1-473f-ac42-b17330ece032)

## Shopping list deletion

![image](https://github.com/LukaszKrolicki/SyncShop/assets/54467678/6f09313c-ce01-4f1f-a537-3147439bfdf4)

When we want to delete a list that we created, a message will appear.

![image](https://github.com/LukaszKrolicki/SyncShop/assets/54467678/32da1051-532c-4019-b47b-2abf505a2d10)

## Shopping list details

![image](https://github.com/LukaszKrolicki/SyncShop/assets/54467678/46ed084a-2a92-4a38-abae-6e1300547052)

When we click on "details"...

![image](https://github.com/LukaszKrolicki/SyncShop/assets/54467678/c2e9b84d-73de-46ca-8519-a8262ed83e86)

The main section of the program will open.

## Add product to shopping list

![image](https://github.com/LukaszKrolicki/SyncShop/assets/54467678/075d6b89-777e-4d52-b0ba-7352f17fe33d)

![image](https://github.com/LukaszKrolicki/SyncShop/assets/54467678/43901027-9564-4468-8bf5-cef078351709)

## Product operations / Planned section

![image](https://github.com/LukaszKrolicki/SyncShop/assets/54467678/76e67a16-a064-4d70-a86a-63328d772f34)

The user can check the product details.

![image](https://github.com/LukaszKrolicki/SyncShop/assets/54467678/31397b53-2ace-4be1-8498-6a7bf7743e10)

The user can also move the product to the "purchased," "planned," or "deleted" sections.

## Reserved section

![image](https://github.com/LukaszKrolicki/SyncShop/assets/54467678/f5d1f6b0-adbd-4048-bfd6-ae10d4015a16)

Here, the user can also check the details, delete the product, or move it back to the planned list.

## Bought section

![image](https://github.com/LukaszKrolicki/SyncShop/assets/54467678/2270332e-a3e0-4b2e-a845-1048ccf6e708)

The user can check which products have already been purchased.

## Settings

![image](https://github.com/LukaszKrolicki/SyncShop/assets/54467678/af70de77-65e2-4ed5-b2e5-d8114a830957)

The user can change their details and password.

## Send bug report

![image](https://github.com/LukaszKrolicki/SyncShop/assets/54467678/5080ac38-3d6f-4bc0-918a-6e80f5328e20)

The user can send an error report to improve the application's functionality.

------------------------------------------------------------------------------------------------

[[ ADMIN SECTION ]]

