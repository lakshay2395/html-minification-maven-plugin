package com.htmlminifier.v1.services;

public interface MinificationService {
	
	void minify(String sourceDir,String targetDir,Boolean isCompressionEnabled,Boolean walkRecursively) throws Exception;
	
    String minifyHtml(String rawHtml)throws Exception; 
	
}
