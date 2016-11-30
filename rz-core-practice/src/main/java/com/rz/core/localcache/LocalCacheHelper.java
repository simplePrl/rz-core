package com.rz.core.localcache;

import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class LocalCacheHelper {
	public static void Test() throws ExecutionException{
		LoadingCache<String, String> loadingCache = CacheBuilder.newBuilder().maximumSize(1000).expireAfterWrite(8, TimeUnit.SECONDS)
				.build(new CacheLoader<String, String>() {
					@Override
					public String load(String arg0) throws Exception {
						// TODO Auto-generated method stub
						arg0 = null == arg0 ? "" : arg0;
						Date date = new Date();

						return arg0 + date.toString();
					}
				});
		// System.out.println(loadingCache.get("key"));
		System.out.println(loadingCache.get("key", () -> {
			return "asdasd" + new Date().toString();
		}));
//		System.out.println(loadingCache.get("key"));
//		loadingCache.put("key", "value");
//		System.out.println(loadingCache.get("key"));
//		for (int i = 0; i < Integer.MAX_VALUE; i++) {
//			System.out.println(loadingCache.get("key", () -> {
//				return "asdasd" + new Date().toString();
//			}));
//			Thread.sleep(5000);
//		}
	}
}
