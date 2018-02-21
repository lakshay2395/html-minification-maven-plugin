package com.htmlminifier.v1;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import com.googlecode.htmlcompressor.compressor.HtmlCompressor;
import com.htmlminifier.v1.services.MinificationService;
import com.htmlminifier.v1.services.impl.MinificationServiceImpl;

@Mojo(name = "html-minifier",defaultPhase = LifecyclePhase.PACKAGE)
public class MyMojo extends AbstractMojo{
	
	static {
		BasicConfigurator.configure();
	}
	
	final static Logger logger = Logger.getLogger(MyMojo.class);
	
	@Parameter(alias="source-folder")
	private String sourceFolder;
	
	@Parameter(alias = "target-folder")
	private String targetFolder;
	
	@Parameter(alias = "walk-recursively")
	private Boolean walkRecursively;
	
	@Parameter(alias = "compression-enabled",defaultValue="true")
	private Boolean isCompressionEnabled;
	
	@Parameter(alias = "remove-html-comments",defaultValue="true")
	private Boolean removeHTMLComments;
	
	@Parameter(alias = "remove-multispaces",defaultValue="true")
	private Boolean removeMultiSpaces;
	
	@Parameter(alias = "remove-intertag-spaces",defaultValue="true")
	private Boolean removeInterTagSpaces;
	
	@Parameter(alias = "remove-quotes",defaultValue="false")
	private Boolean removeQuotes;
	
	@Parameter(alias = "simplify-doctype",defaultValue="false")
	private Boolean simplifyDoctype;
	
	@Parameter(alias = "remove-script-attrs",defaultValue="false")
	private Boolean removeScriptAttributes;
	
	@Parameter(alias = "remove-style-attrs",defaultValue="false")
	private Boolean removeStyleAttributes;
	
	@Parameter(alias = "remove-link-attrs",defaultValue="false")
	private Boolean removeLinkAttributes;
	
	@Parameter(alias = "remove-form-attrs",defaultValue="false")
	private Boolean removeFormAttributes;
	
	@Parameter(alias = "remove-input-attrs",defaultValue="false")
	private Boolean removeInputAttributes;
	
	@Parameter(alias = "remove-javascript-protocol",defaultValue="true")
	private Boolean removeJavascriptProtocol;
	
	@Parameter(alias = "remove-http-protocol",defaultValue="true")
	private Boolean removeHttpProtocol;
	
	@Parameter(alias = "remove-https-protocol",defaultValue="true")
	private Boolean removeHttpsProtocol;
	
	@Parameter(alias = "preserve-original-linebreaks",defaultValue="false")
	private Boolean preserveOriginalLineBreaks;
	
	@Parameter(alias = "minify-css",defaultValue="true")
	private Boolean minifyCss;
	
	@Parameter(alias = "minify-js",defaultValue="true")
	private Boolean minifyJs;

    public void execute() throws MojoExecutionException{
    	try {
    		logger.info("----------------------------------------------------------------------------");
    		logger.info("[HTML-Minification-Initiated]");
    		logger.info("Source Directory : "+sourceFolder);
    		logger.info("Target Directory : "+targetFolder);
    		logger.info("Is Compression Enabled : "+isCompressionEnabled);
    		logger.info("Walk Recursively : "+walkRecursively);
    		logger.info(this.toString());
	    	HtmlCompressor compressor = getInitializedCompressor();
	        MinificationService service = new MinificationServiceImpl(compressor);
	        service.minify(sourceFolder, targetFolder, isCompressionEnabled, walkRecursively);
	        logger.info("[HTML-Minification-Complete]");
	        logger.info("----------------------------------------------------------------------------");
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    		throw new MojoExecutionException(e.getMessage());
    	}
    }
    
    public HtmlCompressor getInitializedCompressor() {
    	HtmlCompressor compressor = new HtmlCompressor();
    	compressor.setRemoveComments(removeHTMLComments);
    	compressor.setRemoveMultiSpaces(removeMultiSpaces);
    	compressor.setRemoveIntertagSpaces(removeInterTagSpaces);
    	compressor.setRemoveQuotes(removeQuotes);
    	compressor.setSimpleDoctype(simplifyDoctype);
    	compressor.setRemoveScriptAttributes(removeScriptAttributes);
    	compressor.setRemoveStyleAttributes(removeStyleAttributes);
    	compressor.setRemoveLinkAttributes(removeLinkAttributes);
    	compressor.setRemoveFormAttributes(removeFormAttributes);
    	compressor.setRemoveInputAttributes(removeInputAttributes);
    	compressor.setRemoveJavaScriptProtocol(removeJavascriptProtocol);
    	compressor.setRemoveHttpProtocol(removeHttpProtocol);
    	compressor.setRemoveHttpsProtocol(removeHttpsProtocol);
    	compressor.setPreserveLineBreaks(preserveOriginalLineBreaks);
    	compressor.setCompressCss(minifyCss);
    	compressor.setCompressJavaScript(minifyJs);
    	return compressor;
    }

	@Override
	public String toString() {
		return "Current Configuration -> [sourceFolder=" + sourceFolder + ", targetFolder=" + targetFolder + ", walkRecursively="
				+ walkRecursively + ", isCompressionEnabled=" + isCompressionEnabled + ", removeHTMLComments="
				+ removeHTMLComments + ", removeMultiSpaces=" + removeMultiSpaces + ", removeInterTagSpaces="
				+ removeInterTagSpaces + ", removeQuotes=" + removeQuotes + ", simplifyDoctype=" + simplifyDoctype
				+ ", removeScriptAttributes=" + removeScriptAttributes + ", removeStyleAttributes="
				+ removeStyleAttributes + ", removeLinkAttributes=" + removeLinkAttributes + ", removeFormAttributes="
				+ removeFormAttributes + ", removeInputAttributes=" + removeInputAttributes
				+ ", removeJavascriptProtocol=" + removeJavascriptProtocol + ", removeHttpProtocol="
				+ removeHttpProtocol + ", removeHttpsProtocol=" + removeHttpsProtocol + ", preserveOriginalLineBreaks="
				+ preserveOriginalLineBreaks + ", minifyCss=" + minifyCss + ", minifyJs=" + minifyJs + "]";
	}
   
}
