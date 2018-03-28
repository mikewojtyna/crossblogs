Challenge Statement 

Cross-blogs is a backend blogging application written in a startup company called WritingForAll, allows user to create / edit / delete their Articles, accepting comments for each article.

Notes:
	Article have 120 characters Title, and 32k body.
	Frontend application excluded from current scope, it is a fully functioning different application handled by another team, so we do not want to modify it by performing any API modifications . 

Your Tasks:
	Increase unit test coverage to reach 70%, achieving more than 70% will only consume your valuable time without extra score.
	Find bugs and fix them, hint: we provided Cross-Blogs application in a good structure, so no need to spend your valuable time on structure modifications,  just focus on fixing bugs.
	Articles search endpoint is very slow, please optimize it on database level.
	Recently Crossover acquired Cross-Blogs and found that Cross-Socials -it is another project for social networking owned by crossover - is very eligible to promote Cross-Blogs,  
	they want to display articles only in Cross-Socials news feed without comments using get article endpoint, the problem is Cross-Blogs traffic was 10M page views per day while Cross-Socials page views are 900M per day, 
	Your goal is to scale Cross-Blogs app to serve expected traffic, Cross-Blogs web application was hosted in a single huge AWS node and the node was loaded for 10M per day’s traffic which makes increasing node size not a valid option, 
	the conclusion is we need to have multi nodes of Cross-Blogs, and offload database.

Notes:
	Perform required modifications in application level, and in case application modifications requires another 3rd party please add it to docker-compose file.
	Database replication is not required in this task.
	Pay attention to articles only, and feel free to exclude comments from current scope as comments will not appear in Cross-Socials at all.


Prerequisites:
	Any IDE
	Docker [Latest Version]
	Java 8 
	MySQL >= [Latest Version]
	Gradle 
	Lombok ( https://projectlombok.org/setup/overview )

How to Run:
1) Use following command to create docker image
	/gradlew bootJar buildDocker

2) Following command will start the DB Service as well as cross-blogs application
   docker-compose up
   
   If you add any service
3) Docker Usermanual


