package jni;

/**
 * [C 헤더 파일 생성]
 * 	- javah -jni jni.TestNative
 * 		동적 라이브러리(Shared Library)를 사용하고자는 시스템과 패키지 구조 맞아야 함
 */
public class TestNative {

	public native void print();
	
	public native String stringParam(String sName);
	
	public native int intParam(int nNum);
	
	public native String byteArrParam(byte[] bArray);
	
	public native int setMethodString();
	
	public native int setMethodByteArray();
	
	private String sData;
	
	private byte[] bData;
	
	static {
		/*
		 * Windows의 경우, 확장자(.dll)를 제외한 파일 명
		 * Unix/Linux의 경우, 확장자(.so) 외에 접두사(lib)를 제외한 파일 명, 단 라이브러 파일에는 lib가 있어야 함
		 */
		
		String sOsName = System.getProperty("os.name");
		if (sOsName.toLowerCase().indexOf("win") > -1) {
			String sPath = TestNative.class.getResource("/jni/JNITest.dll").getPath();
			System.load(sPath);
		} else {
			System.loadLibrary( "JNITest" );
		}
	}

	public String getsData() {
		return sData;
	}

	public void setsData(String sData) {
		this.sData = sData;
	}

	public byte[] getbData() {
		return bData;
	}

	public void setbData(byte[] bData) {
		this.bData = bData;
	}
	
}
