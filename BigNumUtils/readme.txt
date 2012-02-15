readme.txt
BigNumUtils

You need maven and ant in your classpath to build the library
You also need ant-googlecode-0.0.3.jar for ant in your ${user.home}/.ant/lib/ folder

To create the site locally
	mvn clean install site
	After:
		Remember to manually set mimetype to new files in docs folder

To automatically upload jar files.
	Before:
		You need a file called build.googlecode.properties in you classpath, having two lines:
			googlecode.username=your user name
			googlecode.password=your password for google code, not for your account!
		Change
			projest.version
			upload-jar.summary
			upload-jar.labels
		in pom.xml file before updating files

	mvn clean install site -P upload-jar

	After:
		Remember to manually update docs into svn
		Remember to manually set mimetype to new files in docs folder
