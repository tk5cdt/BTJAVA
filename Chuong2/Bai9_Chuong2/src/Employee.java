public class Employee {
    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public int getEmpSalary() {
        return empSalary;
    }

    public void setEmpSalary(int empSalary) {
        this.empSalary = empSalary;
    }

    private String empCode;

    public Employee(String empCode, String empName, int empSalary) {
        this.empCode = empCode;
        this.empName = empName;
        this.empSalary = empSalary;
    }

    private String empName;
    private int empSalary;

    public String toString(){
        return empCode +"-"+empName;
    }


}
