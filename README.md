# Massivian Clean Code Test
In order to apply to massivian developer position

By: Guillermo Andres De Mendoza Corrales 
Email: memoodm@gmail.com

Framework: SpringBoot

Postman collection: ./utilities/CleanCodeTestRoulette.postman_collection

### Notes:
+ The requirements don't imply that two different users can't gamble for the same number or color, so the service 'Gamble' do not make this validation that real roulette have on their rules
+ When a roulette closes, it doesn't erase the gambles on the REDIS database. Those will be erased until it opens again, allowing consulting or auditing purposes
+ For logging events on the roulette, it would be better to implement a relational database than registering them on REDIS, this feature was not implemented in this project 