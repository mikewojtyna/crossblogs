Challenge Statement

Cross-blogs is a backend blogging application written by a startup called WritingForAll. It allows users to create / update / delete their articles, accepting comments for each article.

Notes:
- Articles have a 120 character limit for their title, and a 32k limit for their body.
- The frontend application is excluded from the current scope. It is a separate, fully-functional application handled by another team, so we do not want to modify it.

Your tasks:
- Increase unit test coverage to reach 70%. Achieving more than 70% will only consume your valuable time without extra score.
- Find bugs and fix them, hint: we provided Cross-Blogs application in a good structure, so no need to spend your valuable time on structure modifications,  just focus on fixing bugs.
- Articles search endpoint is very slow, please optimize it.
- Recently Crossover acquired Cross-Blogs and found that Cross-Social (another project for social networking owned by Crossover) is very eligible to promote Cross-Blogs. They want to display articles in Cross-Social's news feed (without comments), using the get-article endpoint. The problem is that Cross-Blogs traffic was 10M page views per day, while Cross-Social's page views are 900M per day. Your goal is to scale the Cross-Blogs app to serve the expected level of traffic. The Cross-Blogs web application was hosted in a single huge AWS node and the node was loaded for 10M views per day, which makes increasing node size not a valid option. The conclusion is that we need to offload the database and run Cross-Blogs on multiple nodes.

Notes:
- Perform required modifications in application level, and in case application modifications requires another 3rd party please add it to docker-compose file.
- Database replication is not required in this task.
- Pay attention to articles only, and feel free to exclude comments from current scope as comments will not appear Cross-Social at all.

Prerequisites:
	Any IDE
	Docker [Latest Version]
	Java 8 
	MySQL >= [Latest Version]
	Gradle 
	Lombok ( https://projectlombok.org/setup/overview )

How to Run:
1) In order to run application in local environment. Set the environment variables as defined in application.properties.
   Use following command to set environment variable o local environment.
   >  Create database name cross-blogs in your Database server.(You can use the mysql docker container as well)
   >  export MYSQL_HOST=127.0.0.1,MYSQL_PORT=3306,MYSQL_USER=root,MYSQL_PASSWORD=mysql123
   >  ./gradlew clean bootRun
   
1) Use following command to create docker image
	/gradlew clean bootJar buildDocker

2) Following command will start the DB Service as well as cross-blogs application
   docker-compose up -d
  
   If you add any new service, update in docker-compose file and make sure docker-compose up is working.
   
3) For Instructions on how to install Docker follow this linke https://store.docker.com/search?type=edition&offering=community



