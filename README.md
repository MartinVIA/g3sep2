# g3sep2
## Team 
### Achilleas, Nandor, Buvan, Martin

### product list,
products get a PK that lets us use that as reference, meaning that every store and the warehouse has the same abailability for objects, all referencing the database, when objects are moved around from both of these, its not really the objects, its the numbers (the method would end up being storeList.order(PK, quantity) or .add(PK,quantity) )

product list will then have the array that holds the information to all of these objects.
When the store makes an order, then a list of what that store is ordering to the warehouse will be updated, with the item PK and the quantity it is asking. 

for this to work, the Product List will hold every single product, and as as stores are initialized, they will all have the static reference to the products, with their own initial values of 0 for all.

Stores then order products to the warehouse, which sees what store is making the order, the items and their  quantity.

Realistically it doesnt make any sence for the stores to automatically get the items they ask for, there has got to be a queue of some sort.

#### At the end this means that a _static_ list of all products will be made, so that it streamlines to the database better, and we dont overwhelm all lists full of objects, as well as keeping things simple, representing everything with only IDs and quantity

so then the visual system just uses the ID of the product to get() the information and display it to the user.

#### and it means that we will have two lists for every store; what they are currently ordering, and what they have in stock

We will be (possibly) discarting the idea of the time at which items perish, and if a store can hold two groups of the same item with different perishing times.

