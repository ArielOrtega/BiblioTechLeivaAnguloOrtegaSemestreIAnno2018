
package Domain;


public class Loan {
    private String studentId;
    private String signature;
    private String loanDay;
    private String deliveryDay;

    public Loan(String studentId, String signature, String loanDay, String deliveryDay) {
        this.studentId = studentId;
        this.signature = signature;
        this.loanDay = loanDay;
        this.deliveryDay = deliveryDay;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getLoanDay() {
        return loanDay;
    }

    public void setLoanDay(String loanDay) {
        this.loanDay = loanDay;
    }

    public String getDeliveryDay() {
        return deliveryDay;
    }

    public void setDeliveryDay(String deliveryDay) {
        this.deliveryDay = deliveryDay;
    }

    @Override
    public String toString() {
        return "Prestamo{" + "carne del estudiante=" + studentId + ", signatura=" + signature 
                + "\ndia de prestamo=" + loanDay + ", dia de entrega=" + deliveryDay + '}';
    }
    
    
}
