package knu.cs.dke.topology_manager;

import java.util.HashMap;
import java.util.Map;

import knu.cs.dke.topology_manager.sources.Source;

public class SourceList {
	
	// singleton - 플랜리스트 > 플랜 > 토폴로지
	private volatile static SourceList instance;
	
	public static SourceList getInstance() {
		if(instance == null) {
			synchronized(SourceList.class) {
				if(instance == null) {
					instance = new SourceList();
				}
			}
		}
		return instance;
	}

	private Map<String, Source> mSources;

	private SourceList() {
		mSources = new HashMap<String, Source>();
	}
	
	public synchronized Source get(String sourceID) {
		return mSources.get(sourceID);
	}
	
	public synchronized boolean add(Source source) {
		if (mSources.containsKey(source.getSourceName())) return false;
		mSources.put(source.getSourceName(), source);
		return true;
	}
	
	public synchronized boolean remove(Source source) {
		if (!mSources.containsKey(source.getSourceName())) return false;
		mSources.remove(source.getSourceName());
		return true;
	}
	
	public synchronized boolean set(Source source) {		
		// 값이 있으면 Update, 없으면 Add 됨..
		if (!mSources.containsKey(source.getSourceName())) return false;
		mSources.put(source.getSourceName(), source);		
		return true;
	}
	
	public synchronized int size() {
		return mSources.size();
	}
	
	public synchronized void printSummary() {
					
		System.out.println("[Source List Summary]");
		System.out.println("Map Size: " + mSources.size());
				
		int i = 0;
		for(String key: mSources.keySet()) {			
			System.out.println("[Source. " + i);
			System.out.println("Source Name: " + key);
			System.out.println("Status: " + mSources.get(key).getStatus());
			System.out.println("Thread Name: " + mSources.get(key).getName());
			System.out.println("Thread Status: " + mSources.get(key).isAlive());
		}
	}
}
