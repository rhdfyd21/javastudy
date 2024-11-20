package studentDB;

public class StudentDate {
	//학번,이름,클래스,국어,수학,영어,총점,평균

	private int no;
	private String name;
	private String StuC;
	private int kor;
	private int math;
	private int eng;
	private int sum;
	private double avg;
	public StudentDate(int no, String name, String stuC, int kor, int math, int eng) {
		super();
		this.no = no;
		this.name = name;
		this.StuC = stuC;
		this.kor = kor;
		this.math = math;
		this.eng = eng;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStuC() {
		return StuC;
	}
	public void setStuC(String stuC) {
		StuC = stuC;
	}
	public int getKor() {
		return kor;
	}
	public void setKor(int kor) {
		this.kor = kor;
	}
	public int getMath() {
		return math;
	}
	public void setMath(int math) {
		this.math = math;
	}
	public int stuSum() {
		this.sum = kor + math + eng;
		return sum;
	}
	
	public double stuAvg() {
		this.avg = sum / 3;
		return avg;
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}
	@Override
	public String toString() {
		return "StudentDate [no=" + no + ", name=" + name + ", StuC=" + StuC + ", kor=" + kor + ", math=" + math
				+ ", eng=" + eng + ", sum=" + sum + ", avg=" + avg + "]";
	}
	
	
}
