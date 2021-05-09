# Jasypt Tool

### How-to

Setup Environment Variable
Before using this solution, the encryption private key needs to be added to the environment variable. This key should ONLY accessible by limited operation team member in TEST, STAGING and PROD environments.

```Private Key Name: ENV_PSW_PRIVATE_KEY```

The private key name can be changed, however, once it is changed, it requires the code level modification to match the change. The key name is defined inside the code.

How to use it?
Run ```java -jar PropertiesEncryption-jar-with-dependencies.jar – h```, you will see the help messages. 

### Encryption Example

```
java -jar PropertiesEncryption-jar-with-dependencies.jar -key Tjinx7e9Jfda1Tdfca4529 -value PassWord123
 
2017-12-22 09:57:08 INFO  Application:82 -   -- [MODE=Single String Decryption]
2017-12-22 09:57:09 INFO  Application:109 -   -- Source String: [PassWord123] Private Key: [Tjinx7e9Jfda1Tdfca4529]
2017-12-22 09:57:09 INFO  Application:110 -   -- Encrypted Result: ENC(3KokiKBH5lPWb3Pxaq5NoIcC0IattGWt)```
```
### Decryption Example
```
java -jar PropertiesEncryption-jar-with-dependencies.jar -d -key Tjinx7e9Jfda1Tdfca4529 -value ENC(3KokiKBH5lPWb3Pxaq5NoIcC0IattGWt)
 
2017-12-22 09:58:46 INFO  Application:82 -   -- [MODE=Single String Decryption]
2017-12-22 09:58:47 INFO  Application:101 -   -- Source String: [ENC(3KokiKBH5lPWb3Pxaq5NoIcC0IattGWt)] Private Key: [Tjinx7e9Jfda1Tdfca4529]
2017-12-22 09:58:47 INFO  Application:102 -   -- Decrypted Result: PassWord123
```

### GUI 
###### Encrypt / Decrypt simple password(s)
The GUI support multiple password encryption, just add Encryption Private Key into the text fields and enter the original password. Then click the RUN button, the encrypted password will display in "Encrypted Password(s)" field.
For decryption, it is the similar process, no need to explain it again
