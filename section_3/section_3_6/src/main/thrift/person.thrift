namespace java com.github.thrift

struct Person {
  1:  string firstName;
  2:  string lastName;
  3:  i32 id;
}

service PersonService {
  Person getPerson();
}