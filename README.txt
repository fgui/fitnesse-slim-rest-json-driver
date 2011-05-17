Small driver to test REST-JSON apis with FitNesse.
The intentation is to be able to test REST-JSON api with FitNesse/SLIM without 
writing extra java code "Fixtures".

Driver provides methods for rest calls:
REST calls get, post, put

and a method to extract data from JSON.
restrieve/eval js on json.

The returned json include status, header, and body.

As a convenience methods it also includes 
a echo function and 
a concatRandomInt to generate random Strings for testing.

It requires apache httpcomponent 4.1 and org.json library.

simple example testing twitter:

--------- FitNesse Wiki Page ------------
!define TEST_SYSTEM {slim} 

!path /home/francesc/repos/fitnesse-rest-json/bin
!path /home/francesc/opt/httpcomponents-client-4.1/lib/*.jar
!note org.json not needed because fitNesse uses it.

Example test twitter trends, test we get a OK response and 10 trends.

!|script|net.zesc.fitnesse.driver.RestJsonDriver         |
|$json= |get        |http://api.twitter.com/1/trends.json|
|check  |retrieve js|status.code        |json |$json|=200|
|check  |retrieve js|body.trends.length |json |$json|=10 |
------------------------------------------
