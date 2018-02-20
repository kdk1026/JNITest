package compare_C_to_Java;

import java.util.Arrays;

public class CompareTest {

	public static void main(String[] args) {
		//comparePrintf();
		//compareSpringf();
		
		compareArrayCopy();
	}
	
	private static void comparePrintf() {
		// C
		/*
			printf("Hello World!\n");
			printf("[%s]\n", "Hello");
			printf("[%d]\n", 1234);
			printf("[%s] --- (%d)\n", "Hello", 1234);
		*/
		//  Java
		System.out.printf("Hello World!\n");	// C 유사
		System.out.println("Hello World!");		// Java Style
		
		System.out.println(String.format("[%s]", "Hello"));
		System.out.println(String.format("[%d]", 1234));
		System.out.println(String.format("[%s] --- (%d)", "Hello", 1234));
	}
	
	private static void compareSpringf() {
		// C
		/*
			char temp[100];
			
			sprintf(temp, "%04d\n", 12);
			printf("[Left 0 Fill]:%s\n", temp);
			
			sprintf(temp,"%+4s\n", "abc");
			printf("[Left Space Fill]:%s\n", temp);
		
			sprintf(temp,"%-4s\n", "abc");
			printf("[Right Space Fill]:%s\n", temp);
			
			printf("\n");
			
			sprintf(temp,"%X\n", 255);
			printf("[10 -> 16 Upper]:%s\n", temp);
		
			sprintf(temp,"%x\n", 255);
			printf("[10 -> 16 Lower]:%s\n", temp);
		*/
		//  Java
		String temp = "";
		
		temp = String.format("%04d", 12);
		System.out.println(String.format("[Left 0 Fill]:%s", temp));
		
		temp = String.format("%4s", "abc");
		System.out.println(String.format("[Left 공백 Fill]:%s", temp));
		
		temp = String.format("%-4s", "abc");
		System.out.println(String.format("[Right 공백 Fill]:%s", temp));
		
		System.out.println();
		
		temp = String.format("%X", 255);	// C 유사
		System.out.println(String.format("[10 -> 16 Upper]:%s", temp));
		
		temp = Integer.toHexString(255).toUpperCase();		// Java 스타일
		System.out.println(String.format("[10 -> 16 Upper]:%s", temp));
		
		temp = String.format("%x", 255);	// C 유사
		System.out.println(String.format("[10 -> 16 Lower]:%s", temp));
		
		temp = Integer.toHexString(255).toLowerCase();		// Java 스타일 (toLowerCase 생략 가능)
		System.out.println(String.format("[10 -> 16 Lower]:%s", temp));
	}
	
	private static void compareArrayCopy() {
		// C
		/*
			char *keyStr = "qwer1234qwer1234";
			char szKey[16];
		
			memset(szKey, 0, sizeof(szKey));
			memcpy(szKey, keyStr, 16);
			printf("[Array에 복사]: %s\n", szKey);
			
			szKey[16] = '\0';
			memset(szKey, 'a', sizeof(szKey));
			printf("[Array 특정 값으로 초기화]: %s\n", szKey);
		*/
		//  Java
		String keyStr = "qwer1234qwer1234";
		byte[] szKey = new byte[16];
		
		System.arraycopy(keyStr.getBytes(), 0, szKey, 0, 16);
		System.out.println(String.format("[Array에 복사]: %s", new String(szKey)));
		
		Arrays.fill(szKey, (byte)'a');
		System.out.println(String.format("[Array 특정 값으로 초기화]: %s", new String(szKey)));
	}
}
