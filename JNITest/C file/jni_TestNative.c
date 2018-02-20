#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "jni_TestNative.h"

/******************************************************************
 *
 * [프로젝트 종류]
 * 	- Shared Library
 *
 * [JNI 컴파일을 위해 헤더 추가]
 * 	- JAVa_HOMe/include
 * 	- JAVa_HOMe/include/win32
 *
 * 		(Code Blocks)
 *			- Settings > Search directories > Add
 *
 * 		(Eclipse)
 *			- 프로젝트 우클릭 > Properties > C/C++ Genereal > Paths and Symols
 *				> Includes > Add
 *					체크박스 2개 체크
 *
 ******************************************************************/
#define BUFFER_ALLOCATE_BLOCK_SIZE (1024)  // 1 Kbytes

/*
 * Class:     jni_TestNative
 * Method:    print
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_jni_TestNative_print
  (JNIEnv *env, jobject obj)
{
	printf("[C] : Hello JNI!\n");
	return;
}

/*
 * Class:     jni_TestNative
 * Method:    stringParam
 * Signature: (Ljava/lang/String;)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_jni_TestNative_stringParam
  (JNIEnv *env, jobject obj, jstring jstr)
{
	// StringChars : UTF-16
	// StringUTFChars : UTF-8

	// string 에 접근하기 위해서는 GetStringChars나 GetStringUTFChars 을 통해 변환
	const char *name = (*env)->GetStringUTFChars(env, jstr, NULL);
	char *msg = "Hello ";
	jstring result;

	char *chTemp = malloc(BUFFER_ALLOCATE_BLOCK_SIZE);
	strcpy(chTemp, msg);
	strcat(chTemp, name);

	// ReleaseStringChrs나 ReleaseStringUTFChars 를 불러주어 얻은 메모리를 해제
	(*env)->ReleaseStringUTFChars(env, jstr, name);

	result = (*env)->NewStringUTF(env, chTemp);

	free(chTemp);
	return result;
}

/*
 * Class:     jni_TestNative
 * Method:    intParam
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_jni_TestNative_intParam
  (JNIEnv *env, jobject obj, jint jint)
{
	int nNum = 2;
	int nRet = (jint + nNum);
	return nRet;
}

/*
 * Class:     jni_TestNative
 * Method:    byteArrParam
 * Signature: ([B)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_jni_TestNative_byteArrParam
  (JNIEnv *env, jobject obj, jbyteArray byteArray)
{
	  char *msg = "Hello ";
	  jstring result;

	  jbyte *pbData;
	  char *pszData;

	  int len = (*env)->GetArrayLength (env, byteArray);
	  pbData = (*env)->GetByteArrayElements(env, byteArray,0);
	  pszData = (char*)malloc(len+1);
	  memset(pszData, 0x00, len+1);
	  memcpy(pszData, pbData, len);
	  (*env)->ReleaseByteArrayElements(env, byteArray, pbData, JNI_ABORT);

	  char *chTemp = malloc(BUFFER_ALLOCATE_BLOCK_SIZE);
	  strcpy(chTemp, msg);
	  strcat(chTemp, pszData);

	  result = (*env)->NewStringUTF(env, chTemp);

	  free(chTemp);
	  return result;
}

/*
 * Class:     jni_TestNative
 * Method:    setMethodString
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_jni_TestNative_setMethodString
  (JNIEnv *env, jobject obj)
{
	char *msg = "C->Java UTF String";
	jstring strResult;

	strResult = (*env)->NewStringUTF(env, msg);

	jclass class;
	jmethodID method;

	// [시그니처 참고] http://aroundck.tistory.com/567

	class = (*env)->GetObjectClass(env, obj);
	method = (*env)->GetMethodID(env, class, "setsData", "(Ljava/lang/String;)V");
	(*env)->CallVoidMethod(env, obj, method, strResult);

	return 0;
}

/*
 * Class:     jni_TestNative
 * Method:    setMethodByteArray
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_jni_TestNative_setMethodByteArray
  (JNIEnv *env, jobject obj)
{
	char *msg = "C->Java ByteArray";
	jbyteArray byteResult;

	int len = strlen(msg);
	byteResult = (*env)->NewByteArray(env, len);

	(*env)->SetByteArrayRegion(env, byteResult, 0, len, (jbyte *)msg);

	jclass class;
	jmethodID method;

	class = (*env)->GetObjectClass(env, obj);
	method = (*env)->GetMethodID(env, class, "setbData", "([B)V");
	(*env)->CallVoidMethod(env, obj, method, byteResult);

	return 0;
}
