package jni;

import java.io.UnsupportedEncodingException;

public class Test {

	public static void main(String[] args) throws UnsupportedEncodingException {
		TestNative test = new TestNative(); 
		test.print();
		
		String sRet = test.stringParam("Smith");
		System.out.println("[Java1] sRet : " + sRet + "\n");
		
		int nRet = test.intParam(2);
		System.out.println("[Java2] nRet : " + nRet + "\n");
		
		String sRet2 = test.byteArrParam("John".getBytes());
		System.out.println("[Java3] sRet2 : " + sRet2 + "\n");
		
		test.setMethodString();
		String sRet3 = test.getsData();
		System.out.println("[Java4] sRet3 : " + sRet3 + "\n");
		
		test.setMethodByteArray();
		byte[] bRet = test.getbData();
		System.out.println("[Java5] bRet Len : " + bRet.length);
		System.out.println("[Java5] bRet Str : " + new String(bRet) + "\n");
	}
}
