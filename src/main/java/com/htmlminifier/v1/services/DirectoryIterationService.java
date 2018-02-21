package com.htmlminifier.v1.services;

import java.io.File;

public abstract class DirectoryIterationService {
	
	public void walk(String path)throws Exception{
        File root = new File(path);
        File[] list = root.listFiles();
        if (list == null) return;
        for ( File f : list ) {
            if (f.isDirectory()){
                walk(f.getAbsolutePath());
                executeForDirectory(f);
            }
            else
            	executeForFile(f);
        }
    }
	
	public abstract void executeForDirectory(File file)throws Exception;
	
	public abstract void executeForFile(File file)throws Exception;

}
