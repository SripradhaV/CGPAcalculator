ass Student {


public static final int numSubjects = 8;

private String regNo;

private String name;

private String subsEnrolled[ ] = new String[numSubjects ];

private float marks[] = new float[numSubjects];

private float gpa;

//default constructor

public Student() {
}

public Student(String regNo, String name, float[] marks, String subCode[]) {

this.regNo = regNo;
this.name = name;
for(int i =0  ; i < numSubjects ; i++) {

this.subsEnrolled[i] = subCode[i];
this.marks[i] = marks[i];
}
}

public float computeGPA() {

int totCredits = 0, creds =0;
for(int i=0; i < numSubjects; i++) {

creds = Result.getCredits(subsEnrolled[i]);
gpa += (Result.getGradePoint(marks[i]) * creds );
totCredits += creds;
}
gpa /= totCredits;
return (	gpa);
}

public float getGPA() {

return (gpa);
}

public void printStudDetails() {

System.out.println(regNo + "\t" + name + "\t" + gpa);
}

}


class Subject {

private String subCode;
private String subName;
private int credits;

public Subject() {


}

public Subject(String name, String code, int credits) {

subName = name;
subCode = code;
this.credits = credits;
}

//getter methods or accessors
public String getCode() {

return subCode;
}

//setter method or mutator
public void setCode(String subCode) {

this.subCode = subCode;
}

public String getSubName() {

return (subName);
}

public int getCredits() {

return credits;
}
}

public class Result {

public static final int numStudents = 3;

private static Subject allSubs[] = new Subject[Student.numSubjects ];
//Wrong!!!!!
private static String subCode[] = {"C31","C32","C33","C34","C35","C36","C37","C38"};
private static String subName[] = {"AAA","BBB","CCC","DDD","EEE","FFF","GGG","HHH"};
private static int credits[] = {4,3,3,3,3,3,1,1};

private static Student students[] = new Student[numStudents ];

static {

for(int i=0; i < Student.numSubjects; i++) {

allSubs[i] = new Subject(subName[i], subCode[i], credits[i] );
}
}

public Result() {


}

public static int getCredits(String subCode) {

for(int i=0; i < Student.numSubjects; i++) {

if(subCode.equals(allSubs[i].getCode())) {

return (allSubs[i].getCredits());
}
}
return -1;
}

//Not well written!!!!!! Needs loooooot of Refactoring :)
public static int getGradePoint(float mark) {

int gradePoint = 0;   //XML //Property
if ( mark >= 90) {

gradePoint = 10;
} else if(mark >= 80 ) {

gradePoint = 9;
}  else if(mark >= 70 ) {

gradePoint = 8;
}  else if(mark >= 60 ) {

gradePoint = 7;
} else if(mark >= 50 ) {

gradePoint = 6;
} else {

gradePoint = 0;
}
return (gradePoint);
}



public static void main(String args[]) {

BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
String rNo="", name="";
float marks[] = new float[Student.numSubjects];

try {
for(int i =0; i< numStudents; i++) {

System.out.print("enter Reg no for student " + (i+1));
rNo = br.readLine();
System.out.print("enter name for student " + (i+1));
name = br.readLine();
for(int j=0; j< Student.numSubjects; j++) {

marks[j] = Float.parseFloat(br.readLine());
}
students[i] = new Student(rNo, name,  marks, subCode);
}
} catch(IOException ioe) {

ioe.printStackTrace();
}

ResultHelper.computeGPA(students);
ResultHelper.generateRankList(students);

for(int i =0; i< students.length; i++) {

students[i].printStudDetails();
}
}
}

class ResultHelper {

public static void computeGPA(Student[] studs) {

for(int i =0 ; i< Result.numStudents; i++) {

studs[i].computeGPA();
}
}

public static void generateRankList(Student[] studs) {

Student temp = null;
boolean sorted = false;

for(int i=0;!sorted && i<studs.length;i++) {

sorted = true;
for(int j =i+1;j<studs.length;j++) {

if(studs[i].getGPA() < studs[j].getGPA()) {

temp = studs[i];
studs[i] = studs[j];
studs[j] = temp;
sorted = false;
}
}
}

}
}


