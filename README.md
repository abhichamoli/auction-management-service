# auction-management-service

A full-fledged auction management solution enables users to list various products for auction and allows bidders to place bids on these items. Win notifications are sent to winners at the end of Auction. Consists of below Micro-services

- Product Manager: Manages the inventory of products available for auction.
- Auction Service: Handles the listing of products for auction.
- Bid Service: Facilitates the placement of bids on auctioned products.
- Notification Service: Sends notifications to inform winners of their successful bids.


![Bidding-System-HLD drawio](https://github.com/user-attachments/assets/983b3a03-f39a-4318-9ebe-2ac0f063b797)


### Auction Strategy

- Auction Slot status: CLOSE,  OPEN,  EXPIRED  
- Product Auction status: INACTIVE,  ACTIVE,  ENDED, COMPLETED
- Scheduled task will run after some constant interval, will check for just opened auction slot. Set Auction Slot as OPEN and Product Auctions in the slot as ACTIVE
- Scheduled task will run after some constant interval, will check for just closed auction slot. Set Auction Slot as Expired and Product Auction in the slot as ENDED
- Scheduled task will run after some constant interval, will check for just ended product auctions. Set Auction Slot as COMPLETED And will send win messages to the messaging 
  queue.  

