package com.rz.core.elasticsearch;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.elasticsearch.action.ActionFuture;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.get.GetField;
import org.elasticsearch.index.get.GetResult;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;

public class ElasticsearchHelper {
	public static void Test() throws Exception {
		Settings settings = Settings.builder().put("cluster.name", "elasticsearch").build();
		TransportClient client = TransportClient.builder().settings(settings).build();
		client.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("192.168.127.138"), 9300));

		// SearchRequestBuilder builder =
		// client.prepareSearch("comment_index").setTypes("comment_ugc").setSearchType(SearchType.DEFAULT).setFrom(0).setSize(100);
		// BoolQueryBuilder qb = QueryBuilders.boolQuery().must(new
		// QueryStringQueryBuilder("北京").field("body"))
		// .should(new QueryStringQueryBuilder("太多").field("body"));
		// builder.setQuery(qb);
		// SearchResponse response = builder.execute().actionGet();
		// System.out.println(" " + response);
		// System.out.println(response.getHits().getTotalHits());
	
		XContentBuilder xContentBuilder = XContentFactory.jsonBuilder()
				.startObject()
				.field("user", "66666")
				.field("tags", Arrays.asList("Liao", "9999", "志", "diffrent").toString())
				.field("update_date", new Date())
				.endObject();
		//IndexResponse indexResponse = client.prepareIndex("usertags", "default").setSource(xContentBuilder).setId("2").get();

		IndexRequest indexRequest = new IndexRequest();
		indexRequest.index("usertags");
		indexRequest.type("default");
		indexRequest.id("3");
		indexRequest.source(xContentBuilder);
		ActionFuture<IndexResponse> indexRequestActionFuture = client.index(indexRequest);
		IndexResponse indexResponse = indexRequestActionFuture.actionGet();
		
		GetRequest getRequest = new GetRequest();
		getRequest.index("usertags");
		getRequest.type("default");
		getRequest.id("2");
		ActionFuture<GetResponse> getResponseActionFuture = client.get(getRequest);
		GetResponse getResponse = getResponseActionFuture.actionGet();
		
		UpdateRequest updateRequest = new UpdateRequest();
		updateRequest.index("usertags");
		updateRequest.type("default");
		updateRequest.id("2");
		updateRequest.fields("user", "tags", "update_date");
		updateRequest.doc(xContentBuilder);
		ActionFuture<UpdateResponse> updateResponseActionFuture = client.update(updateRequest);
		UpdateResponse updateResponse = updateResponseActionFuture.actionGet();
		
//		GetResult getResult = updateResponse.getGetResult();
//		Map<String, GetField> fields = getResult.getFields();
//		if (null != fields) {
//			System.out.println(fields.size());
//			for (Entry<String, GetField> field : fields.entrySet()) {
//				System.out.println(field.getKey() + "  " + field.getValue().getValue());
//			}
//		}
		
		
		//client.prepareUpdate("usertags", "default", id)
//		System.out.println(indexResponse.getIndex());

//		IndexRequest indexRequest = client.prepareIndex("usertags", "default").request();
//		String[] assd = indexRequest.indices();
//		for (String aaa : assd) {
//			System.out.println(aaa);
//		}
//
//		String indexName = indexRequest.index();
//		String typeName = indexRequest.type();
//		System.out.println(indexName + " " + typeName);
//
//		GetResponse getResponse = client.prepareGet("usertags", "default", "1").setFields("user", "tags", "update_date").get();
//		Map<String, GetField> fields = getResponse.getFields();
//		if (null != fields) {
//			System.out.println(fields.size());
//			for (Entry<String, GetField> field : fields.entrySet()) {
//				System.out.println(field.getKey() + "  " + field.getValue().getValue());
//			}
//		}
	}
}
