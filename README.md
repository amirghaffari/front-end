A Front-End For Knowledge 2.0 Platform
-----------

Introduction
------------
My master's thesis project was developing a web 2.0 front-end for a knowledge platform. The project aimed to employ web 2.0 technologies like searching, linking, tagging, ranking, commenting and blogging to implement a front-end for a scientific publication platform that provides an easy access to data and desktop functionality for users.
In the implementation of the front-end, both server side technologies (i.e. `Spring framework` on `J2EE` platform) and client side technologies (i.e. `JavaScript`, `AJAX`, `JQuery`, `HTML`, and `CSS`) are used.
Front-end is able to be configured to uses `RESTful API's` to obtain data from a Core (an application that is responsible to handle data layer tasks).

How to build and deploy
----------------------------------------

To build the front-end, [`Apache Ant`](http://ant.apache.org/) can be used as shown below:

	$ ant -find build.xml deploy

The `war` file located in the `dist` folder.

