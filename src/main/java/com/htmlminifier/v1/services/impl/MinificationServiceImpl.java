package com.htmlminifier.v1.services.impl;

import java.io.File;
import java.io.PrintWriter;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.googlecode.htmlcompressor.compressor.HtmlCompressor;
import com.htmlminifier.v1.services.DirectoryIterationService;
import com.htmlminifier.v1.services.MinificationService;

public class MinificationServiceImpl implements MinificationService{
	
	final static Logger logger = Logger.getLogger(MinificationServiceImpl.class);
	
	private HtmlCompressor compressor;
	
	public MinificationServiceImpl(HtmlCompressor compressor) {
		this.setCompressor(compressor);
	}

	public String minifyHtml(String rawHtml) throws Exception {
		return this.compressor.compress(rawHtml).toString();
	}

	public void minify(String sourceDir,String targetDir,Boolean isCompressionEnabled,Boolean walkRecursively) throws Exception {
		if(isCompressionEnabled) {
			if(walkRecursively) {
				final String targetDirectory = targetDir;
				DirectoryIterationService iterator = new DirectoryIterationService() {
		
					@Override
					public void executeForDirectory(File file) throws Exception {}
		
					@Override
					public void executeForFile(File file) throws Exception {
						logger.info("File Being Processed : "+file.getAbsolutePath());
						String html = FileUtils.readFileToString(file);
		        		PrintWriter compressedFile = new PrintWriter(targetDirectory+File.pathSeparator+file.getName());
						compressedFile.print(minifyHtml(html));
						compressedFile.close();
						logger.info("Compressed File Saved At : "+targetDirectory+File.pathSeparator+file.getName());
					}				
				};
				iterator.walk(sourceDir);
			}
			else {
				File root = new File(sourceDir);
		        File[] list = root.listFiles();
		        for(File file : list) {
		        	if(file.isFile()) {
		        		logger.info("File Being Processed : "+file.getAbsolutePath());
		        		String html = FileUtils.readFileToString(file);
		        		PrintWriter compressedFile = new PrintWriter(targetDir+"/"+file.getName());
						compressedFile.print(minifyHtml(html));
						compressedFile.close();
						logger.info("Compressed File Saved At : "+targetDir+"/"+file.getName());
		        	}
		        }
			}
		}
	}

	public HtmlCompressor getCompressor() {
		return compressor;
	}

	public void setCompressor(HtmlCompressor compressor) {
		this.compressor = compressor;
	}
}
