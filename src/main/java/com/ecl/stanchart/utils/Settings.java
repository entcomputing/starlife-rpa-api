// Decompiled by DJ v3.9.9.91 Copyright 2005 Atanas Neshkov  Date: 12/14/2006 6:47:16 PM
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3) fieldsfirst lnc 
package com.ecl.stanchart.utils;

import java.io.*;
import java.util.*;

public class Settings {

	private static Settings uniqueInstance = null;
	public Properties props;
	private static String configFileName = "application.properties";

	private Settings(String s) {
		props = null;
		// logger = //logger.getLogger(getClass().getName());
		props = new Properties();
		myLoad(s);
	}

	public static boolean SaveFileContent(String filename2, String msgx) {
		boolean success = false;
		try {
			File outFile = new File(filename2);
			success = outFile.createNewFile();
			if (success) {
				BufferedWriter out = new BufferedWriter(new FileWriter(filename2));
				out.write(msgx);
				out.close();

			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return success;

	}

	public static boolean WriteLog(String msgx) {
		boolean success = false;
		try {
			System.out.println(msgx);
			//// logger.debug(msgx) ;
			// File outFile = new File("C:\\OmniFlexLog.txt");
			// String filename2 = "D:\\BASISFLEX.txt";
			// BufferedWriter out = new BufferedWriter(new FileWriter(filename2),true);
			// out.write(msgx);
			// out.close();
			/*
			 * PrintWriter pw = null; File outFile = new File(filename2);
			 * System.out.println(msgx); if (outFile.exists()) { pw = new PrintWriter(new
			 * FileWriter(filename2, true)); } else { pw = new PrintWriter(new
			 * FileWriter(filename2)); } pw.println(msgx); pw.flush();
			 */
			// FileOutputStream fos = new FileOutputStream(filename2, true);
			// fos.write(msgx.getBytes());
			// fos.close();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return success;

	}

	public static boolean SaveImageToFile(byte[] b) {
		boolean result = false;
		try {
			Calendar calD = Calendar.getInstance();
			Random rand = new Random();
			String filename2 = "C://" + calD.getTimeInMillis() + "_" + rand.nextInt(10000) + ".XML";
			System.out.println("File Name Incoming Response FCC: " + filename2);
			FileOutputStream fos = new FileOutputStream(filename2);
			fos.write(b);
			fos.close();
		} catch (Exception ex) {
			ex.printStackTrace();
			Settings.WriteLog(ex.getClass() + " " + ex.getMessage());
		}
		return result;

	}

	public static String ReadFileContent(String resultFile) {
		String response = "";
		try {
			File ff = new File(resultFile);
			BufferedReader in = null;
			if (ff.isFile()) {
				in = new BufferedReader(new FileReader(ff));
				String tmp = "";
				while ((tmp = in.readLine()) != null) {
					response = response + tmp;
				}
				in.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return response;
	}

	public static String filename(String fullPath) { // gets filename without extension
		int dot = fullPath.lastIndexOf(".");
		int sep = fullPath.lastIndexOf("/");
		return fullPath.substring(sep + 1, dot);
	}

	private synchronized void myLoad(String s) {
		try {
			Thread currentThread = Thread.currentThread();
			ClassLoader contextClassLoader = currentThread.getContextClassLoader();
			InputStream propertiesStream = contextClassLoader.getResourceAsStream(configFileName);
			if (propertiesStream != null) {
				props.load(propertiesStream);
				propertiesStream.close();
			} else {
			}
		} catch (IOException ioexception) {
			System.out.println(ioexception.getMessage());
		}
	}

	public static synchronized Settings getInstance(String s) {
		if (uniqueInstance == null) {
			uniqueInstance = new Settings(s);
		}
		return uniqueInstance;
	}

	public Properties getPropertyFile() {
		return props; // .getProperty(s);
	}

	public synchronized String getProperty(String s) {
		return props.getProperty(s);
	}

	public synchronized void setProperty(String s, String s1) {
		try {
			if (s1 == null) {
				// logger.debug("Key=" + s + ", Value=" + s1);
				s1 = "Empty";
			}
			props.setProperty(s, s1);
			FileOutputStream fileoutputstream = new FileOutputStream(configFileName);
			props.store(fileoutputstream, "Settings");
		} catch (IOException ioexception) {
			// logger.debug("failed load properties due to : " + ioexception);
		}
	}

	public static String[] tokenize(String input, String delim) {
		Vector v = new Vector();
		StringTokenizer t;
		System.out.println("...TOKENIZE::" + input + "    " + delim);
		if (delim.equals("default")) {
			t = new StringTokenizer(input);
		} else {
			t = new StringTokenizer(input, delim);
		}
		for (; t.hasMoreTokens(); v.addElement(t.nextToken()))
			;
		String cmd[] = new String[v.size()];
		for (int i = 0; i < cmd.length; i++) {
			cmd[i] = (String) v.elementAt(i);
			System.out.println("...TOKENIZE CMD::" + cmd[i]);
		}

		return cmd;
	}

	public static void SetSSLCertSetting() {
		String sslClientFile = Settings.getInstance("").getProperty("JDK_SSLFILE");
		String sslClientPwd = Settings.getInstance("").getProperty("JDK_SSLPWD");

		System.setProperty("javax.net.ssl.trustStore", sslClientFile);
		System.setProperty("javax.net.ssl.trustStorePassword", sslClientPwd);

		System.setProperty("javax.net.ssl.keyStore", sslClientFile);
		System.setProperty("javax.net.ssl.keyStorePassword", sslClientPwd);
		// System.setProperty("javax.net.debug", "all");
		System.setProperty("https.protocols", "TLSv1.2"); // ,TLSv1,SSLv3");

	}

}
