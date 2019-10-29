# Twitter-Rhetoric-Analysis
Project to analyze rhetoric of prominent public leader's tweets based on multilingual search, sentiment analysis and topic analysis.

Tweets of famous politicians, journalists and social activists from USA, Brazil and India were collected for 3 languages English, Hindi and Portuguese. The tweets were indexed in solr and queried to find their accuracy.

To run the above code, add twitter auth keys to appconfig.json. The code will automatically fetch tweets of 15 persons along with their replies and add it to an individual file.

### TODO list

- [x] Automatically restart fetching tweets from the last checkpoint when twitter api hits rate limit.
- [ ] Automate code to fetch users from JSON file instead of manually adding them to code.