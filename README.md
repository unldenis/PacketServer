# PacketServer
## What is PacketServer? 
Packet server is an api that allows you to communicate fully encrypted (RSA / AES) via the <a href="https://github.com/unldenis/PacketServer/blob/3b9685a3d16fa8a3d7e96882f25ff92db3b1fe8e/src/main/java/com/github/unldenis/packet/Packet.java#L6">packets</a> in the network.
<br>
Your packages will be converted into Json by the client to the server which will then be able to read them.
<br>
<br>  
## How to install
### Maven
Add this dependency to your dependencies in the pom:
<br>
```xml
<dependency>
  <groupId>com.github.unldenis</groupId>
  <artifactId>PacketServer</artifactId>
  <version>v1.0</version>
</dependency>
```
### Gradle
Add this dependency to your build.gradle:
<br>
```xml
implementation 'com.github.unldenis:PacketServer:v1.0'
```
## Examples
If you need some examples to get started, check the <a href="https://github.com/unldenis/PacketServer/tree/master/src/test/java">examples' directory</a> in this project. 
