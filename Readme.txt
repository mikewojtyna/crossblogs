Cross-blogs is a backend blogging application written in a startup company called WritingForAll, allows user to create / edit / delete their Articles, accepting comments for each article.
Your goal is to review application code, fixing as much bugs as you can and increase unit test coverage to 70%.    
Notes:
	Article body less than 32k text.
	Comment body less than 8k text. 
	Comments per Article are un-limited, ordered descending by date with pagination.
	Searching for article was slow in the past, and it is not working now !

Frontend application excluded from current scope, it is a fully functioning different application handled by another team and not included in this repository. 

New Requirements:   
Recently Crossover acquired Cross-Blogs and found that Cross-Socials -it is another project for social networking owned by crossover- is very eligible to promote Cross-Blogs,  they want to display random articles in Cross-Socials news feed, the point is Cross-Blogs traffic was 10M page views per day while Cross-Socials page views are 900M per day, also they want to send email notification to article’s owner once a comment is added on Cross-Blogs as a value added service.
1) Your goal is to scale Cross-Blogs app specially getting one article API to serve expected traffic, Cross-Blogs was hosted in a single huge AWS node and the node was loaded for 10M per day’s traffic which makes increasing node size not a valid option.
		Notes:
			If you want to use another 3rd party adding its docker-compose file will be a plus.
2) Sending instant email notification once a comment is added to Articles Owner
		Notes:
			Cross-blogs must send the email under any circumstances.

Prerequisites:
	Any IDE
	Java 8 
	MySQL >= 5.5
	Gradle




