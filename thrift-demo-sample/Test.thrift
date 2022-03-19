namespace java com.example.test

struct Example {
  1: required string name = "张三" //required代表该字段必须填写
  2: optional i32 age = 0 //默认值
  3: bool gender //默认字段类型为optional
}
exception MyException {
  1: i32 errorCode
  2: string message
}

service ExampleService {
	void setExample(1:i32 key, 2:string value)
	string get(1:i32 key) throws (1:MyException ex),
	void deleteExample(1:i32 key)
}