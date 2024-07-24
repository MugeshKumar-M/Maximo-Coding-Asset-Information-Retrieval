package com.demo.main;


import com.ibm.websphere.security.MaximoLoginException;
import psdi.app.asset.Asset;
import psdi.mbo.MboRemote;
import psdi.mbo.MboSetRemote;
import psdi.server.MXServer;
import psdi.util.MXException;
public class Main {
	    public static void main(String[] args) {
	        try {
	            MXServer mxServer = MXServer.getMXServer();

	            UserInfo userInfo = mxServer.getUserInfo("username", "password");

	            MboSetRemote assetSet = mxServer.getMboSet("ASSET", userInfo);

	            displayAssetsInfo(assetSet);

	        } catch (MXException | RemoteException e) {
	            e.printStackTrace();
	        }
	    }

	    private static void displayAssetsInfo(MboSetRemote assetSet) throws MXException, RemoteException {
	        assetSet.setWhere("status='ACTIVE'"); 
	        assetSet.reset();
	        while (assetSet.moveNext()) {
	            //asset attributes
	            String assetNum = assetSet.getString("ASSETNUM");
	            String description = assetSet.getString("DESCRIPTION");
	            String location = assetSet.getString("LOCATION");

	            // Printing asset details
	            System.out.println("Asset Number: " + assetNum);
	            System.out.println("Description: " + description);
	            System.out.println("Location: " + location);
	        }
	    }
	}

