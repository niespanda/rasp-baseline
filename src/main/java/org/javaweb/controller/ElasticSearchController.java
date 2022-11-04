package org.javaweb.controller;

import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ElasticSearch")
public class ElasticSearchController {

	/**
	 * ElasticSearch 基线登录测试
	 * username elastic
	 * password test1234
	 *
	 * @return org.elasticsearch.client.RestHighLevelClient
	 * @throws
	 * @user
	 */
	@RequestMapping("/login")
	public RestHighLevelClient elasticSearchLogin(String ip, int port, String username, String password) throws Exception {
		final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
		credentialsProvider.setCredentials(
				AuthScope.ANY, new UsernamePasswordCredentials(username, password));
		RestHighLevelClient client = new RestHighLevelClient(
				RestClient.builder(
						new HttpHost(ip, port, "http")
				).setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
					public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
						httpClientBuilder.disableAuthCaching();
						return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
					}
				})
		);
		client.getLowLevelClient().getNodes();
		return client;
	}
}
