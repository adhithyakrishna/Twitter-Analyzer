package twitter4jProg.Utility;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
import twitter4jProg.builder.TwitterJsonConfig;
import twitter4jProg.builder.InitTwitterLimitConfig;

public class TwitterInitializer {
	
	public InitTwitterLimitConfig initTweet() throws JsonParseException, JsonMappingException, IOException {
		InitTwitterLimitConfig limitConfig = new InitTwitterLimitConfig();
		ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
				false);
		TwitterJsonConfig config = objectMapper.readValue(new File("appConfig.json"), TwitterJsonConfig.class);

		int limit = config.getTweetLimit();
		String OAuthConsumerKey = config.getoAuthConsumerKey();
		String OAuthConsumerSecret = config.getoAuthConsumerSecret();
		String OAuthAccessToken = config.getoAuthAccessToken();
		String OAuthAccessTokenSecret = config.getoAuthAccessTokenSecret();

		ConfigurationBuilder configBuilder = new ConfigurationBuilder();
		configBuilder.setJSONStoreEnabled(true);
		configBuilder.setOAuthConsumerKey(OAuthConsumerKey);
		configBuilder.setOAuthConsumerSecret(OAuthConsumerSecret);
		configBuilder.setOAuthAccessToken(OAuthAccessToken);
		configBuilder.setOAuthAccessTokenSecret(OAuthAccessTokenSecret);
		configBuilder.setTweetModeExtended(true);
		

		Twitter twitter = new TwitterFactory(configBuilder.build()).getInstance();
		limitConfig.setTweet(twitter);
		limitConfig.setLimit(limit);
		return limitConfig;
	}
}
