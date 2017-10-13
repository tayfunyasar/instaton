package com.instaton.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.sf.ehcache.Ehcache;

@Service
public class CacheManagementService {

	@Autowired
	private net.sf.ehcache.CacheManager cacheManager;

	public Boolean clearAllCaches() {
		String[] names = cacheManager.getCacheNames();
		ArrayList<String> namesArray = new ArrayList<>(Arrays.asList(names));
		for (String name : namesArray) {
			Ehcache cache = cacheManager.getEhcache(name);
			if (cache != null)
				cache.removeAll();
		}
		return true;
	}

	public List<String> getCacheNames() {
		String[] names = cacheManager.getCacheNames();
		return new ArrayList<>(Arrays.asList(names));
	}

	public Boolean clearCacheByName(String name) {
		Ehcache cache = cacheManager.getEhcache(name);
		if (cache != null)
			cache.removeAll();
		return true;
	}
}
