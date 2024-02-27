# 입출력 (Input & Output)

1. [자바에서의 입출력](#1장-자바에서의-입출력)
2. [바이트기반 스트림](#2장-바이트기반-스트림)
3. [바이트기반의 보조스트림](#3장-바이트기반의-보조스트림)
4. [문자기반 스트림](#4장-문자기반-스트림)
5. [문자기반의 보조스트림](#5장-문자기반의-보조스트림)
6. [표준 입출력과 File](#6장-표준-입출력과-file)
7. [직렬화](#7장-직렬화)

</br>
</br>

## 1장 자바에서의 입출력

1. [입출력이란?](#1-1-입출력이란)
2. [스트림(stream)](#1-2-스트림stream)
3. [바이트기반 스트림 - InputStream, OutputStream](#1-3-바이트기반-스트림---inputstream-outputstream)
4. [보조 스트림](#1-4-보조-스트림)
5. [문자기반 스트림 - Reader, Writer](#1-5-문자기반-스트림---reader-writer)

</br>

[🔗 Package java.io](https://docs.oracle.com/javase/8/docs/api/java/io/package-summary.html)

### 1-1. 입출력이란?

컴퓨터 내부 또는 외부의 장치와 프로그램 사이의 데이터를 주고 받는 것

</br>

### 1-2. 스트림(stream)

데이터를 운반하는데 사용되는 연결통로로 데이터의 흐름을 물에 비유하여 stream이라는 이름이 붙여졌다. 이름의 유래대로 stream은 단방향통신만 가능하기 때문에 입력과 출력을 동시에 수행할 수 없다.

</br>

### 1-3. 바이트기반 스트림 - InputStream, OutputStream

스트림은 바이트 단위로 데이터를 전송한다. 아래표의 입출력 스트림은 모두 InputStream 또는 OutputStream의 자손이며 각각 읽고 쓰는데 필요한 추상메서드를 자신에 맞게 구현해 놓았다.

</br>

📌 입력스트림과 출력스트림의 종류
| 입력스트림 | 출력스트림 | 입출력 대상의 종류 |
| :------------------- | :--------------------- | :------------------------ |
| FileInputStream | FileOutputStream | File |
| ByteArrayInputStream | ByteArrrayOutputStream | Memory(Byte Array) |
| PipedInputStream | PipedOutputStream | Process(프로세스 간 통신) |
| AudioInputStream | AudioOutputStream | Audio |

```java
// InputStream
abstract int read()
int read(byte[] b)
int read(byte[] b, int off, int len)

// OutputStream
abstract void write(int b)
void write(byte[] b)
void write(byte[] b, int off, int len)
```

</br>

### 1-4. 보조 스트림

- 보조스트림은 실제 데이터를 주고 받는 스트림이 아니라 스트림의 기능을 향상시키거나 새로운 기능을 추가하는 역할을 한다.
- 보조스트림만으로 입출력을 처리할 수 없기 떄문에 보조스트림은 스트림을 먼저 생성한 다음에 생성해야 한다.
- 버퍼를 이용한 보조스트림을 사용하여 입출력을 수행하는 경우가 그렇지 않는 경우보다 성능에서 큰 차이가 나기 때문에 대부분의 경우 버퍼를 이용한 보조스트림을 사용한다.

```java
// 1) 먼저 기반스트림을 생성
FileInputStream fis = new FileInputStream("test.txt");

// 2) 기반스트림을 이용하여 보조스트림 생성
BufferedInputStream bis = new BufferedInputStream(fis);

bis.read() // 보조스트림인 BufferedInputStream으로부터 데이터를 읽어온다.
```

</br>

📌 보조스트림의 종류
|Input|Output|Description|
|:-|:-|:-|
|FilterInputStream|FilterOutputStream|필터를 이용한 입출력 처리|
|BufferedInputStream|BufferedOutputStream|버퍼를 이용한 입출력 성능 향상|
|DataInputStream|DataOutputStream|int, float와 같은 기본형 단위로 데이터를 처리하는 기능|
|SequenceInputStream|x|두 개의 스트림을 하나로 연결|
|LineNumberInputStream|x|읽어 온 데이터의 라인 번호를 카운트 (JDK1.1부터 LineNumberReader로 대체)|
|ObjectInputStream|ObjectOutputStream|데이터를 객체단위로 읽고 쓰는데 사용, 주로 파일을 이용하여 객체 직렬화와 관련 있음|
|x|PrintStream|버퍼를 이용하여 추가적인 print 관련 기능 (print, ptinf, println)|
|PushbackInputStream|x|버퍼를 이용해서 읽어 온 데이터를 다시 되돌리는 기능(unread, push back to buffer)|

</br>

### 1-5. 문자기반 스트림 - Reader, Writer

- 바이트기반의 스트림으로는 2byte인 문자를 처리하는데 한계가 있다. 이를 보완하기 위해 문자기반 스트림이 제공된다.
- 문자 데이터를 입출력하는 경우에는 바이트기반 스트림 대신 문자기반 스트림을 이용하는 것이 좋다.

</br>

📌 문자기반 입력스트림과 출력스트림의 종류
| 입력스트림 | 출력스트림
| :------------------- | :--------------------- |
| FileRedader | FileWriter |
| CharArrayRedader | CharArrrayWriter |
| PipedRedader | PipedWriter |
| StringReader | StringWriter |

</br>

```java
// Reader
int read()
int read(char[] c)
abstract int read(char[] c, int off, int len)

// Writer
void write(int c)
void write(char[] c)
abstract void write(char[] c, int off, int len)
```

</br>

📌 문자기반 보조스트림의 종류
|Input|Output|Description|
|:-|:-|:-|
|FilterReader|FilterWriter|필터를 이용한 입출력 처리|
|BufferedReader|BufferedWriter|버퍼를 이용한 입출력 성능 향상|
|LineNumberReader|x|읽어 온 데이터의 라인 번호를 카운트|
|x|PrintWriter|버퍼를 이용하여 추가적인 print 관련 기능 (print, ptinf, println)|
|PushbackReader|x|버퍼를 이용해서 읽어 온 데이터를 다시 되돌리는 기능(unread, push back to buffer)|

</br>

## 2장 바이트기반 스트림

1. [ InputStream & OutputStream](#2-1-inputstream--outputstream)
2. [ByteArrayInputStream & ByteArrayOutputStream](#2-2-bytearrayinputstream--bytearrayoutputstream)
3. [FileInputStream & FileOutputStream](#2-3-fileinputstream--fileoutputstream)

</br>

### 2-1. InputStream & OutputStream

[🔗 Class InputStream](https://docs.oracle.com/javase/8/docs/api/java/io/InputStream.html)

[🔗 Class OutputStream](https://docs.oracle.com/javase/8/docs/api/java/io/OutputStream.html)

</br>

<b>📌 InputStream의 메서드</b>

| Method name                          | Description                                                                                                                                   |
| :----------------------------------- | :-------------------------------------------------------------------------------------------------------------------------------------------- |
| int available()                      | 스트림으로부터 읽어 올 수 있는 데이터의 크기를 반환                                                                                           |
| void close()                         | 스트림을 닫음으로써 사용하고 있는 자원을 반환                                                                                                 |
| void mark(int readlimit)             | 현재 위치를 표시해 놓는다. reset()에 의해 표시해 놓은 위치로 다시 돌아갈 수 있다. readlimit은 되돌아갈 수 있는 Byte의 수                      |
| boolean markSupported()              | mark()와 reset()을 지원하는 지 알려준다.                                                                                                      |
| abstract int read()                  | 1byte(0~255)를 읽어 옴. 더이상 읽어 올 데이터가 없으면 -1을 반환. abstract 메서드라서 InputStream의 자손들은 자신의 상황에 맞도록 구현해야 함 |
| int read(byte[] b)                   | 배열의 b크기 만큼 읽어서 배열을 채우고 읽어 온 데이터의 수를 반환. 반환하는 값은 항상 배열의 크기보다 작거나 같음                             |
| int read(byte[] b, int off, int len) | 최대 len개의 byte를 읽어서 배열 b에 지정된 위치(off)부터 저장. 실제 읽어 올 수 있는 데이터가 len보다 적을 수 있음                             |
| void reset()                         | 스트림에서의 위치를 마지막으로 mark()가 호출되었던 위치로 되돌림                                                                              |
| long skip(long n)                    | 스트림에서 주어진 길이(n)만큼 건너뜀                                                                                                          |

</br>

<b>📌 OutputStream의 메서드</b>

| Method name                            | Description                                                                            |
| :------------------------------------- | :------------------------------------------------------------------------------------- |
| void close()                           | 입력소스를 닫음으로써 사용하고 있는 자원을 반환                                        |
| void flush()                           | 스트림의 버퍼에 있는 모든 내용을 출력 소스에 쓴다                                      |
| abstract void write(int b)             | 주어진 값을 출력 소스에 쓴다                                                           |
| void write(byte[] b)                   | 주어진 배열 b에 저장된 모든 내용을 출력소스에 쓴다.                                    |
| void write(byte[] b, int off, int len) | 주어진 배열 b에 저장된 내용 중 off번째부터 len개 만큼 데이터를 읽어서 출력소스에 쓴다. |

</br>

### 2-2. ByteArrayInputStream & ByteArrayOutputStream

[🔗 Class ByteArrayInputStream](https://docs.oracle.com/javase/8/docs/api/java/io/ByteArrayInputStream.html)

[🔗Class ByteArrayOutputStream](https://docs.oracle.com/javase/8/docs/api/java/io/ByteArrayOutputStream.html)

`IOEx1.java`,
`IOEx2.java`,
`IOEx3.java`,
`IOEx4.java`

- `ByteArrayInputStream` : 바이트 배열에 데이터를 입력하는데 사용
- `ByteArrayOutputStream` : 바이트 배열에 데이터를 출력하는데 사용
- 주로 다른 곳에 입출력하기 전에 데이터를 임시로 바이트 배열에 담아서 변환하는 등의 작업을 하는 데 사용

</br>

### 2-3. FileInputStream & FileOutputStream

[🔗 Class FileInputStream](https://docs.oracle.com/javase/8/docs/api/java/io/FileInputStream.html)

[🔗 Class FileOutputStream](https://docs.oracle.com/javase/8/docs/api/java/io/FileOutputStream.html)

`FileViewer.java`, `FileCopy.java`
</br>

<b>📌 InputStream의 생성자</b>

| 생성자                                | 설명                                                                                                 |
| :------------------------------------ | :--------------------------------------------------------------------------------------------------- |
| FileInputStream(String name)          | 지정된 파일이름(name)을 가진 실제파일과 연결된 FileInputStream 생성                                  |
| FileInputStream(File file)            | 파일의 이름이 String이 아닌 File 인스턴스로 지정한 점을 제외하고 FileInputStream(String name)과 같음 |
| FileInputStream(FileDescriptor fdObj) | 파일 디스크립터(fdObj)로 FileInputStream 생성                                                        |

</br>

<b>📌 OutputStream의 생성자</b>

| 생성자                                        | 설명                                                                                                                                                                               |
| :-------------------------------------------- | :--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| FileOutputStream(String name)                 | 지정된 파일 이름을 가진 실제 파일과 연결된 FileOutputStream 생성                                                                                                                   |
| FileOutputStream(String name, boolean append) | 지정된 파일이름을 가진 실제 파일과 연결된 FileOutputStream 생성. 두번째 인자가 `true`인 경우 출력 시 기존의 파일 내용의 마지막에 덧붙이고 `false`인 경우 기존의 파일 내용을 덮어씀 |
| FileOutputStream(File file)                   | 파일의 이름이 String이 아닌 File 인스턴스로 지정한 점을 제외하고 FileOutputStream(String name)과 같음                                                                              |
| FileOutputStream(File file, boolean append)   | 파일의 이름이 String이 아닌 File 인스턴스로 지정한 점을 제외하고 FileOutputStream(String name, boolean append)와 같음                                                              |
| FileOutputStream(FileDescriptor fdObj)        | 파일 디스크립터(fdObj)로 FileOutputStream 생성                                                                                                                                     |

</br>

## 3장 바이트기반의 보조스트림

1. [ FilterInputStream & FilterOutputStream](#3-1-filterinputstream--filteroutputstream)
2. [BufferedInputStream & BufferedOutputStream](#3-2-bufferedinputstream--bufferedoutputstream)
3. [ DataInputStream & DataOutputStream](#3-3-datainputstream--dataoutputstream)
4. [SequenceInputStream](#3-4-sequenceinputstream)
5. [PrintStream](#3-5-printstream)

### 3-1. FilterInputStream & FilterOutputStream

[🔗 Class FilterInputStream](https://docs.oracle.com/javase/8/docs/api/java/io/FilterInputStream.html)

[🔗 Class FilterOutputStream](https://docs.oracle.com/javase/8/docs/api/java/io/FilterOutputStream.html)

- `FilterInputStream` & `FilterOutputStream`: `InputStream`/`OutputStream`의 자손이면서 모든 보조스트림의 조상

</br>

### 3-2. BufferedInputStream & BufferedOutputStream

[🔗 Class BufferedInputStream](https://docs.oracle.com/javase/8/docs/api/java/io/BufferedInputStream.html)

[🔗 Class BufferedOutputStream](https://docs.oracle.com/javase/8/docs/api/java/io/BufferedOutputStream.html)

`BufferedOutputStreamEx1.java`

</br>

<b>📌 BufferedInputStream의 생성자</b>

| 생성자                                        | 설명                                                                                                                                                    |
| :-------------------------------------------- | :------------------------------------------------------------------------------------------------------------------------------------------------------ |
| BufferedInputStream(InputStream in, int size) | 주어진 InputStream인스턴스를 입력소스로 하며 지정된 크기의 버퍼를 갖는 BufferedInputStream 인스턴스 생성                                                |
| BufferedInputStream(InputStream in)           | 주어진 InputStream인스턴스를 입력소스로 하며 버퍼의 크기를 지정해주지 않으므로 기존적으로 8192byte 크기의 버퍼를 갖는 BufferedInputStream 인스턴스 생성 |

</br>

<b>📌 BufferedOutputStream의 생성자와 메서드</b>

| 생성자                                           | 설명                                                                                                                                                      |
| :----------------------------------------------- | :-------------------------------------------------------------------------------------------------------------------------------------------------------- |
| BufferedOutputStream(OutputStream out, int size) | 주어진 OutputStream인스턴스를 출력소스로 하며 지정된 크기의 버퍼를 갖는 BufferedOutputStream 인스턴스 생성                                                |
| BufferedOutputStream(OutputStream out)           | 주어진 OutputStream인스턴스를 입력소스로 하며 버퍼의 크기를 지정해주지 않으므로 기존적으로 8192byte 크기의 버퍼를 갖는 BufferedOutputStream 인스턴스 생성 |

| 메서드  | 설명                                                                                                                |
| :------ | :------------------------------------------------------------------------------------------------------------------ |
| flush() | 버퍼의 모든 내용을 출력소스에 출력한 다음, 버퍼를 비움                                                              |
| close() | flush()를 호출하여 버퍼의 모든 내용을 출력소스에 출력하고, BufferedOutputStream인스턴스가 사용한던 모든 자원을 반환 |

</br>

### 3-3. DataInputStream & DataOutputStream

[🔗 Class DataInputStream](https://docs.oracle.com/javase/8/docs/api/java/io/DataInputStream.html)

[🔗 Class DataOutputStream](https://docs.oracle.com/javase/8/docs/api/java/io/DataOutputStream.html)

`DataOutputStreamEx1.java`, `DataOutputStreamEx2.java`, `DataOutputStreamEx3.java`

`DataInputStreamEx1.java`, `DataInputStreamEx2.java`, `DataInputStreamEx3.java`

</br>

<b>📌 DataInputStream의 생성자와 메서드</b>

| 생성자/메서드                                                                                                                                                                                                                            | 설명                                                                                                                                                               |
| :--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- | :----------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| DataInputStream(InputStream in)                                                                                                                                                                                                          | 주어진 InputStream인스턴스를 기반으로 하는 DataInputStream 생성                                                                                                    |
| boolean readBoolean() </br> byte readByte() </br> char readChar() </br> short readShort() </br> int readInt </br> long readLong </br> float readFloat </br> double readDouble </br> int readUnsignedByte() </br> int readUnsignedShort() | 각 타입에 맞는 값을 읽어오며 더이상 읽을 값이 없는 경우 EOFException을 발생시킴                                                                                    |
| void readFully(byte[] b)</br> void readFully(byte[] b, int off, int len)                                                                                                                                                                 | 입력스트림에서 지정된 배열의 크기만큼 또는 지정된 위치에서 len만큼 데이터를 읽어옴. 파일의 끝에 도달하면 EOFException이 발생하고 I/O에러 발생 시 IOExection이 발생 |
| String readUTF()                                                                                                                                                                                                                         | UTF-8형식으로 쓰여진 문자를 읽음. 더이상 읽을 값이 없는 경우 EOFException 발생                                                                                     |
| static String readUTF(DataInput in)                                                                                                                                                                                                      | 입력스트림에서 UTF-8형식의 유니코드를 읽어옴                                                                                                                       |
| int skipBytes(int n)                                                                                                                                                                                                                     | 현재 읽고있는 위치에서 지정된 숫자만큼 건너뜀                                                                                                                      |

</br>

<b>📌 DataOutputStream의 생성자와 메서드</b>

| 생성자/메서드                                                                                                                                                                                                                                                                   | 설명                                                                         |
| :------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ | :--------------------------------------------------------------------------- |
| DataOutputStream(OutputStream out)                                                                                                                                                                                                                                              | 주어진 OutStream인스턴스를 기반스트림으로 하는 DataOutputStream인스턴스 생성 |
| void writeBoolean(boolean b) </br> void writeByte(int b) </br> void writeChar(int c) </br> void writeChars(String s) </br> void writeShort(int s) </br> void writeInt(int i) </br> void writeLong(long l) </br> void writeFloat(float f) </br> void writeDouble(double d) </br> | 각 자료형에 알맞는 값 출력                                                   |
| void writeUTF(String s)                                                                                                                                                                                                                                                         | UTF 형식으로 문자를 출력                                                     |
| void writeChars(String s)                                                                                                                                                                                                                                                       | 주어진 문자열 출력. `void writeChar(int c)`를 여러번 호출한 결과와 같음      |
| int size()                                                                                                                                                                                                                                                                      | 지금까지 DataOutputStream에 쓰여진 byte의 수를 알려줌                        |

</br>

### 3-4. SequenceInputStream

[🔗 Class SequenceInputStream](https://docs.oracle.com/javase/8/docs/api/java/io/SequenceInputStream.html)

`SequenceInputStreamEx1.java`

- `SequenceInputStream` : 여러 개의 입력스트림을 연속적으로 연결하여 하나의 스트림으로부터 데이터를 읽는 것과 같이 처리할 수 있도록 도와줌
- `FileInputStream`의 자손이 아닌 `InputStream`을 바로 상속받아 구현됨

</br>

<b>📌 SequenceInputStream의 생성자</b>

| 생성자                                              | 설명                                                               |
| :-------------------------------------------------- | :----------------------------------------------------------------- |
| SequenceInputStream(Enumeration e)                  | Enumeration에 저장된 순서대로 입력 스트림을 하나의 스트림으로 연결 |
| SequenceInputStream(InputStream s1, InputStream s2) | 두 개의 입력 스트림을 하나로 연결                                  |

</br>

### 3-5. PrintStream

[🔗 Class PrintStream](https://docs.oracle.com/javase/8/docs/api/java/io/PrintStream.html)

`PrintStreamEx1.java`

- `PrintStream` : 데이터를 기반스트림에 다양한 형태로 출력할 수 있는 print, printf, println과 같은 메서드를 오버로딩하여 제공

- printf()는 JDK1.5부터 추가된 것으로 형식화된 출력을 지원하는데 이때 사용될 수 있는 옵션은 [🔗 Class Formatter](https://docs.oracle.com/javase/8/docs/api/java/util/Formatter.html)를 통해 참고하면 된다.

</br>

## 4장 문자기반 스트림

1. [Reader & Writer](#4-1-reader--writer)
2. [FileReader & FileWriter](#4-2-filereader--filewriter)
3. [PipedReader & PipedWriter](#4-3-pipedreader--pipedwriter)
4. [StringReader & StringWriter](#4-4-stringreader--stringwriter)

### 4-1. Reader & Writer

[🔗 Class Reader](https://docs.oracle.com/javase/8/docs/api/java/io/Reader.html)

[🔗Class Writer](https://docs.oracle.com/javase/8/docs/api/java/io/Writer.html)

- 바이트기반 스트림의 조상이 InputStream/OutputStream인 것과 같이 문자기반 스트림에서는 Reader/Writer가 조상이다.
- byte[] 대신 char[]을 사용한다는 것 외에는 InputStream/OutputStream의 메서드와 다르지 않다.
- 문자기반 스트림은 여러 종류의 인코딩과 자바에서 사용하는 유니코드간의 변환을 자동적으로 처리해준다.

</br>

### 4-2. FileReader & FileWriter

[🔗 Class FileReader](https://docs.oracle.com/javase/8/docs/api/java/io/FileReader.html)

[🔗 Class FileWriter](https://docs.oracle.com/javase/8/docs/api/java/io/FilterWriter.html)

`FileReaderEx1.java`
`FileConversion.java`

- `FileReader` & `FileWriter` : 파일로부터 텍스트 데이터를 읽고 쓰는데 사용

</br>

### 4-3. PipedReader & PipedWriter

[🔗 Class PipedReader](https://docs.oracle.com/javase/8/docs/api/java/io/PipedReader.html)

[🔗 Class PipedWriter](https://docs.oracle.com/javase/8/docs/api/java/io/PipedWriter.html)

`PipedReaderWriter.java`

- `PipedReader` & `PipedWriter` : 쓰레드 간에 데이터를 주고 받을 때 사용
- 다른 스트림과 달리 입력과 출력 스트림을 하나의 스트림으로 연결해서 데이터를 주고 받음
- 스트림을 생성한 다음 어느 한 쪽 쓰레드에서 `connect()`를 호출해서 입력스트림과 출력 스트림을 연결
- 입출력을 마친 후 어느 한 쪽의 스트림만 닫아도 나머지 스트림은 자동으로 닫힘

</br>

### 4-4. StringReader & StringWriter

[🔗 Class StringReader](https://docs.oracle.com/javase/8/docs/api/java/io/StringReader.html)

[🔗 Class StringWriter](https://docs.oracle.com/javase/8/docs/api/java/io/StringWriter.html)

`StringReaderWriterEx.java`

- `StringReader` & `StringWriter` : 입출력 대상이 메모리인 스트림
- `StringWriter`에 출력되는 데이터는 내부의 StringBuffer에 저장되며 `StringWriter`의 `getBuffer()`, `toString()` 메서드를 이용하여 저장된 데이터를 얻을 수 있음

</br>

## 5장 문자기반의 보조스트림

1. [BufferedReader & BufferedWriter](#5-1-bufferedreader--bufferedwriter)
2. [InputStreamReader & OutputStreamWriter](#5-2-inputstreamreader--outputstreamwriter)

### 5-1. BufferedReader & BufferedWriter

[🔗 Class BufferedReader](https://docs.oracle.com/javase/8/docs/api/java/io/BufferedReader.html)

[🔗 Class BufferedWriter](https://docs.oracle.com/javase/8/docs/api/java/io/BufferedWriter.html)

`BufferedReaderEx1.java`

- `BufferedReader` & `BufferedWriter` : 버퍼를 이용해서 입출력의 효율을 높이도록 해준다
- 버퍼를 사용하면 입출력의 효율이 높아지기 때문에 사용하는 것을 권장
- `BufferedReader`의 `readLine()`을 사용하면 데이터를 라인단위로 읽을 수 있다.
- `BufferedWriter`의 `newLine()`을 사용하면 줄바꿈을 할 수 있다.

</br>

### 5-2. InputStreamReader & OutputStreamWriter

[🔗 Class InputStreamReader](https://docs.oracle.com/javase/8/docs/api/java/io/InputStreamReader.html)

[🔗 Class OutputStreamWriter](https://docs.oracle.com/javase/8/docs/api/java/io/OutputStreamWriter.html)

`InputStreamReaderEx.java`

- `InputStreamReader` & `OutputStreamWriter` : 바이트기반 스트림을 문자기반 스트림으로 연결시켜주는 역할을 한다.
- 바이트기반 스트림의 데이터를 지정된 인코딩의 문자데이터로 변환하는 작업을 수행

</br>

## 6장 표준 입출력과 File

1. [표준 입출력 - System.in, System.out, System.err](#6-1-표준-입출력---systemin-systemout-systemerr)
2. [표준 입출력의 대상 변경 - `setOut()`, `setErr()`, `setIn()`](#6-2-표준-입출력의-대상-변경---setout-seterr-setin)
3. [RandomAccessFile](#6-3-randomaccessfile)
4. [File](#6-4-file)

### 6-1. 표준 입출력 - System.in, System.out, System.err

`StandardIOEx1.java`

- 표준입출력은 콘솔을 통한 데이터 입력과 콘솔로의 데이터 출력을 의미
- 자바에서는 표준입출력을 위해 `System.in`, `System.out`, `System.err`의 3가지 입출력 스트림을 제공

</br>

### 6-2. 표준 입출력의 대상 변경 - `setOut()`, `setErr()`, `setIn()`

`StandardIOEx2.java`, `StandardIOEx3.java`

</br>

### 6-3. RandomAccessFile

[🔗 Class RandomAccessFile](https://docs.oracle.com/javase/8/docs/api/java/io/RandomAccessFile.html)

`RandomAccessFileEx1.java`, `RandomAccessFileEx2.java`, `RandomAccessFileEx3.java`

- `RandomAccessFile` : 하나의 클래스로 파일에 대한 입력과 출력을 모두 할 수 있도록 함
- 내부적으로 파일 포인터를 사용하여 입출력시 파일 포인터가 위치한 곳에서 작업이 수행 됨
- 파일 포인터의 위치는 파일의 제일 첫 부분이며 읽기 또는 쓰기를 수행할 때마다 작업이 수행된 다음 위치로 이동
- 파일의 임의의 위치에 있는 내용에 대해 작업하고자 하면 파일 포인터를 원하는 위치로 옮긴 다음 작업을 해야 함
- `long getFilePointer()`: 현재 작업중인 위치를 알고 싶은 경우 사용
- `void seek(long pos)`, `int skipBytes(int n)` : 파일 포인터의 위치를 옮기고 싶은 경우 사용

  </br>

### 6-4. File

[🔗 Class File](https://docs.oracle.com/javase/8/docs/api/java/io/File.html)

`FileEx1.java`,
`FileEx2.java`,
`FileEx3.java`,
`FileEx4.java`,
`FileEx5.java`,
`FileEx6.java`,
`FileEx7.java`,
`FileEx8.java`,
`FileEx9.java`,
`FileSplit.java`,
`FileMerge.java`

</br>

## 7장 직렬화

1. [직렬화란?](#7-1-직렬화란)
2. [ObjectInputStream, ObjectOutputStream](#7-2-objectinputstream-objectoutputstream)
3. [직렬화가 가능한 클래스 만들기 - Serializable, transient](#7-3-직렬화가-가능한-클래스-만들기---serializable-transient)
4. [직렬화가 가능한 클래스의 버전 관리](#7-4-직렬화가-가능한-클래스의-버전-관리)

### 7-1. 직렬화란?

- 객체를 데이터 스트림으로 만드는 것
- 객체에 저장된 데이터를 스트림에 쓰기 위해 연속적인 데이터로 변환하는 것

</br>

### 7-2. ObjectInputStream, ObjectOutputStream

[🔗 Class ObjectInputStream](https://docs.oracle.com/javase/8/docs/api/java/io/ObjectInputStream.html)

[🔗 Class ObjectOutputStream](https://docs.oracle.com/javase/8/docs/api/java/io/ObjectOutputStream.html)

- 직렬화(스트림에 객체를 출력)에는 `ObjectOutputStream`을 사용
- 역직렬화(스트림으로부터 객체를 입력)에는 `ObjectInputStream`을 사용

</br>

### 7-3. 직렬화가 가능한 클래스 만들기 - Serializable, transient

[🔗 Interface Serializable](https://docs.oracle.com/javase/8/docs/api/java/io/Serializable.html)

`UserInfo.java`, `UserInfo2.java`, `SerialEx1.java`, `SerialEx2.java`

- 직렬화하고자 하는 클래스가 `Serializable` 인터페이스를 구현하도록 하면 된다.
- 클래슨 내에 인스턴스 변수 중 직렬화하면 안되는 값에 대해 `transient`를 사용하면 직렬화 대상에서 제외된다.

```java
public class Student implements java.io.Serializable {
    String name;
    transient String address; // 직렬화 대상에서 제외
    int ban;
    int totalScore;
}
```

</br>

### 7-4. 직렬화가 가능한 클래스의 버전 관리

- 직렬화된 객체를 역직렬화할 때는 직렬화 했을 때와 같은 클래스를 사용해야 한다. 그러나 클래스의 이름이 같더라도 클래스의 내용이 변경된 경우 역직렬화는 실패하며 예외가 발생한다.
- 네크웍으로 객체를 직렬화하여 전송하는 경우 보내는 쪽과 받는 쪽 모두 같은 버전의 클래스를 가지고 있어야 하는데 클래스가 조금만 변경되어도 해당 클래스를 재배포하는 것은 프로그램 관리를 어렵게 만든다.
- 이럴 때 클래스의 버전을 수동으로 관리해줄 필요가 있다.

```java
class MyData implements java.io.Serializable{
    static final long serialVersionUID = 3418731765298119L;
    int myDataVal;
}
```

- 이처럼 `serialVersionUID`를 정의해주면 클래스의 내용이 바뀌어도 클래스의 버번이 자동생성된 값으로 변경되지 않는다.

  </br>
