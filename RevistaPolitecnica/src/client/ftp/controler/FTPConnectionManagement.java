package client.ftp.controler;

import it.sauronsoftware.ftp4j.FTPAbortedException;
import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPDataTransferException;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;

import java.io.FileNotFoundException;
import java.io.IOException;

public class FTPConnectionManagement {
	
FTPClient client = new FTPClient();
	
	public void createConection(String server, int port) throws IllegalStateException, IOException, FTPIllegalReplyException, FTPException{
		//client.setSecurity(FTPClient.SECURITY_FTPS);
		client.connect(server, port);
	}
	
	public void login(String username, String password) throws IllegalStateException, IOException, FTPIllegalReplyException, FTPException{
		client.login(username, password);
		client.setType(FTPClient.TYPE_BINARY);
	}
	
	public void logout() throws IllegalStateException, IOException, FTPIllegalReplyException, FTPException{
		client.disconnect(true);
	}
	
	public void doUpload(String localFilePath) throws IllegalStateException, FileNotFoundException, IOException, FTPIllegalReplyException, FTPException, FTPDataTransferException, FTPAbortedException{
		client.upload(new java.io.File(localFilePath));
	}
	
	public void doDownload(String localFilePath, String remoteFilePath) throws IllegalStateException, FileNotFoundException, IOException, FTPIllegalReplyException, FTPException, FTPDataTransferException, FTPAbortedException{
		client.download(remoteFilePath, new java.io.File(localFilePath));
	}
	
	public String getCurrentDirectory() throws IllegalStateException, IOException, FTPIllegalReplyException, FTPException{
		return client.currentDirectory();
	}
	
	public void createDirectory(String dir) throws IllegalStateException, IOException, FTPIllegalReplyException, FTPException{
		client.createDirectory(dir);
	}
	
	public void setCurrentDirectory(String dir) throws IllegalStateException, IOException, FTPIllegalReplyException, FTPException{
		client.changeDirectory(dir);
	}

	public void deleteFile(String filename) throws IllegalStateException,
			IOException, FTPIllegalReplyException, FTPException {
		client.deleteFile(filename);
	}
	
	

}
