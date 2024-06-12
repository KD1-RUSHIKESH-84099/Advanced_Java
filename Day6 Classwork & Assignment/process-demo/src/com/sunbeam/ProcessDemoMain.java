package com.sunbeam;

public class ProcessDemoMain {
	public static void main(String[] args) {
		Runtime rt = Runtime.getRuntime();
		System.out.println("Total Memory: MB " + rt.totalMemory() / 1024 / 1024);
		System.out.println("Free Memory: MB " + rt.freeMemory() / 1024 / 1024);
		try {
			//Process process = rt.exec("notepad.exe");		// OS: fork() + exec()
			String[] cmd = { "C:\\Program Files\\Mozilla Firefox\\firefox.exe", "http://sunbeaminfo.com" };
			Process process = rt.exec(cmd);
			int status = process.waitFor();					// OS: wait()
			System.out.println("Exit status: " + status);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
