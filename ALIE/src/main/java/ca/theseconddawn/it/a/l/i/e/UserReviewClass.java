/*
Paramand Mohabir N01421732
CENG322 Section 0NC
Software Project
*/
/*
Vladislav Vassilyev N01436627
CENG322 Section 0NB
Software Project
*/
/*
Dave Patel N01465129
CENG322 Section 0NA
Software Project
*/
/*
Paolo Brancato N01434080
CENG322 Section ONC
Software Project
*/

package ca.theseconddawn.it.a.l.i.e;

public class UserReviewClass {

    String reviewName, reviewEmail, reviewPhoneNo, reviewMessage;

    public UserReviewClass() {

    }

    public UserReviewClass(String reviewName, String reviewEmail, String reviewPhoneNo, String reviewMessage) {
        this.reviewName = reviewName;
        this.reviewEmail = reviewEmail;
        this.reviewPhoneNo = reviewPhoneNo;
        this.reviewMessage = reviewMessage;
    }

    public String getReviewName() {
        return reviewName;
    }

    public void setReviewName(String reviewName) {
        this.reviewName = reviewName;
    }

    public String getReviewEmail() {
        return reviewEmail;
    }

    public void setReviewEmail(String reviewEmail) {
        this.reviewEmail = reviewEmail;
    }

    public String getReviewPhoneNo() {
        return reviewPhoneNo;
    }

    public void setReviewPhoneNo(String reviewPhoneNo) {
        this.reviewPhoneNo = reviewPhoneNo;
    }

    public String getReviewMessage() {
        return reviewMessage;
    }

    public void setReviewMessage(String reviewMessage) {
        this.reviewMessage = reviewMessage;
    }
}
